package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ClubCRUD;
import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClubController extends Controller implements Initializable {

    private static int selectedClub;

    private static boolean addClubFlag;

    private static boolean updateClubFlag;

    private static boolean fundFlag;

    private static boolean addMemberFlag;

    private static boolean deleteMemberFlag;
    @FXML
    private AnchorPane clubPage;

    public static boolean isFundFlag() {
        return fundFlag;
    }

    public static void setFundFlag(boolean fundFlag) {
        ClubController.fundFlag = fundFlag;
    }

    public static boolean isAddMemberFlag() {
        return addMemberFlag;
    }

    public static void setAddMemberFlag(boolean addMemberFlag) {
        ClubController.addMemberFlag = addMemberFlag;
    }

    public static boolean isDeleteMemberFlag() {
        return deleteMemberFlag;
    }

    public static void setDeleteMemberFlag(boolean deleteMemberFlag) {
        ClubController.deleteMemberFlag = deleteMemberFlag;
    }

    public static int getSelectedClub() {
        return selectedClub;
    }

    public static void setSelectedClub(int selectedClub) {
        ClubController.selectedClub = selectedClub;
    }

    public static boolean isAddClubFlag() {
        return addClubFlag;
    }

    public static void setAddClubFlag(boolean addClubFlag) {
        ClubController.addClubFlag = addClubFlag;
    }

    public static boolean isUpdateClubFlag() {
        return updateClubFlag;
    }

    public static void setUpdateClubFlag(boolean updateClubFlag) {
        ClubController.updateClubFlag = updateClubFlag;
    }

    @FXML
    private Button addMember;

    @FXML
    private Button allMembers;

    @FXML
    private Button deleteMember;

    @FXML
    Button updateClub;

    @FXML
    private Button addClub;

    @FXML
    private Label assistantGS;

    @FXML
    private Button club_2;

    @FXML
    private Label clubModerator;

    @FXML
    private Button club_1;

    @FXML
    private Button club_10;

    @FXML
    private Button club_11;

    @FXML
    private Button club_12;

    @FXML
    private Button club_3;

    @FXML
    private Button club_4;

    @FXML
    private Button club_5;

    @FXML
    private Button club_6;

    @FXML
    private Button club_7;

    @FXML
    private Button club_8;

    @FXML
    private Button club_9;

    @FXML
    private Button deleteClub;

    @FXML
    private Label executive_1;

    @FXML
    private Label executive_2;

    @FXML
    private Label executive_3;

    @FXML
    private Button fund;

    @FXML
    private Label fundAmount;

    @FXML
    private Label generalSecretary;

    @FXML
    private Label president;

    @FXML
    private Label publicRelations;

    @FXML
    private Label secretary;

    @FXML
    private Label treasurer;

    @FXML
    private Label vicePresident;

    @FXML
    void handleAddClub(ActionEvent event) throws IOException, SQLException {
        ClubController.addClubFlag = true;
        ClubController.updateClubFlag = false;
        ClubFormController controller = new ClubFormController();
        controller.handleClubFormPage(event);
    }

    @FXML
    void handleAddMember(ActionEvent event) throws IOException {
        ClubController.addMemberFlag = true;
        ClubController.fundFlag = false;
        ClubController.deleteMemberFlag = false;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/schoolmanagementsystem/fxml_Files/clubMemberEntry.fxml"));
        Parent clubMemberEntryPage = loader.load();

        clubMemberEntryPage.setLayoutX(clubPage.getWidth() / 2 - clubMemberEntryPage.prefWidth(-1) / 2);
        clubMemberEntryPage.setLayoutY(clubPage.getHeight() / 2 - clubMemberEntryPage.prefHeight(-1) / 2);
        clubMemberEntryPage.setTranslateX(0);
        clubMemberEntryPage.setTranslateY(0);

        // Add the new page as a child node to the root pane
        clubPage.getChildren().add(clubMemberEntryPage);
    }

    @FXML
    void handleUpdateClub(ActionEvent event) throws SQLException, IOException {
        ClubController.addClubFlag = false;
        ClubController.updateClubFlag = true;
        ClubFormController controller = new ClubFormController();
        controller.handleClubFormPage(event);
    }

    @FXML
    void handleClub_1(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 0;
        handleClubPage(event);
    }

    @FXML
    void handleClub_10(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 9;
        handleClubPage(event);
    }

    @FXML
    void handleClub_11(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 10;
        handleClubPage(event);
    }

    @FXML
    void handleClub_12(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 11;
        handleClubPage(event);
    }

    @FXML
    void handleClub_2(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 1;
        handleClubPage(event);
    }

    @FXML
    void handleClub_3(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 2;
        handleClubPage(event);
    }

    @FXML
    void handleClub_4(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 3;
        handleClubPage(event);
    }

    @FXML
    void handleClub_5(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 4;
        handleClubPage(event);
    }

    @FXML
    void handleClub_6(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 5;
        handleClubPage(event);
    }

    @FXML
    void handleClub_7(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 6;
        handleClubPage(event);
    }

    @FXML
    void handleClub_8(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 7;
        handleClubPage(event);
    }

    @FXML
    void handleClub_9(ActionEvent event) throws SQLException, IOException {
        ClubController.selectedClub = 8;
        handleClubPage(event);
    }

    @FXML
    void handleDeleteClub(ActionEvent event) throws SQLException, IOException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT clubName FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.selectedClub + 1);
        ResultSet r = statement.executeQuery();

        if (r.next()) {
            if (handleAlert("The club " + r.getString("clubName") + " will be deleted permanently",
                    "Are you sure to proceed")) {
                ClubCRUD crud = new ClubCRUD();
                crud.deleteClub(r.getString("clubName"));
                handleClubPage(event);
            }
        }
    }

    @FXML
    void handleDeleteMember(ActionEvent event) throws IOException {
        ClubController.addMemberFlag = false;
        ClubController.fundFlag = false;
        ClubController.deleteMemberFlag = true;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/schoolmanagementsystem/fxml_Files/clubMemberEntry.fxml"));
        Parent clubMemberEntryPage = loader.load();

        clubMemberEntryPage.setLayoutX(clubPage.getWidth() / 2 - clubMemberEntryPage.prefWidth(-1) / 2);
        clubMemberEntryPage.setLayoutY(clubPage.getHeight() / 2 - clubMemberEntryPage.prefHeight(-1) / 2);
        clubMemberEntryPage.setTranslateX(0);
        clubMemberEntryPage.setTranslateY(0);

        // Add the new page as a child node to the root pane
        clubPage.getChildren().add(clubMemberEntryPage);
    }

    @FXML
    void handleAllMembers(ActionEvent event) throws SQLException, IOException {
        ClubMemberController.setCurrentIndex(0);
        ClubMemberController controller = new ClubMemberController();
        controller.handleClubMemberPage(event);
    }

    @FXML
    void handleFund(ActionEvent event) throws IOException {
        ClubController.fundFlag = true;
        ClubController.addMemberFlag = false;
        ClubController.deleteMemberFlag = false;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/schoolmanagementsystem/fxml_Files/clubMemberEntry.fxml"));
        Parent clubMemberEntryPage = loader.load();

        clubMemberEntryPage.setLayoutX(clubPage.getWidth() / 2 - clubMemberEntryPage.prefWidth(-1) / 2);
        clubMemberEntryPage.setLayoutY(clubPage.getHeight() / 2 - clubMemberEntryPage.prefHeight(-1) / 2);
        clubMemberEntryPage.setTranslateX(0);
        clubMemberEntryPage.setTranslateY(0);

        // Add the new page as a child node to the root pane
        clubPage.getChildren().add(clubMemberEntryPage);
    }

    public String fetchData(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String q = "SELECT name FROM studentInfo WHERE studentID = ?";
        PreparedStatement s = con.prepareStatement(q);
        s.setInt(1, id);
        ResultSet resultSet = s.executeQuery();

        String str = "";

        if (resultSet.next()) {
            str = resultSet.getString("name");
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            s.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return str;
    }

    public void handleClubPage(Event event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/club.fxml", event);

        ClubController controller = fxmlLoader.getController();

        if (!Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            controller.addClub.setVisible(false);
            controller.fund.setVisible(false);
            controller.deleteClub.setVisible(false);
            controller.updateClub.setVisible(false);
            controller.addMember.setVisible(false);
            controller.deleteMember.setVisible(false);
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT clubName FROM club";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet r = statement.executeQuery();

        ArrayList<String> records = new ArrayList<String>();

        while (r.next()) {
            String record = r.getString("clubName");
            records.add(record);
        }

        int size = records.size();
        int i = 0;

        if (i < size) {
            controller.club_1.setText(records.get(i++));
        } else {
            controller.club_1.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_2.setText(records.get(i++));
        } else {
            controller.club_2.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_3.setText(records.get(i++));
        } else {
            controller.club_3.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_4.setText(records.get(i++));
        } else {
            controller.club_4.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_5.setText(records.get(i++));
        } else {
            controller.club_5.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_6.setText(records.get(i++));
        } else {
            controller.club_6.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_7.setText(records.get(i++));
        } else {
            controller.club_7.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_8.setText(records.get(i++));
        } else {
            controller.club_8.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_9.setText(records.get(i++));
        } else {
            controller.club_9.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_10.setText(records.get(i++));
        } else {
            controller.club_10.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_11.setText(records.get(i++));
        } else {
            controller.club_11.setDisable(true);
            i++;
        }
        if (i < size) {
            controller.club_12.setText(records.get(i));
        } else {
            controller.club_12.setDisable(true);
        }

        if (ClubController.selectedClub == 0) {
            controller.club_1.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 1) {
            controller.club_2.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 2) {
            controller.club_3.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 3) {
            controller.club_4.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 4) {
            controller.club_5.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 5) {
            controller.club_6.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 6) {
            controller.club_7.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 7) {
            controller.club_8.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 8) {
            controller.club_9.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 9) {
            controller.club_10.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 10) {
            controller.club_11.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        } else if (ClubController.selectedClub == 11) {
            controller.club_12.setStyle("-fx-background-color: #ff4b2b; -fx-background-radius: 30px;");
        }

        if (ClubController.selectedClub < records.size()) {

            String query2 = "SELECT * FROM club WHERE clubName = ?";
            PreparedStatement statement2 = con.prepareStatement(query2);

            statement2.setString(1, records.get(ClubController.selectedClub));

            ResultSet r2 = statement2.executeQuery();

            String q;
            PreparedStatement s;
            ResultSet resultSet;

            if (r2.next()) {

                q = "SELECT name FROM employeeInfo WHERE employeeID = ?";
                s = con.prepareStatement(q);
                s.setInt(1, r2.getInt("clubModerator"));
                resultSet = s.executeQuery();
                if (resultSet.next()) {
                    controller.clubModerator.setText(resultSet.getString("name"));
                }

                controller.fundAmount.setText(r2.getString("fund"));
                controller.president.setText(fetchData(r2.getInt("president")));
                controller.vicePresident.setText(fetchData(r2.getInt("vicePresident")));
                controller.generalSecretary.setText(fetchData(r2.getInt("generalSecretary")));
                controller.treasurer.setText(fetchData(r2.getInt("treasurer")));
                controller.assistantGS.setText(fetchData(r2.getInt("assistantGS")));
                controller.publicRelations.setText(fetchData(r2.getInt("publicRelations")));
                controller.secretary.setText(fetchData(r2.getInt("secretary")));
                controller.executive_1.setText(fetchData(r2.getInt("executive_1")));
                controller.executive_2.setText(fetchData(r2.getInt("executive_2")));
                controller.executive_3.setText(fetchData(r2.getInt("executive_3")));

            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
