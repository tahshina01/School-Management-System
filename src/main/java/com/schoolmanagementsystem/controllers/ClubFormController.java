package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.co_curricular.Club;
import com.schoolmanagementsystem.database.ClubCRUD;
import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClubFormController extends Controller implements Initializable {

    private static boolean confirmFlag;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField assistantGS;

    @FXML
    private TextField clubModerator;

    @FXML
    private TextField clubName;

    @FXML
    private Button confirm;

    @FXML
    private TextField executive_1;

    @FXML
    private TextField executive_2;

    @FXML
    private TextField executive_3;

    @FXML
    private TextField fund;

    @FXML
    private TextField generalSecretary;

    @FXML
    private TextField president;

    @FXML
    private TextField publicRelations;

    @FXML
    private TextField secretary;

    @FXML
    private TextField treasurer;

    @FXML
    private TextField vicePresident;

    @FXML
    private Label heading;

    public void handleClubFormPage(Event event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/clubForm.fxml", event);

        if (ClubController.isAddClubFlag()) {
            return;
        }

        ClubFormController controller = fxmlLoader.getController();
        controller.clubName.setDisable(true);

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT clubName,fund FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet r = statement.executeQuery();

        if (r.next()) {
            if (r.getString("fund") != null) {
                controller.fund.setText(r.getString("fund"));
            }
            if (r.getString("clubName") != null) {
                controller.clubName.setText(r.getString("clubName"));
            }
        }

        Club club = new Club();
        ArrayList<Pair<String, Integer>> executivePanel = club.ecPanel();
        for (int i = 0; i < executivePanel.size(); i++) {
            if (executivePanel.get(i).getValue() != 0) {
                if (i == 0)
                    controller.president.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 1)
                    controller.vicePresident.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 2)
                    controller.generalSecretary.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 3)
                    controller.treasurer.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 4)
                    controller.clubModerator.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 5)
                    controller.assistantGS.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 6)
                    controller.publicRelations.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 7)
                    controller.secretary.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 8)
                    controller.executive_1.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 9)
                    controller.executive_2.setText(String.valueOf(executivePanel.get(i).getValue()));
                if (i == 10)
                    controller.executive_3.setText(String.valueOf(executivePanel.get(i).getValue()));
            }
        }
    }

    public boolean fetchData(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String q = "SELECT * FROM studentInfo WHERE studentID = ?";
        PreparedStatement s = con.prepareStatement(q);
        s.setInt(1, id);
        ResultSet resultSet = s.executeQuery();

        if (resultSet.next()) {
            try {
                resultSet.close();
                s.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            try {
                resultSet.close();
                s.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @FXML
    void handleConfirm(ActionEvent event) throws SQLException, IOException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query;
        PreparedStatement statement;
        ResultSet r;

        ClubFormController.confirmFlag = true;

        if (clubName.getText().isEmpty()) {
            handleAlert("Club Name cannot be empty", "");
            ClubFormController.confirmFlag = false;
        } else {
            if (!president.getText().isEmpty()) {
                if (validateNum(president.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(president.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!vicePresident.getText().isEmpty()) {
                if (validateNum(vicePresident.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(vicePresident.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!generalSecretary.getText().isEmpty()) {
                if (validateNum(generalSecretary.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(generalSecretary.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!treasurer.getText().isEmpty()) {
                if (validateNum(treasurer.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(treasurer.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            } else if (!assistantGS.getText().isEmpty()) {
                if (validateNum(assistantGS.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(assistantGS.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!publicRelations.getText().isEmpty()) {
                if (validateNum(publicRelations.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(publicRelations.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!secretary.getText().isEmpty()) {
                if (validateNum(secretary.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(secretary.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!executive_1.getText().isEmpty()) {
                if (validateNum(executive_1.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(executive_1.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!executive_2.getText().isEmpty()) {
                if (validateNum(executive_2.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(executive_2.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!executive_3.getText().isEmpty()) {
                if (validateNum(executive_3.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    if (fetchData(Integer.parseInt(executive_3.getText()))) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    }
                }
            }
            if (!clubModerator.getText().isEmpty()) {

                if (validateNum(clubModerator.getText())) {
                    handleAlert("ID must be a number", "");
                    ClubFormController.confirmFlag = false;
                } else {
                    query = "SELECT * FROM loginInfo where ID = ?";
                    statement = con.prepareStatement(query);
                    statement.setInt(1, Integer.parseInt(clubModerator.getText()));
                    r = statement.executeQuery();

                    if (!r.next()) {
                        handleAlert("This id doesn't exist", "");
                        ClubFormController.confirmFlag = false;
                    } else {
                        if (!Objects.equals(r.getString("userType"), "Teacher")) {
                            handleAlert("Invalid Teacher ID", "");
                            ClubFormController.confirmFlag = false;
                        }
                    }
                }
            }
            if (!fund.getText().isEmpty()) {
                if (validateNum(fund.getText())) {
                    handleAlert("fund must be a number", "");
                    ClubFormController.confirmFlag = false;
                }
            }
        }

        String nameOfClub = clubName.getText();
        String presidentID = (president.getText().isEmpty() ? null : president.getText());
        String vicePresidentID = (vicePresident.getText().isEmpty() ? null : vicePresident.getText());
        String generalSecretaryID = (generalSecretary.getText().isEmpty() ? null : generalSecretary.getText());
        String treasurerID = (treasurer.getText().isEmpty() ? null : treasurer.getText());
        String clubModeratorID = (clubModerator.getText().isEmpty() ? null : clubModerator.getText());
        String assistantGSID = (assistantGS.getText().isEmpty() ? null : assistantGS.getText());
        String publicRelationsID = (publicRelations.getText().isEmpty() ? null : publicRelations.getText());
        String secretaryID = (secretary.getText().isEmpty() ? null : secretary.getText());
        String executive_1_ID = (executive_1.getText().isEmpty() ? null : executive_1.getText());
        String executive_2_ID = (executive_2.getText().isEmpty() ? null : executive_2.getText());
        String executive_3_ID = (executive_3.getText().isEmpty() ? null : executive_3.getText());
        String fundAmount = (fund.getText().isEmpty() ? null : fund.getText());

        Club club = new Club(nameOfClub, presidentID, vicePresidentID, generalSecretaryID, treasurerID, clubModeratorID,
                assistantGSID, publicRelationsID, secretaryID, executive_1_ID, executive_2_ID, executive_3_ID,
                fundAmount);

        if (ClubFormController.confirmFlag) {
            if (ClubController.isAddClubFlag()) {
                String query2 = "SELECT * FROM club WHERE clubName = ?";
                PreparedStatement statement2 = con.prepareStatement(query2);
                statement2.setString(1, nameOfClub);
                ResultSet r1 = statement2.executeQuery();

                if (r1.next()) {
                    ClubFormController.confirmFlag = false;
                    handleAlert("This club already exists", "");
                }
            }
        }

        if (ClubFormController.confirmFlag) {
            ClubCRUD crud = new ClubCRUD();
            crud.addOrUpdateClub(club);
            String message;
            if (ClubController.isAddClubFlag()) {
                message = "Club is added successfully";
            } else {
                message = "Club is updated successfully";
            }
            ClubController clubController = new ClubController();
            if (handleAlert(message, "")) {
                clubController.handleClubPage(event);
            } else {
                clubController.handleClubPage(event);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
