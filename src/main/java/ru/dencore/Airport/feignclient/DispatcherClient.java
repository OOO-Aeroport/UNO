package ru.dencore.Airport.feignclient;


import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "dispatcher-client", url = "http://26.55.151.11:5555")
public interface DispatcherClient {

    @GetMapping("/dispatcher/plane/{planeId}")
    List<Integer> requestPermission(@PathVariable Integer planeId);

}
