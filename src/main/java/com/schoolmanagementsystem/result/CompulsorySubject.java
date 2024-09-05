package com.schoolmanagementsystem.result;

import com.schoolmanagementsystem.controllers.ClubController;
import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.users.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompulsorySubject implements Subject{
    private double Ban1;
    private double Ban2;
    private double Eng1;
    private double Eng2;

    private int Class;
    private int teacherID;

    public CompulsorySubject() {
        super();
    }

    public CompulsorySubject(int Class, double Ban1, double Ban2, double Eng1, double Eng2) {
        this.Ban1 = Ban1;
        this.Ban2 = Ban2;
        this.Eng1 = Eng1;
        this.Eng2 = Eng2;
        this.Class = Class;
    }

    public void setBan1(double Ban1) {
        this.Ban1 = Ban1;
    }

    private void setBan2(double Ban2) {
        this.Ban2 = Ban2;
    }

    private void setEng1(double Eng1) {
        this.Eng1 = Eng1;
    }

    private void setEng2(double Eng2) {
        this.Eng2 = Eng2;
    }

    public double getBan1() {
        return Ban1;
    }

    public double getBan2() {
        return Ban2;
    }

    public double getEng1() {
        return Eng1;
    }

    public double getEng2() {
        return Eng2;
    }

    @Override
    public ArrayList<Integer> getStudents() throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT studentID FROM studentName WHERE class = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, this.Class);
        ResultSet r = statement.executeQuery();

        ArrayList<Integer> records = new ArrayList<>();

        while (r.next()) {
            int record = r.getInt("studentID");
            records.add(record);
        }

        return records;
    }

    @Override
    public int getTeacher() {
        return this.teacherID;
    }
}
