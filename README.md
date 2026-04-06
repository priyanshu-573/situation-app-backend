# 🚀 Situation App Backend (Spring Boot + MySQL)

## 📌 Project Overview

This is a backend application built using Spring Boot and MySQL.
It provides REST APIs to perform CRUD operations (Create, Read, Update, Delete) on user data.

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Maven

---

## ⚙️ Features

* Create user (POST)
* Get all users (GET)
* Get user by ID (GET)
* Update user (PUT)
* Delete user (DELETE)
* Health check API

---

## 🔗 API Endpoints

### ➤ Create User

POST /users
Body:
{
"name": "Ram",
"age": 20
}

---

### ➤ Get All Users

GET /users

---

### ➤ Get User By ID

GET /users/{id}

---

### ➤ Update User

PUT /users/{id}
Body:
{
"name": "Shyam",
"age": 25
}

---

### ➤ Delete User

DELETE /users/{id}

---

### ➤ Health Check

GET /users/health

---

## 🧠 Project Structure

* model → Entity classes
* repository → Database operations
* controller → API layer

---

## 🧩 How It Works

Client → Controller → Repository → Hibernate → MySQL → Response

---

## ▶️ How to Run

1. Clone the repository
2. Open in IDE (Eclipse/IntelliJ)
3. Configure MySQL in application.properties
4. Run main class
5. Access APIs using browser, curl, or Postman

---

## 🎯 Learning Outcome

* Built REST APIs using Spring Boot
* Connected backend with MySQL
* Implemented CRUD operations
* Learned JPA and Hibernate basics

---

## 👨‍💻 Author

Priyanshu Bohra
