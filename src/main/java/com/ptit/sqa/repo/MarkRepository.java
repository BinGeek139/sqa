package com.ptit.sqa.repo;

import com.ptit.sqa.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MarkRepository extends JpaRepository<Mark,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE mark m set mark =:mark where m.id = :id",
            nativeQuery = true)
    void update(@Param("mark") Float mark, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO mark (mark, class_student_id, spoint_id) "+
                    "VALUES (:mark, :classStudentId, :spointId) ", nativeQuery = true)
    void insert (Float mark, Long classStudentId, Long spointId);

    @Query(value ="SELECT * FROM mark m JOIN class_student cs ON m.class_student_id = cs.id "+
            "JOIN user u ON cs.user_id = u.id "+
            "JOIN class c ON cs.class_id = c.id "+
            "WHERE u.id = :userId AND m.spoint_id = :spointId AND c.id = :classId",
            nativeQuery = true)
    Mark findMarkByClassStudentId(@Param("userId") Long userId,
                                  @Param("spointId") Long spointId,
                                  @Param("classId") Long classId);
}
