package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.co_curricular.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClubMemberEntryController extends Controller implements Initializable {

    private static boolean addFund;
    private static boolean spendFund;
    @FXML
    private Button apply;

    @FXML
    private Button cross;

    @FXML
    private TextField inputField;

    @FXML
    private Label invalid;

    @FXML
    private Label label;

    @FXML
    private MenuButton selectMenu;

    @FXML
    void handleApply(ActionEvent event) throws SQLException {
        if (inputField.getText().isEmpty()) {
            invalid.setText("Empty input");
        } else {

            Club club = new Club();

            if (ClubController.isFundFlag()) {
                if (validateNum(inputField.getText())) {
                    invalid.setText("Fund must be a number");
                } else {
                    if (!ClubMemberEntryController.addFund && !ClubMemberEntryController.spendFund) {
                        invalid.setText("Select add or spend");
                    } else {
                        if (ClubMemberEntryController.addFund) {
                            club.addFund(inputField.getText());
                            invalid.setText("Fund added successfully");
                        } else {
                            if (club.spendFund(inputField.getText())) {
                                invalid.setText("Amount deducted successfully");
                            } else {
                                invalid.setText("Insufficient fund");
                            }
                        }
                    }
                }
            } else {
                if (validateNum(inputField.getText())) {
                    invalid.setText("ID must be a number");
                } else {
                    if (ClubController.isAddMemberFlag()) {
                        invalid.setText(club.addMember(Integer.parseInt(inputField.getText())));
                    } else if (ClubController.isDeleteMemberFlag()) {
                        invalid.setText(club.removeMember(Integer.parseInt(inputField.getText())));
                    }
                }
            }
        }
    }

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        ClubController controller = new ClubController();
        controller.handleClubPage(event);
    }

    @FXML
    void handleInvalid(KeyEvent event) {
        invalid.setText("");
    }

    @FXML
    void handleAddFund(ActionEvent event) {
        ClubMemberEntryController.addFund = true;
        ClubMemberEntryController.spendFund = false;
        selectMenu.setText("Add Fund");
    }

    @FXML
    void handleSpendFund(ActionEvent event) {
        ClubMemberEntryController.addFund = false;
        ClubMemberEntryController.spendFund = true;
        selectMenu.setText("Spend Fund");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!ClubController.isFundFlag()) {
            selectMenu.setVisible(false);
        } else {
            label.setText("Enter fund amount");
        }
        ClubMemberEntryController.addFund = false;
        ClubMemberEntryController.spendFund = false;
    }
}
