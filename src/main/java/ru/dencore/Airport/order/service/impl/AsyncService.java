package ru.dencore.Airport.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.feignclient.CateringClient;
import ru.dencore.Airport.feignclient.FollowMeClient;
import ru.dencore.Airport.feignclient.PassengerAndBaggageClient;
import ru.dencore.Airport.feignclient.TankerTruckClient;
import ru.dencore.Airport.order.dto.OrderRequest;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Autowired
    private CateringClient cateringClient;

    @Autowired
    private FollowMeClient followMeClient;

    @Autowired
    private PassengerAndBaggageClient passengerAndBaggageClient;

    @Autowired
    private TankerTruckClient tankerTruckClient;

    public CompletableFuture<Void> processCateringOrderAsync(OrderRequest orderRequest) {
        return CompletableFuture.runAsync(() -> {
            cateringClient.processOrder(orderRequest);
            log.info("Заказ с id: {} отправлен на службу питания", orderRequest.getOrderId());
        });
    }

    public CompletableFuture<Void> processFollowMeOrderAsync(Long orderId, Integer planeId) {
        return CompletableFuture.runAsync(() -> {
            followMeClient.processOrder(orderId, planeId);
            log.info("Заказ с id: {} отправлен на службу follow me", orderId);
        });
    }

    public CompletableFuture<Void> processPassengerAndBaggageOrderAsync(Integer planeId, Long orderId) {
        return CompletableFuture.runAsync(() -> {
            passengerAndBaggageClient.processOrder(planeId, orderId);
            log.info("Заказ с id: {} отправлен на службу перевозки пассажиров и багажа", orderId);
        });
    }

    public CompletableFuture<Void> processTankerTruckOrderAsync(Long orderId, Integer fuel, Integer planeId) {
        return CompletableFuture.runAsync(() -> {
            tankerTruckClient.processOrder(orderId, fuel, planeId);
            log.info("Заказ с id: {} отправлен на топливозаправщик", orderId);
        });
    }

}