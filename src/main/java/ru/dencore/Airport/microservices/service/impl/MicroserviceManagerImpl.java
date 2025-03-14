package ru.dencore.Airport.microservices.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dencore.Airport.microservices.dao.MicroserviceRepository;
import ru.dencore.Airport.microservices.model.Microservices;
import ru.dencore.Airport.microservices.service.MicroserviceManager;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MicroserviceManagerImpl implements MicroserviceManager {

    private final MicroserviceRepository microserviceRepository;

    @Override
    public void saveMicroservice(Microservices microservices) {
        microserviceRepository.save(microservices);
    }

    @Override
    @Transactional
    public void setTimeOfEnd(String name, Long orderId) {

        Optional<Microservices> optionalMicroservices = microserviceRepository.findByNameAndOrderId(name, orderId);

        if (optionalMicroservices.isEmpty()) {
            throw new RuntimeException();
        }

        Microservices microservices = optionalMicroservices.get();
        microservices.setFinishTime(LocalDateTime.now());

        microserviceRepository.save(microservices);
    }

}
