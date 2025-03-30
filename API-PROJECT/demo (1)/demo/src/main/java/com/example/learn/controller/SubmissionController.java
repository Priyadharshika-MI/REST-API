package com.example.learn.controller;
// import java.util.Collections;
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

import com.example.learn.entity.Submission;
import com.example.learn.repository.SubmissionRepository;
import com.example.learn.service.SubmissionService;

@RestController
@RequestMapping("/Submission")

public class SubmissionController {
    @Autowired
    SubmissionService ss;
    @Autowired
    SubmissionRepository ur;

    @PostMapping("/post")
public ResponseEntity<List<Submission>> createSubmission(@RequestBody List<Submission> submissions) {
    return new ResponseEntity<>(ss.createSubmission(submissions), HttpStatus.CREATED);
}

    

    @GetMapping("/get")
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        return new ResponseEntity<>(ss.getAllSubmissions(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Submission>> updateSubmissions(@RequestBody List<Submission> updatedSubmissions) {
        List<Submission> updatedSubmissionList = ss.updateSubmissions(updatedSubmissions);
        if (updatedSubmissionList != null && !updatedSubmissionList.isEmpty()) {
            return new ResponseEntity<>(updatedSubmissionList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @DeleteMapping("/delete/{ids}")
    // public ResponseEntity<List<Submission>> deleteSubmissions(@PathVariable List<Integer> ids) {
    //     List<Submission> remainingSubmissions = ss.deleteSubmissions(ids);
    
    //     return remainingSubmissions.isEmpty()
    //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
    //         : ResponseEntity.ok(remainingSubmissions);
    // }
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteSubmissions(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    ss.deleteSubmissions(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}

@GetMapping("/{offset}/{pagesize}")
    public List<Submission>getSubmissions(@PathVariable int offset,@PathVariable int pagesize)
    {
        return ss.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Submission>sortSubmissions(@PathVariable String field)
   {
    return ss.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Submission>getSubmissionsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return ss.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByScore")
   public Optional<Submission> getSubmissionByScore(@RequestParam float score) {
       return ss.getSubmissionByScore(score);
   }
   @PostMapping("/addsubmission")
public ResponseEntity<Submission> addSubmission(@RequestBody Submission s) {
   Submission savedSubmission = ss.addSubmission(s); 
    return ResponseEntity.ok(savedSubmission);
   }
}
