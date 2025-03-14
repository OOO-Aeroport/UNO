package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Клиент для взаимодействия со службой Follow Me
 */
@FeignClient(name = "follow-me-client", url = "http://26.34.23.177:5555")
public interface FollowMeClient {

    /**
     * Отправить заказ на выполнение на follow me
     * @param orderId id заказа
     * @param planeId id самолёта
     */
    @GetMapping("/follow-me/handle-new-plane/{planeId}/{orderId}")
    void processOrder(@PathVariable Long orderId, @PathVariable Integer planeId);

}
