package com.park_track.service.impl;

import com.park_track.dto.evaluated_filter.EvaluatedFilterDTO;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.mapper.EvaluatedFilterMapper;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.service.interfaces.EvaluatedFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluatedFilterServiceImpl implements EvaluatedFilterService {
    private final EvaluatedRepository evaluatedRepository;
    private final EvaluatedFilterMapper evaluatedMapper;

    @Override
    public List<EvaluatedResponseDTO> getAllEvaluated(EvaluatedFilterDTO filterDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            if (filterDTO.getStartDate() != null && !filterDTO.getStartDate().isEmpty()) {
                startDate = dateFormat.parse(filterDTO.getStartDate());
            }
            if (filterDTO.getEndDate() != null && !filterDTO.getEndDate().isEmpty()) {
                endDate = dateFormat.parse(filterDTO.getEndDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return evaluatedRepository.findAllFilteredAndSorted(
                        startDate,
                        endDate,
                        filterDTO.getNameRangeStart(),
                        filterDTO.getNameRangeEnd(),
                        filterDTO.getTypeOfEvaluated(),
                        filterDTO.getSortBy(),
                        filterDTO.getSortDirection()
                ).stream()
                .map(evaluatedMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}