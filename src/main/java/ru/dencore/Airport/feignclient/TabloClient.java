package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.dencore.Airport.feignclient.dto.SuccessReportRequest;


/**
 * Клиент для взаимодействия с табло
 */
@FeignClient(name = "table-client", url = "http://26.228.200.110:5555")
public interface TabloClient {

    /**
     * Отправить отчёт об успешном выполнении заказа
     */
    @PutMapping("/departure-board/planes/{id}/handled")
    void successReport(@PathVariable Integer id);

}
