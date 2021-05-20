package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClazzRepository extends JpaRepository<Clazz,Long> {

    Clazz findClassByName(@Param("className") String className);

    @Query(value = "SELECT c.name as cname, s.name as sname from class c "+
            "JOIN subject s ON c.subject_id = s.id "+
            "WHERE c.semester_id = :semesterId AND c.user_id = :userId",
            nativeQuery = true)
    List<Object[]> findActivatingClass(@Param("semesterId") Long semesterId, @Param("userId") Long userId);

    @Query(value = "SELECT cs.id AS csid, u.id AS uid, u.full_name AS uname FROM class c " +
            "JOIN class_student cs ON cs.class_id = c.id "+
            "JOIN user u ON cs.user_id = u.id "+
            "WHERE c.id = :classId",
            nativeQuery = true)
    List<Object[]> findStudentByClass(@Param("classId") Long classId);
}
