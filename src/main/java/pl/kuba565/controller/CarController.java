package pl.kuba565.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kuba565.model.Car;
import pl.kuba565.service.CarService;
import pl.kuba565.service.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/")
@Validated
public class CarController implements Controller<Car> {
    private final Service<Car> carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("car/{id}")
    public @ResponseBody
    Car findById(@PathVariable(value = "id") @NotNull Long id) {
        return carService.findById(id);
    }

    @GetMapping("car")
    public @ResponseBody
    List<Car> findAll() {
        return carService.findAll();
    }

    @PostMapping("car")
    public @ResponseBody
    Long post(@RequestBody Car newCar) {
        return carService.create(newCar);
    }

    @PutMapping("car")
    public @ResponseBody
    Car put(@RequestBody Car newCar) {
        return carService.update(newCar);
    }

    @DeleteMapping("car/{id}")
    public @ResponseBody
    void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        carService.deleteById(id);
    }
}
