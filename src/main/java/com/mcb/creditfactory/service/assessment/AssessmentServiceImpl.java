package com.mcb.creditfactory.service.assessment;

import com.mcb.creditfactory.dto.AssessmentDto;
import com.mcb.creditfactory.model.Assessment;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Override
    public Assessment fromDto(AssessmentDto dto) {
        return new Assessment(dto.getValue());
    }

    @Override
    public AssessmentDto toDto(Assessment assessment) {
        return new AssessmentDto(
                assessment.getValue(),
                assessment.getDate()
        );
    }

    @Override
    public Set<Assessment> fromDtoSet(Set<AssessmentDto> dtoSet) {
        return dtoSet.stream()
                .map(this::fromDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AssessmentDto> toDtoSet(Set<Assessment> assessmentSet) {
        return assessmentSet.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }
}
