package com.hospitalManagement;

import java.sql.*;
import java.util.Scanner;

public class hospitalManagement {
    private static final String url = "jdbc:mysql://localhost:3306/hospitalm";
    private static final String username = "root";
    private static final String password = "1234";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {

        }
        Scanner scn = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected db");
            Patient patient = new Patient(connection, scn);
            Doctors doctor = new Doctors(connection);
            while (true) {
                System.out.println("Welcome to ABC Hospital Management");
                System.out.println("1,Add Patient");
                System.out.println("2, View Patient");
                System.out.println("3, View Doctors");
                System.out.println("4,Book Appointment");
                System.out.println("5,Exit");
                System.out.println("Enter your choice");
                int choice = scn.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatient();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        bookappointment(patient, doctor, connection, scn);
                        System.out.println();
                        break;
                    case 5:
                        return;

                    default:
                        System.out.println("invalid Request");
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bookappointment(Patient patient, Doctors doctors, Connection connection, Scanner scn) {
        System.out.println("enter the patient Id: ");
        int patientId = scn.nextInt();

        System.out.println("enter the patient name: ");
        String patientname = scn.next();
        System.out.println("enter the doctor id: ");
        int doctorId = scn.nextInt();
        System.out.println("enter the appointment date(yyyy-mm-dd): ");
        String appointmentdate = scn.next();

        if (patient.getPatientId(patientId) && doctors.getdoctorid(doctorId)) {
            if (checkdoctoravailability(doctorId, appointmentdate, connection)) {
                String appointmentquery = "SELECT INTO appointments(patient_id,patient_name,appointment_date)VALUES (?,?,?,?)";
                try {
                    PreparedStatement ps = connection.prepareStatement(appointmentquery);
                    ps.setInt(1, patientId);
                    ps.setString(2, patientname);
                    ps.setInt(3, doctorId);
                    ps.setString(4, appointmentdate);

                    int rowsaaffected = ps.executeUpdate();
                    if (rowsaaffected > 0) {
                        System.out.println("Appointment Booked");
                    } else {
                        System.out.println("failed to book appointment");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkdoctoravailability(int doctorid, String appointmentdate, Connection connection) {
        try {
            String query = "SELECT COUNT(*) FROM appointments WHERE doctor id =? ANd appointment_date=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doctorid);
            ps.setString(2, appointmentdate);
            ResultSet r = ps.executeQuery();
            if (r.next()) {
                int count = r.getInt(1);
                if (count == 0) {
                    return true;
                } else
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

