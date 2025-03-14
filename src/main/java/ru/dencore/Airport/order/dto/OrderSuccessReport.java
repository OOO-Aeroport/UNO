package ru.dencore.Airport.order.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class OrderSuccessReport {

    /**
     * id заказа
     */
    @JsonProperty("order_id")
    @NotNull
    private Long orderId;


    private String name;

}