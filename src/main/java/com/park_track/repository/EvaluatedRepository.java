package com.park_track.repository;

import com.park_track.entity.Evaluated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluatedRepository extends JpaRepository<Evaluated, Long> {
	Optional<Evaluated> findByIdNumber(String idNumber); // Search evaluated by ID number

    boolean existsByIdNumber(String id_number);

    void deleteByIdNumber(String id_number);

    @Query("SELECT e FROM Evaluated e WHERE e.typeOfEvaluated.type = :type")
    List<Evaluated> findByTypeOfEvaluated(@Param("type") String type);

    @Query("SELECT e FROM Evaluated e WHERE " +
            "(COALESCE(:startDate, NULL) IS NULL OR e.dateOfBirth >= :startDate) AND " +
            "(COALESCE(:endDate, NULL) IS NULL OR e.dateOfBirth <= :endDate) AND " +
            "(COALESCE(:nameRangeStart, '') = '' OR e.firstName >= :nameRangeStart) AND " +
            "(COALESCE(:nameRangeEnd, '') = '' OR e.firstName <= :nameRangeEnd) AND " +
            "(COALESCE(:typeOfEvaluated, '') = '' OR e.typeOfEvaluated.type = :typeOfEvaluated) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'firstName' AND :sortDirection = 'asc' THEN e.firstName END ASC, " +
            "CASE WHEN :sortBy = 'firstName' AND :sortDirection = 'desc' THEN e.firstName END DESC, " +
            "CASE WHEN :sortBy = 'lastName' AND :sortDirection = 'asc' THEN e.lastName END ASC, " +
            "CASE WHEN :sortBy = 'lastName' AND :sortDirection = 'desc' THEN e.lastName END DESC, " +
            "CASE WHEN :sortBy = 'dateOfBirth' AND :sortDirection = 'asc' THEN e.dateOfBirth END ASC, " +
            "CASE WHEN :sortBy = 'dateOfBirth' AND :sortDirection = 'desc' THEN e.dateOfBirth END DESC")
    List<Evaluated> findAllFilteredAndSorted(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("nameRangeStart") String nameRangeStart,
            @Param("nameRangeEnd") String nameRangeEnd,
            @Param("typeOfEvaluated") String typeOfEvaluated,
            @Param("sortBy") String sortBy,
            @Param("sortDirection") String sortDirection
    );

}