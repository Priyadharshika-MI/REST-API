package com.example.learn.repository;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.LearningGoal;

import jakarta.transaction.Transactional;

@Repository
public interface LearningGoalRepository extends JpaRepository<LearningGoal, Integer> {

    @Query("SELECT lg FROM LearningGoal lg")
    List<LearningGoal> getAllLearningGoals();

    @Query("SELECT lg FROM LearningGoal lg WHERE lg.goalName = :goalName")
    Optional<LearningGoal> getLearningGoalByGoalName(@Param("goalName") String goalName);

    

    @Query("SELECT lg FROM LearningGoal lg WHERE lg.goalName = :goalName")
    Optional<LearningGoal> findLearningGoalByGoalName(@Param("goalName") String goalName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO learning_goal (goal_name, target_completion_date, user_id, submission_date) " +
                   "VALUES (:goalName, :targetCompletionDate, :userId, :submissionDate)", nativeQuery = true)
    void insertLearningGoalNative(@Param("goalName") String goalName,
                                  @Param("targetCompletionDate") Date targetCompletionDate,
                                  @Param("userId") int userId,
                                  @Param("submissionDate") String submissionDate);
}
