package com.park_track.service.interfaces;

import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;

import java.util.List;

public interface ListOfEvaluatedService {
    List<EvaluatedResponseDTO> getAllEvaluated();
}