package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
    @Query(value = "SELECT * FROM semester ORDER BY id DESC LIMIT 0, 1", nativeQuery = true)
    Semester findActivatingSemester();
}
