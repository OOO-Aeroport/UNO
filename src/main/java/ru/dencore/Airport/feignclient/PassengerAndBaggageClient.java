package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;


/**
 * Клиент для взаимодействия со службой перевозки пассажиров и багажа
 */
@FeignClient(name = "passenger-and-baggage-client", url = "http://..")
public interface PassengerAndBaggageClient {

    /**
     * Отправить заказ на выполнение на службу перевозки пассажиров и багажа
     *
     * @param flightId id самолёта
     * @param orderId  id заказа
     */
    @GetMapping("/plane-info/{flightId}/{orderId}")
    void processOrder(@PathVariable Integer flightId, @PathVariable Long orderId);

}
