# Kwik Tips - Backend

This is the backend for the Kwik Tips tip distribution app. Built with Java and Spring Boot, it powers the logic and database operations behind calculating and distributing tips for bar and restaurant staff.

## 🌐 Live API

👉 [Render Deployment](https://your-render-url.onrender.com)  
(API routes are protected and used by the Vue frontend)

## 🛠 Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- JPA / Hibernate
- Render for deployment

## 📋 Features

- Calculate payouts for bartenders, barbacks, and food runners
- Support for partial shifts and multiple roles
- Server mode to calculate owed tip-outs based on food/alcohol sales
- User registration and login (JWT authentication)
- Anonymous usage supported without saving data

## 📂 Project Structure

- `src/main/java`: Main app code
    - `controller`: API endpoints
    - `service`: Business logic
    - `model`: Entities and DTOs
    - `repository`: Spring JPA interfaces
- `src/main/resources`: App config files (like `application.properties`)

## 🧪 Running Locally

Make sure to set these environment variables before running:

```bash
DB_URL=jdbc:postgresql://localhost:5432/smart_tips
DB_USERNAME=postgres
DB_PASSWORD=yourpassword