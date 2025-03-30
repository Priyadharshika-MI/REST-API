package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.Submission;
import com.example.learn.repository.SubmissionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class SubmissionService {
    
    @Autowired
    SubmissionRepository sr;

    public List<Submission> createSubmission(List<Submission>s) {
        return sr.saveAll(s);
    }

    public List<Submission> getAllSubmissions() {
        return sr.findAll();
    }

    public Submission getSubmissionById(int id) {
        return sr.findById(id).orElse(null);
    }

    public List<Submission> updateSubmissions(List<Submission> updatedSubmissions) {
        List<Submission> result = new ArrayList<>();
        for (Submission submission : updatedSubmissions) {
            Submission existingSubmission = sr.findById(submission.getId()).orElse(null);
            if (existingSubmission != null) {
                existingSubmission.setUser(submission.getUser());
                existingSubmission.setAssessment(submission.getAssessment());
                existingSubmission.setScore(submission.getScore());
                existingSubmission.setSubmissionDate(submission.getSubmissionDate());
                
                sr.save(existingSubmission);
                result.add(existingSubmission);
            }
        }
        return result; 
    }
    
    public List<Submission> deleteSubmissions(List<Integer> SubmissionIds) {
        sr.deleteAllById(SubmissionIds);
        return sr.findAll(); // Return remaining users
    }
    
    public List<Submission> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return sr.findAll(page).getContent();
        
    }
    public List<Submission>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return sr.findAll(sort);
    }
    public List<Submission>pagesort(int pageSize,int pageNumber,String field)
    {
        return sr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Submission addSubmission(Submission submission) {
        return sr.save(submission); 
    }
    public Optional<Submission> getSubmissionByScore(float score) {
        return sr.findSubmissionByScore(score);
    }
    
}
