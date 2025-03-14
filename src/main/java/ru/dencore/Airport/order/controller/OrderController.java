package ru.dencore.Airport.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderDto;
import ru.dencore.Airport.order.model.Order;

import java.util.List;

@Tag(name = "Order API", description = "API для работы с заказами на обслуживание самолёта")
public interface OrderController {

    /**
     * Получить заказ на обслуживание
     */
    @Operation(summary = "Создание и выполнения заказа",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Заказ успешно принят на выполнение."),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные для заказа.")
            })
    void processOrder(@Valid @RequestBody OrderDto orderDto) throws InterruptedException;

    /**
     * Получить отчёт об успешном выполнении
     */
    @Operation(summary = "Уведомить об успешном выполнении заказа",
    responses = {
            @ApiResponse(responseCode = "200", description = "Отчёт о выполнении успешно записан."),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для записи отчёта")
    })
    void reportSuccessOrder(@PathVariable String name, @PathVariable Long orderId);


    /**
     * Получить все заказы
     */
    @Operation(summary = "Получить все заказы",
    responses = {
            @ApiResponse(responseCode = "200", description = "Данные о заказах успешно возвращены")
    })
    List<Order> getAllOrders();

}
