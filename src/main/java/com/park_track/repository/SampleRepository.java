package com.park_track.repository;

import com.park_track.entity.Sample;
import com.park_track.entity.SampleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface SampleRepository extends JpaRepository<Sample, SampleId> {

	List<Sample> findByEvaluatedId(Long evaluatedId);

	Sample findTopByEvaluatedIdOrderByDateDesc(Long evaluatedId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO samples (evaluated_id, id, test_type_id, on_off_state, date, aptitude_for_the_test, raw_data) " +
			"VALUES (:evaluatedId, :id, :testTypeId, :onOffState, :date, :aptitudeForTheTest, CAST(:rawData AS jsonb))",
			nativeQuery = true)
	void insertSampleWithJsonbCast(@Param("evaluatedId") Long evaluatedId,
								   @Param("id") Long id,
								   @Param("testTypeId") Long testTypeId,
								   @Param("onOffState") String onOffState,
								   @Param("date") Timestamp date,
								   @Param("aptitudeForTheTest") String aptitudeForTheTest,
								   @Param("rawData") String rawData);

	Optional<Sample> findByEvaluatedIdAndIdAndTestTypeId(Long evaluatedId, Long id, Long testTypeId);
}