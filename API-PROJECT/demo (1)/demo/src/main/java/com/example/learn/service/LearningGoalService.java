package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.LearningGoal;
import com.example.learn.repository.LearningGoalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LearningGoalService {
    @Autowired
    LearningGoalRepository lgr;
    // Create multiple learning goals
    public List<LearningGoal> createLearningGoal(List<LearningGoal> lg) {
        return lgr.saveAll(lg);
    }
    // Get all learning goals
    public List<LearningGoal> getAllLearningGoals() {
        return lgr.findAll();
    }
    // Get a single learning goal by ID
    public LearningGoal getLearningGoalById(int id) {
        return lgr.findById(id).orElse(null);
    }

    // Update multiple learning goals
    public List<LearningGoal> updateLearningGoals(List<LearningGoal> updatedLearningGoals) {
        List<LearningGoal> result = new ArrayList<>();
        for (LearningGoal learningGoal : updatedLearningGoals) {
            Optional<LearningGoal> existingGoalOpt = lgr.findById(learningGoal.getId());
            if (existingGoalOpt.isPresent()) {
                LearningGoal existingLearningGoal = existingGoalOpt.get();
                existingLearningGoal.setGoalName(learningGoal.getGoalName());
                existingLearningGoal.setTargetCompletionDate(learningGoal.getTargetCompletionDate());

                lgr.save(existingLearningGoal);
                result.add(existingLearningGoal);
            }
        }
        return result; 
    }

    // Delete multiple learning goals
    public List<LearningGoal> deleteLearningGoals(List<Integer> learningGoalIds) {
        lgr.deleteAllById(learningGoalIds);
        return lgr.findAll(); // Return remaining learning goals
    }

    // Pagination
    public List<LearningGoal> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return lgr.findAll(page).getContent();
    }

    // Sorting
    public List<LearningGoal> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return lgr.findAll(sort);
    }

    // Pagination with sorting
    public List<LearningGoal> pagesort(int pageSize, int pageNumber, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field));
        return lgr.findAll(pageable).getContent();
    }

    // Add a single learning goal
    public LearningGoal addLearningGoal(LearningGoal learningGoal) {
        return lgr.save(learningGoal);
    }

    // Get learning goal by goal name
    public Optional<LearningGoal> getLearningGoalByGoalName(String goalName) {
        return lgr.findLearningGoalByGoalName(goalName);
    }
}
