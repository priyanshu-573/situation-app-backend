package com.example.situation_app.controller;

import com.example.situation_app.model.User;
import com.example.situation_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Save user
    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
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

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "File is empty";
        }

        // Check file type
        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            return "Only Excel files allowed";
        }

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                User user = new User();
                user.setName(row.getCell(0).getStringCellValue());
                user.setAge((int) row.getCell(1).getNumericCellValue());

                // NEW COLUMNS ADDED ↓
                user.setEmail(row.getCell(2).getStringCellValue());
                user.setCity(row.getCell(3).getStringCellValue());
                user.setOccupation(row.getCell(4).getStringCellValue());
                // NEW COLUMNS ADDED ↑

                userRepository.save(user);
            }

            workbook.close();
            return "Data uploaded successfully";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}