package ru.dencore.Airport.order.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.exception.NotFoundException;
import ru.dencore.Airport.feignclient.*;
import ru.dencore.Airport.feignclient.dto.SuccessReportRequest;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MicroserviceManager microserviceManager;

    private final TankerTruckClient tankerTruckClient;
    private final PassengerAndBaggageClient passengerAndBaggageClient;
    private final FollowMeClient followMeClient;
    private final CateringClient cateringClient;
    private final TabloClient tabloClient;

    private final static List<String> nameOfMicroservices = List.of(new String[]{"tanker-truck", "passenger-and-baggage", "follow-me", "catering-service"});


    @Override
    @Transactional
    public Order saveOrder(Order order) {

        Order orderSaved = orderRepository.save(order);
        log.info("Заказ сохранился с id : {}", orderSaved.getId());

        return orderSaved;
    }

    @Override
    @Transactional
    public void broadcastOrder(Order order) {

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

        cateringClient.processOrder(orderRequest);
        log.info("Заказ с id: {} отправлен на службу питания", order.getId());

        followMeClient.processOrder(order.getId(), order.getPlaneId());
        log.info("Заказ с id: {} отправлен на службу follow me", order.getId());

        passengerAndBaggageClient.processOrder(order.getPlaneId(), order.getId());
        log.info("Заказ с id: {} отправлен на службу перевозки пассажиров и багажа", order.getId());

        tankerTruckClient.processOrder(order.getId(), order.getFuel(), order.getPlaneId());
        log.info("Заказ с id: {} отправлен на топливозаправщик", order.getId());

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
        log.info("Найдены заказы для отправки: " + ordersToSend);

        for (Order order : ordersToSend) {
            order.setStatus(Status.SENDED);

            SuccessReportRequest successReportRequest = SuccessReportRequest.builder()
                    .planeId(order.getPlaneId())
                    .build();

            tabloClient.successReport(successReportRequest);
        }

        orderRepository.saveAll(ordersToSend);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
