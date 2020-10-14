package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.AbstractAdapter;
import com.mcb.creditfactory.external.CollateralType;

public class AirplaneAdapter extends AbstractAdapter<AirplaneDto> {

    public AirplaneAdapter(AirplaneDto dto) {
        super(dto);
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
