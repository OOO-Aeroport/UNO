package ru.dencore.Airport.order.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.exception.NotFoundException;
import ru.dencore.Airport.feignclient.*;
import ru.dencore.Airport.microservices.model.Microservices;
import ru.dencore.Airport.microservices.service.MicroserviceManager;
import ru.dencore.Airport.order.dao.OrderRepository;
import ru.dencore.Airport.order.dto.OrderRequest;
import ru.dencore.Airport.order.model.Order;
import ru.dencore.Airport.order.model.Status;
import ru.dencore.Airport.order.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MicroserviceManager microserviceManager;
    private final AsyncService asyncService;

    private final TankerTruckClient tankerTruckClient;
    private final PassengerAndBaggageClient passengerAndBaggageClient;
    private final FollowMeClient followMeClient;
    private final CateringClient cateringClient;
    private final TabloClient tabloClient;

    private final static List<String> nameOfMicroservices = List.of(new String[]{"tanker-truck", "passenger-and-baggage", "follow-me", "catering-service"});


    @Override

    public Order saveOrder(Order order) {

        Order orderSaved = orderRepository.save(order);
        log.info("Заказ сохранился с id : {}", orderSaved.getId());

        return orderSaved;
    }

    @Override
    public void broadcastOrder(Order order) throws InterruptedException {

        OrderRequest orderRequest = OrderRequest.builder()
                .orderId(order.getId())
                .planeId(order.getPlaneId())
                .build();

        for (String nameOfMicroservice : nameOfMicroservices) {

            Microservices microservices = Microservices.builder()
                    .name(nameOfMicroservice)
                    .startTime(LocalDateTime.now())
                    .order(order)
                    .build();

            microserviceManager.saveMicroservice(microservices);

        }

        log.info("Начинается отправка заказов на службы");

        CompletableFuture<Void> cateringFuture = asyncService.processCateringOrderAsync(orderRequest);
        CompletableFuture<Void> followMeFuture = asyncService.processFollowMeOrderAsync(order.getId(), order.getPlaneId());
        CompletableFuture<Void> passengerAndBaggageFuture = asyncService.processPassengerAndBaggageOrderAsync(order.getPlaneId(), order.getId());
        CompletableFuture<Void> tankerTruckFuture = asyncService.processTankerTruckOrderAsync(order.getId(), order.getFuel(), order.getPlaneId());

    }

    @Override
    @Transactional
    public void updateStage(Long orderId) {

        Optional<Order> optionalOrder = orderRepository.findByIdWithLock(orderId);

        if (optionalOrder.isEmpty()) {
            throw new NotFoundException("Заказ с id: %d не найден".formatted(orderId));
        }

        Order order = optionalOrder.get();
        order.setStage(order.getStage() + 1);

        if (order.getStage() == 4) {
            order.setTimeFinish(LocalDateTime.now());
        }

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void findOrderToSend() {

        List<Order> ordersToSend = orderRepository.findByStageAndStatus(4, Status.WAITING_TO_PROCESS);


        if (!ordersToSend.isEmpty()) {

            log.info("Найдены заказы для отправки: " + ordersToSend);

            for (Order order : ordersToSend) {
                order.setStatus(Status.SENDED);
                tabloClient.successReport(order.getPlaneId());

                log.info("Заказ с id=%d успешно отправился".formatted(order.getPlaneId()));
            }

            log.info("Список заказов " + ordersToSend + " успешно отправились");

            orderRepository.saveAll(ordersToSend);

            log.info("Статус списка заказов " + ordersToSend + " успешно поменялся в БД на SENDED");
        }


    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
