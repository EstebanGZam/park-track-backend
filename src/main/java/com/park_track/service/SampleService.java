package com.park_track.service;
import com.park_track.entity.ObservationNote;
import com.park_track.dto.sample.SampleListDTO;
import com.park_track.entity.Sample;
import com.park_track.repository.ObservationNoteRepository;
import com.park_track.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SampleService {
    private final SampleRepository sampleRepository;
    private final ObservationNoteRepository observationNoteRepository;

    @Autowired
    public SampleService(SampleRepository sampleRepository, ObservationNoteRepository observationNoteRepository) {
        this.sampleRepository = sampleRepository;
        this.observationNoteRepository = observationNoteRepository;
    }
    public Optional<Sample> getSampleByIds(Long evaluatedId, Long id, Long testTypeId) {
        return sampleRepository.findByEvaluatedIdAndIdAndTestTypeId(evaluatedId, id, testTypeId);
    }
    @Transactional
    public boolean deleteSampleIfExists(Long evaluatedId, Long id, Long testTypeId) {
        Optional<Sample> sample = getSampleByIds(evaluatedId, id, testTypeId);
        if (sample.isPresent()) {
            sampleRepository.delete(sample.get());
            return true;
        }
        return false;
    }

    public List<SampleListDTO> getSamplesByEvaluatedId(Long evaluatedId) {
        return sampleRepository.findByEvaluatedId(evaluatedId).stream()
                .map(sample -> {
                    List<String> comments = observationNoteRepository.findBySampleIdAndSampleEvaluatedIdAndSampleTestTypeId(
                                    sample.getId(), sample.getEvaluatedId(), sample.getTestTypeId())
                            .stream()
                            .map(ObservationNote::getDescription)
                            .collect(Collectors.toList());

                    return SampleListDTO.builder()
                            .id(sample.getId())
                            .date(sample.getDate())
                            .typeOfTestId(sample.getTestTypeId())
                            .description(sample.getAptitudeForTheTest())
                            .onOffState(sample.getOnOffState())
                            .rawData(sample.getRawData())
                            .aptitudeForTheTest(sample.getAptitudeForTheTest())
                            .comments(comments)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public Sample getLastSampleByEvaluatedId(Long evaluatedId) {
        return sampleRepository.findTopByEvaluatedIdOrderByDateDesc(evaluatedId);
    }
}