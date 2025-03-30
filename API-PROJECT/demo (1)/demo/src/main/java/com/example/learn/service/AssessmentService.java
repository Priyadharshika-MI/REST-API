package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.Assessment;
import com.example.learn.repository.AssessmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AssessmentService
{
    @Autowired
    AssessmentRepository ar;

    public List<Assessment> createAssessment(List<Assessment>a) {
        return ar.saveAll(a);
    }
    public List<Assessment> getAllAssessments() {
        return ar.findAll();
    }
    public Assessment getAssessmentById(int id) {
        return ar.findById(id).orElse(null);
    }
    public List<Assessment> updateAssessments(List<Assessment> updatedAssessments) {
        List<Assessment> result = new ArrayList<>();
        for (Assessment assessment : updatedAssessments) {
            Assessment existingAssessment = ar.findById(assessment.getId()).orElse(null);
            if (existingAssessment != null) {
                existingAssessment.setTitle(assessment.getTitle());
                existingAssessment.setCourse(assessment.getCourse());
                existingAssessment.setTotalMarks(assessment.getTotalMarks());

                ar.save(existingAssessment);
                result.add(existingAssessment);
            }
        }
        return result; 
    }
    public List<Assessment> deleteAssessments(List<Integer> AssessmentIds) {
        ar.deleteAllById(AssessmentIds);
        return ar.findAll(); 
    }
    public List<Assessment> page(int pageSize,int pageNumber)
    {
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return ar.findAll(page).getContent();
    }
    public List<Assessment>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return ar.findAll(sort);
    }
    public List<Assessment>pagesort(int pageSize,int pageNumber,String field)
    {
        return ar.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
    public Assessment addAssessment(Assessment assessment) {
        return ar.save(assessment); 
    }
    public Optional<Assessment> getAssessmentByCourse(String course) {
        return ar.findAssessmentByCourse(course);
    }
}


