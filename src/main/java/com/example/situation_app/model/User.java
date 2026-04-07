package com.example.situation_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    // NEW COLUMNS ADDED ↓
 // ADD THIS ↓
    @Email(message = "Please enter valid email")
    @NotBlank(message = "Email cannot be empty")
    // ADD THIS ↑

    private String email;
    private String city;
    private String occupation;
    // NEW COLUMNS ADDED ↑

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // NEW GETTERS AND SETTERS ADDED ↓

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter for occupation
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // NEW GETTERS AND SETTERS ADDED ↑
}