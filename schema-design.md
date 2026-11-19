
# MySQL Database Schema Design
## Doctor Patient Management System

## 1. Database Overview

The system uses MySQL to store and manage information about doctors, patients, appointments, and administrators.

## 2. Database Tables

### Users
Stores login and account information.

| Column | Data Type | Description |
|---|---|---|
| user_id | INT PRIMARY KEY AUTO_INCREMENT | Unique user ID |
| username | VARCHAR(100) | Username |
| email | VARCHAR(150) UNIQUE | User email |
| password | VARCHAR(255) | Hashed password |
| role | ENUM('Doctor','Patient','Admin') | User role |

### Doctors
Stores doctor information.

| Column | Data Type | Description |
|---|---|---|
| doctor_id | INT PRIMARY KEY AUTO_INCREMENT | Doctor ID |
| user_id | INT UNIQUE | Reference to Users |
| specialization | VARCHAR(100) | Medical specialization |
| phone | VARCHAR(20) | Contact number |

### Patients
Stores patient information.

| Column | Data Type | Description |
|---|---|---|
| patient_id | INT PRIMARY KEY AUTO_INCREMENT | Patient ID |
| user_id | INT UNIQUE | Reference to Users |
| date_of_birth | DATE | Birth date |
| gender | VARCHAR(20) | Gender |
| phone | VARCHAR(20) | Contact number |

### Appointments
Stores appointment information.

| Column | Data Type | Description |
|---|---|---|
| appointment_id | INT PRIMARY KEY AUTO_INCREMENT | Appointment ID |
| doctor_id | INT | Reference to Doctors |
| patient_id | INT | Reference to Patients |
| appointment_date | DATETIME | Appointment date and time |
| status | VARCHAR(30) | Appointment status |

### Medical_Records
Stores patient medical information.

| Column | Data Type | Description |
|---|---|---|
| record_id | INT PRIMARY KEY AUTO_INCREMENT | Record ID |
| patient_id | INT | Reference to Patients |
| doctor_id | INT | Reference to Doctors |
| diagnosis | TEXT | Medical diagnosis |
| prescription | TEXT | Prescribed medication |
| record_date | DATE | Record date |

## 3. Relationships

- One User can have one Doctor or Patient profile.
- One Doctor can have many Appointments.
- One Patient can have many Appointments.
- One Patient can have many Medical Records.
- One Doctor can create many Medical Records.

## 4. Database Relationships and Constraints

- Primary keys uniquely identify each record.
- Foreign keys connect related tables.
- Email addresses must be unique.
- Doctor and Patient profiles reference the Users table.
- Appointment records reference existing doctors and patients.

## 5. Database Technology

- Database Management System: MySQL
- Query Language: SQL
- Database Design: Relational Database Model
