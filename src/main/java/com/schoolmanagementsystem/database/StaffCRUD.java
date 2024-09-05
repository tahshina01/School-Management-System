package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.users.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class StaffCRUD {

    private PreparedStatement statement;

    public void addStaff(Staff emp, String imgPath) throws SQLException, FileNotFoundException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String insertQuery = "INSERT INTO employeeInfo (employeeID, name, profession, fatherName, motherName, address, dateOfBirth, joiningDate, gender, contactNumber, religion, profilePicture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = con.prepareStatement(insertQuery);

        java.sql.Date sqlDate = java.sql.Date.valueOf(emp.getDateofbirth());

        LocalDate localDate = LocalDate.now();
        Date joinDate = Date.valueOf(localDate);

        statement.setInt(1, emp.getId());
        statement.setString(2, emp.getName());
        statement.setString(3, emp.getDesignation());
        statement.setString(4, emp.getFather());
        statement.setString(5, emp.getMother());
        statement.setString(6, emp.getAdderss());
        statement.setDate(7, sqlDate);
        statement.setDate(8, joinDate);
        statement.setString(9, emp.getGender());
        statement.setString(10, emp.getContact());
        statement.setString(11, emp.getReligion());

        if (imgPath != null) {
            FileInputStream fis = null;
            File file = new File(imgPath); // the path to the image file
            fis = new FileInputStream(file);
            statement.setBinaryStream(12, fis, (int) file.length());
        }

        statement.executeUpdate();
    }

    public void updateStaff(Staff emp, String imgPath) throws SQLException, FileNotFoundException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String updateQuery;

        if (imgPath != null) {
            updateQuery = "UPDATE employeeInfo SET name = ?, profession = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ?, profilePicture = ? WHERE employeeID = ?";
        } else {
            updateQuery = "UPDATE employeeInfo SET name = ?, profession = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ? WHERE employeeID = ?";
        }
        statement = con.prepareStatement(updateQuery);

        java.sql.Date sqlDate = java.sql.Date.valueOf(emp.getDateofbirth());

        if (imgPath != null) {
            statement.setInt(11, emp.getId());
        } else {
            statement.setInt(10, emp.getId());
        }

        statement.setString(1, emp.getName());
        statement.setString(2, emp.getDesignation());
        statement.setString(3, emp.getFather());
        statement.setString(4, emp.getMother());
        statement.setString(5, emp.getAdderss());
        statement.setDate(6, sqlDate);
        statement.setString(7, emp.getGender());
        statement.setString(8, emp.getContact());
        statement.setString(9, emp.getReligion());

        if (imgPath != null) {
            FileInputStream fis = null;
            File file = new File(imgPath); // the path to the image file
            fis = new FileInputStream(file);
            statement.setBinaryStream(10, fis, (int) file.length());
        }

        statement.executeUpdate();
    }

    public void deleteStaff(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteQuery = "DELETE FROM employeeInfo WHERE employeeID = ?";

        PreparedStatement statement = con.prepareStatement(deleteQuery);

        statement.setInt(1, id);
        statement.executeUpdate();
    }

}
