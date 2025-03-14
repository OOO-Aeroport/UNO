package ru.dencore.Airport.feignclient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderRequest;

@FeignClient(name = "follow-me-client", url = "http://..")
public interface FollowMeClient {

    @PostMapping("")
    void processOrder(@RequestBody OrderRequest orderRequest);

}
