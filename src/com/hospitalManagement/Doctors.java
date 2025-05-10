package com.hospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "SELECT * from doctors";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
            System.out.println("doctors");
            System.out.println("_____*****_____");
            System.out.println("|doctor id | Name | dept|");
            while (r.next()) ;
            {
                int id = r.getInt("id");
                String name = r.getString("name");
                String department = r.getString("dept");
                System.out.printf("|%-10s|%-7s|%-14s\n", id, name, department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getdoctorid(int id) {
        try {
            String query = "Select * from doctors where id=?";

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