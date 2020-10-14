package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.AbstractAdapter;
import com.mcb.creditfactory.external.CollateralType;

public class CarAdapter extends AbstractAdapter<CarDto> {

    public CarAdapter(CarDto dto) {
        super(dto);
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
