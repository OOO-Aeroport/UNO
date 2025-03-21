package ru.dencore.Airport.order.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.exception.NotFoundException;
import ru.dencore.Airport.feignclient.*;
import ru.dencore.Airport.feignclient.dto.LoadOrderRequest;
import ru.dencore.Airport.feignclient.dto.LoadingPassengersRequest;
import ru.dencore.Airport.feignclient.dto.PassengersToLoad;
import ru.dencore.Airport.feignclient.dto.UnloadOrderRequestToCatering;
import ru.dencore.Airport.microservices.service.MicroserviceManager;
import ru.dencore.Airport.order.dao.OrderRepository;
import ru.dencore.Airport.order.dto.OrderRequest;
import ru.dencore.Airport.order.dto.RequestFromRegistration;
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

    private final TankerTruckClient tankerTruckClient;
    private final PassengerClient passengerClient;
    private final BaggageClient baggageClient;
    private final FollowMeClient followMeClient;
    private final CateringClient cateringClient;
    private final TabloClient tabloClient;
    private final DispatcherClient dispatcherClient;
    private final PlaneClient planeClient;

    private final static List<String> nameOfMicroservices = List.of(new String[]{"tanker-truck", "passenger-and-baggage", "follow-me", "catering-service"});


    @Override

    public Order saveOrder(Order order) {

        Order orderSaved = orderRepository.save(order);
        log.info("Заказ сохранился с id : {}", orderSaved.getId());

        return orderSaved;
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

        if (order.getStage() == 8) {
            order.setTimeFinish(LocalDateTime.now());
            order.setStatus(Status.READY_FOR_TAKEOFF);
        } else if (order.getStage() == 5) {
            order.setStatus(Status.AWAITING_DEPARTURE_AFTER_UNLOADING);
        }

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @Override
    public void requestOrderToFollowMe(Order order) {

//        order.setStatus(Status.DURING_FOLLOW_ME);
//        Order orderSaved = orderRepository.save(order);

        // отправляем заказ на follow me
        CompletableFuture.runAsync(() -> {
            log.info("Отдаём заказ на самолёт с id = %d на follow me".formatted(order.getPlaneId()));
            followMeClient.processOrder(order.getId(), order.getPlaneId());
        });


    }

    @Override
    public void requestOrderToCateringAndPBC(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Заказ с id = %d не найден".formatted(orderId)));

        order.setStage(1);
        order.setStatus(Status.DURING_UNLOADING);

        Order orderSaved = orderRepository.save(order);

        // отдадим заказ на службу питания на разгрузку
        UnloadOrderRequestToCatering unloadOrderRequestToCatering = UnloadOrderRequestToCatering.builder()
                .orderId(orderSaved.getId())
                .planeId(orderSaved.getPlaneId())
                .build();

        // отдаём заказ на службу питания

        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на службу питания для разгрузки".formatted(orderSaved.getId(), orderSaved.getPlaneId()));

            cateringClient.processOrder(unloadOrderRequestToCatering);
        });

        // отдадим заказ на разгрузку на службу перевозки багажа

        OrderRequest orderRequest = OrderRequest.builder()
                .orderId(orderSaved.getId())
                .planeId(orderSaved.getPlaneId())
                .build();

        // отдаём заказ на разгрузку багажа

        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на разгрузку багажа".formatted(orderSaved.getId(), orderSaved.getPlaneId()));

            baggageClient.requestOrderToBaggageDischarge(orderRequest);
        });


        // отдаём заказ на разгрузку пассажиров

        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на разгрузку пассажиров".formatted(orderSaved.getId(), orderSaved.getPlaneId()));

            passengerClient.requestOrderToPassengersDischarge(orderRequest);
        });


    }

    @Override
    public void requestOrderToTankerTruck(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Заказ с id = %d не найден".formatted(orderId)));

        // отдаём заказ на топливозаправщик

        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на топливозаправщик".formatted(order.getId(), order.getPlaneId()));

            tankerTruckClient.processOrder(order.getId(), order.getFuel(), order.getPlaneId());
        });

    }

    @Override
    public void requestOrderToCateringAndPBCLoad(RequestFromRegistration requestFromRegistration) {

        Order order = orderRepository.findByPlaneId(requestFromRegistration.getPlaneId()).orElseThrow(() -> new NotFoundException(("Заказ с planeId = %d не существует").formatted(requestFromRegistration.getPlaneId())));

        // нужно раздать заказ на службу питания на загрузку
        LoadOrderRequest loadOrderRequest = LoadOrderRequest.builder()
                .orderId(order.getId())
                .planeId(order.getPlaneId())
                .quantity(requestFromRegistration.getFood())
                .build();

        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на службу питания для загрузки".formatted(order.getId(), order.getPlaneId()));
            cateringClient.processOrder(loadOrderRequest);
        });

        OrderRequest orderRequest = OrderRequest
                .builder()
                .orderId(order.getId())
                .planeId(order.getPlaneId())
                .build();


        // нужно раздать заказ на службу загрузки багажа
        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на службу загрузки багажа".formatted(order.getId(), order.getPlaneId()));

            baggageClient.requestOrderToBaggageLoading(orderRequest);

        });

        LoadingPassengersRequest loadingPassengersRequest = LoadingPassengersRequest.builder()
                .orderId(order.getId())
                .planeId(order.getPlaneId())
                .passengers(requestFromRegistration.getPassengers().stream()
                        .map(passengers -> new PassengersToLoad(passengers.getPassengerId()))
                        .toList())
                .build();

        // нужно раздать заказ на службу загрузки пассажиров
        CompletableFuture.runAsync(() -> {

            log.info("Отдаём заказ с id заказа = %d и id самолёта = %d на службу загрузки пассажиров".formatted(order.getId(), order.getPlaneId()));
            passengerClient.requestOrderToPassengersLoading(loadingPassengersRequest);
        });


    }

    @Override
    @Transactional
    public void findOrderToSendLoad() {

        List<Order> orderList = orderRepository.findByStageAndStatus(5, Status.AWAITING_DEPARTURE_AFTER_UNLOADING);

        if (!orderList.isEmpty()) {
            log.info("Найдены следующие заказы, которые прошли разгрузку и готовы для отправки на регистрацию: " + orderList);

            for (Order order : orderList) {
                tabloClient.successReport(order.getPlaneId());
                log.info("Заказ с planeId = %d отправлен на регистрацию (на табло)".formatted(order.getPlaneId()));
                order.setStatus(Status.DURING_LOADING);
                orderRepository.save(order);
            }

        }


    }

    @Override
    @Transactional
    public void findOrderToFly() {

        List<Order> orderList = orderRepository.findByStageAndStatus(8, Status.READY_FOR_TAKEOFF);

        if (!orderList.isEmpty()) {

            log.info("Найдены следующие заказы, которые прошли регистрацию и загрузку и самолёты готовы взлетать: " + orderList);

            for (Order order : orderList) {

                planeClient.sendOrderToFly(order.getPlaneId());
                order.setStatus(Status.SENDED);
                orderRepository.save(order);

            }

        }
    }


}
