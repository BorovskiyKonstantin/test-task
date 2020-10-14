package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

@Data
public abstract class AbstractVehicleDto implements Collateral {
    Long id;
    Short year;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    BigDecimal value;
    TreeSet<AssessmentDto> assessments;

    public AbstractVehicleDto(Long id, Short year, Set<AssessmentDto> assessments) {
        this.id = id;
        this.year = year;
        if (assessments != null)
            this.assessments = new TreeSet<>(assessments);
    }
}
