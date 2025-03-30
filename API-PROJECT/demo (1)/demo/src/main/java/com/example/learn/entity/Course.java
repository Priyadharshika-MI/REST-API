package com.example.learn.entity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")  
public class Course {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column(nullable = false)
    private String name;
    //@Column(length = 1000)
    private String description;
    //@Column(nullable = false)
    private String language;
    //@Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;

    public Course() 
    {

    }
    public Course(Integer id, String name, String description, String language, String difficultyLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name); // Ensure it takes a String, not float
}

    // Getters and Setters
    // public Integer getId() { return id; }
    // public void setId(Integer id) { this.id = id; }

    // public String getName() { return name; }
    // public void setName(String name) { this.name = name; }

    // public String getDescription() { return description; }
    // public void setDescription(String description) { this.description = description; }

    // public String getLanguage() { return language; }
    // public void setLanguage(String language) { this.language = language; }

    // public String getDifficultyLevel() { return difficultyLevel; }
    // public void setDifficultyLevel(String difficultyLevel) { this.difficultyLevel = difficultyLevel; }
    
}
