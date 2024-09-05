package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.FeeCRUD;
import com.schoolmanagementsystem.database.SalaryCRUD;
import com.schoolmanagementsystem.models.Fee;
import com.schoolmanagementsystem.models.Salary;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class FeeController extends Controller {

    @FXML
    private DatePicker dateInput;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField feeInput;

    @FXML
    private Label feeLabel;
    @FXML
    private Button cross;

    @FXML
    private Label invalid;

    @FXML
    private Button update;

    private static boolean updateFlag;

    private static boolean applyFlag;

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        FeeController.applyFlag = false;
        FeeController.updateFlag = false;
        StudentProfileController controller = new StudentProfileController();
        controller.handleStudentProfile(event, Controller.requiredID);
    }

    @FXML
    void handleInvalid(Event event) {
        invalid.setVisible(false);
    }

    @FXML
    void handleUpdate(ActionEvent event) throws SQLException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        if (!FeeController.updateFlag) {
            update.setText("Apply");
            FeeController.updateFlag = true;
            FeeController.applyFlag = false;

            feeLabel.setVisible(false);
            feeLabel.setManaged(false);
            dateLabel.setVisible(false);
            dateLabel.setManaged(false);

            feeInput.setVisible(true);
            feeInput.setManaged(true);
            dateInput.setVisible(true);
            dateInput.setManaged(true);

            String query = "SELECT * FROM studentFees WHERE studentID = ?";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, Controller.requiredID);
            ResultSet r = statement.executeQuery();

            if (r.next()) {
                feeInput.setText(String.valueOf(r.getInt("feeAmount")));
                dateInput.setValue(LocalDate.parse(String.valueOf(r.getDate("paymentDate"))));
            }

        } else {
            if (feeInput.getText().isEmpty() || dateInput.getValue() == null) {
                invalid.setVisible(true);
                invalid.setManaged(true);
            } else if (validateNum(feeInput.getText()) || validateDate(dateInput)) {
                invalid.setVisible(true);
                invalid.setManaged(true);
            } else {
                FeeController.updateFlag = false;
                FeeController.applyFlag = true;
                update.setText("Update");

                invalid.setVisible(false);
                invalid.setVisible(false);
                feeInput.setVisible(false);
                feeInput.setManaged(false);
                dateInput.setVisible(false);
                dateInput.setManaged(false);

                feeLabel.setVisible(true);
                feeLabel.setManaged(true);
                dateLabel.setVisible(true);
                dateLabel.setManaged(true);

                Fee fee = new Fee(Controller.requiredID, Integer.parseInt(feeInput.getText()), dateInput.getValue());
                FeeCRUD crud = new FeeCRUD();
                crud.addOrUpdateFee(fee);

                String query = "SELECT * FROM studentFees WHERE studentID = ?";
                PreparedStatement statement = con.prepareStatement(query);

                statement.setInt(1, Controller.requiredID);
                ResultSet r = statement.executeQuery();

                if (r.next()) {
                    feeLabel.setText(String.valueOf(r.getInt("feeAmount")));
                    dateLabel.setText(String.valueOf(LocalDate.parse(String.valueOf(r.getDate("paymentDate")))));
                }

            }
        }
    }

    public void handleFeePage(FXMLLoader fxmlLoader) throws SQLException {
        FeeController controller = fxmlLoader.getController();
        FeeController.applyFlag = true;
        FeeController.updateFlag = false;

        controller.invalid.setVisible(false);
        controller.invalid.setVisible(false);
        controller.feeInput.setVisible(false);
        controller.feeInput.setManaged(false);
        controller.dateInput.setVisible(false);
        controller.dateInput.setManaged(false);

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM studentFees WHERE studentID = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setInt(1, Controller.requiredID);
        ResultSet r = statement.executeQuery();

        if (r.next()) {
            controller.feeLabel.setText(String.valueOf(r.getInt("feeAmount")));
            controller.dateLabel.setText(String.valueOf(r.getDate("paymentDate")));
        }
    }
}
