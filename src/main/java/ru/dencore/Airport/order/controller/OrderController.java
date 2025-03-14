package ru.dencore.Airport.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dencore.Airport.order.dto.OrderDto;

@Tag(name = "Order API", description = "API для работы с заказами на обслуживание самолёта")
public interface OrderController {

    @Operation(summary = "Создание и выполнения заказа",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Заказ успешно принят на выполнение."),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные для заказа.")
            })
    void processOrder(@Valid @RequestBody OrderDto orderDto);


    void reportSuccessOrder(@PathVariable String name, @PathVariable Long orderId);

}
