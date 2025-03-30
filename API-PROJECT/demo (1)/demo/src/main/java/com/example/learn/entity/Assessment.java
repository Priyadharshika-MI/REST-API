package com.example.learn.entity;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assessment {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String course;
    int totalMarks;
    public Assessment()
    {

    }
    public Assessment(int id, String title, String course, int totalMarks) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.totalMarks = totalMarks;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getTotalMarks() {
        return totalMarks;
    }
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
