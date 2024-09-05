package com.schoolmanagementsystem.database.routineDB;

import java.sql.*;

public class ConnectRoutineDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/RoutineDatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "20NS01";

    private final Connection con;

    public ConnectRoutineDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
