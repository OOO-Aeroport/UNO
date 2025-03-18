package ru.dencore.Airport.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


//TODO: доделать сигнатуру, когда Егор скажет
@FeignClient(name = "plane-client", url = "http://26.55.151.11:5555")
public interface PlaneClient {

    @PostMapping("...")
    void sendLandingApprovalData(@RequestBody List<Integer> data);

    @PostMapping
    void sendOrderToFly();
}
