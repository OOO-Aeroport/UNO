package ru.dencore.Airport.order.service;


import ru.dencore.Airport.order.dto.RequestFromRegistration;
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
     * Обновить этап обслуживания для заказа
     *
     * @param orderId id заказа
     */
    void updateStage(Long orderId);

    /**
     * Получить все заказы
     */
    List<Order> getAllOrders();

    /**
     * Начать первую стадию обработки заказа
     * Отдать заказ на follow me
     */
    void requestOrderToFollowMe(Order order);

    /**
     * Начать вторую стадию обработки заказа
     * Отдать заказ на разгрузку на службу питания и СПБП (служба перевозки багажа и пассажиров)
     */
    void requestOrderToCateringAndPBC(Long orderId);

    /**
     * Начать третью стадию обработки заказа
     * Отдать заказ на топливозаправщик
     */
    void requestOrderToTankerTruck(Long orderId);

    /**
     * Начать четвертую стадию обработки заказа
     * Отдать заказ на загрузку на службу питания и службу загрузки багажа и пассажиров
     */
    void requestOrderToCateringAndPBCLoad(RequestFromRegistration requestFromRegistration);

    /**
     * Найти заказы, которые уже прошли разгрузку и не были ещё переданы на табло для регистрации
     */
    void findOrderToSendLoad();

    /**
     * Найти заказы, которые уже прошли загрузку и самолёты готовы взлетать
     */
    void findOrderToFly();
}
