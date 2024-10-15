package com.park_track.repository;

import com.park_track.entity.Evaluated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluatedRepository extends JpaRepository<Evaluated, Long> {
	Optional<Evaluated> findByIdNumber(String idNumber); // Buscar evaluado por número de identificación
}
