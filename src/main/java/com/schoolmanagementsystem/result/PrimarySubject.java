package com.schoolmanagementsystem.result;

public class PrimarySubject extends CompulsorySubject {
    private double reli;
    private double science;
    private double math;

    public PrimarySubject() {
        super();
    }

    public PrimarySubject(int id, double Ban1, double Ban2, double Eng1, double Eng2, double reli, double science,
            double math) {
        super(id, Ban1, Ban2, Eng1, Eng2);

        this.reli = reli;
        this.math = math;
        this.science = science;
    }

    public double getReli() {
        return reli;
    }

    public void setReli(double reli) {
        this.reli = reli;
    }

    public double getScience() {
        return science;
    }

    public void setScience(double science) {
        this.science = science;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }
}
