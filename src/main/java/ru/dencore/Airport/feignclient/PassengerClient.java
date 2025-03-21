package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.feignclient.dto.LoadingPassengersRequest;
import ru.dencore.Airport.order.dto.OrderRequest;


/**
 * Клиент для взаимодействия со службой перевозки пассажиров
 */
@FeignClient(name = "passenger-client", url = "http://26.132.135.106:5556")
public interface PassengerClient {

    /**
     * Отправить заказ на выполнение на СПБП на разгрузку пассажиров
     */
    @PostMapping("/passengers-discharge")
    void requestOrderToPassengersDischarge(@RequestBody OrderRequest orderRequest);

    /**
     * Отправить заказ на выполнение на СПБП на загрузку пассажиров
     */
    @PostMapping("/passengers-discharge")
    void requestOrderToPassengersLoading(@RequestBody LoadingPassengersRequest loadingPassengersRequest);

}
