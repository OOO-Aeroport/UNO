package ru.dencore.Airport.microservices.service;

import ru.dencore.Airport.microservices.model.Microservices;

import java.util.List;

public interface MicroserviceManager {

    /**
     * Сохранить информацию о сервисе, который будет выполнить заказ
     *
     * @param microservices сервис
     */
    void saveMicroservice(Microservices microservices);

    /**
     * Установить время завершения заказа на обслуживание сервисом
     */
    void setTimeOfEnd(String name, Long orderId);

    /**
     * Получить все микросервисы
     */
    List<Microservices> getAllMicroservices();

}
