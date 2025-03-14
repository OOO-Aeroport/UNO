package ru.dencore.Airport.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Запрос на выполнение заказа для отправки на сервисы
 * 1) Служба питания
 * 2) Топливозаправщик
 * 3) Служба перевозки пассажиров и багажа
 * 4) Служба Follow Me
 */
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class OrderRequest {

    /**
     * id заказа
     */
    @JsonProperty("order_id")
    private Long orderId;

    /**
     * id борта
     */
    @JsonProperty("plane_id")
    private Integer planeId;

}
