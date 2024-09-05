module com.schoolmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.schoolmanagementsystem to javafx.fxml;
    opens com.schoolmanagementsystem.controllers to javafx.fxml;
    opens com.schoolmanagementsystem.users to javafx.fxml;

    exports com.schoolmanagementsystem;
    exports com.schoolmanagementsystem.controllers;
    exports com.schoolmanagementsystem.users;
    exports com.schoolmanagementsystem.database;
    exports com.schoolmanagementsystem.models;

    opens com.schoolmanagementsystem.models to javafx.fxml;

    exports com.schoolmanagementsystem.database.routineDB;
    exports com.schoolmanagementsystem.co_curricular;
    exports com.schoolmanagementsystem.result;

    opens com.schoolmanagementsystem.result to javafx.fxml;
}
