package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.Course;
import com.example.learn.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository cr;

    public List<Course> createCourse(List<Course>c) {
        return cr.saveAll(c);
    }
    public List<Course> getAllCourses() {
        return cr.findAll();
    }
    public Course getCourseById(int id) {
        return cr.findById(id).orElse(null);
    }
    public List<Course> updateCourses(List<Course> updatedCourses) {
        List<Course> result = new ArrayList<>();
        for (Course course : updatedCourses) {
            Course existingCourse = cr.findById(course.getId()).orElse(null);
            if (existingCourse != null) {
                existingCourse.setName(course.getName());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLanguage(course.getLanguage());
                existingCourse.setDifficultyLevel(course.getDifficultyLevel());

                cr.save(existingCourse);
                result.add(existingCourse);
            }
        }
        return result; 
    }
    public List<Course> deleteCourses(List<Integer> CourseIds) {
        cr.deleteAllById(CourseIds);
        return cr.findAll(); 
    }
    public List<Course> page(int pageSize,int pageNumber)
    {
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return cr.findAll(page).getContent();
    }
    public List<Course>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return cr.findAll(sort);
    }
    public List<Course>pagesort(int pageSize,int pageNumber,String field)
    {
        return cr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
    public Course addCourse(Course course) {
        return cr.save(course); 
    }
    public Optional<Course> getCourseByName(String name) {
        return cr.findCourseByName(name);
    }
}
