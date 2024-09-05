package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.controllers.NoticeController;
import com.schoolmanagementsystem.models.Notice;

import java.io.Reader;
import java.io.StringReader;
import java.sql.*;
import java.time.LocalDate;

public class NoticeCRUD {

    public void addNotice(Notice notice) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String insertQuery = "INSERT INTO notice (title, description, issueDate) VALUES (?, ?, ?)";

        PreparedStatement statement = con.prepareStatement(insertQuery);

        // set values for the insert query
        Reader reader = new StringReader(notice.getNoticeDesc());

        LocalDate localDate = LocalDate.now();
        Date issueDate = Date.valueOf(localDate);

        statement.setString(1, notice.getNoticeTitle());
        statement.setCharacterStream(2, reader, notice.getNoticeDesc().length());
        statement.setDate(3, issueDate);

        statement.executeUpdate();
    }

    public void updateNotice(Notice notice) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String updateQuery = "UPDATE notice SET title = ?, description = ? WHERE noticeID = ?";
        PreparedStatement statement = con.prepareStatement(updateQuery);

        Reader reader = new StringReader(notice.getNoticeDesc());

        // statement.setInt(2, emp.getId());
        statement.setString(1, notice.getNoticeTitle());
        // statement.setString(2, notice.getNoticeDesc());
        statement.setCharacterStream(2, reader, notice.getNoticeDesc().length());
        statement.setInt(3, NoticeController.getCurrentNotice());

        statement.executeUpdate();
    }

    public void deleteNotice() throws SQLException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteSql = "DELETE FROM notice WHERE noticeID = ?";

        PreparedStatement delStatement = con.prepareStatement(deleteSql);
        delStatement.setInt(1, NoticeController.getCurrentNotice());
        delStatement.executeUpdate();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(noticeID) FROM notice");
        rs.next();
        int maxId = rs.getInt(1);

        // Reset the id values of the remaining rows
        int resetId = 0;
        stmt.executeUpdate("SET @reset_id = " + resetId);
        stmt.executeUpdate("UPDATE notice SET noticeID = (@reset_id := @reset_id + 1) WHERE noticeID <= " + maxId);

        // Reset the auto-increment value
        stmt.executeUpdate("ALTER TABLE notice AUTO_INCREMENT = " + resetId);
    }
}
