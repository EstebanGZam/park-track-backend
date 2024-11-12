package com.park_track.service.impl;

import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.mapper.ListOfEvaluatedMapper;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.service.interfaces.ListOfEvaluatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListOfEvaluatedServiceImpl implements ListOfEvaluatedService {
    private final EvaluatedRepository evaluatedRepository;
    private final ListOfEvaluatedMapper evaluatedMapper;

    @Override
    public List<EvaluatedResponseDTO> getAllEvaluated() {
        return evaluatedRepository.findAll().stream()
                .map(evaluatedMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}