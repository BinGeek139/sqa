package com.ptit.sqa.controller;

import com.ptit.sqa.common.Const;
import com.ptit.sqa.entity.ClassStudent;
import com.ptit.sqa.entity.Clazz;
import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.entity.Semester;
import com.ptit.sqa.model.SemesterResponse;
import com.ptit.sqa.model.StatisticRequest;
import com.ptit.sqa.model.StatisticResponse;
import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.repo.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class StatisticController {
    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    ClazzRepository clazzRepository;

    @GetMapping("statistic")
    public String view(Model model, StatisticRequest statisticRequest) {
        if (Objects.isNull(statisticRequest)) {
            statisticRequest = new StatisticRequest();
        }
        List<Semester> semesters = semesterRepository.findAll();
        List<SemesterResponse> semesterResponses = new ArrayList<>();
        List<Clazz> clazzList = clazzRepository.findAll();
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
                if (sumMarkForStudent >= Const.POINT_PASS_THE_SUBJECT) {
                    pass++;
                }
            }

            statisticResponse.setStudentPass(pass);
            statisticResponse.setStudentNotPass(classStudents.size() - pass);
            statisticResponse.setSum(classStudents.size());
        }
        model.addAttribute("statisticResponseList", semesterResponses);
        model.addAttribute("semesters", semesterResponses);
        model.addAttribute("statisticRequest", statisticRequest);
        return "statistic";

    }


}
