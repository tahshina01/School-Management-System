package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.co_curricular.Club;
import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClubMemberController extends Controller implements Initializable {

    @FXML
    private Label nameOfSelectedClub;

    @FXML
    private Label id_1;

    @FXML
    private Label id_10;

    @FXML
    private Label id_11;

    @FXML
    private Label id_12;

    @FXML
    private Label id_13;

    @FXML
    private Label id_14;

    @FXML
    private Label id_15;

    @FXML
    private Label id_2;

    @FXML
    private Label id_3;

    @FXML
    private Label id_4;

    @FXML
    private Label id_5;

    @FXML
    private Label id_6;

    @FXML
    private Label id_7;

    @FXML
    private Label id_8;

    @FXML
    private Label id_9;

    @FXML
    private Label name_1;

    @FXML
    private Label name_10;

    @FXML
    private Label name_11;

    @FXML
    private Label name_12;

    @FXML
    private Label name_13;

    @FXML
    private Label name_14;

    @FXML
    private Label name_15;

    @FXML
    private Label name_2;

    @FXML
    private Label name_3;

    @FXML
    private Label name_4;

    @FXML
    private Label name_5;

    @FXML
    private Label name_6;

    @FXML
    private Label name_7;

    @FXML
    private Label name_8;

    @FXML
    private Label name_9;

    @FXML
    private Button next;

    @FXML
    private Button previous;

    private static int currentIndex;

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        ClubController clubController = new ClubController();
        clubController.handleClubPage(event);
    }

    @FXML
    void handleNext(ActionEvent event) throws SQLException, IOException {
        ClubMemberController.currentIndex += 15;
        handleClubMemberPage(event);
    }

    @FXML
    void handlePrevious(ActionEvent event) throws SQLException, IOException {
        ClubMemberController.currentIndex -= 15;
        handleClubMemberPage(event);
    }

    public static void setCurrentIndex(int currentIndex) {
        ClubMemberController.currentIndex = currentIndex;
    }

    public void handleClubMemberPage(Event event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/clubMembers.fxml", event);
        ClubMemberController controller = fxmlLoader.getController();

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT clubName FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet r = statement.executeQuery();
        if(r.next()) {
            controller.nameOfSelectedClub.setText(r.getString("clubName"));
        }

        Club club = new Club();
        ClubController clubController = new ClubController();

        ArrayList<Integer> allMember = club.allMember();
        for (int i = ClubMemberController.currentIndex; i < ClubMemberController.currentIndex + 15; i++) {
            if (i == allMember.size()) {
                break;
            } else if (i == ClubMemberController.currentIndex) {
                controller.id_1.setText(String.valueOf(allMember.get(i)));
                controller.name_1.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 1) {
                controller.id_2.setText(String.valueOf(allMember.get(i)));
                controller.name_2.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 2) {
                controller.id_3.setText(String.valueOf(allMember.get(i)));
                controller.name_3.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 3) {
                controller.id_4.setText(String.valueOf(allMember.get(i)));
                controller.name_4.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 4) {
                controller.id_5.setText(String.valueOf(allMember.get(i)));
                controller.name_5.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 5) {
                controller.id_6.setText(String.valueOf(allMember.get(i)));
                controller.name_6.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 6) {
                controller.id_7.setText(String.valueOf(allMember.get(i)));
                controller.name_7.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 7) {
                controller.id_8.setText(String.valueOf(allMember.get(i)));
                controller.name_8.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 8) {
                controller.id_9.setText(String.valueOf(allMember.get(i)));
                controller.name_9.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 9) {
                controller.id_10.setText(String.valueOf(allMember.get(i)));
                controller.name_10.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 10) {
                controller.id_11.setText(String.valueOf(allMember.get(i)));
                controller.name_11.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 11) {
                controller.id_12.setText(String.valueOf(allMember.get(i)));
                controller.name_12.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 12) {
                controller.id_13.setText(String.valueOf(allMember.get(i)));
                controller.name_13.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 13) {
                controller.id_14.setText(String.valueOf(allMember.get(i)));
                controller.name_14.setText(clubController.fetchData(allMember.get(i)));
            } else if (i == ClubMemberController.currentIndex + 14) {
                controller.id_15.setText(String.valueOf(allMember.get(i)));
                controller.name_15.setText(clubController.fetchData(allMember.get(i)));
            }
        }

        if (ClubMemberController.currentIndex == 0) {
            controller.previous.setVisible(false);
        }
        if (ClubMemberController.currentIndex + 15 >= allMember.size()) {
            controller.next.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
