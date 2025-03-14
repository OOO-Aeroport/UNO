package ru.dencore.Airport.microservices.model;

import jakarta.persistence.*;
import lombok.*;
import ru.dencore.Airport.order.model.Order;

import java.time.LocalDateTime;


/**
 * Службы обслуживания самолёта
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Microservices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "start_finish")
    private LocalDateTime finishTime;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
