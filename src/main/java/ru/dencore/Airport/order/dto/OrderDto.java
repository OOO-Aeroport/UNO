package ru.dencore.Airport.order.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Заказ на обслуживание борта
 */
@NoArgsConstructor
@Setter
@Getter
@Schema(description = "Заказ на обслуживание")
@ToString
public class OrderDto {

    @Schema(description = "id борта")
    @NotNull(message = "ID борта не может быть null")
    private Integer id;

    @Schema(description = "Текущее топливо у самолёта")
    @NotNull(message = "Топливо не должно быть null")
    private Integer currentFuel;

    @Schema(description = "Максимальное топливо у самолёта")
    @NotNull(message = "Топливо не должно быть null")
    private Integer maxFuel;

}
