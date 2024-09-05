package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ResultCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;

public class ResultAlgorithmController extends Controller {

    @FXML
    private Button apply;

    @FXML
    private Button cross;

    @FXML
    private TextField halfYearlyInput;

    @FXML
    private Label invalid;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private MenuButton selectPriority;

    @FXML
    private TextField yearFinalInput;

    private static String priority;

    @FXML
    void handleApply(ActionEvent event) throws SQLException {
        if (halfYearlyInput.getText().isEmpty() || yearFinalInput.getText().isEmpty()) {
            invalid.setText("Empty input");
        } else if (ResultAlgorithmController.priority == null) {
            invalid.setText("Select priority");
        } else if (validateNum(halfYearlyInput.getText()) || validateNum(yearFinalInput.getText())) {
            invalid.setText("Weight must be integer");
        } else if (Integer.parseInt(halfYearlyInput.getText()) + Integer.parseInt(yearFinalInput.getText()) != 100) {
            invalid.setText("Sum is not 100");
        } else {
            invalid.setText("Algorithm is set");
            ResultCRUD crud = new ResultCRUD();
            crud.resultAlgoCrud(Integer.parseInt(halfYearlyInput.getText()), Integer.parseInt(yearFinalInput.getText()),
                    ResultAlgorithmController.priority);
        }
    }

    @FXML
    void handleCross(ActionEvent event) throws IOException, SQLException {
        Controller.resultFlag = true;
        ClassResultController controller = new ClassResultController();
        controller.handleClassResultPage(event);
    }

    @FXML
    void handleGPA(ActionEvent event) {
        ResultAlgorithmController.priority = "gpa";
        selectPriority.setText("G.P.A.");
    }

    @FXML
    void handleInvalid(KeyEvent event) {
        invalid.setText("");
    }

    @FXML
    void handleMarks(ActionEvent event) {
        ResultAlgorithmController.priority = "marks";
        selectPriority.setText("Marks");
    }

}
