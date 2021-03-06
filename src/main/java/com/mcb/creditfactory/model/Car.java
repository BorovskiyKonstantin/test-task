package com.mcb.creditfactory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "CAR")
public class Car extends BaseVehicleEntity {
    private Double power;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASSESSMENT_CAR",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "assessment_id")}
    )
    private Set<Assessment> values = new TreeSet<>();

    public Car(Long id, String brand, String model, Short year, Double power, Set<Assessment> values) {
        super(id, brand, model, year);
        this.power = power;
        this.values = values;
    }

    @Override
    public Set<Assessment> getAssessments() {
        return values;
    }
}
