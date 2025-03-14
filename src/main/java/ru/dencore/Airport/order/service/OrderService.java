package ru.dencore.Airport.order.service;

import ru.dencore.Airport.order.dto.OrderRequest;
import ru.dencore.Airport.order.model.Order;

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

    void findOrderToSend();
}
