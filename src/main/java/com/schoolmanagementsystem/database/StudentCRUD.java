package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.controllers.Controller;
import com.schoolmanagementsystem.users.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentCRUD {

    private PreparedStatement statement;

    public void addStudent(Student st, String imgPath) throws SQLException, FileNotFoundException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String insertQuery = "INSERT INTO studentInfo (name, class, roll, section, fatherName, motherName, address, dateOfBirth, gender, contactNumber, religion, profilePicture, studentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        statement = con.prepareStatement(insertQuery);

        crudHelper(st, imgPath);
    }

    public void updateStudent(Student st, String imgpath) throws SQLException, FileNotFoundException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String updateQuery;

        if (imgpath != null) {
            updateQuery = "UPDATE studentInfo SET name = ?, class = ?, roll = ?, section = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ?, profilePicture = ? WHERE studentID = ?";
        } else {
            updateQuery = "UPDATE studentInfo SET name = ?, class = ?, roll = ?, section = ?, fatherName = ?, motherName = ?, address = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, religion = ? WHERE studentID = ?";
        }
        statement = con.prepareStatement(updateQuery);

        crudHelper(st, imgpath);
    }

    public void crudHelper(Student st, String imgPath) throws SQLException, FileNotFoundException {

        java.sql.Date sqlDate = java.sql.Date.valueOf(st.getDateofbirth());

        // set values for the insert query
        if (imgPath != null) {
            statement.setInt(13, st.getId());
        } else {
            statement.setInt(12, st.getId());
        }
        statement.setString(1, st.getName());
        statement.setInt(2, st.getclas());
        statement.setInt(3, st.getRoll());
        statement.setString(4, st.getSection());
        statement.setString(5, st.getFather());
        statement.setString(6, st.getMother());
        statement.setString(7, st.getAdderss());
        statement.setDate(8, sqlDate);
        statement.setString(9, st.getGender());
        statement.setString(10, st.getContact());
        statement.setString(11, st.getReligion());

        if (imgPath != null) {
            FileInputStream fis = null;
            File file = new File(imgPath); // the path to the image file
            fis = new FileInputStream(file);
            statement.setBinaryStream(12, fis, (int) file.length());
        }

        statement.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteQuery = "DELETE FROM studentInfo WHERE studentID = ?";

        PreparedStatement statement = con.prepareStatement(deleteQuery);

        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
