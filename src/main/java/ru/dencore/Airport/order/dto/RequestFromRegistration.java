package ru.dencore.Airport.order.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class RequestFromRegistration {

    private Integer flightId;

    private Integer baggage;

    private Integer food;

    private List<Passengers> passengers;
}
