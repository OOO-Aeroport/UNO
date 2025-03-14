package ru.dencore.Airport.order.job.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.order.job.ProcessOrderJob;
import ru.dencore.Airport.order.service.OrderService;


@Service
@RequiredArgsConstructor
public class ProcessOrderJobImpl implements ProcessOrderJob {

    private final OrderService orderService;

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void processJob() {

        orderService.findOrderToSend();

    }
}
