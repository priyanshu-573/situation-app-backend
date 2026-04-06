package com.example.situation_app.controller;

import com.example.situation_app.model.User;
import com.example.situation_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Save user
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
    	  if (!userRepository.existsById(id)) {
    	        throw new RuntimeException("User not found");
    	    }
        user.setId(id);
        return userRepository.save(user);
    }
    
    // Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
    	
    	if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }
    
    // Get User By id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    // Get Server Status 
    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running successfully!";
    }
}