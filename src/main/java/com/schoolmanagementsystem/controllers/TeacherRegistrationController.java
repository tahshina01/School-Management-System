package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.TeacherCRUD;
import com.schoolmanagementsystem.database.LoginCRUD;
import com.schoolmanagementsystem.users.Teacher;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.Random;
import java.util.ResourceBundle;

public class TeacherRegistrationController extends Controller implements Initializable {

    private String imgPath;
    private Stage stage;
    @FXML
    Button imgButton;
    @FXML
    private TextField address;

    @FXML
    private TextField contact;

    @FXML
    private ChoiceBox<String> designation;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField ename;

    @FXML
    private TextField fname;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField religion;

    @FXML
    private TextField sub;

    @FXML
    private TextField mname;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private Button cross;

    @FXML
    private ImageView Img;

    @FXML
    private Label wrongInput;

    @FXML
    private Label heading;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Male", "Female");
        designation.getItems().addAll("Primary (1 - 5)", "Secondary (6 - 10)");
        cross.setVisible(false);
    }

    public void submitHandler(ActionEvent event) throws SQLException, IOException {

        if (religion.getText().isEmpty() || ename.getText().isEmpty() || fname.getText().isEmpty()
                || mname.getText().isEmpty() || password.getText().isEmpty() || contact.getText().isEmpty()
                || address.getText().isEmpty() || sub.getText().isEmpty()) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (gender.getValue() == null || designation.getValue() == null || dob.getValue() == null) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (validateNum(contact.getText()) || contact.getText().length() != 11 || validateDate(dob)) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (!Controller.isUpdate && imgPath == null) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else {
            int year = Year.now().getValue();

            Random rand = new Random();

            int id;

            if (!Controller.isUpdate) {
                while (true) {

                    ConnectDatabase db = new ConnectDatabase();
                    Connection con = db.getCon();

                    String query = "SELECT * FROM loginInfo WHERE ID = ?";
                    id = 1000 * year + rand.nextInt(900) + 100;

                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setInt(1, id);

                    ResultSet r = statement.executeQuery();

                    if (!r.next()) {
                        break;
                    }
                }
            } else {
                id = Controller.requiredID;
            }

            String message1 = "You are about to register.";
            String message2 = "Your id is " + id + "\nPlease remember this id for further access.";

            if (!Controller.isUpdate) {
                if (handleAlert(message1, message2)) {
                    wrongInput.setText("Congratulation. You have successfully Registered");
                    cross.setVisible(true);

                    Teacher teacher = new Teacher(id, ename.getText(), contact.getText(), address.getText(),
                            dob.getValue(),
                            gender.getValue(), fname.getText(), mname.getText(), religion.getText(),
                            designation.getValue(),
                            password.getText(), sub.getText());

                    TeacherCRUD teacherCrud = new TeacherCRUD();
                    teacherCrud.addTeacher(teacher, imgPath);

                    LoginCRUD loginCRUD = new LoginCRUD();
                    loginCRUD.addNewLoginInfo(teacher, "Teacher");
                    handleHome(event);
                }
            } else {
                message1 = "The data will be permanently updated.";
                message2 = "Are you sure to proceed ?";

                if (handleAlert(message1, message2)) {
                    wrongInput.setText("Congratulation. The profile is updated successfully.");
                    cross.setVisible(true);

                    Teacher teacher = new Teacher(id, ename.getText(), contact.getText(), address.getText(),
                            dob.getValue(),
                            gender.getValue(), fname.getText(), mname.getText(), religion.getText(),
                            designation.getValue(),
                            password.getText(), sub.getText());

                    TeacherCRUD teacherCrud = new TeacherCRUD();
                    teacherCrud.updateTeacher(teacher, imgPath);

                    LoginCRUD loginCRUD = new LoginCRUD();
                    loginCRUD.updateLoginInfo(teacher, "Teacher");

                    TeacherProfileController cont = new TeacherProfileController();
                    cont.handleTeacherProfile(event, Controller.requiredID);
                }

            }

        }
    }

    public void handleCross() {
        if (cross.isVisible()) {
            cross.setVisible(false);
            wrongInput.setText("");
        } else {
            cross.setVisible(true);
        }
    }

    public void handleImgUpload(ActionEvent actionEvent) {
        imgPath = uploadImage(stage, Img, imgButton);
    }

    public void updateHelp(Event event) throws IOException, SQLException {

        Controller.isUpdate = true;

        FXMLLoader fxmlLoader = loadPage("label", "/com/schoolmanagementsystem/fxml_Files/teacherRegistrationForm.fxml",
                event);

        TeacherRegistrationController controller = fxmlLoader.getController();

        controller.heading.setText("Information Update Form");
        controller.register.setText("Update");

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM employeeInfo WHERE employeeID = ?";

        PreparedStatement statement = con.prepareStatement(query);

        statement.setInt(1, Controller.requiredID);

        ResultSet r = statement.executeQuery();
        byte[] imageData;

        if (r.next()) {
            controller.ename.setText(r.getString("name"));
            controller.sub.setText(r.getString("subject"));
            controller.fname.setText(r.getString("fatherName"));
            controller.mname.setText(r.getString("motherName"));
            controller.dob.setValue(LocalDate.parse(String.valueOf(r.getDate("dateOfBirth"))));
            controller.designation.setValue(r.getString("profession"));
            controller.religion.setText(r.getString("religion"));
            controller.gender.setValue(r.getString("gender"));
            controller.address.setText(r.getString("address"));
            controller.contact.setText(r.getString("contactNumber"));

            imageData = r.getBytes("profilePicture");
        } else {
            imageData = null;
        }

        String query2 = "SELECT * FROM loginInfo WHERE ID = ?";

        PreparedStatement statement2 = con.prepareStatement(query2);

        statement2.setInt(1, Controller.requiredID);

        ResultSet r2 = statement2.executeQuery();

        if (r2.next()) {
            controller.password.setText(r2.getString("password"));
        }

        Image image = createImageFromByteArray(imageData);

        if (image != null) {
            controller.Img.setImage(image);
        }
        controller.imgButton.setVisible(true);
    }

}
