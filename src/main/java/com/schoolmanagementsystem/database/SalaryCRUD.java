package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.models.Salary;

import java.sql.*;

public class SalaryCRUD {
    public void addOrUpdateSalary(Salary salary, int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT * FROM employeeSalary WHERE employeeID = ?";
        PreparedStatement statement = con.prepareStatement(selectQuery);

        statement.setInt(1, id);

        ResultSet r = statement.executeQuery();

        String query;

        if (r.next()) {
            query = "UPDATE employeeSalary SET baseSalary = ?, houseRent = ?, medical = ?, noOfChild = ?, receivedDate = ? WHERE employeeID = ?";
        } else {
            query = "INSERT INTO employeeSalary (baseSalary, houseRent, medical, noOfChild, receivedDate, employeeID) VALUES (?, ?, ?, ?, ?, ?)";
        }

        PreparedStatement state = con.prepareStatement(query);
        state.setInt(1, salary.getBaseSalary());
        state.setInt(2, salary.getRentPercentage());
        state.setInt(3, salary.getMedicalAllowance());
        state.setInt(4, salary.getNoOfChildren());
        state.setDate(5, Date.valueOf(salary.getDate()));
        state.setInt(6, id);

        state.executeUpdate();
    }
}
