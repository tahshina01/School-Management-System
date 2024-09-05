package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.co_curricular.Club;
import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllMembersController extends Controller implements Initializable {

    @FXML
    private MenuButton select;

    @FXML
    private Label id;

    @FXML
    private Label listID;

    @FXML
    private Label name;

    @FXML
    private Hyperlink id_1;

    @FXML
    private Hyperlink id_10;

    @FXML
    private Hyperlink id_11;

    @FXML
    private Hyperlink id_12;

    @FXML
    private Hyperlink id_13;

    @FXML
    private Hyperlink id_14;

    @FXML
    private Hyperlink id_15;

    @FXML
    private Hyperlink id_2;

    @FXML
    private Hyperlink id_3;

    @FXML
    private Hyperlink id_4;

    @FXML
    private Hyperlink id_5;

    @FXML
    private Hyperlink id_6;

    @FXML
    private Hyperlink id_7;

    @FXML
    private Hyperlink id_8;

    @FXML
    private Hyperlink id_9;

    @FXML
    private Label name_1;

    @FXML
    private Label name_10;

    @FXML
    private Label name_11;

    @FXML
    private Label name_12;

    @FXML
    private Label name_13;

    @FXML
    private Label name_14;

    @FXML
    private Label name_15;

    @FXML
    private Label name_2;

    @FXML
    private Label name_3;

    @FXML
    private Label name_4;

    @FXML
    private Label name_5;

    @FXML
    private Label name_6;

    @FXML
    private Label name_7;

    @FXML
    private Label name_8;

    @FXML
    private Label name_9;

    @FXML
    private Button next;

    @FXML
    private Button previous;

    @FXML
    private HBox hbox;

    @FXML
    private MenuButton Class;

    @FXML
    private MenuButton section;

    @FXML
    private ScrollPane scrollPane;

    private static int currentIndex;

    private static String currentList;

    private static boolean allUserFlag;

    private static int selectedClass;

    private static String selectedSection;

    public static int getSelectedClass() {
        return selectedClass;
    }

    public static void setSelectedClass(int selectedClass) {
        AllMembersController.selectedClass = selectedClass;
    }

    public static String getSelectedSection() {
        return selectedSection;
    }

    public static void setSelectedSection(String selectedSection) {
        AllMembersController.selectedSection = selectedSection;
    }

    public static boolean isAllUserFlag() {
        return allUserFlag;
    }

    public static void setAllUserFlag(boolean allUserFlag) {
        AllMembersController.allUserFlag = allUserFlag;
    }

    @FXML
    void handleCross(ActionEvent event) throws SQLException, IOException {
        handleHome(event);
    }

    @FXML
    void handleNext(ActionEvent event) throws SQLException, IOException {
        AllMembersController.currentIndex += 15;
        AllMembersController.allUserFlag = true;
        handleAllMemberPage(event);
    }

    @FXML
    void handlePrevious(ActionEvent event) throws SQLException, IOException {
        AllMembersController.currentIndex -= 15;
        AllMembersController.allUserFlag = true;
        handleAllMemberPage(event);
    }

    @FXML
    void handleStudentList(Event event) throws SQLException, IOException {
        AllMembersController.currentList = "Student";
        AllMembersController.currentIndex = 0;
        AllMembersController.allUserFlag = false;
        handleAllMemberPage(event);
    }

    @FXML
    void handleTeacherList(Event event) throws SQLException, IOException {
        AllMembersController.currentList = "Teacher";
        AllMembersController.currentIndex = 0;
        AllMembersController.allUserFlag = false;
        handleAllMemberPage(event);
    }

    @FXML
    void handleStaffList(Event event) throws SQLException, IOException {
        AllMembersController.currentList = "Staff";
        AllMembersController.currentIndex = 0;
        AllMembersController.allUserFlag = false;
        handleAllMemberPage(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        AllMembersController.currentIndex = currentIndex;
    }

    public static String getCurrentList() {
        return currentList;
    }

    public static void setCurrentList(String currentList) {
        AllMembersController.currentList = currentList;
    }

    public void handleAllMemberPage(Event event) throws IOException, SQLException {
        String buttonType;
        if (AllMembersController.allUserFlag) {
            buttonType = "button";
        } else {
            buttonType = "menuButton";
        }
        FXMLLoader fxmlLoader = loadPage(buttonType, "/com/schoolmanagementsystem/fxml_Files/allMembers.fxml", event);
        AllMembersController controller = fxmlLoader.getController();

        if (AllMembersController.currentList == null) {
            controller.scrollPane.setVisible(false);
            controller.hbox.setVisible(false);
            controller.previous.setVisible(false);
            controller.next.setVisible(false);
            controller.Class.setVisible(false);
            controller.section.setVisible(false);
            return;
        }

        if (AllMembersController.currentList.equals("Student")) {
            controller.select.setText("STUDENT");
            if(AllMembersController.selectedClass != 0) {
                controller.Class.setText("Class " + AllMembersController.selectedClass);
            }
            if(AllMembersController.selectedSection != null) {
                controller.section.setText("Section " + AllMembersController.selectedSection);
            }
        }

        if (AllMembersController.currentList.equals("Staff")) {
            controller.select.setText("STAFF");
            controller.id.setText("Staff ID");
            controller.name.setText("Staff Name");
            controller.Class.setVisible(false);
            controller.section.setVisible(false);
        }

        if (AllMembersController.currentList.equals("Teacher")) {
            controller.select.setText("TEACHER");
            controller.id.setText("Teacher ID");
            controller.name.setText("Teacher Name");
            controller.Class.setVisible(false);
            controller.section.setVisible(false);
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        ArrayList<Pair<Integer, String>> allMember = new ArrayList<>();
        String query, selectQuery;
        PreparedStatement statement, preparedStatement;
        ResultSet r, resultSet;

        if (Objects.equals(AllMembersController.currentList, "Student")) {
            if(AllMembersController.selectedClass == 0 && AllMembersController.selectedSection == null) {
                query = "SELECT studentID, name FROM studentInfo";
                statement = con.prepareStatement(query);
            } else if(AllMembersController.selectedClass != 0 && AllMembersController.selectedSection == null) {
                query = "SELECT studentID, name FROM studentInfo WHERE class = ?";
                statement = con.prepareStatement(query);
                statement.setInt(1, AllMembersController.selectedClass);
            } else if(AllMembersController.selectedClass == 0) {
                query = "SELECT studentID, name FROM studentInfo WHERE section = ?";
                statement = con.prepareStatement(query);
                statement.setString(1, AllMembersController.selectedSection);
            } else {
                query = "SELECT studentID, name FROM studentInfo WHERE class = ? AND section = ?";
                statement = con.prepareStatement(query);
                statement.setInt(1, AllMembersController.selectedClass);
                statement.setString(2, AllMembersController.selectedSection);
            }
            r = statement.executeQuery();
            while (r.next()) {
                int id = r.getInt("studentID");
                String name = r.getString("name");
                Pair<Integer, String> p = new Pair<>(id, name);
                allMember.add(p);
            }
        } else {
            query = "SELECT ID FROM loginInfo WHERE userType = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, AllMembersController.currentList);
            r = statement.executeQuery();
            while (r.next()) {
                int id = r.getInt("ID");
                selectQuery = "SELECT name FROM employeeInfo WHERE employeeID = ?";
                preparedStatement = con.prepareStatement(selectQuery);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                String name = "";
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                }
                Pair<Integer, String> p = new Pair<>(id, name);
                allMember.add(p);
            }
        }

        for (int i = AllMembersController.currentIndex; i < AllMembersController.currentIndex + 15; i++) {
            if (i == allMember.size()) {
                break;
            } else if (i == AllMembersController.currentIndex) {
                controller.id_1.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_1.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 1) {
                controller.id_2.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_2.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 2) {
                controller.id_3.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_3.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 3) {
                controller.id_4.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_4.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 4) {
                controller.id_5.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_5.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 5) {
                controller.id_6.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_6.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 6) {
                controller.id_7.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_7.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 7) {
                controller.id_8.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_8.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 8) {
                controller.id_9.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_9.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 9) {
                controller.id_10.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_10.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 10) {
                controller.id_11.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_11.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 11) {
                controller.id_12.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_12.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 12) {
                controller.id_13.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_13.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 13) {
                controller.id_14.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_14.setText(allMember.get(i).getValue());
            } else if (i == AllMembersController.currentIndex + 14) {
                controller.id_15.setText(String.valueOf(allMember.get(i).getKey()));
                controller.name_15.setText(allMember.get(i).getValue());
            }
        }

        if (AllMembersController.currentIndex == 0) {
            controller.previous.setVisible(false);
        }
        if (AllMembersController.currentIndex + 15 >= allMember.size()) {
            controller.next.setVisible(false);
        }
    }

    @FXML
    void handleLink_1(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_1.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_2(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_2.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_3(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_3.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_4(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_4.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_5(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_5.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_6(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_6.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_7(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_7.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_8(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_8.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_9(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_9.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_10(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_10.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_11(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_11.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_12(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_12.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_13(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_13.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_14(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_14.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }
    @FXML
    void handleLink_15(ActionEvent event) throws SQLException, IOException {
        Controller.requiredID = Integer.parseInt(id_15.getText());
        Controller.requiredType = AllMembersController.currentList;
        loadProfile(event);
    }

    public void loadProfile(ActionEvent event) throws SQLException, IOException {
        if(AllMembersController.currentList.equals("Student")) {
            StudentProfileController cont = new StudentProfileController();
            cont.handleStudentProfile(event, Controller.requiredID);
        }
        if(AllMembersController.currentList.equals("Teacher")) {
            TeacherProfileController cont = new TeacherProfileController();
            cont.handleTeacherProfile(event, Controller.requiredID);
        }
        if(AllMembersController.currentList.equals("Staff")) {
            StaffProfileController cont = new StaffProfileController();
            cont.handleStaffProfile(event, Controller.requiredID);
        }
    }

    @FXML
    void handleClassNone(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 0;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_1(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 1;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_10(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 10;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_2(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 2;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_3(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 3;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_4(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 4;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_5(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 5;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_6(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 6;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_7(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 7;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_8(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 8;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleClass_9(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedClass = 9;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleSectionNone(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedSection = null;
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleSection_A(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedSection = "A";
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleSection_B(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedSection = "B";
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

    @FXML
    void handleSection_C(ActionEvent event) throws SQLException, IOException {
        AllMembersController.selectedSection = "C";
        AllMembersController.allUserFlag = false;
        AllMembersController.currentIndex = 0;
        handleAllMemberPage(event);
    }

}
