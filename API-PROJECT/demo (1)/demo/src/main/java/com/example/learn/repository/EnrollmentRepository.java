package com.example.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Enrollment;

import jakarta.transaction.Transactional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer>{
    @Query("SELECT e FROM Enrollment e")
    public List<Enrollment> getAllEnrollments();

    @Query("SELECT e FROM Enrollment e WHERE e.id = :id")
    public Enrollment getEnrollmentByProgressPercentage(@Param("progressPercentage") float progressPercentage);
    
    @Query("SELECT e FROM Enrollment e WHERE e.progressPercentage = :progressPercentage")
    Optional<Enrollment> findEnrollmentByProgressPercentage(@Param("progressPercentage") float progressPercentage);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (user, course, progressPercentage, Enrollmentdate) VALUES (:user, :course, :progressPercentage, :Enrollmentdate)", nativeQuery = true)
    void insertUserNative(@Param("user")String user,
      @Param("course")String course,
      @Param("progressPercentage")float progressPercentage,
      @Param("Enrollmentdate")String Enrollmentdate);
}
