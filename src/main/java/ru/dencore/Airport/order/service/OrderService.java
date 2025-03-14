package ru.dencore.Airport.order.service;


import ru.dencore.Airport.order.model.Order;

import java.util.List;

/**
 * Сервис для работы с заказами
 */
public interface OrderService {

    /**
     * Сохранить заказ
     *
     * @param order заказ
     * @return сохранённый заказ
     */
    Order saveOrder(Order order);

    /**
     * Разослать сервисом заказ на обслуживание
     *
     * @param order заказ
     */
    void broadcastOrder(Order order);

    /**
     * Обновить этап обслуживания для заказа
     * @param orderId id заказа
     */
    void updateStage(Long orderId);

    /**
     * Поиск заказа для отправки
     */
    void findOrderToSend();

    /**
     * Получить все заказы
     */
    List<Order> getAllOrders();
}
