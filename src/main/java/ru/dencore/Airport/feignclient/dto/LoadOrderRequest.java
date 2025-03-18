package ru.dencore.Airport.feignclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class LoadOrderRequest {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("plane_id")
    private Integer planeId;

    private Integer quantity;
}
