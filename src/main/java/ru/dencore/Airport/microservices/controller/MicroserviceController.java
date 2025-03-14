package ru.dencore.Airport.microservices.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.dencore.Airport.microservices.model.Microservices;

import java.util.List;

@Tag(name = "Microservice API", description = "API для работы с microservice для сбора логов")
public interface MicroserviceController {

    /**
     * Получить всю историю отчётов от служб
     */
    @Operation(summary = "Получить все отчёты от служб",
            responses = {
                @ApiResponse(responseCode = "200", description = "Данные об отчётах успешно получены")
            })
    List<Microservices> getAllMicroservices();
}
