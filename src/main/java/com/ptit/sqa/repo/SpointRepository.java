package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Spoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpointRepository extends JpaRepository<Spoint,Long> {
    @Query(value = "SELECT * FROM spoint s where s.subject_id = :subjectId", nativeQuery = true)
    List<Spoint> findSpointBySubjectId(@Param("subjectId") Long subjectId);
}
