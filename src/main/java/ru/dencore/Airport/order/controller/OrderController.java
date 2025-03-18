package ru.dencore.Airport.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderDto;
import ru.dencore.Airport.order.dto.RequestFromRegistration;
import ru.dencore.Airport.order.model.Order;

import java.util.List;

@Tag(name = "Order API", description = "API для работы с заказами на обслуживание самолёта")
public interface OrderController {


    /**
     * Получить отчёт об успешном выполнении от follow me
     */
    @Operation(summary = "Уведомить об успешном выполнении заказа",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отчёт о выполнении успешно записан."),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные для записи отчёта")
            })
    void reportSuccessOrderFromFollowMe(Long orderId);


    /**
     * Получить отчёт об успешном выполнении от службы разгрузки багажа
     */
    void reportSuccessOrderFromBaggageDischarge(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от разгрузки пассажиров
     */
    void reportSuccessOrderFromPassengersDischarge(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от топливозаправщика
     */
    void reportSuccessOrderFromTankerTruck(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от загрузки пассажиров
     */
    void reportSuccessOrderFromPassengersLoading(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от загрузки багажа
     */
    void reportSuccessFromBaggageLoading(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от службы питания (разгрузка)
     */
    void reportSuccessFromCateringUnload(Long orderId);

    /**
     * Получить отчёт об успешном выполнении от службы питания (загрузка)
     */
    void reportSuccessFromCateringLoad(Long orderId);

    void getOrderRequestFromRegistration(RequestFromRegistration requestFromRegistration);


    /**
     * Получить все заказы
     */
    @Operation(summary = "Получить все заказы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные о заказах успешно возвращены")
            })
    List<Order> getAllOrders();

}
