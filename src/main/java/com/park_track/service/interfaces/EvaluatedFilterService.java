package com.park_track.service.interfaces;

import com.park_track.dto.evaluatedFilter.EvaluatedFilterDTO;
import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;

import java.util.List;

public interface EvaluatedFilterService {
    List<EvaluatedResponseDTO> getAllEvaluated(EvaluatedFilterDTO filterDTO);
}