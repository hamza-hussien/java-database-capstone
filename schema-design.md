MySQL Database Schema Design
1. Users Table
Column	Data Type	Description
user_id	INT PRIMARY KEY AUTO_INCREMENT	Unique user ID
name	VARCHAR(100)	Full name
email	VARCHAR(100) UNIQUE	Email address
password	VARCHAR(255)	Hashed password
role	ENUM('admin','doctor','patient')	User role
created_at	TIMESTAMP	Account creation date
2. Doctors Table
Column	Data Type	Description
doctor_id	INT PRIMARY KEY AUTO_INCREMENT	Doctor ID
user_id	INT FOREIGN KEY	References Users
specialization	VARCHAR(100)	Medical specialization
phone	VARCHAR(20)	Phone number
availability	VARCHAR(255)	Doctor availability
3. Patients Table
Column	Data Type	Description
patient_id	INT PRIMARY KEY AUTO_INCREMENT	Patient ID
user_id	INT FOREIGN KEY	References Users
date_of_birth	DATE	Date of birth
gender	VARCHAR(20)	Gender
phone	VARCHAR(20)	Phone number
address	VARCHAR(255)	Address
4. Appointments Table
Column	Data Type	Description
appointment_id	INT PRIMARY KEY AUTO_INCREMENT	Appointment ID
doctor_id	INT FOREIGN KEY	References Doctors
patient_id	INT FOREIGN KEY	References Patients
appointment_date	DATETIME	Appointment date and time
status	ENUM('pending','confirmed','cancelled','completed')	Appointment status
reason	TEXT	Appointment reason
5. Medical Records Table
Column	Data Type	Description
record_id	INT PRIMARY KEY AUTO_INCREMENT	Record ID
patient_id	INT FOREIGN KEY	References Patients
doctor_id	INT FOREIGN KEY	References Doctors
diagnosis	TEXT	Medical diagnosis
prescription	TEXT	Prescribed medication
created_at	TIMESTAMP	Record creation date
Relationships
One User can have one Doctor or Patient profile.
One Doctor can have multiple Appointments.
One Patient can have multiple Appointments.
One Patient can have multiple Medical Records.
One Doctor can create multiple Medical Records.
Database
Database Management System: MySQL
Primary Keys: Auto-increment integer IDs.
Foreign Keys: Used to maintain referential integrity.
Passwords: Stored as secure hashes.
