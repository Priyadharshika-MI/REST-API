// package com.example.learn.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;
// import com.example.learn.entity.Enrollment;
// import com.example.learn.repository.EnrollmentRepository;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// @Service
// public class EnrollmentService {
//     @Autowired
//     EnrollmentRepository er;

//     public List<Enrollment> createEnrollment(List<Enrollment>e) {
//         return er.saveAll(e);
//     }

//     public List<Enrollment> getAllEnrollments() {
//         return er.findAll();
//     }

//     public Enrollment getEnrollmentById(int id) {
//         return er.findById(id).orElse(null);
//     }

//     public List<Enrollment> updateEnrollments(List<Enrollment> updatedEnrollments) {
//         List<Enrollment> result = new ArrayList<>();
//         for (Enrollment enrollment : updatedEnrollments) {
//             Enrollment existingEnrollment = er.findById(enrollment.getId()).orElse(null);
//             if (existingEnrollment != null) {
//                 existingEnrollment.setUser(enrollment.getUser());
//                 existingEnrollment.setCourse(enrollment.getCourse());
//                 existingEnrollment.setProgressPercentage(enrollment.getProgressPercentage());
//                 existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentdate());

//                 er.save(existingEnrollment);
//                 result.add(existingEnrollment);
//             }
//         }
//         return result; 
//     }
    
//     public List<Enrollment> deleteEnrollments(List<Integer> EnrollmentIds) {
//         er.deleteAllById(EnrollmentIds);
//         return er.findAll(); // Return remaining users
//     }
    
//     public List<Enrollment> page(int pageSize,int pageNumber)
//     {
        
//         Pageable page=PageRequest.of(pageNumber, pageSize);
//         return er.findAll(page).getContent();
        
//     }
//     public List<Enrollment>sort(String field)
//     {
//         Sort sort=Sort.by(Sort.Direction.ASC,field);
//         return er.findAll(sort);
//     }
//     public List<Enrollment>pagesort(int pageSize,int pageNumber,String field)
//     {
//         return er.findAll(
//             PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
//         ).getContent();
//     }
   
//     public Enrollment addEnrollment(Enrollment enrollment) {
//         return er.save(enrollment); 
//     }
//     public Optional<Enrollment> getEnrollmentByProgressPercentage(float progressPercentage) {
//         return er.findEnrollmentByProgressPercentage(progressPercentage);
//     }
// }
package com.example.learn.service;

import com.example.learn.entity.Enrollment;
import com.example.learn.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }
}

