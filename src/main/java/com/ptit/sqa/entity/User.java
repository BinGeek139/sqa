package com.ptit.sqa.entity;

import javax.persistence.*;
import java.util.List;
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
    @Column(name = "role")
    String role;
    @Column(name = "code")
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @OneToMany(mappedBy = "userByUserId")
    private  List<Clazz> clazzesById;
    @OneToMany(mappedBy = "userByUserId")
    private  List<ClassStudent> classStudentsById;
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


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public  List<Clazz> getClazzesById() {
        return clazzesById;
    }

    public void setClazzesById( List<Clazz> clazzesById) {
        this.clazzesById = clazzesById;
    }


    public  List<ClassStudent> getClassStudentsById() {
        return classStudentsById;
    }

    public void setClassStudentsById( List<ClassStudent> classStudentsById) {
        this.classStudentsById = classStudentsById;
    }
}
