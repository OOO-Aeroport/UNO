package ru.dencore.Airport.microservices.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ru.dencore.Airport.microservices.model.Microservices;

import java.util.List;

@Tag(name = "Microservice API", description = "API для работы с microservice для сбора логов")
public interface MicroserviceController {

    /**
     * Получить все службы
     */
    List<Microservices> getAllMicroservices();
}
