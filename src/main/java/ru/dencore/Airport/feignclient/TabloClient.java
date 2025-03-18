package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Клиент для взаимодействия с табло
 */
@FeignClient(name = "table-client", url = "http://26.228.200.110:5555")
public interface TabloClient {

    /**
     * Отправить отчёт о самолёте, который прошёл разгрузку и готов для регистрации
     */
    @PutMapping("/dep-board/api/v1/airplanes/{airplaneId}/ready")
    void successReport(@PathVariable Integer airplaneId);

}
