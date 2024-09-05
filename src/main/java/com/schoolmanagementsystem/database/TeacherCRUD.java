package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.controllers.RoutineController;
import com.schoolmanagementsystem.database.routineDB.ConnectRoutineDB;
import com.schoolmanagementsystem.users.Teacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;

public class TeacherCRUD {

    public void addTeacher(Teacher emp, String imgPath) throws SQLException, FileNotFoundException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String insertQuery = "INSERT INTO employeeInfo (employeeID, name, profession, fatherName, motherName, subject, address, dateOfBirth, joiningDate, gender, contactNumber, religion, profilePicture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = con.prepareStatement(insertQuery);

        java.sql.Date sqlDate = java.sql.Date.valueOf(emp.getDateofbirth());

        LocalDate localDate = LocalDate.now();
        Date joinDate = Date.valueOf(localDate);

        // set values for the insert query
        statement.setInt(1, emp.getId());
        statement.setString(2, emp.getName());
        statement.setString(3, emp.getDesignation());
        statement.setString(4, emp.getFather());
        statement.setString(5, emp.getMother());
        statement.setString(6, emp.getSubject());
        statement.setString(7, emp.getAdderss());
        statement.setDate(8, sqlDate);
        statement.setDate(9, joinDate);
        statement.setString(10, emp.getGender());
        statement.setString(11, emp.getContact());
        statement.setString(12, emp.getReligion());

        FileInputStream fis = null;
        File file = new File(imgPath); // the path to the image file
        fis = new FileInputStream(file);
        statement.setBinaryStream(13, fis, (int) file.length());

        statement.executeUpdate();
    }

    public void updateTeacher(Teacher emp, String imgPath) throws SQLException, FileNotFoundException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String updateQuery;

        if (imgPath != null) {
            updateQuery = "UPDATE employeeInfo SET name = ?, profession = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ?, subject = ?, profilePicture = ? WHERE employeeID = ?";
        } else {
            updateQuery = "UPDATE employeeInfo SET name = ?, profession = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ?, subject = ? WHERE employeeID = ?";
        }
        PreparedStatement statement = con.prepareStatement(updateQuery);

        java.sql.Date sqlDate = java.sql.Date.valueOf(emp.getDateofbirth());

        if (imgPath != null) {
            statement.setInt(12, emp.getId());
        } else {
            statement.setInt(11, emp.getId());
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
        statement.setString(10, emp.getSubject());

        if (imgPath != null) {
            FileInputStream fis = null;
            File file = new File(imgPath); // the path to the image file
            fis = new FileInputStream(file);
            statement.setBinaryStream(11, fis, (int) file.length());
        }

        statement.executeUpdate();
    }

    public void deleteTeacher(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteQuery = "DELETE FROM employeeInfo WHERE employeeID = ?";

        PreparedStatement statement = con.prepareStatement(deleteQuery);

        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
