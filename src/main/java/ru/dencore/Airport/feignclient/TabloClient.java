package ru.dencore.Airport.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.feignclient.dto.SuccessReportRequest;


/**
 * Клиент для взаимодействия с табло
 */
@FeignClient(name = "table-client", url = "http://..")
public interface TabloClient {

    /**
     * Отправить отчёт об успешном выполнении заказа
     *
     * @param successReportRequest хранится id борта
     */
    @PatchMapping("/departure-board/planes/handled")
    void successReport(@RequestBody SuccessReportRequest successReportRequest);

}
