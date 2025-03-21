package ru.dencore.Airport.plane.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Plane ID", description = "API для работы с самолётом")
public interface PlaneController {

    /**
     * Получить уведомление о спавне самолёта
     */
    void createPlane(Integer fuel, Integer planeId);

}
