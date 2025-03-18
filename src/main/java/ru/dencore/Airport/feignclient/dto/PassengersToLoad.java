package ru.dencore.Airport.feignclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class PassengersToLoad {

    @JsonProperty("passenger_id")
    private Integer passengerId;

}
