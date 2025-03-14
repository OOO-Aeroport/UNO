package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;

@FeignClient(name = "passenger-and-baggage-client", url = "http://..")
public interface PassengerAndBaggageClient {

    @PostMapping("")
    void processOrder(@RequestBody OrderRequest orderRequest);

}
