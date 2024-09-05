package com.schoolmanagementsystem.users;

import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends People {
    private int clas;
    private String section;
    private int roll;

    public Student() {
        super();
    }

    public Student(int id, String name, String contact, String address, LocalDate date_of_birth, String gender,
            String father, String mother, String religion, int clas, String section, int roll) {
        super(id, name, contact, address, date_of_birth, gender, father, mother, religion);
        this.clas = clas;
        this.section = section;
        this.roll = roll;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getclas() {
        return this.clas;
    }

    public String getSection() {
        return this.section;
    }

    public int getRoll() {
        return this.roll;
    }

    public ArrayList<Pair<Integer, Integer>> allStudents(int classNum, String sectionNum) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT studentID, roll FROM studentInfo WHERE class = ? AND section = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, classNum);
        statement.setString(2, sectionNum);
        ResultSet r = statement.executeQuery();

        ArrayList<Pair<Integer, Integer>> records = new ArrayList<>();

        while (r.next()) {
            Pair<Integer, Integer> record;
            int id = r.getInt("studentID");
            int roll = r.getInt("roll");
            record = new Pair<>(id, roll);
            records.add(record);
        }

        return records;
    }
}
