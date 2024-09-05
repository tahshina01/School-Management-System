package com.schoolmanagementsystem.models;

import java.time.LocalDate;

public class Fee {
    private int studentID;
    private int fee;
    private LocalDate paymentDate;

    public Fee(int studentID, int fee, LocalDate paymentDate) {
        this.studentID = studentID;
        this.fee = fee;
        this.paymentDate = paymentDate;
    }

    public Fee() {

    }

    public int getStudentID() {
        return studentID;
    }

    public int getFee() {
        return fee;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
