package com.example.learn.controller;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learn.entity.Assessment;
import com.example.learn.repository.AssessmentRepository;
import com.example.learn.service.AssessmentService;

@RestController
@RequestMapping("/Assessment")

public class AssessmentController {
    @Autowired
    AssessmentService as;
    @Autowired
    AssessmentRepository ar;

    @PostMapping("/post")
public ResponseEntity<List<Assessment>> createAssessment(@RequestBody List<Assessment> assessments) {
    return new ResponseEntity<>(as.createAssessment(assessments), HttpStatus.CREATED);
}

    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        return new ResponseEntity<>(as.getAllAssessments(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Assessment>> updateAssessments(@RequestBody List<Assessment> updatedAssessments) {
        List<Assessment> updatedAssessmentList = as.updateAssessments(updatedAssessments);
        if (updatedAssessmentList != null && !updatedAssessmentList.isEmpty()) {
            return new ResponseEntity<>(updatedAssessmentList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<List<Assessment>> deleteAssessments(@PathVariable List<Integer> ids) {
        List<Assessment> remainingAssessments = as.deleteAssessments(ids);
    
        return remainingAssessments.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
            : ResponseEntity.ok(remainingAssessments);
    }
@GetMapping("/{offset}/{pagesize}")
    public List<Assessment>getAssessments(@PathVariable int offset,@PathVariable int pagesize)
    {
        return as.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Assessment>sortAssessments(@PathVariable String field)
   {
    return as.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Assessment>getAssessmentsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return as.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByCourse")
   public Optional<Assessment> getAssessmentByCourse(@RequestParam String course) {
       return as.getAssessmentByCourse(course);
   }
   @PostMapping("/addAssessment")
public ResponseEntity<Assessment> addAssessment(@RequestBody Assessment a) {
    Assessment savedAssessment = as.addAssessment(a); 
    return ResponseEntity.ok(savedAssessment);
   }
}
