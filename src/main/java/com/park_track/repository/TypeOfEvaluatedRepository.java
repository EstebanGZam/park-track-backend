package com.park_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.park_track.entity.TypeOfEvaluated;

@Repository
public interface TypeOfEvaluatedRepository extends JpaRepository<TypeOfEvaluated,Long>{

    @Query("SELECT t FROM TypeOfEvaluated t WHERE t.type = ?1")
    TypeOfEvaluated findTypeOfEvaluatedByString(String typeOfEvaluated);
} 