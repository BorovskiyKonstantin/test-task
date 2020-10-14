package com.mcb.creditfactory.external;

import com.mcb.creditfactory.dto.AbstractVehicleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
public abstract class AbstractAdapter<DTO extends AbstractVehicleDto> implements CollateralObject {
    @Getter
    private DTO dto;

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public BigDecimal getValue() {
        return dto.getValue();
    }

    @Override
    public Short getYear() {
        return dto.getYear();
    }
}
