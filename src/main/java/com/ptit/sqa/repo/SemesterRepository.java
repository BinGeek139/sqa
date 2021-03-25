package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
}
