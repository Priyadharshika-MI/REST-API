package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.Instructor;
import com.example.learn.repository.InstructorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InstructorService {
    @Autowired
    InstructorRepository ir;

    public List<Instructor> createInstructor(List<Instructor>i) {
        return ir.saveAll(i);
    }

    public List<Instructor> getAllInstructors() {
        return ir.findAll();
    }

    public Instructor getInstructorById(int id) {
        return ir.findById(id).orElse(null);
    }

    public List<Instructor> updateInstructors(List<Instructor> updatedInstructors) {
        List<Instructor> result = new ArrayList<>();
        for (Instructor instructor : updatedInstructors) {
            Instructor existingInstructor = ir.findById(instructor.getId()).orElse(null);
            if (existingInstructor != null) {
                existingInstructor.setName(instructor.getName());
                existingInstructor.setEmail(instructor.getEmail());
                existingInstructor.setExpertise(instructor.getExpertise());
                
                ir.save(existingInstructor);
                result.add(existingInstructor);
            }
        }
        return result; 
    }
    
    public List<Instructor> deleteInstructors(List<Integer> InstructorIds) {
        ir.deleteAllById(InstructorIds);
        return ir.findAll(); // Return remaining users
    }
    
    public List<Instructor> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return ir.findAll(page).getContent();
        
    }
    public List<Instructor>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return ir.findAll(sort);
    }
    public List<Instructor>pagesort(int pageSize,int pageNumber,String field)
    {
        return ir.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Instructor addInstructor(Instructor instructor) {
        return ir.save(instructor); 
    }
    public Optional<Instructor> getInstructorByName(String name) {
        return ir.findInstructorByName(name);
    }
    
}
