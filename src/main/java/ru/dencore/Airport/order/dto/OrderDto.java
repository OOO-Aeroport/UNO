package ru.dencore.Airport.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@Schema(description = "Заказ на обслуживание")
@ToString
public class OrderDto {

    @Schema(description = "id борта")
    @NotNull(message = "ID борта не может быть null")
    private Integer id;

    @Schema(description = "Номер гейта (стоянки)")
    @NotNull(message = "Номер gate не может быть null")
    private Integer gate;

    @Schema(description = "Текущее топливо у самолёта")
    @NotNull(message = "Топливо не должно быть null")
    private Integer currentFuel;

    @Schema(description = "Максимальное топливо у самолёта")
    @NotNull(message = "Топливо не должно быть null")
    private Integer maxFuel;

}
