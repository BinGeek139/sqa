package com.ptit.sqa.entity;


import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "class_student", schema = "mark_manage", catalog = "")
public class ClassStudent {
    private Long id;
    private User userByUserId;
    private Clazz clazzByClassId;
    private Collection<Mark> marksById;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassStudent that = (ClassStudent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "ClassId", referencedColumnName = "Id", nullable = false)
    public Clazz getClazzByClassId() {
        return clazzByClassId;
    }

    public void setClazzByClassId(Clazz clazzByClassId) {
        this.clazzByClassId = clazzByClassId;
    }

    @OneToMany(mappedBy = "classStudentByClassStudentId")
    public Collection<Mark> getMarksById() {
        return marksById;
    }

    public void setMarksById(Collection<Mark> marksById) {
        this.marksById = marksById;
    }
}
