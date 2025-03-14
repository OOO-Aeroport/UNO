package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "table-client", url = "http://..")
public interface TabloClient {

    @PostMapping("")
    void successReport();
}
