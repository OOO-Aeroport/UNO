package ru.dencore.Airport.feignclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class UnloadOrderRequestToCatering {

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
