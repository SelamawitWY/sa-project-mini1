package com.cs590.springbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String first;
    private String last;
    private Double gpa;

    @Transient
    @JsonIgnore
    private Integer age;

    private Date DOB;

    public Student() {
    }

    public Student(String first, String last, Double gpa, Integer age) {
        this.first = first;
        this.last = last;
        this.gpa = gpa;
        this.age = age;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Double getGpa() {
        return gpa;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gpa=" + gpa +
                ", dob=" + DOB +
                '}';
    }
}
