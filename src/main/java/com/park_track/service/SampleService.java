package com.park_track.service;

import com.park_track.entity.Sample;
import com.park_track.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    @Autowired
    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
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
}