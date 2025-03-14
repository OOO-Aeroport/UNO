package ru.dencore.Airport.microservices.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dencore.Airport.microservices.controller.MicroserviceController;
import ru.dencore.Airport.microservices.model.Microservices;
import ru.dencore.Airport.microservices.service.MicroserviceManager;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("uno/api/v1/microservice")
@RequiredArgsConstructor
public class MicroserviceControllerImpl implements MicroserviceController {

    private final MicroserviceManager microserviceManager;

    @Override
    @GetMapping("/getAllMicroservices")
    public List<Microservices> getAllMicroservices() {
        return microserviceManager.getAllMicroservices();
    }


}
