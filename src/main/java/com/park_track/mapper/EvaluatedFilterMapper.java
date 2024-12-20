package com.park_track.mapper;

import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.entity.Evaluated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvaluatedFilterMapper {
    @Mapping(target = "typeOfEvaluated", source = "typeOfEvaluated.type")
    EvaluatedResponseDTO toResponseDTO(Evaluated evaluated);
}