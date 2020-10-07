package pl.kuba565.resttask.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.CarDtoTransformerImpl;
import pl.kuba565.resttask.transformer.model.CarTransformerImpl;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kuba565.resttask.util.StringUtil.CAR;

@RequestMapping(CAR)
@Validated
public class CarControllerImpl extends GenericControllerImpl<Car, CarDto> implements CarController {
    private final GenericService<Car> carGenericService;
    private CarDtoTransformerImpl carDtoTransformer;
    private CarTransformerImpl carTransformer;

    public CarControllerImpl(GenericService<Car> carGenericService,
                             CarDtoTransformerImpl carDtoTransformer,
                             CarTransformerImpl carTransformer) {
        super(carGenericService, carDtoTransformer, carTransformer);
        this.carGenericService = carGenericService;
        this.carDtoTransformer = carDtoTransformer;
        this.carTransformer = carTransformer;
    }

    public CarDto findById(@PathVariable(value = "id") @NotNull Long id) {
        return carTransformer.apply(carGenericService.findById(id));
    }

    public List<CarDto> findAll() {
        return carGenericService.findAll()
                .stream()
                .map(car -> carTransformer.apply(car))
                .collect(Collectors.toList());
    }

    public void create(@RequestBody CarDto carDto) {
        carGenericService.create(carDtoTransformer.apply(carDto));
    }

    public CarDto update(@RequestBody CarDto carDto) {
        return carTransformer.apply(
                carGenericService.update(
                        carDtoTransformer.apply(carDto)));
    }

    public void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        carGenericService.deleteById(id);
    }
}
