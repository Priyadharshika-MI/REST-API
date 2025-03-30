package com.example.learn.entity;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.*;
import java.util.Date;
@Entity
public class LearningGoal{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String goalName;
    Date targetCompletionDate;
    public LearningGoal()
    {

    }
    public LearningGoal(int id, String goalName, Date targetCompletionDate, User user) {
        this.id = id;
        this.goalName = goalName;
        this.targetCompletionDate = targetCompletionDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGoalName() {
        return goalName;
    }
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }
    public Date getTargetCompletionDate() {
        return targetCompletionDate;
    }
    public void setTargetCompletionDate(Date targetCompletionDate) {
        this.targetCompletionDate = targetCompletionDate;
    }
}

