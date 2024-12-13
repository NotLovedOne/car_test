package org.example.car_test.controller;

import jakarta.validation.Valid;
import org.example.car_test.model.Car;
import org.example.car_test.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        logger.info("getall request.");
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable UUID id) {
        logger.info("get car with id", id);
        return carService.getCarById(id);
    }

    @PostMapping
    public Car createCar(@Valid @RequestBody Car car) {
        logger.info("create car");
        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable UUID id, @Valid @RequestBody Car car) {
        logger.info("update car with id", id);

        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        logger.info("delete car with id", id);

        carService.deleteCar(id);
    }
}

