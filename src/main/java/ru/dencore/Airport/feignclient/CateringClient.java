package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;


/**
 * Клиент для взаимодействия со службой питания
 */
@FeignClient(name = "catering-client", url = "http://..")
public interface CateringClient {

    /**
     * Отправить заказ на выполнение на службу питания
     * @param orderRequest заказ
     */
    @PostMapping("/catering-service/api/v1/order")
    void processOrder(@RequestBody OrderRequest orderRequest);

}
