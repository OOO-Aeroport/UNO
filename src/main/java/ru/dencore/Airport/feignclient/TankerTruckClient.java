package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Клиент для взаимодействия с топливозаправщиком
 */
@FeignClient(name = "tanker-truck-client", url = "http://26.231.166.224:5555")
public interface TankerTruckClient {


    /**
     * Отправить заказ на выполнение на топливозаправщик
     *
     * @param orderId id заказа
     * @param fuel    топливо, которое нужно заправить
     * @param planeId id самолёта
     */
    @GetMapping("/fueltruck/order/{orderId}/{fuel}/{planeId}")
    void processOrder(@PathVariable Long orderId, @PathVariable Integer fuel, @PathVariable Integer planeId);

}
