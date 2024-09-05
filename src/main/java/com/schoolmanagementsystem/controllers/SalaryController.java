package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.SalaryCRUD;
import com.schoolmanagementsystem.models.Salary;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class SalaryController extends Controller implements Initializable {

    @FXML
    private Label baseSalary;

    @FXML
    private TextField baseSalaryInput;

    @FXML
    private ChoiceBox<String> childInput;

    @FXML
    private DatePicker dateInput;

    @FXML
    private Label education;

    @FXML
    private Label invalid;

    @FXML
    private Label medical;

    @FXML
    private TextField medicalInput;

    @FXML
    private Label noOfChild;

    @FXML
    private Label receivedDate;

    @FXML
    private Label rentAmount;

    @FXML
    private TextField rentInput;

    @FXML
    private Label rentPercentage;

    @FXML
    private Label total;

    @FXML
    private Button update;

    @FXML
    private Button cross;

    private int childNo;

    private int base;

    private int percentageOfRent;

    private int medicalFee;

    private static boolean updateFlag;

    private static boolean applyFlag;

    public void handleSalaryPage(Event event) throws IOException, SQLException {

        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/salary.fxml", event);

        SalaryController controller = fxmlLoader.getController();

        if (!SalaryController.updateFlag) {
            controller.medicalInput.setVisible(false);
            controller.medicalInput.setManaged(false);
            controller.childInput.setVisible(false);
            controller.childInput.setManaged(false);
            controller.baseSalaryInput.setVisible(false);
            controller.baseSalaryInput.setManaged(false);
            controller.rentInput.setVisible(false);
            controller.rentInput.setManaged(false);
            controller.dateInput.setVisible(false);
            controller.dateInput.setManaged(false);
            controller.invalid.setVisible(false);
            controller.invalid.setManaged(false);
        } else {
            controller.medical.setVisible(false);
            controller.medical.setManaged(false);
            controller.education.setVisible(false);
            controller.education.setManaged(false);
            controller.baseSalary.setVisible(false);
            controller.baseSalary.setManaged(false);
            controller.rentAmount.setVisible(false);
            controller.rentAmount.setManaged(false);
            controller.invalid.setVisible(false);
            controller.invalid.setManaged(false);
            controller.rentPercentage.setText("% of Base Salary");
            controller.noOfChild.setText("");
        }

        if (!Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            controller.update.setVisible(false);
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM employeeSalary WHERE employeeID = ?";
        PreparedStatement statement = con.prepareStatement(query);

        int id;
        if (Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            id = Controller.requiredID;
        } else {
            id = loginController.getLoggedInID();
        }

        statement.setInt(1, id);

        ResultSet r = statement.executeQuery();

        if (r.next()) {

            if (String.valueOf(r.getInt("baseSalary")) != null) {
                base = r.getInt("baseSalary");
            }
            if (String.valueOf(r.getInt("houseRent")) != null) {
                percentageOfRent = r.getInt("houseRent");
            }
            if (String.valueOf(r.getInt("noOfChild")) != null) {
                childNo = r.getInt("noOfChild");
            }
            if (String.valueOf(r.getInt("medical")) != null) {
                medicalFee = r.getInt("medical");
            }

            boolean dateFlag = false;

            if (r.getDate("receivedDate") != null) {
                dateFlag = true;
            }

            int rentValue = (int) (base * percentageOfRent) / 100;

            if (!SalaryController.updateFlag) {
                controller.update.setText("Update");
                // if(SalaryController.applyFlag) {
                // controller.update.setText("Apply");
                // }
                controller.rentPercentage.setText(r.getInt("houseRent") + "% of Base Salary");
                controller.noOfChild.setText("for " + r.getInt("noOfChild") + " children");
                controller.baseSalary.setText(String.valueOf(r.getInt("baseSalary")));

                controller.rentAmount.setText(String.valueOf(rentValue));
                controller.medical.setText(String.valueOf(r.getInt("medical")));

                if (childNo == 0) {
                    controller.education.setText("0");
                } else if (childNo == 1) {
                    controller.education.setText("500");
                } else {
                    controller.education.setText("1000");
                }

                int childEducation = 0;
                if (childNo == 1)
                    childEducation = 500;
                else if (childNo == 2)
                    childEducation = 1000;

                int totalSalary = base + rentValue + medicalFee + childEducation;

                controller.total.setText("Total Salary : " + totalSalary);
                controller.receivedDate.setText("Last received date : " + String.valueOf(r.getDate("receivedDate")));

            } else {
                controller.update.setText("Apply");
                controller.rentPercentage.setText("select percentage");
                controller.noOfChild.setText("select children");
                controller.baseSalaryInput.setText(String.valueOf(base));
                controller.rentInput.setText(String.valueOf(percentageOfRent));
                controller.medicalInput.setText(String.valueOf(medicalFee));

                if (childNo == 0) {
                    controller.childInput.setValue("0 child");
                } else if (childNo == 1) {
                    controller.childInput.setValue("1 child");
                } else {
                    controller.childInput.setValue("More than 1");
                }

                if (dateFlag) {
                    controller.dateInput.setValue(LocalDate.parse(String.valueOf(r.getDate("receivedDate"))));
                }

                controller.total.setText("Total Salary : ");
                controller.receivedDate.setText("Last received date : ");

            }

        }

    }

    @FXML
    void handleUpdate(ActionEvent event) throws SQLException, IOException {
        if (!SalaryController.updateFlag) {
            SalaryController.updateFlag = true;
            SalaryController.applyFlag = false;
            handleSalaryPage(event);
        } else {
            if (baseSalaryInput.getText().isEmpty() || rentInput.getText().isEmpty() || medicalInput.getText().isEmpty()
                    || childInput.getValue() == null || dateInput.getValue() == null) {
                invalid.setVisible(true);
                invalid.setManaged(true);
            } else if (validateNum(baseSalaryInput.getText()) || validateNum(rentInput.getText())
                    || validateNum(medicalInput.getText()) || validateDate(dateInput)) {
                invalid.setVisible(true);
                invalid.setManaged(true);
            } else {
                SalaryController.updateFlag = false;
                SalaryController.applyFlag = true;

                int child = 0;
                if (Objects.equals(childInput.getValue(), "0 child")) {
                    child = 0;
                } else if (Objects.equals(childInput.getValue(), "1 child")) {
                    child = 1;
                } else {
                    child = 2;
                }

                Salary salary = new Salary(Integer.parseInt(baseSalaryInput.getText()),
                        Integer.parseInt(rentInput.getText()), Integer.parseInt(medicalInput.getText()), child,
                        dateInput.getValue());
                int id;
                if (Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
                    id = Controller.requiredID;
                } else {
                    id = loginController.getLoggedInID();
                }

                SalaryCRUD crud = new SalaryCRUD();
                crud.addOrUpdateSalary(salary, id);

                handleSalaryPage(event);
            }
        }

    }

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        SalaryController.updateFlag = false;
        SalaryController.applyFlag = false;

        if (Objects.equals(loginController.getLoggedInPerson(), "Teacher")) {
            TeacherProfileController controller = new TeacherProfileController();
            controller.handleTeacherProfile(event, loginController.getLoggedInID());
        } else if (Objects.equals(loginController.getLoggedInPerson(), "Staff")) {
            StaffProfileController controller = new StaffProfileController();
            controller.handleStaffProfile(event, loginController.getLoggedInID());
        } else {
            if (Objects.equals(Controller.requiredType, "Teacher")) {
                TeacherProfileController controller = new TeacherProfileController();
                controller.handleTeacherProfile(event, Controller.requiredID);
            } else {
                StaffProfileController controller = new StaffProfileController();
                controller.handleStaffProfile(event, Controller.requiredID);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        childInput.getItems().addAll("0 child", "1 child", "More than 1");
    }

    @FXML
    void handleInvalid(Event keyEvent) {
        invalid.setVisible(false);
    }
}
