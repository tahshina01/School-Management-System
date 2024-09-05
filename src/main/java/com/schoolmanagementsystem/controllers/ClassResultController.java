package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.result.Result;
import com.schoolmanagementsystem.users.Student;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ClassResultController extends Controller {

    @FXML
    private Button calculate;

    @FXML
    private Label exam_1_gpa_1;

    @FXML
    private Label exam_1_gpa_10;

    @FXML
    private Label exam_1_gpa_11;

    @FXML
    private Label exam_1_gpa_12;

    @FXML
    private Label exam_1_gpa_13;

    @FXML
    private Label exam_1_gpa_14;

    @FXML
    private Label exam_1_gpa_15;

    @FXML
    private Label exam_1_gpa_2;

    @FXML
    private Label exam_1_gpa_3;

    @FXML
    private Label exam_1_gpa_4;

    @FXML
    private Label exam_1_gpa_5;

    @FXML
    private Label exam_1_gpa_6;

    @FXML
    private Label exam_1_gpa_7;

    @FXML
    private Label exam_1_gpa_8;

    @FXML
    private Label exam_1_gpa_9;

    @FXML
    private Label exam_1_mark_1;

    @FXML
    private Label exam_1_mark_10;

    @FXML
    private Label exam_1_mark_11;

    @FXML
    private Label exam_1_mark_12;

    @FXML
    private Label exam_1_mark_13;

    @FXML
    private Label exam_1_mark_14;

    @FXML
    private Label exam_1_mark_15;

    @FXML
    private Label exam_1_mark_2;

    @FXML
    private Label exam_1_mark_3;

    @FXML
    private Label exam_1_mark_4;

    @FXML
    private Label exam_1_mark_5;

    @FXML
    private Label exam_1_mark_6;

    @FXML
    private Label exam_1_mark_7;

    @FXML
    private Label exam_1_mark_8;

    @FXML
    private Label exam_1_mark_9;

    @FXML
    private Label exam_2_gpa_1;

    @FXML
    private Label exam_2_gpa_10;

    @FXML
    private Label exam_2_gpa_11;

    @FXML
    private Label exam_2_gpa_12;

    @FXML
    private Label exam_2_gpa_13;

    @FXML
    private Label exam_2_gpa_14;

    @FXML
    private Label exam_2_gpa_15;

    @FXML
    private Label exam_2_gpa_2;

    @FXML
    private Label exam_2_gpa_3;

    @FXML
    private Label exam_2_gpa_4;

    @FXML
    private Label exam_2_gpa_5;

    @FXML
    private Label exam_2_gpa_6;

    @FXML
    private Label exam_2_gpa_7;

    @FXML
    private Label exam_2_gpa_8;

    @FXML
    private Label exam_2_gpa_9;

    @FXML
    private Label exam_2_mark_1;

    @FXML
    private Label exam_2_mark_10;

    @FXML
    private Label exam_2_mark_11;

    @FXML
    private Label exam_2_mark_12;

    @FXML
    private Label exam_2_mark_13;

    @FXML
    private Label exam_2_mark_14;

    @FXML
    private Label exam_2_mark_15;

    @FXML
    private Label exam_2_mark_2;

    @FXML
    private Label exam_2_mark_3;

    @FXML
    private Label exam_2_mark_4;

    @FXML
    private Label exam_2_mark_5;

    @FXML
    private Label exam_2_mark_6;

    @FXML
    private Label exam_2_mark_7;

    @FXML
    private Label exam_2_mark_8;

    @FXML
    private Label exam_2_mark_9;

    @FXML
    private Button next;

    @FXML
    private Button previous;

    @FXML
    private Label rank_1;

    @FXML
    private Label rank_10;

    @FXML
    private Label rank_11;

    @FXML
    private Label rank_12;

    @FXML
    private Label rank_13;

    @FXML
    private Label rank_14;

    @FXML
    private Label rank_15;

    @FXML
    private Label rank_2;

    @FXML
    private Label rank_3;

    @FXML
    private Label rank_4;

    @FXML
    private Label rank_5;

    @FXML
    private Label rank_6;

    @FXML
    private Label rank_7;

    @FXML
    private Label rank_8;

    @FXML
    private Label rank_9;

    @FXML
    private Label roll_1;

    @FXML
    private Label roll_10;

    @FXML
    private Label roll_11;

    @FXML
    private Label roll_12;

    @FXML
    private Label roll_13;

    @FXML
    private Label roll_14;

    @FXML
    private Label roll_15;

    @FXML
    private Label roll_2;

    @FXML
    private Label roll_3;

    @FXML
    private Label roll_4;

    @FXML
    private Label roll_5;

    @FXML
    private Label roll_6;

    @FXML
    private Label roll_7;

    @FXML
    private Label roll_8;

    @FXML
    private Label roll_9;

    @FXML
    private MenuButton selectClass;

    @FXML
    private MenuButton selectSection;

    @FXML
    private Label wAvgGpa_1;

    @FXML
    private Label wAvgGpa_10;

    @FXML
    private Label wAvgGpa_11;

    @FXML
    private Label wAvgGpa_12;

    @FXML
    private Label wAvgGpa_13;

    @FXML
    private Label wAvgGpa_14;

    @FXML
    private Label wAvgGpa_15;

    @FXML
    private Label wAvgGpa_2;

    @FXML
    private Label wAvgGpa_3;

    @FXML
    private Label wAvgGpa_4;

    @FXML
    private Label wAvgGpa_5;

    @FXML
    private Label wAvgGpa_6;

    @FXML
    private Label wAvgGpa_7;

    @FXML
    private Label wAvgGpa_8;

    @FXML
    private Label wAvgGpa_9;

    @FXML
    private Label wAvgMark_1;

    @FXML
    private Label wAvgMark_10;

    @FXML
    private Label wAvgMark_11;

    @FXML
    private Label wAvgMark_12;

    @FXML
    private Label wAvgMark_13;

    @FXML
    private Label wAvgMark_14;

    @FXML
    private Label wAvgMark_15;

    @FXML
    private Label wAvgMark_2;

    @FXML
    private Label wAvgMark_3;

    @FXML
    private Label wAvgMark_4;

    @FXML
    private Label wAvgMark_5;

    @FXML
    private Label wAvgMark_6;

    @FXML
    private Label wAvgMark_7;

    @FXML
    private Label wAvgMark_8;

    @FXML
    private Label wAvgMark_9;

    @FXML
    private AnchorPane classResult;

    @FXML
    private ScrollPane resultScroll;

    private static int selectedClass;

    private static String selectedSection;

    private static int currentIndex;

    public static int getSelectedClass() {
        return selectedClass;
    }

    public static void setSelectedClass(int selectedClass) {
        ClassResultController.selectedClass = selectedClass;
    }

    public static String getSelectedSection() {
        return selectedSection;
    }

    public static void setSelectedSection(String selectedSection) {
        ClassResultController.selectedSection = selectedSection;
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        ClassResultController.currentIndex = currentIndex;
    }

    @FXML
    void class_1(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 1;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_10(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 10;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_2(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 2;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_3(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 3;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_4(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 4;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_5(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 5;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_6(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 6;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_7(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 7;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_8(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 8;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void class_9(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedClass = 9;
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void handleCalculate(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/schoolmanagementsystem/fxml_Files/resultAlgorithm.fxml"));
        Parent algorithmPage = loader.load();

        algorithmPage.setLayoutX(classResult.getWidth() / 2 - algorithmPage.prefWidth(-1) / 2);
        algorithmPage.setLayoutY(classResult.getHeight() / 2 - algorithmPage.prefHeight(-1) / 2);
        algorithmPage.setTranslateX(0);
        algorithmPage.setTranslateY(0);

        // Add the new page as a child node to the root pane
        classResult.getChildren().add(algorithmPage);
    }

    @FXML
    void handleClass(ActionEvent event) throws IOException {

    }

    @FXML
    void handleSection(ActionEvent event) {

    }

    @FXML
    void section_A(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedSection = "A";
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void section_B(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedSection = "B";
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void section_C(ActionEvent event) throws IOException, SQLException {
        ClassResultController.selectedSection = "C";
        Controller.resultFlag = false;
        ClassResultController.currentIndex = 0;
        handleClassResultPage(event);
    }

    @FXML
    void handleNext(ActionEvent event) throws SQLException, IOException {
        ClassResultController.currentIndex += 15;
        Controller.resultFlag = true;
        handleClassResultPage(event);
    }

    @FXML
    void handlePrevious(ActionEvent event) throws SQLException, IOException {
        ClassResultController.currentIndex -= 15;
        Controller.resultFlag = true;
        handleClassResultPage(event);
    }

    public void handleClassResultPage(Event event) throws IOException, SQLException {
        String buttonType;
        if (Controller.resultFlag) {
            buttonType = "button";
        } else {
            buttonType = "menuButton";
        }

        FXMLLoader fxmlLoader = loadPage(buttonType, "/com/schoolmanagementsystem/fxml_Files/classResult.fxml", event);
        ClassResultController controller = fxmlLoader.getController();

        if (ClassResultController.selectedSection != null) {
            controller.selectSection.setText("Section " + ClassResultController.selectedSection);
        }
        if (ClassResultController.selectedClass != 0) {
            controller.selectClass.setText("Class " + ClassResultController.selectedClass);
        }

        if (ClassResultController.selectedClass == 0 || ClassResultController.selectedSection == null) {
            controller.resultScroll.setVisible(false);
            controller.previous.setVisible(false);
            controller.next.setVisible(false);
            controller.calculate.setVisible(false);
            return;
        }

        Student student = new Student();
        ArrayList<Pair<Integer, Integer>> allStudents = student.allStudents(ClassResultController.selectedClass,
                ClassResultController.selectedSection);

        ArrayList<String> allSubjects = null;
        StudentResultController controller1 = new StudentResultController();
        if (allStudents.size() > 0) {
            allSubjects = controller1.allSubjects(allStudents.get(0).getKey());
        }

        ArrayList<Result> allResults = new ArrayList<>();

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query;
        PreparedStatement statement;
        ResultSet r;

        query = "SELECT * FROM resultAlgo WHERE demoID = ?";
        statement = con.prepareStatement(query);
        statement.setInt(1, 1);
        r = statement.executeQuery();

        int w1 = 50, w2 = 50;
        String priority = "gpa";

        if (r.next()) {
            w1 = r.getInt("halfYearlyWeight");
            w2 = r.getInt("yearFinalWeight");
            priority = r.getString("priority");
        }

        Result.setSortBy(priority);

        DecimalFormat df = new DecimalFormat("#.##");

        for (int i = 0; i < allStudents.size(); i++) {
            int studentID = allStudents.get(i).getKey();
            int roll = allStudents.get(i).getValue();
            int total = 0;
            float totalGPA = 0;
            float averageGPA = 0;
            Result result = new Result();
            result.setRoll(roll);

            query = "SELECT * FROM resultinfo_HalfYearly WHERE ID = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, studentID);
            r = statement.executeQuery();

            if (r.next()) {
                for (int j = 0; j < (Objects.requireNonNull(allSubjects)).size(); j++) {
                    total += r.getInt(allSubjects.get(j));
                    totalGPA += Float.parseFloat(controller1.getGPA(r.getInt(allSubjects.get(j))));
                }
            }
            assert allSubjects != null;
            averageGPA = Float.parseFloat(df.format(totalGPA / allSubjects.size()));
            result.setHalfYearlyGpa(averageGPA);
            result.setHalfYearlyMark(total);

            total = 0;
            totalGPA = 0;

            query = "SELECT * FROM resultinfo_YearFinal WHERE ID = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, studentID);
            r = statement.executeQuery();

            if (r.next()) {
                for (int j = 0; j < Objects.requireNonNull(allSubjects).size(); j++) {
                    total += r.getInt(allSubjects.get(j));
                    totalGPA += Float.parseFloat(controller1.getGPA(r.getInt(allSubjects.get(j))));
                }
            }
            averageGPA = Float.parseFloat(df.format(totalGPA / allSubjects.size()));
            result.setYearFinalGpa(averageGPA);
            result.setYearFinalMark(total);

            result.setwAvgGpa(Float
                    .parseFloat(df.format((result.getHalfYearlyGpa() * w1 + result.getYearFinalGpa() * w2) / 100)));
            result.setwAvgMark(Float.parseFloat(
                    df.format((result.getHalfYearlyMark() * w1 + result.getYearFinalMark() * w2) / 100.0f)));

            allResults.add(result);
        }

        Collections.sort(allResults);

        for (int i = 0; i < allResults.size(); i++) {
            allResults.get(i).setRank(i + 1);
        }

        Result.setSortBy(null);

        Collections.sort(allResults);
        // if(allStudents.size() == )

        for (int i = ClassResultController.currentIndex; i < ClassResultController.currentIndex + 15; i++) {
            if (i >= allResults.size()) {
                break;
            } else if (i == ClassResultController.currentIndex) {
                controller.roll_1.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_1.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_1.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_1.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_1.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_1.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_1.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_1.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 1) {
                controller.roll_2.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_2.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_2.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_2.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_2.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_2.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_2.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_2.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 2) {
                controller.roll_3.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_3.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_3.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_3.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_3.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_3.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_3.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_3.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 3) {
                controller.roll_4.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_4.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_4.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_4.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_4.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_4.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_4.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_4.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 4) {
                controller.roll_5.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_5.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_5.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_5.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_5.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_5.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_5.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_5.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 5) {
                controller.roll_6.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_6.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_6.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_6.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_6.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_6.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_6.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_6.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 6) {
                controller.roll_7.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_7.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_7.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_7.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_7.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_7.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_7.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_7.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 7) {
                controller.roll_8.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_8.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_8.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_8.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_8.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_8.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_8.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_8.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 8) {
                controller.roll_9.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_9.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_9.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_9.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_9.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_9.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_9.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_9.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 9) {
                controller.roll_10.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_10.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_10.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_10.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_10.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_10.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_10.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_10.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 10) {
                controller.roll_11.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_11.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_11.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_11.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_11.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_11.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_11.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_11.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 11) {
                controller.roll_12.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_12.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_12.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_12.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_12.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_12.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_12.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_12.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 12) {
                controller.roll_13.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_13.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_13.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_13.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_13.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_13.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_13.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_13.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 13) {
                controller.roll_14.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_14.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_14.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_14.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_14.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_14.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_14.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_14.setText(String.valueOf(allResults.get(i).getRank()));
            } else if (i == ClassResultController.currentIndex + 14) {
                controller.roll_15.setText(String.valueOf(allResults.get(i).getRoll()));
                controller.exam_1_mark_15.setText(String.valueOf(allResults.get(i).getHalfYearlyMark()));
                controller.exam_1_gpa_15.setText(String.valueOf(allResults.get(i).getHalfYearlyGpa()));
                controller.exam_2_mark_15.setText(String.valueOf(allResults.get(i).getYearFinalMark()));
                controller.exam_2_gpa_15.setText(String.valueOf(allResults.get(i).getYearFinalGpa()));
                controller.wAvgMark_15.setText(String.valueOf(allResults.get(i).getwAvgMark()));
                controller.wAvgGpa_15.setText(String.valueOf(allResults.get(i).getwAvgGpa()));
                controller.rank_15.setText(String.valueOf(allResults.get(i).getRank()));
            }
        }

        if (ClassResultController.currentIndex == 0) {
            controller.previous.setVisible(false);
        }
        if (ClassResultController.currentIndex + 15 >= allResults.size()) {
            controller.next.setVisible(false);
        }

    }
}
