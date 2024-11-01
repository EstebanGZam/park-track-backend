package com.park_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.park_track.entity.Sex;

@Repository
public interface SexRepository extends JpaRepository<Sex, Long> {

    @Query("SELECT * FROM Sex s WHERE s.sex = ?1")
    Sex findSexByString(String sex);
}
