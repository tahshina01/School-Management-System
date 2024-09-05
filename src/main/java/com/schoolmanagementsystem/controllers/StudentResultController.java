package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.ResultCRUD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentResultController extends Controller {

    @FXML
    private Label avgGPA;

    @FXML
    private Button confirm;

    @FXML
    private Button editResult;

    @FXML
    private Label gpa_1;

    @FXML
    private Label gpa_10;

    @FXML
    private Label gpa_11;

    @FXML
    private Label gpa_12;

    @FXML
    private Label gpa_13;

    @FXML
    private Label gpa_2;

    @FXML
    private Label gpa_3;

    @FXML
    private Label gpa_4;

    @FXML
    private Label gpa_5;

    @FXML
    private Label gpa_6;

    @FXML
    private Label gpa_7;

    @FXML
    private Label gpa_8;

    @FXML
    private Label gpa_9;

    @FXML
    private TextField input_1;

    @FXML
    private TextField input_10;

    @FXML
    private TextField input_11;

    @FXML
    private TextField input_12;

    @FXML
    private TextField input_13;

    @FXML
    private TextField input_2;

    @FXML
    private TextField input_3;

    @FXML
    private TextField input_4;

    @FXML
    private TextField input_5;

    @FXML
    private TextField input_6;

    @FXML
    private TextField input_7;

    @FXML
    private TextField input_8;

    @FXML
    private TextField input_9;

    @FXML
    private Label mark_1;

    @FXML
    private Label mark_10;

    @FXML
    private Label mark_11;

    @FXML
    private Label mark_12;

    @FXML
    private Label mark_13;

    @FXML
    private Label mark_2;

    @FXML
    private Label mark_3;

    @FXML
    private Label mark_4;

    @FXML
    private Label mark_5;

    @FXML
    private Label mark_6;

    @FXML
    private Label mark_7;

    @FXML
    private Label mark_8;

    @FXML
    private Label mark_9;

    @FXML
    private MenuButton selectExam;

    @FXML
    private Label sub_1;

    @FXML
    private Label sub_10;

    @FXML
    private Label sub_11;

    @FXML
    private Label sub_12;

    @FXML
    private Label sub_13;

    @FXML
    private Label sub_2;

    @FXML
    private Label sub_3;

    @FXML
    private Label sub_4;

    @FXML
    private Label sub_5;

    @FXML
    private Label sub_6;

    @FXML
    private Label sub_7;

    @FXML
    private Label sub_8;

    @FXML
    private Label sub_9;

    @FXML
    private Label totalMark;

    @FXML
    private Button crossButton;

    private static int selectedExam;

    private static boolean editFlag;

    private static int studentClass;

    public static int getSelectedExam() {
        return selectedExam;
    }

    public static int getStudentClass() {
        return studentClass;
    }

    @FXML
    void handleConfirm(ActionEvent event) throws SQLException, IOException {
        ArrayList<Integer> allMarks = new ArrayList<>();
        if (input_1.isVisible()) {
            if (input_1.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_1.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_1.getText()));
                }
            }
        }
        if (input_2.isVisible()) {
            if (input_2.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_2.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_2.getText()));
                }
            }
        }
        if (input_3.isVisible()) {
            if (input_3.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_3.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_3.getText()));
                }
            }
        }
        if (input_4.isVisible()) {
            if (input_4.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_4.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_4.getText()));
                }
            }
        }
        if (input_5.isVisible()) {
            if (input_5.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_5.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_5.getText()));
                }
            }
        }
        if (input_6.isVisible()) {
            if (input_6.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_6.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_6.getText()));
                }
            }
        }
        if (input_7.isVisible()) {
            if (input_7.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_7.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_7.getText()));
                }
            }
        }
        if (input_8.isVisible()) {
            if (input_8.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_8.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_8.getText()));
                }
            }
        }
        if (input_9.isVisible()) {
            if (input_9.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_9.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_9.getText()));
                }
            }
        }
        if (input_10.isVisible()) {
            if (input_10.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_10.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_10.getText()));
                }
            }
        }
        if (input_11.isVisible()) {
            if (input_11.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_11.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_11.getText()));
                }
            }
        }
        if (input_12.isVisible()) {
            if (input_12.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_12.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_12.getText()));
                }
            }
        }
        if (input_13.isVisible()) {
            if (input_13.getText().isEmpty()) {
                allMarks.add(0);
            } else {
                if (validateNum(input_13.getText())) {
                    handleAlert("Marks must be Integer", "");
                    return;
                } else {
                    allMarks.add(Integer.parseInt(input_13.getText()));
                }
            }
        }
        ResultCRUD crud = new ResultCRUD();
        crud.addOrUpdateResult(allMarks);
        StudentResultController.editFlag = false;
        StudentProfileController.setResultLabelFlag(true);
        handleStudentResultPage(event);
    }

    @FXML
    void handleEditResult(ActionEvent event) throws SQLException, IOException {
        if (StudentResultController.selectedExam == 0)
            return;
        StudentResultController.editFlag = true;
        StudentProfileController.setResultLabelFlag(true);
        handleStudentResultPage(event);
    }

    @FXML
    void handleSelectExam(ActionEvent event) {

    }

    @FXML
    void handleHalfYearly(ActionEvent event) throws SQLException, IOException {
        StudentProfileController.setResultLabelFlag(false);
        StudentResultController.selectedExam = 1;
        StudentResultController.editFlag = false;
        handleStudentResultPage(event);
    }

    @FXML
    void handleYearFinal(ActionEvent event) throws SQLException, IOException {
        StudentProfileController.setResultLabelFlag(false);
        selectExam.setText("Year Final");
        StudentResultController.selectedExam = 2;
        StudentResultController.editFlag = false;
        handleStudentResultPage(event);
    }

    public String getGPA(int num) {
        if (num >= 80)
            return "5.00";
        if (num >= 70)
            return "4.00";
        if (num >= 60)
            return "3.50";
        if (num >= 50)
            return "3.00";
        if (num >= 40)
            return "2.00";
        return "0.00";
    }

    public ArrayList<String> allSubjects(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT class FROM studentInfo WHERE studentID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet r = statement.executeQuery();
        if (r.next()) {
            StudentResultController.studentClass = r.getInt("class");
        }

        query = "SELECT * FROM subjectInfo WHERE Class = ?";
        statement = con.prepareStatement(query);
        statement.setInt(1, StudentResultController.studentClass);
        r = statement.executeQuery();

        ArrayList<String> allSubjects = new ArrayList<>();
        String subject = null;
        if (r.next()) {
            subject = r.getString("sub1");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub2");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub3");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub4");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub5");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub6");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub7");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub8");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub9");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub10");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub11");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub12");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub13");
            if (subject != null) {
                allSubjects.add(subject);
            }
            subject = r.getString("sub14");
            if (subject != null) {
                allSubjects.add(subject);
            }
        }
        return allSubjects;
    }

    public void handleStudentResultPage(Event event) throws IOException, SQLException {
        String buttonType;
        if (StudentProfileController.isResultLabelFlag()) {
            buttonType = "button";
        } else {
            buttonType = "menuButton";
        }
        FXMLLoader fxmlLoader = loadPage(buttonType, "/com/schoolmanagementsystem/fxml_Files/studentResult.fxml",
                event);
        StudentResultController controller = fxmlLoader.getController();
        if (!StudentResultController.editFlag) {
            controller.confirm.setVisible(false);
        }
        controller.input_1.setVisible(false);
        controller.input_2.setVisible(false);
        controller.input_3.setVisible(false);
        controller.input_4.setVisible(false);
        controller.input_5.setVisible(false);
        controller.input_6.setVisible(false);
        controller.input_7.setVisible(false);
        controller.input_8.setVisible(false);
        controller.input_9.setVisible(false);
        controller.input_10.setVisible(false);
        controller.input_11.setVisible(false);
        controller.input_12.setVisible(false);
        controller.input_13.setVisible(false);

        if (StudentResultController.selectedExam == 0) {
            return;
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = null;
        PreparedStatement statement;
        ResultSet r;
        ArrayList<String> allSubjects = allSubjects(Controller.requiredID);

        for (int i = 0; i < allSubjects.size(); i++) {
            if (i == 0) {
                controller.sub_1.setText(allSubjects.get(i));
            }
            if (i == 1) {
                controller.sub_2.setText(allSubjects.get(i));
            }
            if (i == 2) {
                controller.sub_3.setText(allSubjects.get(i));
            }
            if (i == 3) {
                controller.sub_4.setText(allSubjects.get(i));
            }
            if (i == 4) {
                controller.sub_5.setText(allSubjects.get(i));
            }
            if (i == 5) {
                controller.sub_6.setText(allSubjects.get(i));
            }
            if (i == 6) {
                controller.sub_7.setText(allSubjects.get(i));
            }
            if (i == 7) {
                controller.sub_8.setText(allSubjects.get(i));
            }
            if (i == 8) {
                controller.sub_9.setText(allSubjects.get(i));
            }
            if (i == 9) {
                controller.sub_10.setText(allSubjects.get(i));
            }
            if (i == 10) {
                controller.sub_11.setText(allSubjects.get(i));
            }
            if (i == 11) {
                controller.sub_12.setText(allSubjects.get(i));
            }
            if (i == 12) {
                controller.sub_13.setText(allSubjects.get(i));
            }
        }

        if (StudentResultController.selectedExam == 1) {
            query = "SELECT * FROM resultinfo_HalfYearly WHERE ID = ?";
            controller.selectExam.setText("Half Yearly");
        } else if (StudentResultController.selectedExam == 2) {
            query = "SELECT * FROM resultinfo_YearFinal WHERE ID = ?";
            controller.selectExam.setText("Year Final");
        }

        statement = con.prepareStatement(query);
        statement.setInt(1, Controller.requiredID);
        r = statement.executeQuery();
        int total = 0;
        float totalGPA = 0;
        float averageGPA = 0;

        if (r.next()) {
            if (!StudentResultController.editFlag) {
                for (int i = 0; i < allSubjects.size(); i++) {

                    total += r.getInt(allSubjects.get(i));
                    totalGPA += Float.parseFloat(getGPA(r.getInt(allSubjects.get(i))));

                    if (i == 0) {
                        controller.mark_1.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_1.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 1) {
                        controller.mark_2.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_2.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 2) {
                        controller.mark_3.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_3.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 3) {
                        controller.mark_4.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_4.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 4) {
                        controller.mark_5.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_5.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 5) {
                        controller.mark_6.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_6.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 6) {
                        controller.mark_7.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_7.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 7) {
                        controller.mark_8.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_8.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 8) {
                        controller.mark_9.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_9.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 9) {
                        controller.mark_10.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_10.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 10) {
                        controller.mark_11.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_11.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 11) {
                        controller.mark_12.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_12.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 12) {
                        controller.mark_13.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                        controller.gpa_13.setText(getGPA(r.getInt(allSubjects.get(i))));
                    }
                }
                controller.totalMark.setText(String.valueOf(total));
                averageGPA = totalGPA / allSubjects.size();
                DecimalFormat df = new DecimalFormat("#.##");
                String formatted = df.format(averageGPA);
                controller.avgGPA.setText(formatted);
            } else {
                for (int i = 0; i < allSubjects.size(); i++) {

                    if (i == 0) {
                        controller.input_1.setVisible(true);
                        controller.input_1.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 1) {
                        controller.input_2.setVisible(true);
                        controller.input_2.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 2) {
                        controller.input_3.setVisible(true);
                        controller.input_3.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 3) {
                        controller.input_4.setVisible(true);
                        controller.input_4.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 4) {
                        controller.input_5.setVisible(true);
                        controller.input_5.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 5) {
                        controller.input_6.setVisible(true);
                        controller.input_6.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 6) {
                        controller.input_7.setVisible(true);
                        controller.input_7.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 7) {
                        controller.input_8.setVisible(true);
                        controller.input_8.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 8) {
                        controller.input_9.setVisible(true);
                        controller.input_9.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 9) {
                        controller.input_10.setVisible(true);
                        controller.input_10.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 10) {
                        controller.input_11.setVisible(true);
                        controller.input_11.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 11) {
                        controller.input_12.setVisible(true);
                        controller.input_12.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                    if (i == 12) {
                        controller.input_13.setVisible(true);
                        controller.input_13.setText(String.valueOf(r.getInt(allSubjects.get(i))));
                    }
                }
            }
        }
        if (!r.next() && StudentResultController.editFlag) {
            for (int i = 0; i < allSubjects.size(); i++) {

                if (i == 0) {
                    controller.input_1.setVisible(true);
                }
                if (i == 1) {
                    controller.input_2.setVisible(true);
                }
                if (i == 2) {
                    controller.input_3.setVisible(true);
                }
                if (i == 3) {
                    controller.input_4.setVisible(true);
                }
                if (i == 4) {
                    controller.input_5.setVisible(true);
                }
                if (i == 5) {
                    controller.input_6.setVisible(true);
                }
                if (i == 6) {
                    controller.input_7.setVisible(true);
                }
                if (i == 7) {
                    controller.input_8.setVisible(true);
                }
                if (i == 8) {
                    controller.input_9.setVisible(true);
                }
                if (i == 9) {
                    controller.input_10.setVisible(true);
                }
                if (i == 10) {
                    controller.input_11.setVisible(true);
                }
                if (i == 11) {
                    controller.input_12.setVisible(true);
                }
                if (i == 12) {
                    controller.input_13.setVisible(true);
                }
            }
        }
    }

    @FXML
    void handleCrossButton(Event event) throws SQLException, IOException {
        StudentResultController.editFlag = false;
        StudentResultController.selectedExam = 0;
        StudentResultController.studentClass = 0;
        StudentProfileController controller = new StudentProfileController();
        controller.handleStudentProfile(event, Controller.requiredID);
    }
}