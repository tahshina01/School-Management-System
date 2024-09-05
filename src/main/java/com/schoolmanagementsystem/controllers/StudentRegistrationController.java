package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.StudentCRUD;
import com.schoolmanagementsystem.users.Student;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class StudentRegistrationController extends Controller implements Initializable {

    private Stage stage;

    private String imgPath;
    @FXML
    Button imgButton;

    @FXML
    private TextField address;

    @FXML
    private ChoiceBox<Integer> classNumber;

    @FXML
    private TextField contact;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField fname;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField religion;

    @FXML
    private TextField mname;

    @FXML
    private Button register;

    @FXML
    private TextField roll;

    @FXML
    private ChoiceBox<String> section;

    @FXML
    private TextField sname;

    @FXML
    private ImageView Img;

    @FXML
    private Label wrongInput;

    @FXML
    private Button cross;

    @FXML
    private Label heading;

    public DatePicker getDob() {
        return dob;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Male", "Female");
        classNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        section.getItems().addAll("A", "B", "C");
        cross.setVisible(false);
    }

    public void submitHandler(ActionEvent event) throws SQLException, IOException {

        if (religion.getText().isEmpty() || sname.getText().isEmpty() || fname.getText().isEmpty()
                || mname.getText().isEmpty() || roll.getText().isEmpty() || contact.getText().isEmpty()
                || address.getText().isEmpty()) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (gender.getValue() == null || classNumber.getValue() == null || section.getValue() == null
                || dob.getValue() == null) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (validateNum(roll.getText()) || validateNum(contact.getText())
                || contact.getText().length() != 11 || validateDate(dob)) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else if (!Controller.isUpdate && imgPath == null) {
            wrongInput.setText("Incorrect or empty Input. Give correct information");
            cross.setVisible(true);
        } else {
            int clas = Integer.parseInt(classNumber.getValue().toString());

            int year = Year.now().getValue();

            Random rand = new Random();

            int id;

            if (!Controller.isUpdate) {
                while (true) {

                    ConnectDatabase db = new ConnectDatabase();
                    Connection con = db.getCon();

                    String query = "SELECT * FROM studentInfo WHERE studentID = ?";
                    id = 100000 * year + 10000 * clas + rand.nextInt(9000) + 1000;

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

                    Student st = new Student(id, sname.getText(), contact.getText(), address.getText(), dob.getValue(),
                            gender.getValue(), fname.getText(), mname.getText(), religion.getText(), clas,
                            section.getValue(), Integer.parseInt(roll.getText()));
                    StudentCRUD stCrud = new StudentCRUD();
                    stCrud.addStudent(st, imgPath);

                    handleHome(event);
                }
            } else {
                message1 = "The data will be permanently updated.";
                message2 = "Are you sure to proceed ?";

                if (handleAlert(message1, message2)) {
                    wrongInput.setText("Congratulation. The profile is updated successfully.");
                    cross.setVisible(true);

                    Student st = new Student(id, sname.getText(), contact.getText(), address.getText(), dob.getValue(),
                            gender.getValue(), fname.getText(), mname.getText(), religion.getText(), clas,
                            section.getValue(), Integer.parseInt(roll.getText()));
                    StudentCRUD stCrud = new StudentCRUD();
                    stCrud.updateStudent(st, imgPath);

                    StudentProfileController cont = new StudentProfileController();
                    cont.handleStudentProfile(event, Controller.requiredID);
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

        FXMLLoader fxmlLoader = loadPage("label", "/com/schoolmanagementsystem/fxml_Files/studentRegistrationForm.fxml",
                event);

        StudentRegistrationController controller = fxmlLoader.getController();

        controller.heading.setText("Information Update Form");
        controller.register.setText("Update");

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM studentInfo WHERE studentID = ?";

        PreparedStatement statement = con.prepareStatement(query);

        statement.setInt(1, Controller.requiredID);

        ResultSet r = statement.executeQuery();
        byte[] imageData;

        if (r.next()) {
            controller.sname.setText(r.getString("name"));
            controller.classNumber.setValue(r.getInt("class"));
            controller.roll.setText(String.valueOf(r.getInt("roll")));
            controller.fname.setText(r.getString("fatherName"));
            controller.mname.setText(r.getString("motherName"));
            controller.dob.setValue(LocalDate.parse(String.valueOf(r.getDate("dateOfBirth"))));
            controller.section.setValue(r.getString("section"));
            controller.religion.setText(r.getString("religion"));
            controller.gender.setValue(r.getString("gender"));
            controller.address.setText(r.getString("address"));
            controller.contact.setText(r.getString("contactNumber"));

            imageData = r.getBytes("profilePicture");
        } else {
            imageData = null;
        }

        Image image = createImageFromByteArray(imageData);

        if (image != null) {
            controller.Img.setImage(image);
        }
        controller.imgButton.setVisible(true);
    }

}