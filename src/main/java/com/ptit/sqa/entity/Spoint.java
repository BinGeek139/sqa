package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Spoint {
    private Long id;
    private String name;
    private Integer percent;
    private Collection<Mark> marksById;
    private Subject subjectBySubjectId;

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

    @Basic
    @Column(name = "percent")
    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spoint spoint = (Spoint) o;
        return Objects.equals(id, spoint.id) && Objects.equals(name, spoint.name) && Objects.equals(percent, spoint.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percent);
    }

    @OneToMany(mappedBy = "spointBySpointId")
    public Collection<Mark> getMarksById() {
        return marksById;
    }

    public void setMarksById(Collection<Mark> marksById) {
        this.marksById = marksById;
    }

    @ManyToOne
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id", nullable = false)
    public Subject getSubjectBySubjectId() {
        return subjectBySubjectId;
    }

    public void setSubjectBySubjectId(Subject subjectBySubjectId) {
        this.subjectBySubjectId = subjectBySubjectId;
    }
}
