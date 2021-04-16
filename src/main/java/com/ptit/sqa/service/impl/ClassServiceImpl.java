package com.ptit.sqa.service.impl;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAllActivatingClass(Long semId, Long UserId){
        String sql = "SELECT class.name as cname, subject.name as sname from class" +
                " JOIN Subject ON Class.Subject_id = Subject.id" +
                " WHERE Class.semester_id = ? AND Class.user_id = ?; ";
        List<Map<String, Object>> classList= jdbcTemplate.queryForList(sql,
                new Object[] {semId, UserId});
        return classList;
    }

    public Subject findSubjectByClass(Long classId){

        String sql = "Select subject.id, subject.name from subject join class on subject.id = class.subject_id where class.id= ?";
        Subject subject = (Subject) jdbcTemplate.queryForObject(
                sql, new Object[] { classId }, new BeanPropertyRowMapper(Subject.class));
        return subject;
    }

    public Clazz findClassByName(String classname){
        String sql = "SELECT * FROM CLASS WHERE name=?";
        Clazz clazz = (Clazz) jdbcTemplate.queryForObject(
                sql, new Object[] {classname}, new BeanPropertyRowMapper(Clazz.class));
        return clazz;
    }
}
