package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Table
@Entity(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    String email;
    @Column(name = "role")
    String role;
    private Collection<Clazz> clazzesById;
    private Collection<ClassStudent> classStudentsById;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Clazz> getClazzesById() {
        return clazzesById;
    }

    public void setClazzesById(Collection<Clazz> clazzesById) {
        this.clazzesById = clazzesById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ClassStudent> getClassStudentsById() {
        return classStudentsById;
    }

    public void setClassStudentsById(Collection<ClassStudent> classStudentsById) {
        this.classStudentsById = classStudentsById;
    }
}
