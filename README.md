# 🛒 E-Commerce Backend

A complete **RESTful E-Commerce Backend System** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **PostgreSQL (Neon Cloud Database)**. The backend provides secure authentication, product management, shopping cart, order processing, admin management, and RESTful APIs. The application is deployed on **Railway**.

---

# 🚀 Features

- User Registration & Login (JWT Authentication)
- Role-Based Authorization (Admin/User)
- Product Management (CRUD)
- Category Management
- Shopping Cart Management
- Order Processing System
- Address Management
- Admin Dashboard APIs
- Order Status Tracking
- Product Search & Filtering
- Excel Export of Orders
- Swagger API Documentation
- PostgreSQL (Neon Cloud Database)
- Railway Deployment

---

# 🛠️ Tech Stack

- Java 17
- Spring Boot 2.7.18
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL (Neon Cloud)
- JWT Authentication
- Apache POI
- Maven
- Railway

---

# 📂 Project Structure

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

---

# ⚙️ Run Locally

Clone the repository

```bash
git clone https://github.com/anjumpatel725-boop/ecommerce-backend.git
```

Go to project folder

```bash
cd ecommerce-backend
```

Install dependencies

```bash
mvn clean install
```

Run the application

```bash
mvn spring-boot:run
```

Backend will start at:

```
http://localhost:8080
```

---

# 🌐 Live Demo

## Frontend

https://anjumpatel725-boop.github.io/ecommerce-frontend/

## Backend API

https://ecommerce-backend-production-075f.up.railway.app

## Sample Product API

https://ecommerce-backend-production-075f.up.railway.app/api/products

## Swagger Documentation

https://ecommerce-backend-production-075f.up.railway.app/swagger-ui/index.html

---

# 🗄️ Database

- PostgreSQL
- Neon Cloud Database

https://neon.tech

---

# 📌 Main REST APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register User |
| POST | `/api/auth/login` | Login User |
| GET | `/api/products` | Get All Products |
| GET | `/api/products/search` | Search Products |
| GET | `/api/categories` | Get Categories |
| POST | `/api/cart` | Add to Cart |
| GET | `/api/cart/{userId}` | Get User Cart |
| POST | `/api/orders` | Place Order |
| GET | `/api/orders/user/{id}` | User Orders |
| GET | `/api/admin/users` | Get All Users |
| GET | `/api/admin/orders` | Get All Orders |
| PUT | `/api/admin/orders/{id}/status` | Update Order Status |
| GET | `/api/admin/orders/export/excel` | Export Orders |

---

# 🔐 Security

- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization
- Protected REST APIs
- Spring Security

---

# 📖 API Documentation

Swagger UI

https://ecommerce-backend-production-075f.up.railway.app/swagger-ui/index.html

---

# 👨‍💻 Author

**Anjum Patel**

B.Tech Computer Science & Engineering

GitHub Profile

https://github.com/anjumpatel725-boop
