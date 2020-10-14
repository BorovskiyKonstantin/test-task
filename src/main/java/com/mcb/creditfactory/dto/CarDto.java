package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonTypeName("car")
public class CarDto extends AbstractVehicleDto {
    private String brand;
    private String model;
    private Double power;

    public CarDto(Long id, Short year, Set<AssessmentDto> assessments, String brand, String model, Double power) {
        super(id, year, assessments);
        this.brand = brand;
        this.model = model;
        this.power = power;
    }
}
