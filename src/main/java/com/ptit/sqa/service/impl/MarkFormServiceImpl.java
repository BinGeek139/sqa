package com.ptit.sqa.service.impl;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.entity.Spoint;
import com.ptit.sqa.entity.User;
import com.ptit.sqa.model.MarkForm;
import com.ptit.sqa.model.MarkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarkFormServiceImpl {

    @Autowired
    SpointServiceImpl spointService;

    @Autowired
    ClassStudentServiceImpl classStudentService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void findClassDetail(MarkForm markForm){
        Clazz clazz = markForm.getClazz();
        List<Spoint> spoints = spointService.findAllSpointByClass(markForm.getClazz().getId());
        markForm.setSpoints(spoints);
        List<Map<String, Object>> rows = classStudentService.findAllStudentByClass(clazz.getId());

        for (Map row : rows){
            MarkResponse markResponse = new MarkResponse();
            markResponse.setClassStudentId((Long) row.get("cid"));

            User user = new User();
            user.setId((Long) row.get("uid"));
            user.setFullName((String) row.get("uname"));

            markResponse.setStudent(user);

            for (Spoint spoint : spoints){

                Mark mark;
                try{
                    String sql = "select mark.* from mark " +
                            "join class_student on class_student.id = mark.class_student_id " +
                            "join user on  user.id = class_student.user_id " +
                            "where user.id = ? and mark.spoint_id = ? ";
                    mark= jdbcTemplate.queryForObject(sql,
                            new Object[] {user.getId(), spoint.getId()},
                            new BeanPropertyRowMapper<>(Mark.class));
                }catch (EmptyResultDataAccessException e){
                    mark = new Mark();
                }

                markResponse.addMark(mark);
            }
            markForm.add(markResponse);
        }
    }
}
