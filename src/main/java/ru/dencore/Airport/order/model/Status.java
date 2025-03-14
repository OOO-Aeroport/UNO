package ru.dencore.Airport.order.model;

/**
 * Статусы для работы с заказом
 */
public enum Status {

    /**
     * Заказ в процессе выполнения
     */
    WAITING_TO_PROCESS,

    /**
     * Заказ отправлен
     */
    SENDED
}
