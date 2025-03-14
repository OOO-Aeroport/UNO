package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;

@FeignClient(name = "tanker-truck-client", url = "http://..")
public interface TankerTruckClient {

    @GetMapping("/fueltruck/order/{orderId}/{fuel}/{planeId}")
    void processOrder(@PathVariable Long orderId, @PathVariable Integer fuel, @PathVariable Integer planeId);

}
