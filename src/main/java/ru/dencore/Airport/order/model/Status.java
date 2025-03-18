package ru.dencore.Airport.order.model;

/**
 * Статусы для работы с заказом
 */
public enum Status {

    /**
     * Самолёту не дали разрешение на посадку
     */
    NO_PERMISSION,

    /**
     * Самолёт в процессе получения разрешения на посадку
     */
    DURING_PERMISSION_TO_LAND,

    /**
     * Самолёт в процессе сопровождения follow me
     */
    DURING_FOLLOW_ME,

    /**
     * Самолёт в процессе разгрузки
     */
    DURING_UNLOADING,

    /**
     * Самолёт ожидает отправки на регистрацию после разгрузки
     */
    AWAITING_DEPARTURE_AFTER_UNLOADING,

    /**
     * Самолёт в процессе загрузки
     */
    DURING_LOADING,

    /**
     * Самолёт готов к взлёту
     */
    READY_FOR_TAKEOFF,

    /**
     * Самолёт улетел
     */
    SENDED,

}
