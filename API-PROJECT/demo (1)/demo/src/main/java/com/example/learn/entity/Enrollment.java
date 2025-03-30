// package com.example.learn.entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import java.util.Date;
// @Entity
// public class Enrollment {
//     @Id
//     // @GeneratedValue(strategy = GenerationType.IDENTITY)
//     int id;
//     String user;
//     String course;
//     float progressPercentage;
//     Date enrollmentdate;
//     public Enrollment()
//     {

//     }
//     public Enrollment(int id, String user, String course, float progressPercentage, Date enrollmentdate) {
//         this.id = id;
//         this.user = user;
//         this.course = course;
//         this.progressPercentage = progressPercentage;
//         this.enrollmentdate = enrollmentdate;
//     }
//     public int getId() {
//         return id;
//     }
//     public void setId(int id) {
//         this.id = id;
//     }
//     public String getUser() {
//         return user;
//     }
//     public void setUser(String user) {
//         this.user = user;
//     }
//     public String getCourse() {
//         return course;
//     }
//     public void setCourse(String course) {
//         this.course = course;
//     }
//     public float getProgressPercentage() {
//         return progressPercentage;
//     }
//     public void setProgressPercentage(float progressPercentage) {
//         this.progressPercentage = progressPercentage;
//     }
//     public Date getEnrollmentdate() {
//         return enrollmentdate;
//     }
//     public void setEnrollmentDate(Date enrollmentdate) {  
//         this.enrollmentdate = enrollmentdate;
//     }
// }
package com.example.learn.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String course;
    private float progressPercentage;
    private Date enrollmentDate;

    // Constructors
    public Enrollment() {}

    public Enrollment(User user, String course, float progressPercentage, Date enrollmentDate) {
        this.user = user;
        this.course = course;
        this.progressPercentage = progressPercentage;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(float progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
