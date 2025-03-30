package com.example.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.learn.entity.Language;
import com.example.learn.repository.LanguageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LanguageService {
    @Autowired
    LanguageRepository lr;

    public List<Language> createLanguage(List<Language>l) {
        return lr.saveAll(l);
    }

    public List<Language> getAllLanguages() {
        return lr.findAll();
    }

    public Language getLanguageById(int id) {
        return lr.findById(id).orElse(null);
    }

    public List<Language> updateLanguages(List<Language> updatedLanguages) {
        List<Language> result = new ArrayList<>();
        for (Language language : updatedLanguages) {
            Language existingLanguage = lr.findById(language.getId()).orElse(null);
            if (existingLanguage != null) {
                existingLanguage.setName(language.getName());
                existingLanguage.setCode(language.getCode());
                existingLanguage.setDescription(language.getDescription());
                
                lr.save(existingLanguage);
                result.add(existingLanguage);
            }
        }
        return result; 
    }
    
    public List<Language> deleteLanguages(List<Integer> LanguageIds) {
        lr.deleteAllById(LanguageIds);
        return lr.findAll(); // Return remaining users
    }
    
    public List<Language> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return lr.findAll(page).getContent();
        
    }
    public List<Language>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return lr.findAll(sort);
    }
    public List<Language>pagesort(int pageSize,int pageNumber,String field)
    {
        return lr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Language addLanguage(Language language) {
        return lr.save(language); 
    }
    public Optional<Language> getLanguageByGoalName(String name) {
        return lr.findLanguageByName(name);
    }
}
