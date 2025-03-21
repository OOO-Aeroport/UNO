package ru.dencore.Airport.order.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.dencore.Airport.microservices.service.MicroserviceManager;
import ru.dencore.Airport.order.controller.OrderController;
import ru.dencore.Airport.order.dto.RequestFromRegistration;
import ru.dencore.Airport.order.model.Order;
import ru.dencore.Airport.order.service.OrderService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("uno/api/v1/order")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final MicroserviceManager microserviceManager;

    @Override
    @PostMapping("/successReport/{orderId}/follow-me")
    public void reportSuccessOrderFromFollowMe(@PathVariable Long orderId) {

        log.info("Поступил отчёт о выполнении от follow me по id заказу равному %d".formatted(orderId));

        orderService.requestOrderToCateringAndPBC(orderId);
    }

    @Override
    @PostMapping("/successReport/{orderId}/baggage-discharge")
    public void reportSuccessOrderFromBaggageDischarge(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от baggage-discharge по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);

    }

    @Override
    @PostMapping("/successReport/{orderId}/passengers-discharge")
    public void reportSuccessOrderFromPassengersDischarge(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от passengers-discharge по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);

        // передать заказ на топливозаправщик

        orderService.requestOrderToTankerTruck(orderId);

    }

    @Override
    @PostMapping("/successReport/{orderId}/tanker-truck")
    public void reportSuccessOrderFromTankerTruck(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от tanker-truck по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);

    }

    @Override
    @PostMapping("/successReport/{orderId}/passengers-loading")
    public void reportSuccessOrderFromPassengersLoading(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от passegers-loading по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);
    }

    @Override
    @PostMapping("/successReport/{orderId}/baggage-loading")
    public void reportSuccessFromBaggageLoading(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от passegers-loading по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);

    }

    @Override
    @PostMapping("/successReport/{orderId}/catering-unload")
    public void reportSuccessFromCateringUnload(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от catering-unload по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);
    }

    @Override
    @PostMapping("/successReport/{orderId}/catering-load")
    public void reportSuccessFromCateringLoad(@PathVariable Long orderId) {

        log.info("Получен отчёт о выполнении заказа от catering-load по id заказу равному %d".formatted(orderId));

        orderService.updateStage(orderId);
    }

    @Override
    @PostMapping("/order-from-registration")
    public void getOrderRequestFromRegistration(@RequestBody RequestFromRegistration requestFromRegistration) {

        System.out.println(requestFromRegistration);
        log.info("Пришёл заказ от регистрации с planeId = %d".formatted(requestFromRegistration.getPlaneId()));

        orderService.requestOrderToCateringAndPBCLoad(requestFromRegistration);
    }


    @Override
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {

        log.info("Получен запрос на получение заказов для dashboard");
        return orderService.getAllOrders();

    }

}
