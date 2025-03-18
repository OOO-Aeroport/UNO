package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.feignclient.dto.LoadOrderRequest;
import ru.dencore.Airport.feignclient.dto.UnloadOrderRequestToCatering;
import ru.dencore.Airport.order.dto.OrderRequest;


/**
 * Клиент для взаимодействия со службой питания
 */
@FeignClient(name = "catering-client", url = "http://26.55.151.11:5555")
public interface CateringClient {

    /**
     * Отправить заказ на выполнение на службу питания на разгрузку
     */
    @PostMapping("/catering-service/api/v1/unload-order")
    void processOrder(@RequestBody UnloadOrderRequestToCatering unloadOrderRequestToCatering);

    @PostMapping("/catering-service/api/v1/load-order")
    void processOrder(@RequestBody LoadOrderRequest loadOrderRequest);

}
