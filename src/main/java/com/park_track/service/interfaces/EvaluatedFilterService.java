package com.park_track.service.interfaces;

import com.park_track.dto.evaluated_filter.EvaluatedFilterDTO;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;

import java.util.List;

public interface EvaluatedFilterService {
    List<EvaluatedResponseDTO> getAllEvaluated(EvaluatedFilterDTO filterDTO);
}