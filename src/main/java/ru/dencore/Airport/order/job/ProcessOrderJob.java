package ru.dencore.Airport.order.job;


/**
 * Job обработки заказов
 */
public interface ProcessOrderJob {

    /**
     * Найти заказы для отправки
     */
    void processJob();

}
