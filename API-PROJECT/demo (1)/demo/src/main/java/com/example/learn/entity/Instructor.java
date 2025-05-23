package com.example.learn.entity;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instructor {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String email;
    String expertise;
    public Instructor()
    {

    }
    public Instructor(int id, String name, String email, String expertise) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    
}
