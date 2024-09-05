package com.schoolmanagementsystem.co_curricular;

import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CoCurricular {
    void addFund(String fund) throws SQLException;

    boolean spendFund(String fund) throws SQLException;

    String addMember(int id) throws SQLException;

    String removeMember(int id) throws SQLException;

    ArrayList<Pair<String, Integer>> ecPanel() throws SQLException;

    ArrayList<Integer> allMember() throws SQLException;
}
