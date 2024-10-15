package com.park_track.repository;

import com.park_track.entity.TypeOfTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfTestRepository extends JpaRepository<TypeOfTest, Long> {
	Optional<TypeOfTest> findByType(String type);
}
