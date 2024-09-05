package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.StudentCRUD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class StudentProfileController extends Controller {

    public StackPane getStudentProfile() {
        return studentProfile;
    }

    @FXML
    private StackPane studentProfile;

    @FXML
    private Label studentFees;

    @FXML
    private Label address;

    @FXML
    private Label cls;

    @FXML
    private Label conNum;

    @FXML
    private Label dob;

    @FXML
    private Label fname;

    @FXML
    private Label gender;

    @FXML
    private Label mname;

    @FXML
    private Label nam;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label religion;

    @FXML
    private Label roll;

    @FXML
    private Label sec;

    @FXML
    private Label sname;

    @FXML
    private Label usrID;

    @FXML
    private Label update;

    @FXML
    private ImageView updateIcon;

    @FXML
    private Label delete;

    @FXML
    private ImageView deleteIcon;

    private static boolean resultLabelFlag;

    public static boolean isResultLabelFlag() {
        return resultLabelFlag;
    }

    public static void setResultLabelFlag(boolean resultLabelFlag) {
        StudentProfileController.resultLabelFlag = resultLabelFlag;
    }

    public void handleStudentProfile(Event event, int id) throws IOException, SQLException {
        // loadPage("button","/com/schoolmanagementsystem/student.fxml",event);
        Controller.requiredID = id;

        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/student.fxml", event);

        StudentProfileController controller = fxmlLoader.getController();

        if (!Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            controller.updateIcon.setVisible(false);
            controller.updateIcon.setManaged(false);
            controller.update.setVisible(false);
            controller.update.setManaged(false);
            controller.deleteIcon.setVisible(false);
            controller.deleteIcon.setManaged(false);
            controller.delete.setVisible(false);
            controller.delete.setManaged(false);
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM studentInfo WHERE studentID = ?";

        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet r = statement.executeQuery();

        byte[] imageData;

        if (r.next()) {
            controller.sname.setText(r.getString("name").toUpperCase());
            controller.cls.setText(String.valueOf(r.getInt("class")));
            controller.roll.setText(String.valueOf(r.getInt("roll")));
            controller.fname.setText(r.getString("fatherName").toUpperCase());
            controller.mname.setText(r.getString("motherName").toUpperCase());
            controller.dob.setText(String.valueOf(r.getDate("dateOfBirth")));
            controller.sec.setText(r.getString("section").toUpperCase());
            controller.religion.setText(r.getString("religion").toUpperCase());
            controller.gender.setText(r.getString("gender").toUpperCase());
            controller.address.setText(r.getString("address").toUpperCase());
            controller.conNum.setText(r.getString("contactNumber").toUpperCase());
            controller.nam.setText(r.getString("name").toUpperCase());
            controller.usrID.setText("ID : " + r.getInt("studentID"));

            imageData = r.getBytes("profilePicture");
        } else {
            imageData = null;
        }
        // Convert the byte array to an Image
        Image image = createImageFromByteArray(imageData);

        if (image != null) {
            controller.profilePic.setImage(image);
        }
    }

    public void handleUpdate(MouseEvent mouseEvent) throws IOException, SQLException {
        StudentRegistrationController controller = new StudentRegistrationController();
        controller.updateHelp(mouseEvent);
    }

    public void handleDelete(MouseEvent mouseEvent) throws IOException, SQLException {
        if (handleAlert("The profile will be permanently deleted from the record", "Are you sure to proceed ?")) {
            StudentCRUD crud = new StudentCRUD();
            crud.deleteStudent(Controller.requiredID);
            handleStudentProfile(mouseEvent, Controller.requiredID);
        }
    }

    @FXML
    void handleStudentFees(Event mouseEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/schoolmanagementsystem/fxml_Files/fee.fxml"));
        Parent feePage = loader.load();

        feePage.setLayoutX(studentProfile.getWidth() / 2 - feePage.prefWidth(-1) / 2);
        feePage.setLayoutY(studentProfile.getHeight() / 2 - feePage.prefHeight(-1) / 2);
        feePage.setTranslateX(0);
        feePage.setTranslateY(0);

        // Add the new page as a child node to the root pane
        studentProfile.getChildren().add(feePage);
        FeeController feeController = new FeeController();
        feeController.handleFeePage(loader);
    }

    @FXML
    void handleStudentResult(Event mouseEvent) throws SQLException, IOException {
        StudentProfileController.resultLabelFlag = true;
        StudentResultController con = new StudentResultController();
        con.handleStudentResultPage(mouseEvent);
    }

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        if(Controller.allUserFlag) {
            AllMembersController.setAllUserFlag(true);
            AllMembersController controller = new AllMembersController();
            controller.handleAllMemberPage(event);
        } else {
            handleHome(event);
        }
    }
}
