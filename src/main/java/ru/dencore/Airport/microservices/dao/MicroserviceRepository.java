package ru.dencore.Airport.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dencore.Airport.microservices.model.Microservices;

import java.util.Optional;

@Repository
public interface MicroserviceRepository extends JpaRepository<Microservices, Long> {

    Optional<Microservices> findByNameAndOrderId(String name, Long orderId);

}
