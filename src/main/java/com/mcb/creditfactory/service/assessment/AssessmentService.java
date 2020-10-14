package com.mcb.creditfactory.service.assessment;

import com.mcb.creditfactory.dto.AssessmentDto;
import com.mcb.creditfactory.model.Assessment;

import java.util.Set;

public interface AssessmentService {
    Assessment fromDto(AssessmentDto dto);

    AssessmentDto toDto(Assessment assessment);

    Set<Assessment> fromDtoSet(Set<AssessmentDto> dtoSet);

    Set<AssessmentDto> toDtoSet(Set<Assessment> assessmentSet);
}
