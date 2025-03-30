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

import com.example.learn.entity.Language;
import com.example.learn.repository.LanguageRepository;
import com.example.learn.service.LanguageService;

@RestController
@RequestMapping("/Language")
public class LanguageController {
    @Autowired
    LanguageService ls;
    @Autowired
    LanguageRepository lr;

    @PostMapping("/post")
public ResponseEntity<List<Language>> createLanguage(@RequestBody List<Language> languages) {
    return new ResponseEntity<>(ls.createLanguage(languages), HttpStatus.CREATED);
}

    

    @GetMapping("/get")
    public ResponseEntity<List<Language>> getAllLanguages() {
        return new ResponseEntity<>(ls.getAllLanguages(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<Language>> updateLanguages(@RequestBody List<Language> updatedLanguages) {
        List<Language> updatedLanguageList = ls.updateLanguages(updatedLanguages);
        if (updatedLanguageList != null && !updatedLanguageList.isEmpty()) {
            return new ResponseEntity<>(updatedLanguageList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @DeleteMapping("/delete/{ids}")
    // public ResponseEntity<List<Language>> deleteLanguages(@PathVariable List<Integer> ids) {
    //     List<Language> remainingLanguages = ls.deleteLanguages(ids);
    
    //     return remainingLanguages.isEmpty()
    //         ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
    //         : ResponseEntity.ok(remainingLanguages);
    // }
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteLanguages(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    ls.deleteLanguages(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}
@GetMapping("/{offset}/{pagesize}")
    public List<Language>getLanguages(@PathVariable int offset,@PathVariable int pagesize)
    {
        return ls.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Language>sortLanguages(@PathVariable String field)
   {
    return ls.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Language>getLanguagesSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return ls.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByGoalName")
   public Optional<Language> getLanguageByGoalName(@RequestParam String goalName) {
       return ls.getLanguageByGoalName(goalName);
   }
   @PostMapping("/addLanguage")
public ResponseEntity<Language> addLanguage(@RequestBody Language l) {
    Language savedLanguage = ls.addLanguage(l); 
    return ResponseEntity.ok(savedLanguage);
   }
}
