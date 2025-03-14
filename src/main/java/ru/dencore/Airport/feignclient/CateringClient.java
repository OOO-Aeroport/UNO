package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;

@FeignClient(name = "catering-client", url = "http://..")
public interface CateringClient {

    @PostMapping("")
    void processOrder(@RequestBody OrderRequest orderRequest);

}
