package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.feignclient.dto.LoadingPassengersRequest;
import ru.dencore.Airport.order.dto.OrderRequest;


/**
 * Клиент для взаимодействия со службой перевозки пассажиров и багажа
 */
@FeignClient(name = "passenger-and-baggage-client", url = "http://26.132.135.106:5555")
public interface PassengerAndBaggageClient {

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


    /**
     * Отправить заказ на выполнение на СПБП на разгрузку пассажиров
     */
    @PostMapping("/passegers-discharge")
    void requestOrderToPassengersDischarge(@RequestBody OrderRequest orderRequest);

    /**
     * Отправить заказ на выполнение на СПБП на загрузку пассажиров
     */
    @PostMapping("/passegers-loading")
    void requestOrderToPassengersLoading(@RequestBody LoadingPassengersRequest loadingPassengersRequest);

}
