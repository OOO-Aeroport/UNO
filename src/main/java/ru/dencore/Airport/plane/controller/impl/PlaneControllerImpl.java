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
    public void createPlane(@PathVariable Integer fuel, @PathVariable Long planeId) {

        log.info("Самолёт с id = %d просит разрешение на посадку.".formatted(planeId));

        // нужно сохранить заказ
        Order order = Order.builder()
                .fuel(fuel)
                .id(planeId)
                .stage(0)
                .status(Status.DURING_PERMISSION_TO_LAND)
                .timeStart(LocalDateTime.now())
                .build();

        Order orderSaved = orderService.saveOrder(order);

        // теперь нужно получить разрешение на посадку
        orderService.requestPermissionToLand(orderSaved);

    }


}
