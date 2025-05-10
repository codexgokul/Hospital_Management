A simple yet functional Hospital Management System developed in Java using JDBC (Java Database Connectivity) and SQL. This application allows for efficient management of hospital operations, including patient records, doctor information, appointments.

💻 Technologies Used Java – Core application logic

JDBC – Database connectivity

MySQL / SQL – Backend database

IntelliJ IDEA – for development

📋 Features Add, view, update, patient records

Manage doctor and staff details

Schedule and view appointments

Search functionality for patients and doctors

🛠️ Setup Instructions

Clone the repository bash Copy Edit git clone https://github.com/codexgokul/hospital_management.git cd hospital_management.
Import the project Open your preferred Java IDE (Eclipse, IntelliJ)
Import the project as a Java Project

Configure the Database Install and start MySQL Server
Create a database (e.g. hospitalm) sql Copy Edit CREATE DATABASE hospitalm; -- Run any provided SQL schema file to set up tables

Update Database Credentials In the Java source code, locate the JDBC configuration file/class and update the following:
java Copy Edit String url = "jdbc:mysql://localhost:3306/hospitalm"; String user = "root"; String password = "1234";

Run the Application Compile and run the main class
