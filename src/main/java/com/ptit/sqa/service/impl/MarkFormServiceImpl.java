package com.ptit.sqa.service.impl;

import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.entity.Spoint;
import com.ptit.sqa.entity.User;
import com.ptit.sqa.model.MarkForm;
import com.ptit.sqa.model.MarkResponse;
import com.ptit.sqa.repo.MarkRepository;
import com.ptit.sqa.repo.SpointRepository;
import com.ptit.sqa.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarkFormServiceImpl implements MarkFormService{

    @Autowired
    ClassStudentServiceImpl classStudentService;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SpointRepository spointRepository;

    public void findClassDetail(MarkForm markForm){
        Clazz clazz = markForm.getClazz();
        Long subjectId = subjectRepository.findSubjectByClazzId(clazz.getId()).getId();

        List<Spoint> spoints = spointRepository.findSpointBySubjectId(subjectId);
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
                    mark =  markRepository.findMarkByClassStudentId(user.getId(),
                            spoint.getId(),
                            clazz.getId());
                }catch (EmptyResultDataAccessException e){
                    mark = new Mark();
                }

                markResponse.addMark(mark);
            }
            markForm.add(markResponse);
        }
    }
}
