package ru.dencore.Airport.feignclient.dto;


import lombok.*;


import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class LoadingPassengersRequest {

    private Integer planeId;

    private Long orderId;

    private List<PassengersToLoad> passengers;

}
