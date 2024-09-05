package com.schoolmanagementsystem.models;

import com.schoolmanagementsystem.controllers.loginController;
import com.schoolmanagementsystem.database.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginValidator {

    public static int log(loginController login) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query1 = "SELECT * FROM loginInfo WHERE ID = ?";
        String query2 = "SELECT * FROM loginInfo WHERE ID = ? AND password = ?";
        PreparedStatement statement1 = con.prepareStatement(query1);
        PreparedStatement statement2 = con.prepareStatement(query2);

        statement1.setInt(1, login.getId());
        statement2.setInt(1, login.getId());
        statement2.setString(2, login.getPass());

        ResultSet r1 = statement1.executeQuery();
        ResultSet r2 = statement2.executeQuery();

        if (!r1.next()) {
            // login.getLoggable().setText("Invalid Id. Register first");
            return 0;
        } else {
            if (!r2.next()) {
                // login.getLoggable().setText("Invalid password. Try again");
                return 1;
            } else {
                loginController.setLoggedInID(r2.getInt("ID"));
                loginController.setLoggedInPerson(r2.getString("userType"));
                return 2;
            }
        }

    }
}