# ERP Inventory Management System

A modern Java-based ERP Inventory Management System built using **JavaFX**, **MySQL**, and **JDBC**.

This project provides inventory tracking, CRUD operations, dashboard analytics, charts, role-based access, CSV export, alerts, and a professional enterprise-style UI.

---

# 🚀 Features

## 🔐 Authentication

* User Login System
* Role-Based Access
* Admin / Employee Roles

---

## 📦 Inventory Management

* Add Products
* Update Products
* Delete Products
* Search Products
* Inventory Tracking

---

## 📊 Dashboard Analytics

* KPI Statistic Cards
* Total Products
* Low Stock Count
* Inventory Value
* Active Users

---

## 📈 Charts & Reports

* Line Chart Analytics
* Pie Chart Inventory Distribution
* CSV Export Reports

---

## ⚠️ Inventory Alerts

* Low Stock Notifications
* Reorder Level Monitoring

---

## 🎨 Modern UI

* JavaFX Professional Dashboard
* Sidebar Navigation
* Hover Effects
* Responsive Layout
* Modern Login Screen

---

# 🛠️ Technologies Used

| Technology  | Purpose               |
| ----------- | --------------------- |
| Java 21     | Core Development      |
| JavaFX      | GUI                   |
| MySQL       | Database              |
| JDBC        | Database Connectivity |
| Eclipse IDE | Development           |
| CSS         | Styling               |
| CSV Export  | Reporting             |

---

# 🗂️ Project Structure

```text
ERPSystem/
│
├── src/
│   ├── com.erp.controller/
│   ├── com.erp.dao/
│   ├── com.erp.db/
│   ├── com.erp.main/
│   ├── com.erp.model/
│   ├── com.erp.util/
│   │
│   ├── dashboard.fxml
│   ├── login.fxml
│   └── style.css
│
├── lib/
│
└── README.md
```

---

# ⚙️ Database Setup

## Create Database

```sql
CREATE DATABASE erpdb;
USE erpdb;
```

---

## Create Users Table

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(20)
);
```

---

## Insert Users

```sql
INSERT INTO users(username, password, role)
VALUES
('admin', 'admin123', 'Admin'),
('employee', 'emp123', 'Employee');
```

---

## Create Inventory Table

```sql
CREATE TABLE inventory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    current_stock INT,
    reorder_level INT,
    price DOUBLE
);
```

---

# ▶️ Run Project

## 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/ERPSystem.git
```

---

## 2️⃣ Import into Eclipse

```text
File → Import → Existing Projects into Workspace
```

---

## 3️⃣ Configure JavaFX

Add JavaFX SDK libraries to:

```text
Build Path
```

---

## 4️⃣ Configure MySQL

Update:

```java
DBConnection.java
```

with your:

* username
* password
* database name

---

## 5️⃣ Run Application

Run:

```text
AppLauncher.java
```

---

# 👨‍💻 User Roles

| Role     | Permissions        |
| -------- | ------------------ |
| Admin    | Full CRUD + Export |
| Employee | View Only          |

---

# 📸 Screenshots

## Login Page

* Modern Gradient Login UI

## Dashboard

* KPI Cards
* Charts
* Inventory Table
* Sidebar Navigation

---

# 🔥 Future Enhancements

* Dark Mode
* Notification Panel
* Excel Export
* Barcode System
* Sales Module
* Purchase Orders
* Spring Boot Migration
* REST API
* Cloud Deployment

---

# 📄 License

This project is developed for educational and portfolio purposes.

---

# 🙌 Author
Hasain khan M  Rishikiran Korrapatti 
