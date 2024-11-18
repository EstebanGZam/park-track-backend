package com.park_track.repository;

import com.park_track.entity.ObservationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationNoteRepository extends JpaRepository<ObservationNote, Long> {
    List<ObservationNote> findBySampleIdAndSampleEvaluatedIdAndSampleTestTypeId(Long sampleId, Long evaluatedId, Long testTypeId);
}