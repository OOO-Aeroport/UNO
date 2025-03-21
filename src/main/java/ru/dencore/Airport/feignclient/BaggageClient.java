package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;

@FeignClient(name = "baggage-client", url = "http://26.132.135.106:5555")
public interface BaggageClient {

    /**
     * Отправить заказ на выполнение на СПБП на разгрузку багажа
     */
    @PostMapping("/baggage-discharge")
    void requestOrderToBaggageDischarge(@RequestBody OrderRequest orderRequest);

    /**
     * Отправить заказ на выполнение на СПБП на загрузку багажа
     */
    @PostMapping("/baggage-loading")
    void requestOrderToBaggageLoading(@RequestBody OrderRequest orderRequest);
}
