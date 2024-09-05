package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.models.Fee;

import java.sql.*;

public class FeeCRUD {
    public void addOrUpdateFee(Fee fee) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT * FROM studentFees WHERE studentID = ?";
        PreparedStatement statement = con.prepareStatement(selectQuery);

        statement.setInt(1, fee.getStudentID());

        ResultSet r = statement.executeQuery();

        String query;

        if (r.next()) {
            query = "UPDATE studentFees SET feeAmount = ?, paymentDate = ? WHERE studentID = ?";
        } else {
            query = "INSERT INTO studentFees (feeAmount, paymentDate, studentID) VALUES (?, ?, ?)";
        }

        PreparedStatement state = con.prepareStatement(query);
        state.setInt(1, fee.getFee());
        state.setDate(2, Date.valueOf(fee.getPaymentDate()));
        state.setInt(3, fee.getStudentID());

        state.executeUpdate();
    }
}
