package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Semester {
    private Long id;
    private String name;
    private Integer year;
    private List<Clazz> clazzesById;

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
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id) && Objects.equals(name, semester.name) && Objects.equals(year, semester.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year);
    }

    @OneToMany(mappedBy = "semesterBySemesterId",fetch = FetchType.EAGER)
    public List<Clazz> getClazzesById() {
        return clazzesById;
    }

    public void setClazzesById(List<Clazz> clazzesById) {
        this.clazzesById = clazzesById;
    }
}
