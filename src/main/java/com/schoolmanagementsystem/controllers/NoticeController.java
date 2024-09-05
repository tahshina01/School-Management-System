package com.schoolmanagementsystem.controllers;

import com.schoolmanagementsystem.database.ConnectDatabase;
import com.schoolmanagementsystem.database.NoticeCRUD;
import com.schoolmanagementsystem.models.Notice;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class NoticeController extends Controller implements Initializable {

    private static int currentNotice;

    private static boolean updateFlag;

    private static int lastNotice;

    private static boolean cancelFlag;

    public Label noticeLabel;
    public TextField title;
    public Label titleLabel;

    public Label date;
    public Button back;
    public TextArea noticeInput;

    public Button next;
    public Label edit;
    public Label add;
    public Button done;

    public static int getCurrentNotice() {
        return currentNotice;
    }

    public static void setCurrentNotice(int currentNotice) {
        NoticeController.currentNotice = currentNotice;
    }

    public static int getLastNotice() {
        return lastNotice;
    }

    public static void setLastNotice(int lastNotice) {
        NoticeController.lastNotice = lastNotice;
    }

    public void handleNoticePage(Event event) throws IOException, SQLException {

        FXMLLoader fxmlLoader = loadPage("button", "/com/schoolmanagementsystem/fxml_Files/notice.fxml", event);

        NoticeController controller = fxmlLoader.getController();

        if (!Objects.equals(loginController.getLoggedInPerson(), "Admin")) {
            controller.add.setVisible(false);
            controller.edit.setVisible(false);
            controller.done.setVisible(false);
        }

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query;
        PreparedStatement statement;
        if (NoticeController.currentNotice == 0) {
            query = "SELECT * FROM notice ORDER BY noticeID DESC LIMIT 1;";
            statement = con.prepareStatement(query);
        } else {
            query = "SELECT * FROM notice WHERE noticeID = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, NoticeController.currentNotice);
        }

        ResultSet r = statement.executeQuery();

        if (r.next()) {
            controller.titleLabel.setText("Title: " + r.getString("title"));
            controller.noticeLabel.setText(r.getString("description"));
            controller.date.setText("Issue Date: " + String.valueOf(r.getDate("issueDate")));
            NoticeController.currentNotice = r.getInt("noticeID");
            if (NoticeController.lastNotice == 0) {
                NoticeController.lastNotice = r.getInt("noticeID");
            }
        }

        controller.title.setVisible(false);
        controller.noticeInput.setVisible(false);
        controller.title.setManaged(false);
        controller.noticeInput.setManaged(false);

        controller.back.setVisible(NoticeController.currentNotice != 1);
        controller.next.setVisible(NoticeController.currentNotice != NoticeController.lastNotice);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noticeLabel.setMaxWidth(730);
        noticeLabel.setWrapText(true);
        title.setVisible(false);
        title.setManaged(false);
        noticeInput.setVisible(false);
        noticeInput.setManaged(false);
    }

    @FXML
    void handleAdd(MouseEvent event) throws IOException, SQLException {

        if (NoticeController.cancelFlag) {
            NoticeController.cancelFlag = false;
            handleNoticePage(event);
            return;
        }

        titleLabel.setVisible(false);
        titleLabel.setManaged(false);
        noticeLabel.setVisible(false);
        noticeLabel.setManaged(false);

        title.setVisible(true);
        title.setManaged(true);
        noticeInput.setVisible(true);
        noticeInput.setManaged(true);
        noticeInput.setWrapText(true);

        done.setText("DONE");
        add.setText("X CANCEL");

        date.setText("");

        NoticeController.cancelFlag = true;

    }

    @FXML
    void handleBack(MouseEvent event) throws SQLException, IOException {
        NoticeController.currentNotice -= 1;
        handleNoticePage(event);
    }

    @FXML
    void handleEdit(MouseEvent event) throws SQLException, IOException {

        if (NoticeController.cancelFlag) {
            NoticeController.cancelFlag = false;
            handleNoticePage(event);
            return;
        }

        titleLabel.setVisible(false);
        titleLabel.setManaged(false);
        noticeLabel.setVisible(false);
        noticeLabel.setManaged(false);

        title.setVisible(true);
        title.setManaged(true);
        noticeInput.setVisible(true);
        noticeInput.setManaged(true);
        noticeInput.setWrapText(true);

        done.setText("DONE");
        edit.setText("X CANCEL");

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM notice WHERE noticeID = ?;";

        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, NoticeController.currentNotice);

        ResultSet r = statement.executeQuery();

        if (r.next()) {
            title.setText(r.getString("title"));
            noticeInput.setText(r.getString("description"));
            date.setText("Issue Date: " + String.valueOf(r.getDate("issueDate")));
        }

        NoticeController.updateFlag = true;
        NoticeController.cancelFlag = true;

    }

    @FXML
    void handleNext(MouseEvent event) throws SQLException, IOException {
        NoticeController.currentNotice += 1;
        handleNoticePage(event);
    }

    @FXML
    void handleSpecial(MouseEvent event) throws SQLException, IOException {
        String text = done.getText();
        NoticeCRUD crud = new NoticeCRUD();

        Notice notice;

        if (Objects.equals(text, "DONE")) {
            notice = new Notice(title.getText(), noticeInput.getText());
            if (NoticeController.updateFlag) {
                NoticeController.updateFlag = false;
                crud.updateNotice(notice);
            } else {
                crud.addNotice(notice);
                NoticeController.lastNotice += 1;
                NoticeController.currentNotice = NoticeController.lastNotice;
            }
        } else {
            if (handleAlert("This notice will be deleted permanently", "Are you sure to proceed?")) {
                crud.deleteNotice();
                NoticeController.lastNotice -= 1;
                NoticeController.currentNotice = NoticeController.lastNotice;
            }
        }
        handleNoticePage(event);
    }
}
