package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.users.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginCRUD {

    public void addNewLoginInfo(Employee emp, String userType) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String insertQuery = "INSERT INTO loginInfo (ID, userType, password) VALUES (?, ?, ?)";

        PreparedStatement statement = con.prepareStatement(insertQuery);

        // set values for the insert query
        statement.setInt(1, emp.getId());
        statement.setString(2, userType);
        statement.setString(3, emp.getPass());

        statement.executeUpdate();
    }

    public void updateLoginInfo(Employee emp, String userType) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String updateQuery = "UPDATE loginInfo SET password = ? WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(updateQuery);

        statement.setInt(2, emp.getId());
        statement.setString(1, emp.getPass());

        statement.executeUpdate();
    }

    public void deleteLoginInfo(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteQuery = "DELETE FROM loginInfo WHERE ID = ?";

        PreparedStatement statement = con.prepareStatement(deleteQuery);

        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
