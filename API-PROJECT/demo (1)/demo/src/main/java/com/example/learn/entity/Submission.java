package com.example.learn.entity;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Submission {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String user;
    String assessment;
    float score;
    Date SubmissionDate;
    public Submission()
    {

    }
    public Submission(int id, String user, String assessment, float score, Date submissionDate) {
        this.id = id;
        this.user = user;
        this.assessment = assessment;
        this.score = score;
        SubmissionDate = submissionDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getAssessment() {
        return assessment;
    }
    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public Date getSubmissionDate() {
        return SubmissionDate;
    }
    public void setSubmissionDate(Date submissionDate) {
        SubmissionDate = submissionDate;
    }
}
