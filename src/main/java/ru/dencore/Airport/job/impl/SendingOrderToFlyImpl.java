package ru.dencore.Airport.job.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.job.SendingOrderToFly;
import ru.dencore.Airport.order.service.OrderService;


@Service
@RequiredArgsConstructor
public class SendingOrderToFlyImpl implements SendingOrderToFly {

    private final OrderService orderService;

    @Override
    @Scheduled(fixedDelayString = "5000")
    public void sendingOrderToFly() {

        orderService.findOrderToFly();
    }
}
