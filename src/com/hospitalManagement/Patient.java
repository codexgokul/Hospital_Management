package com.hospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scn;

    public Patient(Connection connection, Scanner scn) {
        this.connection = connection;
        this.scn = scn;
    }

    public void addPatient() {
        System.out.println("patient details : ");
        String name = scn.next();
        System.out.println("Enter the Patient age: ");
        int age = scn.nextInt();
        System.out.println("enter patient gender: ");
        String gender = scn.next();

        try {
            String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("patient added");
            } else {
                System.out.println("Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewPatient() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
            System.out.println("patients");
            System.out.println("______****______");
            System.out.println("patients id  | Name  | Age | Gender |");
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                int age = r.getInt("age");
                String gender = r.getString("gender");
                System.out.printf("|%-12s|%-8s|%-8s|%-10s|\n", id, name, age, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientId(int id) {
        String query = "SELECT * fROM patients where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet r = ps.executeQuery();
            if (r.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}