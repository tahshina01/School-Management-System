package com.schoolmanagementsystem.database;

import com.schoolmanagementsystem.co_curricular.Club;
import com.schoolmanagementsystem.controllers.ClubController;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

public class ClubCRUD {

    public void addOrUpdateHelp(String ID, String query, String clubName) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        if (ID == null) {
            return;
        }

        PreparedStatement statement = con.prepareStatement(query);

        statement.setInt(1, Integer.parseInt(ID));
        statement.setString(2, clubName);
        statement.executeUpdate();
    }

    public void addOrUpdateClub(Club club) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query;
        PreparedStatement statement;

        if (ClubController.isAddClubFlag()) {
            query = "INSERT INTO club (clubName) VALUES (?)";
            statement = con.prepareStatement(query);
            statement.setString(1, club.getNameOfClub());
            statement.executeUpdate();

            query = "ALTER TABLE clubMembers ADD COLUMN " + club.getNameOfClub() + " BOOLEAN NOT NULL DEFAULT FALSE";
            statement = con.prepareStatement(query);
            statement.executeUpdate();
        }

        addOrUpdateHelp(club.getPresidentID(), "UPDATE club SET president = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getVicePresidentID(), "UPDATE club SET vicePresident = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getTreasurerID(), "UPDATE club SET treasurer = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getGeneralSecretaryID(), "UPDATE club SET generalSecretary = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getClubModeratorID(), "UPDATE club SET clubModerator = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getAssistantGSID(), "UPDATE club SET assistantGS = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getPublicRelationsID(), "UPDATE club SET publicRelations = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getSecretaryID(), "UPDATE club SET secretary = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getExecutive_1_ID(), "UPDATE club SET executive_1 = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getExecutive_2_ID(), "UPDATE club SET executive_2 = ? WHERE clubName = ?",
                club.getNameOfClub());
        addOrUpdateHelp(club.getExecutive_3_ID(), "UPDATE club SET executive_3 = ? WHERE clubName = ?",
                club.getNameOfClub());

        if (club.getFundAmount() != null) {
            query = "UPDATE club SET fund = ? WHERE clubName = ?";
            statement = con.prepareStatement(query);
            statement.setString(2, club.getNameOfClub());
            statement.setString(1, club.getFundAmount());
            statement.executeUpdate();
        }

    }

    public void deleteClub(String str) throws SQLException {

        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String deleteSql = "DELETE FROM club WHERE clubName = ?";
        String alterSql = "ALTER TABLE clubMembers DROP COLUMN " + str;

        PreparedStatement delStatement = con.prepareStatement(deleteSql);
        PreparedStatement alterStatement = con.prepareStatement(alterSql);
        delStatement.setString(1, str);
        delStatement.executeUpdate();
        alterStatement.executeUpdate();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(clubID) FROM club");
        rs.next();
        int maxId = rs.getInt(1);

        // Reset the id values of the remaining rows
        int resetId = 0;
        stmt.executeUpdate("SET @reset_id = " + resetId);
        stmt.executeUpdate("UPDATE club SET clubID = (@reset_id := @reset_id + 1) WHERE clubID <= " + maxId);

        // Reset the auto-increment value
        stmt.executeUpdate("ALTER TABLE club AUTO_INCREMENT = " + resetId);
    }

    public void addFund(String fund) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT fund FROM club WHERE clubID = ?";
        PreparedStatement st = con.prepareStatement(selectQuery);
        st.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet r = st.executeQuery();
        int currentFund = 0;
        if (r.next()) {
            if (r.getString("fund") != null) {
                currentFund = Integer.parseInt(r.getString("fund"));
            }
        }
        currentFund += Integer.parseInt(fund);
        String query = "UPDATE club SET fund = ? WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, String.valueOf(currentFund));
        statement.setInt(2, ClubController.getSelectedClub() + 1);
        statement.executeUpdate();
    }

    public boolean spendFund(String fund) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT fund FROM club WHERE clubID = ?";
        PreparedStatement st = con.prepareStatement(selectQuery);
        st.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet r = st.executeQuery();
        int currentFund = 0;
        if (r.next()) {
            if (r.getString("fund") != null) {
                currentFund = Integer.parseInt(r.getString("fund"));
            }
        }
        int deductedFund = Integer.parseInt(fund);
        if (deductedFund > currentFund) {
            return false;
        }
        currentFund -= Integer.parseInt(fund);
        String query = "UPDATE club SET fund = ? WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, String.valueOf(currentFund));
        statement.setInt(2, ClubController.getSelectedClub() + 1);
        statement.executeUpdate();
        return true;
    }

    public String addMember(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT * FROM studentInfo WHERE studentID = ?";
        PreparedStatement st = con.prepareStatement(selectQuery);
        st.setInt(1, id);
        ResultSet r = st.executeQuery();

        if (!r.next()) {
            return "This id doesn't exist";
        }

        Club club = new Club();
        ArrayList<Pair<String, Integer>> ecPanel = club.ecPanel();
        for (Pair<String, Integer> stringIntegerPair : ecPanel) {
            if (stringIntegerPair.getValue() == id) {
                return "This member is in ec panel";
            }
        }

        String query = "SELECT clubName FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet resultSet = statement.executeQuery();
        String clubName = "";
        if (resultSet.next()) {
            clubName = resultSet.getString("clubName");
        }

        selectQuery = "SELECT * FROM clubMembers WHERE studentID = ?";
        st = con.prepareStatement(selectQuery);
        st.setInt(1, id);
        r = st.executeQuery();

        if (r.next()) {
            if (r.getBoolean(clubName)) {
                return "This is already a member";
            } else {
                String q = "UPDATE clubMembers SET " + clubName + " = ? WHERE studentID = ?";
                PreparedStatement stat = con.prepareStatement(q);
                stat.setInt(2, id);
                stat.setBoolean(1, true);
                stat.executeUpdate();
                return "Member added successfully";
            }
        }

        String q = "INSERT INTO clubMembers (studentID, " + clubName + " ) VALUES (?, ?)";
        PreparedStatement stat = con.prepareStatement(q);
        stat.setInt(1, id);
        stat.setBoolean(2, true);
        stat.executeUpdate();
        return "Member added successfully";
    }

    public String deleteMember(int id) throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String selectQuery = "SELECT * FROM studentInfo WHERE studentID = ?";
        PreparedStatement st = con.prepareStatement(selectQuery);
        st.setInt(1, id);
        ResultSet r = st.executeQuery();

        if (!r.next()) {
            return "This id doesn't exist";
        }

        Club club = new Club();
        ArrayList<Pair<String, Integer>> ecPanel = club.ecPanel();
        for (Pair<String, Integer> stringIntegerPair : ecPanel) {
            if (stringIntegerPair.getValue() == id) {
                return "This member is in ec panel";
            }
        }

        String query = "SELECT clubName FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet resultSet = statement.executeQuery();
        String clubName = "";
        if (resultSet.next()) {
            clubName = resultSet.getString("clubName");
        }

        selectQuery = "SELECT * FROM clubMembers WHERE studentID = ?";
        st = con.prepareStatement(selectQuery);
        st.setInt(1, id);
        r = st.executeQuery();

        if (r.next()) {
            if (!r.getBoolean(clubName)) {
                return "This is not a member";
            } else {
                String q = "UPDATE clubMembers SET " + clubName + " = ? WHERE studentID = ?";
                PreparedStatement stat = con.prepareStatement(q);
                stat.setInt(2, id);
                stat.setBoolean(1, false);
                stat.executeUpdate();
                return "Member deleted successfully";
            }
        }

        return "This is not a member";
    }
}
