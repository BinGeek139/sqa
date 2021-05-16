package com.ptit.sqa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassStudentServiceImpl implements ClassStudentService{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAllStudentByClass(Long classId){
        String sql = " select class_student.id as cid, user.id as uid, user.full_name as uname from class_student " +
                "join user on class_student.user_id = user.id " +
                "join class on class_student.class_id = class.id " +
                "where class.id = ? order by user.id";

        List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql,
                new Object[] {classId});

        return rows;
    }
}
