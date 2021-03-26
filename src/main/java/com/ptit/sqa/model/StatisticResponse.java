package com.ptit.sqa.model;

public class StatisticResponse {
    Integer index;
    String nameSubject;
    String codeClass;
    String teacher;
    Integer studentPass;
    Integer studentNotPass;
    Integer sum;

    public StatisticResponse() {
    }

    public StatisticResponse(Integer index, String nameSubject, String codeClass, String teacher, Integer studentPass, Integer studentNotPass, Integer sum) {
        this.index = index;
        this.nameSubject = nameSubject;
        this.codeClass = codeClass;
        this.teacher = teacher;
        this.studentPass = studentPass;
        this.studentNotPass = studentNotPass;
        this.sum = sum;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getCodeClass() {
        return codeClass;
    }

    public void setCodeClass(String codeClass) {
        this.codeClass = codeClass;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentPass() {
        return studentPass;
    }

    public void setStudentPass(Integer studentPass) {
        this.studentPass = studentPass;
    }

    public Integer getStudentNotPass() {
        return studentNotPass;
    }

    public void setStudentNotPass(Integer studentNotPass) {
        this.studentNotPass = studentNotPass;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
