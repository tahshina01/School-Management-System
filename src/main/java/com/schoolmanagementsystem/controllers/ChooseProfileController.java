package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChooseProfileController extends Controller implements Initializable {

    @FXML
    private Button otherProfile;

    @FXML
    private Button self;

    public void handleSelf(ActionEvent actionEvent) throws IOException, SQLException {
        Controller.requiredID = loginController.getLoggedInID();
        Controller.allUserFlag = false;
        StaffProfileController cont = new StaffProfileController();
        cont.handleStaffProfile(actionEvent, loginController.getLoggedInID());
    }

    public void handleOther(ActionEvent actionEvent) throws SQLException, IOException {
        Controller.allUserFlag = true;
        AllMembersController.setAllUserFlag(true);
        AllMembersController controller = new AllMembersController();
        controller.handleAllMemberPage(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
