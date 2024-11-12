package com.park_track.service.interfaces;

import com.park_track.dto.evaluated.EvaluatedResponseDTO;

import java.util.List;

public interface ListOfEvaluatedService {
    List<EvaluatedResponseDTO> getAllEvaluated();
}