package com.schoolmanagementsystem.database.routineDB;

import com.schoolmanagementsystem.controllers.RoutineController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutineCRUD {

    public void updateRoutine(String[] sub, String[] teacher) throws SQLException {
        ConnectRoutineDB db = new ConnectRoutineDB();
        Connection con = db.getCon();

        String tableName = "class_" + RoutineController.getSelectedClass() + "_"
                + RoutineController.getSelectedSection();

        int i;
        for (i = 1; i <= 25; i++) {
            String updateQuery = "UPDATE " + tableName + " SET subject = ?, teacher = ? where periodNo = ?";
            PreparedStatement statement = con.prepareStatement(updateQuery);
            statement.setString(1, sub[i]);
            statement.setString(2, teacher[i]);
            statement.setInt(3, i);
            statement.executeUpdate();
        }
    }
}
