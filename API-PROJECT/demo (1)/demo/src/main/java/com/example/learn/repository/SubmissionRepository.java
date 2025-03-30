package com.example.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Submission;


import jakarta.transaction.Transactional;
@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Integer>{
    @Query("SELECT s FROM Submission s")
    public List<Submission> getAllSubmissions();

    @Query("SELECT s FROM Submission s WHERE s.id = :id")
    public Submission getSubmissionByScore(@Param("score") float score);
    
    @Query("SELECT s FROM Submission s WHERE s.score = :score")
    Optional<Submission> findSubmissionByScore(@Param("score") float score);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (user, assessment, score, Submissiondate) VALUES (:user, :assessment, :score, :Submissiondate)", nativeQuery = true)
    void insertUserNative(@Param("user")String user,
      @Param("assessment")String assessment,
      @Param("score")float score,
      @Param("Submissiondate") String Submissiondate);
}
