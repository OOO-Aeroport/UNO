package ru.dencore.Airport.job;


/**
 * Job обработки заказов
 */
public interface SendingOrderToLoad {

    /**
     * Найти заказы, которые уже полностью прошли разгрузку и ещё не были переданы на табло
     */
    void sendingOrderToLoad();

}
