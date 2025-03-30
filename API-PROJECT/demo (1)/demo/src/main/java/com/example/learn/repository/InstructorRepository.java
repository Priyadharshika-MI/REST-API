package com.example.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Instructor;
import jakarta.transaction.Transactional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer>{
    @Query("SELECT i FROM Instructor i")
    public List<Instructor> getAllInstructors();

    @Query("SELECT i FROM Instructor i WHERE i.id = :id")
    public Instructor getInstructorByName(@Param("name") String name);
    
    @Query("SELECT i FROM Instructor i WHERE i.name = :name")
    Optional<Instructor> findInstructorByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, email, expertise) VALUES (:name, :email, :expertise)", nativeQuery = true)
    void insertUserNative(@Param("name")String name,
      @Param("email")String email,
      @Param("expertise")String expertise);
}
