package com.ptit.sqa.service.impl;

import com.ptit.sqa.entity.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MarkServiceImpl {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Mark> findAllMarkByClass(Long classId){
        String sql = " select class_student.user_id, mark.mark, class_student_id from class" +
                "join class_student on class_student.class_id = class.id " +
                "join mark on  mark.class_student_id = class_student.id " +
                "where class.id = ? order by class_student";

        List<Mark> marks = new ArrayList<>();
        List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql,
                new Object[] {classId});
        for (Map row : rows){
            Mark mark = new Mark();
            mark.setId((Long) row.get("id"));
            marks.add(mark);
        }

        return marks;
    }

    public void insert(Float mark, Long class_student_id, Long spoint_id){
        String sql = "INSERT INTO Mark (mark, class_student_id, spoint_id) VALUES (?, ?, ?)";
        int id  = jdbcTemplate.update(sql, mark, class_student_id, spoint_id);
    }

    public void update(Float mark, Long markId){
        String sql = "UPDATE Mark SET mark = ? where id = ?";
        int id = jdbcTemplate.update(sql, mark, markId);
    }

}
