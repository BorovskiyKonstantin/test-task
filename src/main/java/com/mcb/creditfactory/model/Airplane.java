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
@Table(name = "AIRPLANE")
public class Airplane extends BaseVehicleEntity {
    private String manufacturer;
    @Column(name = "fuel_capacity")
    private Integer fuelCapacity;
    private Integer seats;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASSESSMENT_AIRPLANE",
            joinColumns = {@JoinColumn(name = "airplane_id")},
            inverseJoinColumns = {@JoinColumn(name = "assessment_id")}
    )
    private Set<Assessment> values = new TreeSet<>();

    public Airplane(Long id, String brand, String model, Short year, String manufacturer, Integer fuelCapacity, Integer seats, TreeSet<Assessment> values) {
        super(id, brand, model, year);
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
        this.values = values;
    }

    @Override
    public Set<Assessment> getAssessments() {
        return values;
    }
}
