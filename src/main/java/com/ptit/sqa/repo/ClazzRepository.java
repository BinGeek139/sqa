package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClazzRepository extends JpaRepository<Clazz,Integer> {

    List<Clazz> findByNameContains(String name);

    @Query("FROM Clazz c where c.semesterBySemesterId.id= ?1")
    List<Clazz> findByClazzId(long classId);
}
