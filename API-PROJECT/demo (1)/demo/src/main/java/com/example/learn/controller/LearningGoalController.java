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

import com.example.learn.entity.LearningGoal;
import com.example.learn.service.LearningGoalService;

@RestController
@RequestMapping("/LearningGoal")
public class LearningGoalController {
 
    @Autowired
    LearningGoalService lgs;
    

    @PostMapping("/post")
public ResponseEntity<List<LearningGoal>> createLearningGoal(@RequestBody List<LearningGoal> learningGoals) {
    return new ResponseEntity<>(lgs.createLearningGoal(learningGoals), HttpStatus.CREATED);
}

    

    @GetMapping("/get")
    public ResponseEntity<List<LearningGoal>> getAllLearningGoals() {
        return new ResponseEntity<>(lgs.getAllLearningGoals(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<LearningGoal>> updateLearningGoals(@RequestBody List<LearningGoal> updatedLearningGoals) {
        List<LearningGoal> updatedLearningGoalList = lgs.updateLearningGoals(updatedLearningGoals);
        if (updatedLearningGoalList != null && !updatedLearningGoalList.isEmpty()) {
            return new ResponseEntity<>(updatedLearningGoalList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @DeleteMapping("/delete/{ids}")
    // public ResponseEntity<List<LearningGoal>> deleteLearningGoals(@PathVariable List<Integer> ids) {
    //     List<LearningGoal> remainingLearningGoals = lgs.deleteLearningGoals(ids);
    
    //     return remainingLearningGoals.isEmpty()
    //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
    //         : ResponseEntity.ok(remainingLearningGoals);
    // }
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteLearningGoals(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    lgs.deleteLearningGoals(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}
@GetMapping("/{offset}/{pagesize}")
    public List<LearningGoal>getLearningGoals(@PathVariable int offset,@PathVariable int pagesize)
    {
        return lgs.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<LearningGoal>sortLearningGoals(@PathVariable String field)
   {
    return lgs.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<LearningGoal>getLearningGoalsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return lgs.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByGoalName")
   public Optional<LearningGoal> getLearningGoalByGoalName(@RequestParam String goalName) {
       return lgs.getLearningGoalByGoalName(goalName);
   }
   @PostMapping("/addLearningGoal")
public ResponseEntity<LearningGoal> addLearningGoal(@RequestBody LearningGoal lg) {
    LearningGoal savedLearningGoal = lgs.addLearningGoal(lg); 
    return ResponseEntity.ok(savedLearningGoal);
   }
}
