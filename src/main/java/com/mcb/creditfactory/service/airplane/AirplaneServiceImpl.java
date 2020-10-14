package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Assessment;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.service.assessment.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeSet;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AssessmentService assessmentService;

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new AirplaneAdapter(dto)) == 0;
    }

    @Override
    public Airplane save(Airplane entity) {
        return airplaneRepository.save(entity);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        TreeSet<Assessment> assessments = new TreeSet<>();
        //При создании нового объекта обеспечения с переданным value
        Assessment assessment = new Assessment(dto.getValue());
        assessments.add(assessment);

        return new Airplane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getManufacturer(),
                dto.getFuelCapacity(),
                dto.getSeats(),
                assessments
        );
    }

    @Override
    public AirplaneDto toDto(Airplane entity) {
        return new AirplaneDto(
                entity.getId(),
                entity.getYear(),
                assessmentService.toDtoSet(entity.getValues()),
                entity.getBrand(),
                entity.getModel(),
                entity.getManufacturer(),
                entity.getFuelCapacity(),
                entity.getSeats()
        );
    }

    @Override
    public Long getId(Airplane entity) {
        return entity.getId();
    }
}
