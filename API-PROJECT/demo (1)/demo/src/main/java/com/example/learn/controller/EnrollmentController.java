// package com.example.learn.controller;
// import java.util.List;
// import java.util.Optional;
// // import java.util.Collections;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.learn.entity.Enrollment;
// import com.example.learn.service.EnrollmentService;
// import com.example.learn.repository.EnrollmentRepository;


// @RestController
// @RequestMapping("/Enrollment")
// public class EnrollmentController {
//     @Autowired
//     EnrollmentService es;
//     @Autowired
//     EnrollmentRepository er;

//     @PostMapping("/post")
// public ResponseEntity<List<Enrollment>> createEnrollment(@RequestBody List<Enrollment> enrollments) {
//     return new ResponseEntity<>(es.createEnrollment(enrollments), HttpStatus.CREATED);
// }

    

//     @GetMapping("/get")
//     public ResponseEntity<List<Enrollment>> getAllEnrollments() {
//         return new ResponseEntity<>(es.getAllEnrollments(), HttpStatus.OK);
//     }
//     @PutMapping("/update")
//     public ResponseEntity<List<Enrollment>> updateEnrollments(@RequestBody List<Enrollment> updatedEnrollments) {
//         List<Enrollment> updatedEnrollmentList = es.updateEnrollments(updatedEnrollments);
//         if (updatedEnrollmentList != null && !updatedEnrollmentList.isEmpty()) {
//             return new ResponseEntity<>(updatedEnrollmentList, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
    
//     // @DeleteMapping("/delete/{ids}")
//     // public ResponseEntity<List<Enrollment>> deleteEnrollments(@PathVariable List<Integer> ids) {
//     //     List<Enrollment> remainingEnrollments = es.deleteEnrollments(ids);
    
//     //     return remainingEnrollments.isEmpty()
//     //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
//     //         : ResponseEntity.ok(remainingEnrollments);
//     // }
//     @DeleteMapping("/delete/{ids}")
// public ResponseEntity<String> deleteEnrollments(@PathVariable List<Integer> ids) {
//     // Call the service to delete users
//     es.deleteEnrollments(ids);
    
//     // Return a success message
//     return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
// }
// @GetMapping("/{offset}/{pagesize}")
//     public List<Enrollment>getEnrollments(@PathVariable int offset,@PathVariable int pagesize)
//     {
//         return es.page(pagesize,offset);
//     }
//     @GetMapping("/sortBy/{field}")
//    public List<Enrollment>sortEnrollments(@PathVariable String field)
//    {
//     return es.sort(field);
//    }
//    @GetMapping("/{offset}/{pagesize}/{field}")
//    public List<Enrollment>getEnrollmentsSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
//    {
//     return es.pagesort(pagesize,offset,field);
//    }
//    @GetMapping("/findByProgressPercentage")
//    public Optional<Enrollment> getEnrollmentByProgressPercentage(@RequestParam float progressPercentage) {
//        return es.getEnrollmentByProgressPercentage(progressPercentage);
//    }
//    @PostMapping("/addEnrollment")
// public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment e) {
//     Enrollment savedEnrollment = es.addEnrollment(e); 
//     return ResponseEntity.ok(savedEnrollment);
//    }
// }
package com.example.learn.controller;

import com.example.learn.entity.Enrollment;
import com.example.learn.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable int id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable int id) {
        enrollmentService.deleteEnrollment(id);
    }
}
