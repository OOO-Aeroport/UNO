package ru.dencore.Airport.job;

public interface SendingOrderToFly {

    /**
     * Найти заказы, которые уже полностью прошли загрузку и готовы взлетать
     */
    void sendingOrderToFly();
}
