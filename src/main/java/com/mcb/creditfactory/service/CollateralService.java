package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AbstractVehicleDto;
import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.BaseVehicleEntity;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollateralService {
    private CarService carService;
    private AirplaneService airplaneService;

    @Autowired
    public CollateralService(CarService carService, AirplaneService airplaneService) {
        this.carService = carService;
        this.airplaneService = airplaneService;
    }

    public Long saveCollateral(Collateral object) {
        if (object instanceof CarDto) {
            return saveVehicle(object, carService);
        }
        if (object instanceof AirplaneDto) {
            return saveVehicle(object, airplaneService);
        }
        throw new IllegalArgumentException();
    }

    //saveVehicle
    private <DTO extends AbstractVehicleDto, ENTITY extends BaseVehicleEntity> Long saveVehicle(Collateral object, VehicleService<ENTITY, DTO> service) {
        //noinspection unchecked
        DTO dto = (DTO) object;
        //При добавлении id должен быть null
        if (dto.getId() != null) {
            return null;
        }
        boolean approved = service.approve(dto);
        if (!approved) {
            return null;
        }
        return Optional.of(dto)
                .map(service::fromDto)
                .map(service::save)
                .map(service::getId)
                .orElse(null);
    }

    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {
            return getVehicleInfo(object, carService);
        }
        if (object instanceof AirplaneDto) {
            return getVehicleInfo(object, airplaneService);
        }
        throw new IllegalArgumentException();
    }

    //getVehicleInfo
    private <DTO extends AbstractVehicleDto, ENTITY extends BaseVehicleEntity> Collateral getVehicleInfo(Collateral object, VehicleService<ENTITY, DTO> service) {
        //noinspection unchecked
        return Optional.of((DTO) object)
                .map(AbstractVehicleDto::getId)
                .flatMap(service::load)
                .map(service::toDto)
                .orElse(null);
    }

    public Collateral updateCollateral(Collateral object) {
        if (object instanceof CarDto) {
            return updateVehicle(object, carService);
        }
        if (object instanceof AirplaneDto) {
            return updateVehicle(object, airplaneService);
        }
        throw new IllegalArgumentException();
    }

    //updateVehicle
    private <DTO extends AbstractVehicleDto, ENTITY extends BaseVehicleEntity> Collateral updateVehicle(Collateral object, VehicleService<ENTITY, DTO> service) {
        //noinspection unchecked
        DTO dto = (DTO) object;
        //проверка id на null
        if (dto.getId() == null)
            throw new IllegalArgumentException();

        //поиск сущности в БД по id
        ENTITY entityFromDB = service.load(dto.getId()).orElse(null);
        if (entityFromDB == null) {
            return null;
        }
        //добавление новой оценки в сущность
        service.addAssessmentFromDto(entityFromDB, dto);
        //упаковка в DTO
        DTO resultDto = service.toDto(entityFromDB);

        //approve
        //TODO: костыль с установкой value, чтобы не переписывать логику ExternalApproveService
        resultDto.setValue(dto.getValue()); //передача value для проверки в approve
        service.approve(resultDto);
        boolean approved = service.approve(resultDto);
        if (!approved) {
            return null;
        }
        resultDto.setValue(null); //убрать value, чтобы не отображать в response
        //сохранение в БД
        service.save(entityFromDB);

        return resultDto;
    }
}
