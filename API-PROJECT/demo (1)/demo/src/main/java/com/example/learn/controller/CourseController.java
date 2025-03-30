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

import com.example.learn.entity.Course;
import com.example.learn.repository.CourseRepository;
import com.example.learn.service.CourseService;

@RestController
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    CourseService cs;
    @Autowired
    CourseRepository cr;

    @PostMapping("/post")
public ResponseEntity<List<Course>> createCourse(@RequestBody List<Course> courses) {
    return new ResponseEntity<>(cs.createCourse(courses), HttpStatus.CREATED);
}

    @GetMapping("/get")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(cs.getAllCourses(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Course>> updateCourses(@RequestBody List<Course> updatedCourses) {
        List<Course> updatedCourseList = cs.updateCourses(updatedCourses);
        if (updatedCourseList != null && !updatedCourseList.isEmpty()) {
            return new ResponseEntity<>(updatedCourseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @DeleteMapping("/delete/{ids}")
    // public ResponseEntity<List<Course>> deleteCourses(@PathVariable List<Integer> ids) {
    //     List<Course> remainingCourses = cs.deleteCourses(ids);
    
    //     return remainingCourses.isEmpty()
    //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
    //         : ResponseEntity.ok(remainingCourses);
    // }
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteCourses(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    cs.deleteCourses(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}
@GetMapping("/{offset}/{pagesize}")
    public List<Course>getCourses(@PathVariable int offset,@PathVariable int pagesize)
    {
        return cs.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Course>sortCourses(@PathVariable String field)
   {
    return cs.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Course>getCoursesSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return cs.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByName")
   public Optional<Course> getCourseByName(@RequestParam String name) {
       return cs.getCourseByName(name);
   }
   @PostMapping("/addCourse")
public ResponseEntity<Course> addCourse(@RequestBody Course c) {
    Course savedCourse = cs.addCourse(c); 
    return ResponseEntity.ok(savedCourse);
   }
}
