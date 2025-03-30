package com.example.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Language;

import jakarta.transaction.Transactional;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer>{
    @Query("SELECT l FROM Language l")
    public List<Language> getAllLanguages();

    @Query("SELECT l FROM Language l WHERE l.id = :id")
    public Language getLanguageByName(@Param("name") String name);
    
    @Query("SELECT l FROM Language l WHERE l.name = :name")
    Optional<Language> findLanguageByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, code, description) VALUES (:name, :code, :description)", nativeQuery = true)
    void insertUserNative(@Param("name")String name,
      @Param("code")String code,
      @Param("description")String description);
}
