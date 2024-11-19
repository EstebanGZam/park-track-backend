package com.park_track.repository;

import com.park_track.entity.Evaluator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluatorRepository extends JpaRepository<Evaluator, Long> {


	void deleteByIdNumber(String idNumber);
    

	boolean existsByIdNumber(String idNumber);
}
