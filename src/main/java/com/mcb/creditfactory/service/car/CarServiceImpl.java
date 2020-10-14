package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Assessment;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.service.assessment.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeSet;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        TreeSet<Assessment> assessments = new TreeSet<>();
        //При создании нового объекта обеспечения с переданным value
        if (dto.getValue() != null) {
            Assessment assessment = new Assessment(dto.getValue());
            assessments.add(assessment);
        }

        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getPower(),
                assessments
        );
    }

    @Override
    public CarDto toDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getYear(),
                assessmentService.toDtoSet(car.getValues()),
                car.getBrand(),
                car.getModel(),
                car.getPower()
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

}
