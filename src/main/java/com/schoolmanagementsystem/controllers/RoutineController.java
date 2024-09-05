package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.routineDB.ConnectRoutineDB;
import com.schoolmanagementsystem.database.routineDB.RoutineCRUD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoutineController extends Controller implements Initializable {

    private static int selectedClass;

    private static String selectedSection;

    private static boolean editFlag;

    private static boolean applyFlag;
    @FXML
    private GridPane grid;

    @FXML
    private Label editButton;

    @FXML
    private MenuButton selectClass;

    @FXML
    private MenuButton selectSection;

    @FXML
    private Label sub1;

    @FXML
    private Label sub10;

    @FXML
    private Label sub11;

    @FXML
    private Label sub12;

    @FXML
    private Label sub13;

    @FXML
    private Label sub14;

    @FXML
    private Label sub15;

    @FXML
    private Label sub16;

    @FXML
    private Label sub17;

    @FXML
    private Label sub18;

    @FXML
    private Label sub19;

    @FXML
    private Label sub2;

    @FXML
    private Label sub20;

    @FXML
    private Label sub21;

    @FXML
    private Label sub22;

    @FXML
    private Label sub23;

    @FXML
    private Label sub24;

    @FXML
    private Label sub25;

    @FXML
    private Label sub3;

    @FXML
    private Label sub4;

    @FXML
    private Label sub5;

    @FXML
    private Label sub6;

    @FXML
    private Label sub7;

    @FXML
    private Label sub8;

    @FXML
    private Label sub9;

    @FXML
    private TextField subInput1;

    @FXML
    private TextField subInput10;

    @FXML
    private TextField subInput11;

    @FXML
    private TextField subInput12;

    @FXML
    private TextField subInput13;

    @FXML
    private TextField subInput14;

    @FXML
    private TextField subInput15;

    @FXML
    private TextField subInput16;

    @FXML
    private TextField subInput17;

    @FXML
    private TextField subInput18;

    @FXML
    private TextField subInput19;

    @FXML
    private TextField subInput2;

    @FXML
    private TextField subInput20;

    @FXML
    private TextField subInput21;

    @FXML
    private TextField subInput22;

    @FXML
    private TextField subInput23;

    @FXML
    private TextField subInput24;

    @FXML
    private TextField subInput25;

    @FXML
    private TextField subInput3;

    @FXML
    private TextField subInput4;

    @FXML
    private TextField subInput5;

    @FXML
    private TextField subInput6;

    @FXML
    private TextField subInput7;

    @FXML
    private TextField subInput8;

    @FXML
    private TextField subInput9;

    @FXML
    private Label teacher1;

    @FXML
    private Label teacher10;

    @FXML
    private Label teacher11;

    @FXML
    private Label teacher12;

    @FXML
    private Label teacher13;

    @FXML
    private Label teacher14;

    @FXML
    private Label teacher15;

    @FXML
    private Label teacher16;

    @FXML
    private Label teacher17;

    @FXML
    private Label teacher18;

    @FXML
    private Label teacher19;

    @FXML
    private Label teacher2;

    @FXML
    private Label teacher20;

    @FXML
    private Label teacher21;

    @FXML
    private Label teacher22;

    @FXML
    private Label teacher23;

    @FXML
    private Label teacher24;

    @FXML
    private Label teacher25;

    @FXML
    private Label teacher3;

    @FXML
    private Label teacher4;

    @FXML
    private Label teacher5;

    @FXML
    private Label teacher6;

    @FXML
    private Label teacher7;

    @FXML
    private Label teacher8;

    @FXML
    private Label teacher9;

    @FXML
    private TextField teacherInput1;

    @FXML
    private TextField teacherInput10;

    @FXML
    private TextField teacherInput11;

    @FXML
    private TextField teacherInput12;

    @FXML
    private TextField teacherInput13;

    @FXML
    private TextField teacherInput14;

    @FXML
    private TextField teacherInput15;

    @FXML
    private TextField teacherInput16;

    @FXML
    private TextField teacherInput17;

    @FXML
    private TextField teacherInput18;

    @FXML
    private TextField teacherInput19;

    @FXML
    private TextField teacherInput2;

    @FXML
    private TextField teacherInput20;

    @FXML
    private TextField teacherInput21;

    @FXML
    private TextField teacherInput22;

    @FXML
    private TextField teacherInput23;

    @FXML
    private TextField teacherInput24;

    @FXML
    private TextField teacherInput25;

    @FXML
    private TextField teacherInput3;

    @FXML
    private TextField teacherInput4;

    @FXML
    private TextField teacherInput5;

    @FXML
    private TextField teacherInput6;

    @FXML
    private TextField teacherInput7;

    @FXML
    private TextField teacherInput8;

    @FXML
    private TextField teacherInput9;

    public void handleRoutinePage(Event event) throws IOException, SQLException {

        String buttonType;

        if (Controller.routineFlag) {
            buttonType = "button";
            Controller.routineFlag = false;
        } else {
            if (editFlag || applyFlag) {
                buttonType = "label";
            } else {
                buttonType = "menuButton";
            }
        }

        FXMLLoader fxmlLoader = loadPage(buttonType, "/com/schoolmanagementsystem/fxml_Files/routine.fxml", event);
        RoutineController controller = fxmlLoader.getController();

        if (RoutineController.selectedSection != null) {
            controller.selectSection.setText("Section " + RoutineController.selectedSection);
        }
        if (RoutineController.selectedClass != 0) {
            controller.selectClass.setText("Class " + RoutineController.selectedClass);
        }

        if (RoutineController.selectedSection == null || RoutineController.selectedClass == 0) {
            return;
        }

        controller.grid.setVisible(true);
        controller.editButton.setVisible(true);

        if (RoutineController.editFlag) {
            controller.editButton.setText("APPLY CHANGE");
        } else {
            controller.editButton.setText("EDIT ROUTINE");
        }

        if (!Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            controller.editButton.setVisible(false);
        }

        ConnectRoutineDB db = new ConnectRoutineDB();
        Connection con = db.getCon();

        String tableName = "class_" + RoutineController.getSelectedClass() + "_"
                + RoutineController.getSelectedSection();

        int i;

        if (!RoutineController.editFlag) {
            controller.subInput1.setVisible(false);
            controller.subInput1.setManaged(false);
            controller.subInput2.setVisible(false);
            controller.subInput2.setManaged(false);
            controller.subInput3.setVisible(false);
            controller.subInput3.setManaged(false);
            controller.subInput4.setVisible(false);
            controller.subInput4.setManaged(false);
            controller.subInput5.setVisible(false);
            controller.subInput5.setManaged(false);
            controller.subInput6.setVisible(false);
            controller.subInput6.setManaged(false);
            controller.subInput7.setVisible(false);
            controller.subInput7.setManaged(false);
            controller.subInput8.setVisible(false);
            controller.subInput8.setManaged(false);
            controller.subInput9.setVisible(false);
            controller.subInput9.setManaged(false);
            controller.subInput10.setVisible(false);
            controller.subInput10.setManaged(false);
            controller.subInput11.setVisible(false);
            controller.subInput11.setManaged(false);
            controller.subInput12.setVisible(false);
            controller.subInput12.setManaged(false);
            controller.subInput13.setVisible(false);
            controller.subInput13.setManaged(false);
            controller.subInput14.setVisible(false);
            controller.subInput14.setManaged(false);
            controller.subInput15.setVisible(false);
            controller.subInput15.setManaged(false);
            controller.subInput16.setVisible(false);
            controller.subInput16.setManaged(false);
            controller.subInput17.setVisible(false);
            controller.subInput17.setManaged(false);
            controller.subInput18.setVisible(false);
            controller.subInput18.setManaged(false);
            controller.subInput19.setVisible(false);
            controller.subInput19.setManaged(false);
            controller.subInput20.setVisible(false);
            controller.subInput20.setManaged(false);
            controller.subInput21.setVisible(false);
            controller.subInput21.setManaged(false);
            controller.subInput22.setVisible(false);
            controller.subInput22.setManaged(false);
            controller.subInput23.setVisible(false);
            controller.subInput23.setManaged(false);
            controller.subInput24.setVisible(false);
            controller.subInput24.setManaged(false);
            controller.subInput25.setVisible(false);
            controller.subInput25.setManaged(false);

            controller.teacherInput1.setVisible(false);
            controller.teacherInput1.setManaged(false);
            controller.teacherInput2.setVisible(false);
            controller.teacherInput2.setManaged(false);
            controller.teacherInput3.setVisible(false);
            controller.teacherInput3.setManaged(false);
            controller.teacherInput4.setVisible(false);
            controller.teacherInput4.setManaged(false);
            controller.teacherInput5.setVisible(false);
            controller.teacherInput5.setManaged(false);
            controller.teacherInput6.setVisible(false);
            controller.teacherInput6.setManaged(false);
            controller.teacherInput7.setVisible(false);
            controller.teacherInput7.setManaged(false);
            controller.teacherInput8.setVisible(false);
            controller.teacherInput8.setManaged(false);
            controller.teacherInput9.setVisible(false);
            controller.teacherInput9.setManaged(false);
            controller.teacherInput10.setVisible(false);
            controller.teacherInput10.setManaged(false);
            controller.teacherInput11.setVisible(false);
            controller.teacherInput11.setManaged(false);
            controller.teacherInput12.setVisible(false);
            controller.teacherInput12.setManaged(false);
            controller.teacherInput13.setVisible(false);
            controller.teacherInput13.setManaged(false);
            controller.teacherInput14.setVisible(false);
            controller.teacherInput14.setManaged(false);
            controller.teacherInput15.setVisible(false);
            controller.teacherInput15.setManaged(false);
            controller.teacherInput16.setVisible(false);
            controller.teacherInput16.setManaged(false);
            controller.teacherInput17.setVisible(false);
            controller.teacherInput17.setManaged(false);
            controller.teacherInput18.setVisible(false);
            controller.teacherInput18.setManaged(false);
            controller.teacherInput19.setVisible(false);
            controller.teacherInput19.setManaged(false);
            controller.teacherInput20.setVisible(false);
            controller.teacherInput20.setManaged(false);
            controller.teacherInput21.setVisible(false);
            controller.teacherInput21.setManaged(false);
            controller.teacherInput22.setVisible(false);
            controller.teacherInput22.setManaged(false);
            controller.teacherInput23.setVisible(false);
            controller.teacherInput23.setManaged(false);
            controller.teacherInput24.setVisible(false);
            controller.teacherInput24.setManaged(false);
            controller.teacherInput25.setVisible(false);
            controller.teacherInput25.setManaged(false);

            for (i = 1; i <= 25; i++) {
                String query = "SELECT * FROM " + tableName + " WHERE periodNo = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, i);

                ResultSet r = statement.executeQuery();

                if (r.next()) {
                    if (i == 1) {
                        controller.sub1.setText(r.getString("subject"));
                        controller.teacher1.setText(r.getString("teacher"));
                    } else if (i == 2) {
                        controller.sub2.setText(r.getString("subject"));
                        controller.teacher2.setText(r.getString("teacher"));
                    } else if (i == 3) {
                        controller.sub3.setText(r.getString("subject"));
                        controller.teacher3.setText(r.getString("teacher"));
                    } else if (i == 4) {
                        controller.sub4.setText(r.getString("subject"));
                        controller.teacher4.setText(r.getString("teacher"));
                    } else if (i == 5) {
                        controller.sub5.setText(r.getString("subject"));
                        controller.teacher5.setText(r.getString("teacher"));
                    } else if (i == 6) {
                        controller.sub6.setText(r.getString("subject"));
                        controller.teacher6.setText(r.getString("teacher"));
                    } else if (i == 7) {
                        controller.sub7.setText(r.getString("subject"));
                        controller.teacher7.setText(r.getString("teacher"));
                    } else if (i == 8) {
                        controller.sub8.setText(r.getString("subject"));
                        controller.teacher8.setText(r.getString("teacher"));
                    } else if (i == 9) {
                        controller.sub9.setText(r.getString("subject"));
                        controller.teacher9.setText(r.getString("teacher"));
                    } else if (i == 10) {
                        controller.sub10.setText(r.getString("subject"));
                        controller.teacher10.setText(r.getString("teacher"));
                    } else if (i == 11) {
                        controller.sub11.setText(r.getString("subject"));
                        controller.teacher11.setText(r.getString("teacher"));
                    } else if (i == 12) {
                        controller.sub12.setText(r.getString("subject"));
                        controller.teacher12.setText(r.getString("teacher"));
                    } else if (i == 13) {
                        controller.sub13.setText(r.getString("subject"));
                        controller.teacher13.setText(r.getString("teacher"));
                    } else if (i == 14) {
                        controller.sub14.setText(r.getString("subject"));
                        controller.teacher14.setText(r.getString("teacher"));
                    } else if (i == 15) {
                        controller.sub15.setText(r.getString("subject"));
                        controller.teacher15.setText(r.getString("teacher"));
                    } else if (i == 16) {
                        controller.sub16.setText(r.getString("subject"));
                        controller.teacher16.setText(r.getString("teacher"));
                    } else if (i == 17) {
                        controller.sub17.setText(r.getString("subject"));
                        controller.teacher17.setText(r.getString("teacher"));
                    } else if (i == 18) {
                        controller.sub18.setText(r.getString("subject"));
                        controller.teacher18.setText(r.getString("teacher"));
                    } else if (i == 19) {
                        controller.sub19.setText(r.getString("subject"));
                        controller.teacher19.setText(r.getString("teacher"));
                    } else if (i == 20) {
                        controller.sub20.setText(r.getString("subject"));
                        controller.teacher20.setText(r.getString("teacher"));
                    } else if (i == 21) {
                        controller.sub21.setText(r.getString("subject"));
                        controller.teacher21.setText(r.getString("teacher"));
                    } else if (i == 22) {
                        controller.sub22.setText(r.getString("subject"));
                        controller.teacher22.setText(r.getString("teacher"));
                    } else if (i == 23) {
                        controller.sub23.setText(r.getString("subject"));
                        controller.teacher23.setText(r.getString("teacher"));
                    } else if (i == 24) {
                        controller.sub24.setText(r.getString("subject"));
                        controller.teacher24.setText(r.getString("teacher"));
                    } else if (i == 25) {
                        controller.sub25.setText(r.getString("subject"));
                        controller.teacher25.setText(r.getString("teacher"));
                    }
                }
            }

        } else {
            controller.sub1.setVisible(false);
            controller.sub1.setManaged(false);
            controller.sub2.setVisible(false);
            controller.sub2.setManaged(false);
            controller.sub3.setVisible(false);
            controller.sub3.setManaged(false);
            controller.sub4.setVisible(false);
            controller.sub4.setManaged(false);
            controller.sub5.setVisible(false);
            controller.sub5.setManaged(false);
            controller.sub6.setVisible(false);
            controller.sub6.setManaged(false);
            controller.sub7.setVisible(false);
            controller.sub7.setManaged(false);
            controller.sub8.setVisible(false);
            controller.sub8.setManaged(false);
            controller.sub9.setVisible(false);
            controller.sub9.setManaged(false);
            controller.sub10.setVisible(false);
            controller.sub10.setManaged(false);
            controller.sub11.setVisible(false);
            controller.sub11.setManaged(false);
            controller.sub12.setVisible(false);
            controller.sub12.setManaged(false);
            controller.sub13.setVisible(false);
            controller.sub13.setManaged(false);
            controller.sub14.setVisible(false);
            controller.sub14.setManaged(false);
            controller.sub15.setVisible(false);
            controller.sub15.setManaged(false);
            controller.sub16.setVisible(false);
            controller.sub16.setManaged(false);
            controller.sub17.setVisible(false);
            controller.sub17.setManaged(false);
            controller.sub18.setVisible(false);
            controller.sub18.setManaged(false);
            controller.sub19.setVisible(false);
            controller.sub19.setManaged(false);
            controller.sub20.setVisible(false);
            controller.sub20.setManaged(false);
            controller.sub21.setVisible(false);
            controller.sub21.setManaged(false);
            controller.sub22.setVisible(false);
            controller.sub22.setManaged(false);
            controller.sub23.setVisible(false);
            controller.sub23.setManaged(false);
            controller.sub24.setVisible(false);
            controller.sub24.setManaged(false);
            controller.sub25.setVisible(false);
            controller.sub25.setManaged(false);

            controller.teacher1.setVisible(false);
            controller.teacher1.setManaged(false);
            controller.teacher2.setVisible(false);
            controller.teacher2.setManaged(false);
            controller.teacher3.setVisible(false);
            controller.teacher3.setManaged(false);
            controller.teacher4.setVisible(false);
            controller.teacher4.setManaged(false);
            controller.teacher5.setVisible(false);
            controller.teacher5.setManaged(false);
            controller.teacher6.setVisible(false);
            controller.teacher6.setManaged(false);
            controller.teacher7.setVisible(false);
            controller.teacher7.setManaged(false);
            controller.teacher8.setVisible(false);
            controller.teacher8.setManaged(false);
            controller.teacher9.setVisible(false);
            controller.teacher9.setManaged(false);
            controller.teacher10.setVisible(false);
            controller.teacher10.setManaged(false);
            controller.teacher11.setVisible(false);
            controller.teacher11.setManaged(false);
            controller.teacher12.setVisible(false);
            controller.teacher12.setManaged(false);
            controller.teacher13.setVisible(false);
            controller.teacher13.setManaged(false);
            controller.teacher14.setVisible(false);
            controller.teacher14.setManaged(false);
            controller.teacher15.setVisible(false);
            controller.teacher15.setManaged(false);
            controller.teacher16.setVisible(false);
            controller.teacher16.setManaged(false);
            controller.teacher17.setVisible(false);
            controller.teacher17.setManaged(false);
            controller.teacher18.setVisible(false);
            controller.teacher18.setManaged(false);
            controller.teacher19.setVisible(false);
            controller.teacher19.setManaged(false);
            controller.teacher20.setVisible(false);
            controller.teacher20.setManaged(false);
            controller.teacher21.setVisible(false);
            controller.teacher21.setManaged(false);
            controller.teacher22.setVisible(false);
            controller.teacher22.setManaged(false);
            controller.teacher23.setVisible(false);
            controller.teacher23.setManaged(false);
            controller.teacher24.setVisible(false);
            controller.teacher24.setManaged(false);
            controller.teacher25.setVisible(false);
            controller.teacher25.setManaged(false);

            for (i = 1; i <= 25; i++) {
                String query = "SELECT * FROM " + tableName + " WHERE periodNo = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, i);

                ResultSet r = statement.executeQuery();

                if (r.next()) {
                    if (i == 1) {
                        controller.subInput1.setText(r.getString("subject"));
                        controller.teacherInput1.setText(r.getString("teacher"));
                    } else if (i == 2) {
                        controller.subInput2.setText(r.getString("subject"));
                        controller.teacherInput2.setText(r.getString("teacher"));
                    } else if (i == 3) {
                        controller.subInput3.setText(r.getString("subject"));
                        controller.teacherInput3.setText(r.getString("teacher"));
                    } else if (i == 4) {
                        controller.subInput4.setText(r.getString("subject"));
                        controller.teacherInput4.setText(r.getString("teacher"));
                    } else if (i == 5) {
                        controller.subInput5.setText(r.getString("subject"));
                        controller.teacherInput5.setText(r.getString("teacher"));
                    } else if (i == 6) {
                        controller.subInput6.setText(r.getString("subject"));
                        controller.teacherInput6.setText(r.getString("teacher"));
                    } else if (i == 7) {
                        controller.subInput7.setText(r.getString("subject"));
                        controller.teacherInput7.setText(r.getString("teacher"));
                    } else if (i == 8) {
                        controller.subInput8.setText(r.getString("subject"));
                        controller.teacherInput8.setText(r.getString("teacher"));
                    } else if (i == 9) {
                        controller.subInput9.setText(r.getString("subject"));
                        controller.teacherInput9.setText(r.getString("teacher"));
                    } else if (i == 10) {
                        controller.subInput10.setText(r.getString("subject"));
                        controller.teacherInput10.setText(r.getString("teacher"));
                    } else if (i == 11) {
                        controller.subInput11.setText(r.getString("subject"));
                        controller.teacherInput11.setText(r.getString("teacher"));
                    } else if (i == 12) {
                        controller.subInput12.setText(r.getString("subject"));
                        controller.teacherInput12.setText(r.getString("teacher"));
                    } else if (i == 13) {
                        controller.subInput13.setText(r.getString("subject"));
                        controller.teacherInput13.setText(r.getString("teacher"));
                    } else if (i == 14) {
                        controller.subInput14.setText(r.getString("subject"));
                        controller.teacherInput14.setText(r.getString("teacher"));
                    } else if (i == 15) {
                        controller.subInput15.setText(r.getString("subject"));
                        controller.teacherInput15.setText(r.getString("teacher"));
                    } else if (i == 16) {
                        controller.subInput16.setText(r.getString("subject"));
                        controller.teacherInput16.setText(r.getString("teacher"));
                    } else if (i == 17) {
                        controller.subInput17.setText(r.getString("subject"));
                        controller.teacherInput17.setText(r.getString("teacher"));
                    } else if (i == 18) {
                        controller.subInput18.setText(r.getString("subject"));
                        controller.teacherInput18.setText(r.getString("teacher"));
                    } else if (i == 19) {
                        controller.subInput19.setText(r.getString("subject"));
                        controller.teacherInput19.setText(r.getString("teacher"));
                    } else if (i == 20) {
                        controller.subInput20.setText(r.getString("subject"));
                        controller.teacherInput20.setText(r.getString("teacher"));
                    } else if (i == 21) {
                        controller.subInput21.setText(r.getString("subject"));
                        controller.teacherInput21.setText(r.getString("teacher"));
                    } else if (i == 22) {
                        controller.subInput22.setText(r.getString("subject"));
                        controller.teacherInput22.setText(r.getString("teacher"));
                    } else if (i == 23) {
                        controller.subInput23.setText(r.getString("subject"));
                        controller.teacherInput23.setText(r.getString("teacher"));
                    } else if (i == 24) {
                        controller.subInput24.setText(r.getString("subject"));
                        controller.teacherInput24.setText(r.getString("teacher"));
                    } else if (i == 25) {
                        controller.subInput25.setText(r.getString("subject"));
                        controller.teacherInput25.setText(r.getString("teacher"));
                    }
                }
            }
        }

    }

    @FXML
    void handleClass(Event event) {

    }

    public static int getSelectedClass() {
        return selectedClass;
    }

    public static void setSelectedClass(int selectedClass) {
        RoutineController.selectedClass = selectedClass;
    }

    public static String getSelectedSection() {
        return selectedSection;
    }

    public static void setSelectedSection(String selectedSection) {
        RoutineController.selectedSection = selectedSection;
    }

    @FXML
    void handleEdit(Event event) throws IOException, SQLException {

        if (!RoutineController.editFlag) {
            RoutineController.editFlag = true;
            RoutineController.applyFlag = false;
        } else {
            RoutineController.editFlag = false;
            RoutineController.applyFlag = true;

            String[] sub = new String[26];
            String[] teacher = new String[26];

            sub[1] = (subInput1.getText().isEmpty() ? "" : subInput1.getText());
            teacher[1] = (teacherInput1.getText().isEmpty() ? "" : teacherInput1.getText());
            sub[2] = (subInput2.getText().isEmpty() ? "" : subInput2.getText());
            teacher[2] = (teacherInput2.getText().isEmpty() ? "" : teacherInput2.getText());
            sub[3] = (subInput3.getText().isEmpty() ? "" : subInput3.getText());
            teacher[3] = (teacherInput3.getText().isEmpty() ? "" : teacherInput3.getText());
            sub[4] = (subInput4.getText().isEmpty() ? "" : subInput4.getText());
            teacher[4] = (teacherInput4.getText().isEmpty() ? "" : teacherInput4.getText());
            sub[5] = (subInput5.getText().isEmpty() ? "" : subInput5.getText());
            teacher[5] = (teacherInput5.getText().isEmpty() ? "" : teacherInput5.getText());
            sub[6] = (subInput6.getText().isEmpty() ? "" : subInput6.getText());
            teacher[6] = (teacherInput6.getText().isEmpty() ? "" : teacherInput6.getText());
            sub[7] = (subInput7.getText().isEmpty() ? "" : subInput7.getText());
            teacher[7] = (teacherInput7.getText().isEmpty() ? "" : teacherInput7.getText());
            sub[8] = (subInput8.getText().isEmpty() ? "" : subInput8.getText());
            teacher[8] = (teacherInput8.getText().isEmpty() ? "" : teacherInput8.getText());
            sub[9] = (subInput9.getText().isEmpty() ? "" : subInput9.getText());
            teacher[9] = (teacherInput9.getText().isEmpty() ? "" : teacherInput9.getText());
            sub[10] = (subInput10.getText().isEmpty() ? "" : subInput10.getText());
            teacher[10] = (teacherInput10.getText().isEmpty() ? "" : teacherInput10.getText());
            sub[11] = (subInput11.getText().isEmpty() ? "" : subInput11.getText());
            teacher[11] = (teacherInput11.getText().isEmpty() ? "" : teacherInput11.getText());
            sub[12] = (subInput12.getText().isEmpty() ? "" : subInput12.getText());
            teacher[12] = (teacherInput12.getText().isEmpty() ? "" : teacherInput12.getText());
            sub[13] = (subInput13.getText().isEmpty() ? "" : subInput13.getText());
            teacher[13] = (teacherInput13.getText().isEmpty() ? "" : teacherInput13.getText());
            sub[14] = (subInput14.getText().isEmpty() ? "" : subInput14.getText());
            teacher[14] = (teacherInput14.getText().isEmpty() ? "" : teacherInput14.getText());
            sub[15] = (subInput15.getText().isEmpty() ? "" : subInput15.getText());
            teacher[15] = (teacherInput15.getText().isEmpty() ? "" : teacherInput15.getText());
            sub[16] = (subInput16.getText().isEmpty() ? "" : subInput16.getText());
            teacher[16] = (teacherInput16.getText().isEmpty() ? "" : teacherInput16.getText());
            sub[17] = (subInput17.getText().isEmpty() ? "" : subInput17.getText());
            teacher[17] = (teacherInput17.getText().isEmpty() ? "" : teacherInput17.getText());
            sub[18] = (subInput1.getText().isEmpty() ? "" : subInput18.getText());
            teacher[18] = (teacherInput1.getText().isEmpty() ? "" : teacherInput18.getText());
            sub[19] = (subInput19.getText().isEmpty() ? "" : subInput19.getText());
            teacher[19] = (teacherInput19.getText().isEmpty() ? "" : teacherInput19.getText());
            sub[20] = (subInput20.getText().isEmpty() ? "" : subInput20.getText());
            teacher[20] = (teacherInput20.getText().isEmpty() ? "" : teacherInput20.getText());
            sub[21] = (subInput21.getText().isEmpty() ? "" : subInput21.getText());
            teacher[21] = (teacherInput21.getText().isEmpty() ? "" : teacherInput21.getText());
            sub[22] = (subInput22.getText().isEmpty() ? "" : subInput22.getText());
            teacher[22] = (teacherInput22.getText().isEmpty() ? "" : teacherInput22.getText());
            sub[23] = (subInput23.getText().isEmpty() ? "" : subInput23.getText());
            teacher[23] = (teacherInput23.getText().isEmpty() ? "" : teacherInput23.getText());
            sub[24] = (subInput24.getText().isEmpty() ? "" : subInput24.getText());
            teacher[24] = (teacherInput24.getText().isEmpty() ? "" : teacherInput24.getText());
            sub[25] = (subInput25.getText().isEmpty() ? "" : subInput25.getText());
            teacher[25] = (teacherInput25.getText().isEmpty() ? "" : teacherInput25.getText());

            RoutineCRUD crud = new RoutineCRUD();
            crud.updateRoutine(sub, teacher);
        }
        handleRoutinePage(event);
    }

    @FXML
    void handleSection(Event event) {

    }

    @FXML
    void class_1(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 1;
        handleRoutinePage(event);
    }

    @FXML
    void class_10(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 10;
        handleRoutinePage(event);
    }

    @FXML
    void class_2(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 2;
        handleRoutinePage(event);
    }

    @FXML
    void class_3(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 3;
        handleRoutinePage(event);
    }

    @FXML
    void class_4(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 4;
        handleRoutinePage(event);
    }

    @FXML
    void class_5(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 5;
        handleRoutinePage(event);
    }

    @FXML
    void class_6(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 6;
        handleRoutinePage(event);
    }

    @FXML
    void class_7(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 7;
        handleRoutinePage(event);
    }

    @FXML
    void class_8(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 8;
        handleRoutinePage(event);
    }

    @FXML
    void class_9(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedClass = 9;
        handleRoutinePage(event);
    }

    @FXML
    void section_A(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedSection = "A";
        handleRoutinePage(event);
    }

    @FXML
    void section_B(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedSection = "B";
        handleRoutinePage(event);
    }

    @FXML
    void section_C(ActionEvent event) throws IOException, SQLException {
        RoutineController.editFlag = false;
        RoutineController.applyFlag = false;
        RoutineController.selectedSection = "C";
        handleRoutinePage(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid.setVisible(false);
        editButton.setVisible(false);
    }
}
