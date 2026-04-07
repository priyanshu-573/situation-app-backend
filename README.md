# Full Stack User Management App

A full-stack web application built with Spring Boot, MySQL, and React.

---

## 🛠️ Tech Stack

- **Backend**: Java + Spring Boot
- **Database**: MySQL
- **Frontend**: React + Vite
- **API Docs**: Swagger UI

---

## ⚙️ Setup & Run Instructions

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL installed and running
- Maven installed

---

### Backend Setup

1. Clone the repository
```bash
git clone https://github.com/yourusername/situation-app.git
cd situation-app
```

2. Create MySQL database
```sql
CREATE DATABASE situation_db;
```

3. Update `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/situation_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
server.port=8082
```

4. Run Spring Boot
```bash
mvn spring-boot:run
```

---

### Frontend Setup

1. Go to frontend folder
```bash
cd frontend
```

2. Install dependencies
```bash
npm install
npm install axios
```

3. Start React
```bash
npm run dev
```

4. Open browser

http://localhost:5173

---

## 🗄️ Database Choice + Schema

**Database**: MySQL

**Why MySQL?**
- Easy to set up locally
- Works perfectly with Spring Boot JPA
- Industry standard relational database

**Schema**:

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto generated ID |
| name | VARCHAR | User's name |
| age | INTEGER | User's age |
| email | VARCHAR | User's email |
| city | VARCHAR | User's city |
| occupation | VARCHAR | User's occupation |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /users | Get all users |
| GET | /users/{id} | Get user by ID |
| POST | /users | Create new user |
| PUT | /users/{id} | Update user |
| DELETE | /users/{id} | Delete user |
| GET | /users/health | Health check |
| POST | /users/upload | Import Excel file |

---

## 📖 API Documentation (Swagger)

http://localhost:8082/swagger-ui/index.html

---

## 🤖 AI Usage Log

### Tools Used
- Claude (Anthropic) — https://claude.ai

---

### Exact Prompts I Used

**Prompt 1:**
"I am building a basic backend project using Spring Boot and MySQL.Now I am unable to install Postman so I cannot test POST requests easily. I need an alternative way to test my APIs"

**Prompt 2:**
"Here is my React App.jsx code. Add an Edit/Update feature without changing existing structure, so users can update data from the UI."

**Prompt 3:**
"When I delete a user, the ID numbers are skipping. Is this expected behavior? Explain why this happens."
---

### What I Kept vs Changed vs Wrote Myself

Kept:

Overall backend structure (Controller, Repository, Entity)
Excel upload logic using Apache POI
API integration using Axios in frontend

Changed:

Simplified code structure for better understanding
Added validations (name not empty, age >= 0)
Improved UI with controlled inputs and better UX

Wrote Myself:

Project setup and configuration
Database connection (MySQL setup)
Final UI adjustments and testing
GitHub setup and project organization

## 🚀 Features

- ✅ Full CRUD Operations
- ✅ Excel File Import
- ✅ Input Validation
- ✅ Swagger API Documentation
- ✅ React Frontend with Edit/Delete
- ✅ Health Check API
