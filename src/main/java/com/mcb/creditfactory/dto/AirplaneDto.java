package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonTypeName("airplane")
public class AirplaneDto extends AbstractVehicleDto {
    private String brand;
    private String model;
    private String manufacturer;
    private Integer fuelCapacity;
    private Integer seats;

    public AirplaneDto(Long id, Short year, Set<AssessmentDto> assessments, String brand, String model, String manufacturer, Integer fuelCapacity, Integer seats) {
        super(id, year, assessments);
        this.brand = brand;
        this.model = model;
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
    }
}
