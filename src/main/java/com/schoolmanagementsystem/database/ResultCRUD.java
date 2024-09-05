package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.controllers.Controller;
import com.schoolmanagementsystem.controllers.StudentResultController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultCRUD {
    public void addOrUpdateResult(ArrayList<Integer> allMarks) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery;
        if (StudentResultController.getSelectedExam() == 1) {
            selectQuery = "SELECT * FROM resultinfo_HalfYearly WHERE ID = ?";
        } else {
            selectQuery = "SELECT * FROM resultinfo_YearFinal WHERE ID = ?";
        }
        PreparedStatement statement = con.prepareStatement(selectQuery);

        statement.setInt(1, Controller.getRequiredID());

        ResultSet r = statement.executeQuery();

        String query;
        StudentResultController controller = new StudentResultController();
        ArrayList<String> allSubjects = controller.allSubjects(Controller.getRequiredID());

        if (!r.next()) {
            if (StudentResultController.getSelectedExam() == 1) {
                query = "INSERT INTO resultinfo_HalfYearly (ID) VALUES (?)";
            } else {
                query = "INSERT INTO resultinfo_YearFinal (ID) VALUES (?)";
            }
            statement = con.prepareStatement(query);
            statement.setInt(1, Controller.getRequiredID());
            statement.executeUpdate();
        }

        if (StudentResultController.getSelectedExam() == 1) {
            for (int i = 0; i < allSubjects.size(); i++) {
                query = "UPDATE resultinfo_HalfYearly SET " + allSubjects.get(i) + " = ? WHERE ID = ?";
                statement = con.prepareStatement(query);
                statement.setInt(2, Controller.getRequiredID());
                statement.setInt(1, allMarks.get(i));
                statement.executeUpdate();
            }
        } else {
            for (int i = 0; i < allSubjects.size(); i++) {
                query = "UPDATE resultinfo_YearFinal SET " + allSubjects.get(i) + " = ? WHERE ID = ?";
                statement = con.prepareStatement(query);
                statement.setInt(2, Controller.getRequiredID());
                statement.setInt(1, allMarks.get(i));
                statement.executeUpdate();
            }
        }
    }

    public void resultAlgoCrud(int halfYearlyWeight, int yearFinalWeight, String priority) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM resultAlgo WHERE demoID = ?";

        PreparedStatement statement = con.prepareStatement(query);

        statement.setInt(1, 1);

        ResultSet r = statement.executeQuery();

        if (!r.next()) {
            query = "INSERT INTO resultAlgo (demoID) VALUES (?)";
            statement = con.prepareStatement(query);
            statement.setInt(1, 1);
            statement.executeUpdate();
        }

        query = "UPDATE resultAlgo SET halfYearlyWeight = ?, yearFinalWeight = ?, priority = ? WHERE demoID = ?";
        statement = con.prepareStatement(query);
        statement.setInt(1, halfYearlyWeight);
        statement.setInt(2, yearFinalWeight);
        statement.setString(3, priority);
        statement.setInt(4, 1);
        statement.executeUpdate();
    }
}
