package com.schoolmanagementsystem.result;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Subject {
    ArrayList<Integer> getStudents() throws SQLException;
    int getTeacher();
}
