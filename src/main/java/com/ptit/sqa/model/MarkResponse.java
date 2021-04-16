package com.ptit.sqa.model;

import com.ptit.sqa.entity.Mark;
import com.ptit.sqa.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MarkResponse {
    private Long classStudentId;
    private User student;
    private List<Mark> markList;

    public Long getClassStudentId() {
        return classStudentId;
    }

    public void setClassStudentId(Long classStudentId) {
        this.classStudentId = classStudentId;
    }

    public MarkResponse(){
        this.markList = new ArrayList<>();
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void addMark(Mark mark){
        this.markList.add(mark);
    }

}
