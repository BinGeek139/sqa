package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Subject {
    private Long id;
    private String name;
    private  List<Clazz> clazzesById;
    private  List<Spoint> spointsById;

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
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "subjectBySubjectId")
    public  List<Clazz> getClazzesById() {
        return clazzesById;
    }

    public void setClazzesById( List<Clazz> clazzesById) {
        this.clazzesById = clazzesById;
    }

    @OneToMany(mappedBy = "subjectBySubjectId")
    public  List<Spoint> getSpointsById() {
        return spointsById;
    }

    public void setSpointsById( List<Spoint> spointsById) {
        this.spointsById = spointsById;
    }
}
