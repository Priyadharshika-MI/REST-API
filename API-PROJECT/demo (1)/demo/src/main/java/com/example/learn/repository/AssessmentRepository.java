package com.example.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Assessment;

import jakarta.transaction.Transactional;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer>{
    @Query("SELECT a FROM Assessment a")
    public List<Assessment> getAllCourses();

    @Query("SELECT a FROM Assessment a WHERE a.id = :id")
    public Assessment getAssessmentByCourse(@Param("course") String course);
    
    @Query("SELECT a FROM Assessment a WHERE a.course = : course")
    Optional<Assessment> findAssessmentByCourse(@Param("course") String course);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (title, course, totalMarks) VALUES (:title, :course, :totalMarks)", nativeQuery = true)
    void insertUserNative(@Param("title")String title,
      @Param("course")String course,
      @Param("totalMarks")int totalMarks);
}

