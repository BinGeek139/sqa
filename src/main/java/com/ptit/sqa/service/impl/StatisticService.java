package com.ptit.sqa.service.impl;

import com.ptit.sqa.common.Const;
import com.ptit.sqa.entity.ClassStudent;
import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.model.StatisticRequest;
import com.ptit.sqa.model.StatisticResponse;
import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.repo.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    ClazzRepository clazzRepository;

    public List<StatisticResponse> statisticMark(StatisticRequest statisticRequest){

        List<Clazz> clazzList = clazzRepository.findByClazzId(statisticRequest.getIdSemester());
        List<StatisticResponse> statisticResponses=new ArrayList<>();
        for (int i = 0; i < clazzList.size(); i++) {
            Clazz clazz = clazzList.get(i);
            StatisticResponse statisticResponse = new StatisticResponse();
            statisticResponse.setIndex(i);
            statisticResponse.setCodeClass(clazz.getCode());
            statisticResponse.setTeacher(clazz.getUserByUserId().getFullName());
            statisticResponse.setNameSubject(clazz.getSubjectBySubjectId().getName());
            List<ClassStudent> classStudents = clazz.getClassStudentsById();
            int pass = 0;
            for (ClassStudent classStudent : classStudents) {
                List<Mark> marks = classStudent.getMarksById();
                Double sumMarkForStudent = 0d;
                for (Mark mark : marks) {
                    sumMarkForStudent += mark.getMark() * mark.getSpointBySpointId().getPercent() / 100;
                }
                sumMarkForStudent = Math.floor(sumMarkForStudent);
                if (sumMarkForStudent > Const.POINT_PASS_THE_SUBJECT) {
                    pass++;
                }
            }

            statisticResponse.setStudentPass(pass);
            statisticResponse.setStudentNotPass(classStudents.size() - pass);
            statisticResponse.setSum(classStudents.size());
            statisticResponses.add(statisticResponse);

        }
        return statisticResponses;
    }
}
