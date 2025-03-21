package ru.dencore.Airport.order.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class RequestFromRegistration {

    private Integer planeId;

    private Integer baggage;

    private Integer food;

    private List<Passengers> passengers;
}
