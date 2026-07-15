# 🛒 E-Commerce Backend

A RESTful E-Commerce Backend built with Spring Boot, Spring Security, PostgreSQL (Neon DB), JWT Authentication, and deployed on Railway.

## 🚀 Features

- User Registration & Login (JWT Authentication)
- Role-Based Authorization (Admin/User)
- Product Management
- Category Management
- Shopping Cart
- Order Management
- Admin Dashboard APIs
- Excel Export of Orders
- Swagger API Documentation
- PostgreSQL Database (Neon)
- Railway Deployment

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL (Neon)
- JWT
- Apache POI
- Maven
- Railway

## 📂 Project Structure

```
src
├── controller
├── service
├── repository
├── model
├── dto
├── security
├── config
└── exception
```

## ⚙️ Run Locally

```bash
git clone https://github.com/anjumpatel725-boop/ecommerce-backend.git
cd ecommerce-backend
mvn clean install
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

## 🌐 Live API

```
https://ecommerce-backend-production-075f.up.railway.app
```

## 📖 Swagger Documentation

```
https://ecommerce-backend-production-075f.up.railway.app/swagger-ui/index.html
```

## 🗄️ Database

- PostgreSQL
- Neon Cloud Database

## 📌 Main APIs

| Method | Endpoint |
|---------|----------|
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |
| GET | `/api/products` |
| GET | `/api/categories` |
| POST | `/api/cart` |
| POST | `/api/orders` |
| GET | `/api/admin/users` |
| GET | `/api/admin/orders` |
| GET | `/api/admin/orders/export/excel` |

## 👨‍💻 Author

**Anjum Patel**

GitHub: https://github.com/anjumpatel725-boop
