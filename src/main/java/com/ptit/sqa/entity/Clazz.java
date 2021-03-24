package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "mark_manage", catalog = "")
public class Clazz {
    private Long id;
    private String name;
    private Subject subjectBySubjectId;
    private Semester semesterBySemesterId;
    private User userByUserId;
    private Collection<ClassStudent> classStudentsById;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(id, clazz.id) && Objects.equals(name, clazz.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id", nullable = false)
    public Subject getSubjectBySubjectId() {
        return subjectBySubjectId;
    }

    public void setSubjectBySubjectId(Subject subjectBySubjectId) {
        this.subjectBySubjectId = subjectBySubjectId;
    }

    @ManyToOne
    @JoinColumn(name = "SemesterId", referencedColumnName = "Id", nullable = false)
    public Semester getSemesterBySemesterId() {
        return semesterBySemesterId;
    }

    public void setSemesterBySemesterId(Semester semesterBySemesterId) {
        this.semesterBySemesterId = semesterBySemesterId;
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "clazzByClassId")
    public Collection<ClassStudent> getClassStudentsById() {
        return classStudentsById;
    }

    public void setClassStudentsById(Collection<ClassStudent> classStudentsById) {
        this.classStudentsById = classStudentsById;
    }
}
