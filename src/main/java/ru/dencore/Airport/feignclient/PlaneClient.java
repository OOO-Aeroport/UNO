package ru.dencore.Airport.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "plane-client", url = "http://26.125.155.211:5555")
public interface PlaneClient {


    @PostMapping("/service_complete/{planeId}")
    void sendOrderToFly(@PathVariable Integer planeId);

}
