package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AbstractVehicleDto;
import com.mcb.creditfactory.model.Assessment;
import com.mcb.creditfactory.model.BaseVehicleEntity;

import java.util.Optional;

public interface VehicleService<E extends BaseVehicleEntity, D extends AbstractVehicleDto> {
    boolean approve(D dto);

    E save(E entity);

    Optional<E> load(Long id);

    E fromDto(D dto);

    D toDto(E entity);

    Long getId(E entity);

    default E addAssessmentFromDto(E entity, D dto) {
        entity.getAssessments().add(new Assessment(dto.getValue()));
        return entity;
    }
}
