package com.example.learn.controller;
import java.util.List;
import java.util.Optional;
//import java.util.Collections;

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

import com.example.learn.entity.User;
import com.example.learn.service.UserService;
import com.example.learn.repository.UserRepository;

@RestController
@RequestMapping("/User")

public class UserController {
    @Autowired
    UserService us;
    @Autowired
    UserRepository ur;

    @PostMapping("/post")
   public ResponseEntity<List<User>> createUser(@RequestBody List<User> users) {
    return new ResponseEntity<>(us.createUser(users), HttpStatus.CREATED);
   }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> updatedUsers) {
        List<User> updatedUserList = us.updateUsers(updatedUsers);
        if (updatedUserList != null && !updatedUserList.isEmpty()) {
            return new ResponseEntity<>(updatedUserList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<String> deleteUsers(@PathVariable List<Integer> ids) {
    // Call the service to delete users
    us.deleteUsers(ids);
    
    // Return a success message
    return ResponseEntity.ok("Users with IDs " + ids + " have been successfully deleted.");
}
@GetMapping("/{offset}/{pagesize}")
    public List<User>getUsers(@PathVariable int offset,@PathVariable int pagesize)
    {
        return us.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<User>sortUsers(@PathVariable String field)
   {
    return us.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<User>getUsersSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return us.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByEmail")
   public Optional<User> getUserByEmail(@RequestParam String email) {
       return us.getUserByEmail(email);
   }
   @PostMapping("/adduser")
public ResponseEntity<User> addUser(@RequestBody User u) {
    User savedUser = us.addUser(u); 
    return ResponseEntity.ok(savedUser);
   }
}

