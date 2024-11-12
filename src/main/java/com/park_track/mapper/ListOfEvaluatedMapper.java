package com.park_track.mapper;

import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;
import com.park_track.entity.Evaluated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListOfEvaluatedMapper {
    @Mapping(target = "typeOfEvaluated", source = "typeOfEvaluated.type")
    EvaluatedResponseDTO toResponseDTO(Evaluated evaluated);
}