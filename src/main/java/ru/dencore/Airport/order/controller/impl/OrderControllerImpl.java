package ru.dencore.Airport.order.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.dencore.Airport.microservices.service.MicroserviceManager;
import ru.dencore.Airport.order.controller.OrderController;
import ru.dencore.Airport.order.dto.OrderDto;
import ru.dencore.Airport.order.model.Order;
import ru.dencore.Airport.order.model.Status;
import ru.dencore.Airport.order.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("uno/api/v1/order")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final MicroserviceManager microserviceManager;

    @Override
    @PostMapping("/process-order")
    public void processOrder(OrderDto orderDto) throws InterruptedException {

        log.info("Поступил заказ на обслуживание " + orderDto);

        Order order = Order.builder()
                .planeId(orderDto.getId())
                .timeStart(LocalDateTime.now())
                .status(Status.WAITING_TO_PROCESS)
                .fuel(orderDto.getMaxFuel() - orderDto.getCurrentFuel())
                .stage(0)
                .build();

        Order orderSaved = orderService.saveOrder(order);

        orderService.broadcastOrder(orderSaved);
    }

    @Override
    @GetMapping("/successReport/{orderId}/{name}")
    public void reportSuccessOrder(@PathVariable String name, @PathVariable Long orderId) {

        log.info("Поступил отчёт о выполнении заказа с id=%d от %s".formatted(orderId, name));

        orderService.updateStage(orderId);
        microserviceManager.setTimeOfEnd(name, orderId);

        log.info("Закончилась обработка отчёта о выполнении заказа с id=%d от %s".formatted(orderId, name));
    }

    @Override
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {

        log.info("Получен запрос на получение заказов для dashboard");
        return orderService.getAllOrders();

    }

}
