package ru.dencore.Airport.job.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.job.SendingOrderToLoad;
import ru.dencore.Airport.order.service.OrderService;


@Service
@RequiredArgsConstructor
public class SendingOrderToLoadImpl implements SendingOrderToLoad {

    private final OrderService orderService;

    @Override
    @Scheduled(fixedDelayString = "5000")
    public void sendingOrderToLoad() {
        orderService.findOrderToSendLoad();
    }


}
