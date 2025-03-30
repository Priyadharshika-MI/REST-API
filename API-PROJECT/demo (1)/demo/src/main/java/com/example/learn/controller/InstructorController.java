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

import com.example.learn.entity.Instructor;
import com.example.learn.repository.InstructorRepository;
import com.example.learn.service.InstructorService;

@RestController
@RequestMapping("/Instructor")
public class InstructorController {
    @Autowired
    InstructorService is;
    @Autowired
    InstructorRepository ir;

    @PostMapping("/post")
public ResponseEntity<List<Instructor>> createInstructor(@RequestBody List<Instructor> instructors) {
    return new ResponseEntity<>(is.createInstructor(instructors), HttpStatus.CREATED);
}

    

    @GetMapping("/get")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return new ResponseEntity<>(is.getAllInstructors(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Instructor>> updateInstructors(@RequestBody List<Instructor> updatedInstructors) {
        List<Instructor> updatedInstructorList = is.updateInstructors(updatedInstructors);
        if (updatedInstructorList != null && !updatedInstructorList.isEmpty()) {
            return new ResponseEntity<>(updatedInstructorList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @DeleteMapping("/delete/{ids}")
    // public ResponseEntity<List<Instructor>> deleteInstructors(@PathVariable List<Integer> ids) {
    //     List<Instructor> remainingInstructors = is.deleteInstructors(ids);
    
    //     return remainingInstructors.isEmpty()
    //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
    //         : ResponseEntity.ok(remainingInstructors);
    // }
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteInstructors(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    is.deleteInstructors(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}
@GetMapping("/{offset}/{pagesize}")
    public List<Instructor>getInstructors(@PathVariable int offset,@PathVariable int pagesize)
    {
        return is.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Instructor>sortInstructors(@PathVariable String field)
   {
    return is.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Instructor>getInstructorsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return is.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByName")
   public Optional<Instructor> getInstructorByName(@RequestParam String name) {
       return is.getInstructorByName(name);
   }
   @PostMapping("/addInstructor")
public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor i) {
    Instructor savedInstructor = is.addInstructor(i); 
    return ResponseEntity.ok(savedInstructor);
   }
}
