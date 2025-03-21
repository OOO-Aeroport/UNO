package ru.dencore.Airport.plane.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dencore.Airport.order.model.Order;
import ru.dencore.Airport.order.model.Status;
import ru.dencore.Airport.order.service.OrderService;
import ru.dencore.Airport.plane.controller.PlaneController;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("uno/api/v1/plane")
@RequiredArgsConstructor
public class PlaneControllerImpl implements PlaneController {

    private final OrderService orderService;

    @PostMapping("/{planeId}/{fuel}/create-plane")
    @Override
    public void createPlane(@PathVariable Integer fuel, @PathVariable Integer planeId) {

        log.info("Самолёт с id = %d прибыл в аэропорт.".formatted(planeId));

        // нужно сохранить заказ
        Order order = Order.builder()
                .fuel(fuel)
                .planeId(planeId)
                .stage(0)
                .status(Status.DURING_FOLLOW_ME)
                .timeStart(LocalDateTime.now())
                .build();

        Order orderSaved = orderService.saveOrder(order);

        // отправляем заказ на follow me
        orderService.requestOrderToFollowMe(orderSaved);

    }


}
