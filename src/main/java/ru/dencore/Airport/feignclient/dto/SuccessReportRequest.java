package ru.dencore.Airport.feignclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


/**
 * Отправка на табло об успешном обслуживании борта
 */
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class SuccessReportRequest {

    /**
     * id борта
     */
    @JsonProperty("id")
    private Integer planeId;

}
