package com.schoolmanagementsystem.co_curricular;

import com.schoolmanagementsystem.controllers.ClubController;
import com.schoolmanagementsystem.database.ClubCRUD;
import com.schoolmanagementsystem.database.ConnectDatabase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Club implements CoCurricular {

    private String nameOfClub;
    private String presidentID;
    private String vicePresidentID;
    private String generalSecretaryID;
    private String treasurerID;
    private String clubModeratorID;
    private String assistantGSID;
    private String publicRelationsID;
    private String secretaryID;
    private String executive_1_ID;
    private String executive_2_ID;
    private String executive_3_ID;
    private String fundAmount;
    private ClubCRUD crud;

    public String getNameOfClub() {
        return nameOfClub;
    }

    public String getPresidentID() {
        return presidentID;
    }

    public String getVicePresidentID() {
        return vicePresidentID;
    }

    public String getGeneralSecretaryID() {
        return generalSecretaryID;
    }

    public String getTreasurerID() {
        return treasurerID;
    }

    public String getClubModeratorID() {
        return clubModeratorID;
    }

    public String getAssistantGSID() {
        return assistantGSID;
    }

    public String getPublicRelationsID() {
        return publicRelationsID;
    }

    public String getSecretaryID() {
        return secretaryID;
    }

    public String getExecutive_1_ID() {
        return executive_1_ID;
    }

    public String getExecutive_2_ID() {
        return executive_2_ID;
    }

    public String getExecutive_3_ID() {
        return executive_3_ID;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public Club(String nameOfClub, String presidentID, String vicePresidentID, String generalSecretaryID,
            String treasurerID, String clubModeratorID, String assistantGSID, String publicRelationsID,
            String secretaryID, String executive_1_ID, String executive_2_ID, String executive_3_ID,
            String fundAmount) {
        this.nameOfClub = nameOfClub;
        this.presidentID = presidentID;
        this.vicePresidentID = vicePresidentID;
        this.generalSecretaryID = generalSecretaryID;
        this.treasurerID = treasurerID;
        this.clubModeratorID = clubModeratorID;
        this.assistantGSID = assistantGSID;
        this.publicRelationsID = publicRelationsID;
        this.secretaryID = secretaryID;
        this.executive_1_ID = executive_1_ID;
        this.executive_2_ID = executive_2_ID;
        this.executive_3_ID = executive_3_ID;
        this.fundAmount = fundAmount;

        this.crud = new ClubCRUD();
    }

    public Club() {
        this.crud = new ClubCRUD();
    }

    @Override
    public void addFund(String fund) throws SQLException {
        crud.addFund(fund);
    }

    @Override
    public boolean spendFund(String fund) throws SQLException {
        return crud.spendFund(fund);
    }

    @Override
    public String addMember(int id) throws SQLException {
        return crud.addMember(id);
    }

    @Override
    public String removeMember(int id) throws SQLException {
        return crud.deleteMember(id);
    }

    @Override
    public ArrayList<Pair<String, Integer>> ecPanel() throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT * FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet r = statement.executeQuery();

        ArrayList<Pair<String, Integer>> executivePanel = new ArrayList<>();

        if (r.next()) {
            executivePanel.add(new Pair<>("president", r.getInt("president")));
            executivePanel.add(new Pair<>("vicePresident", r.getInt("vicePresident")));
            executivePanel.add(new Pair<>("generalSecretary", r.getInt("generalSecretary")));
            executivePanel.add(new Pair<>("treasurer", r.getInt("treasurer")));
            executivePanel.add(new Pair<>("clubModerator", r.getInt("clubModerator")));
            executivePanel.add(new Pair<>("assistantGS", r.getInt("assistantGS")));
            executivePanel.add(new Pair<>("publicRelations", r.getInt("publicRelations")));
            executivePanel.add(new Pair<>("secretary", r.getInt("secretary")));
            executivePanel.add(new Pair<>("executive_1", r.getInt("executive_1")));
            executivePanel.add(new Pair<>("executive_2", r.getInt("executive_2")));
            executivePanel.add(new Pair<>("executive_3", r.getInt("executive_3")));

        }
        return executivePanel;
    }

    @Override
    public ArrayList<Integer> allMember() throws SQLException {
        ConnectDatabase db = new ConnectDatabase();
        Connection con = db.getCon();

        String query = "SELECT clubName FROM club WHERE clubID = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, ClubController.getSelectedClub() + 1);
        ResultSet resultSet = statement.executeQuery();
        String clubName = "";
        if (resultSet.next()) {
            clubName = resultSet.getString("clubName");
        }

        query = "SELECT studentID FROM clubMembers WHERE " + clubName + " = ?";
        statement = con.prepareStatement(query);
        statement.setBoolean(1, true);
        ResultSet r = statement.executeQuery();

        ArrayList<Integer> records = new ArrayList<>();

        while (r.next()) {
            int record = r.getInt("studentID");
            records.add(record);
        }

        return records;
    }
}
