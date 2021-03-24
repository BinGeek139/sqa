package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Mark {
    private Long id;
    private Integer mark;
    private Spoint spointBySpointId;
    private ClassStudent classStudentByClassStudentId;

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
    @Column(name = "Mark")
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark1 = (Mark) o;
        return Objects.equals(id, mark1.id) && Objects.equals(mark, mark1.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark);
    }

    @ManyToOne
    @JoinColumn(name = "SpointId", referencedColumnName = "Id", nullable = false)
    public Spoint getSpointBySpointId() {
        return spointBySpointId;
    }

    public void setSpointBySpointId(Spoint spointBySpointId) {
        this.spointBySpointId = spointBySpointId;
    }

    @ManyToOne
    @JoinColumn(name = "ClassStudentId", referencedColumnName = "Id", nullable = false)
    public ClassStudent getClassStudentByClassStudentId() {
        return classStudentByClassStudentId;
    }

    public void setClassStudentByClassStudentId(ClassStudent classStudentByClassStudentId) {
        this.classStudentByClassStudentId = classStudentByClassStudentId;
    }
}
