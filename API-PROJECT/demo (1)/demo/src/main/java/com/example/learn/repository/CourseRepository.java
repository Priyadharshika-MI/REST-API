package com.example.learn.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.learn.entity.Course;
import jakarta.transaction.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>
{
    @Query("SELECT c FROM Course c")
    public List<Course> getAllCourses();

    @Query("SELECT c FROM Course c WHERE c.id = :id")
    public Course getCourseByName(@Param("name") String name);
    
    @Query("SELECT c FROM Course c WHERE c.name = :name")
    Optional<Course> findCourseByName(@Param("name") String name);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, description, language, difficultyLevel) VALUES (:name, :description, :language, :difficultyLevel)", nativeQuery = true)
    void insertUserNative(@Param("name")String name,
      @Param("description")String description,
      @Param("language")String language,
      @Param("difficultyLevel")String difficultyLevel);
      
}
