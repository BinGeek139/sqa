package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("SELECT s " +
            "FROM Subject s JOIN Clazz c On c.subjectBySubjectId = s "+
            "WHERE c.id= :idClass")
    Subject findSubjectByClazzId(@Param("idClass") Long idClass);
}
