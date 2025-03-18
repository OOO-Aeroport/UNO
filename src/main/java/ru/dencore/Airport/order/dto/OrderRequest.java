package ru.dencore.Airport.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class OrderRequest {

    /**
     * id заказа
     */
    @JsonProperty("orderId")
    private Long orderId;

    /**
     * id борта
     */
    @JsonProperty("planeId")
    private Integer planeId;

}
