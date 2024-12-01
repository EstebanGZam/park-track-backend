package com.park_track.service;

import com.park_track.dto.sample.SampleUpdateRequestDTO;
import com.park_track.entity.ObservationNote;
import com.park_track.dto.sample.CreateObservationDTO;
import com.park_track.dto.sample.SampleListDTO;
import com.park_track.entity.Sample;
import com.park_track.entity.SampleId;
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
                    List<String> observationNotes = observationNoteRepository
                            .findBySampleIdAndSampleEvaluatedIdAndSampleTestTypeId(
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
                            .observationNotes(observationNotes)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public Sample getLastSampleByEvaluatedId(Long evaluatedId) {
        return sampleRepository.findTopByEvaluatedIdOrderByDateDesc(evaluatedId);
    }

    @Transactional
    public void updateSample(Long evaluatedId, Long sampleId, Long testTypeId, SampleUpdateRequestDTO updateRequest) {
        if (!updateRequest.isValid()) {
            throw new IllegalArgumentException("If state is OFF, a comment is required.");
        }

        SampleId sampleIdKey = new SampleId(sampleId, evaluatedId, testTypeId);
        Sample sample = sampleRepository.findById(sampleIdKey)
                .orElseThrow(() -> new IllegalArgumentException("Sample not found"));

        sample.setOnOffState(updateRequest.getOnOffState());
        sample.setAptitudeForTheTest(updateRequest.getAptitudeForTheTest());
        sampleRepository.save(sample);

        List<ObservationNote> existingNotes = observationNoteRepository
                .findBySampleIdAndSampleEvaluatedIdAndSampleTestTypeId(
                        sampleId, evaluatedId, testTypeId);

        observationNoteRepository.deleteAll(existingNotes);

        if (updateRequest.getNotes() != null && !updateRequest.getNotes().isEmpty()) {
            List<ObservationNote> newNotes = updateRequest.getNotes().stream()
                    .map(note -> ObservationNote.builder()
                            .sample(sample)
                            .description(note)
                            .build())
                    .collect(Collectors.toList());
            observationNoteRepository.saveAll(newNotes);
        }

    }

    @Transactional
    public void createObservation(Long evaluatedId, Long sampleId, Long testTypeId, CreateObservationDTO observation) {

        SampleId sampleIdKey = new SampleId(sampleId, evaluatedId, testTypeId);
        Sample sample = sampleRepository.findById(sampleIdKey)
                .orElseThrow(() -> new IllegalArgumentException("Sample not found"));

        ObservationNote observationNote = ObservationNote.builder()
                .sample(sample)
                .description(observation.getDescription())
                .build();

        observationNoteRepository.save(observationNote);
    }

    public void createObservationToLastSample(Long evaluatedID, Long testTypeId, CreateObservationDTO observation) {
        Sample sample = sampleRepository.findLatestSampleByEvaluatedIdAndTestTypeId(evaluatedID, testTypeId).get();
        if (sample != null) {
            ObservationNote observationNote = ObservationNote.builder()
                    .sample(sample)
                    .description(observation.getDescription())
                    .build();
            observationNoteRepository.save(observationNote);
        } else {
            throw new IllegalArgumentException();
        }
    }
}