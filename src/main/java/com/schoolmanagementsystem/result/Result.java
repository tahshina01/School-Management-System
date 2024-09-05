package com.schoolmanagementsystem.result;

import com.schoolmanagementsystem.users.Student;

public class Result implements Comparable<Result> {
    private int roll;
    private int halfYearlyMark;
    private int yearFinalMark;
    private float halfYearlyGpa;
    private float yearFinalGpa;
    private float wAvgMark;
    private float wAvgGpa;
    private int rank;

    public static String getSortBy() {
        return sortBy;
    }

    public static void setSortBy(String sortBy) {
        Result.sortBy = sortBy;
    }

    private static String sortBy = null;

    public Result(int roll, int halfYearlyMark, int yearFinalMark, float halfYearlyGpa, float yearFinalGpa,
            float wAvgMark, float wAvgGpa) {
        this.roll = roll;
        this.halfYearlyMark = halfYearlyMark;
        this.yearFinalMark = yearFinalMark;
        this.halfYearlyGpa = halfYearlyGpa;
        this.yearFinalGpa = yearFinalGpa;
        this.wAvgMark = wAvgMark;
        this.wAvgGpa = wAvgGpa;
    }

    public Result(int roll, int halfYearlyMark, int yearFinalMark, float halfYearlyGpa, float yearFinalGpa,
            float wAvgMark, float wAvgGpa, int rank) {
        this.roll = roll;
        this.halfYearlyMark = halfYearlyMark;
        this.yearFinalMark = yearFinalMark;
        this.halfYearlyGpa = halfYearlyGpa;
        this.yearFinalGpa = yearFinalGpa;
        this.wAvgMark = wAvgMark;
        this.wAvgGpa = wAvgGpa;
        this.rank = rank;
    }

    public Result() {

    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getHalfYearlyMark() {
        return halfYearlyMark;
    }

    public void setHalfYearlyMark(int halfYearlyMark) {
        this.halfYearlyMark = halfYearlyMark;
    }

    public int getYearFinalMark() {
        return yearFinalMark;
    }

    public void setYearFinalMark(int yearFinalMark) {
        this.yearFinalMark = yearFinalMark;
    }

    public float getHalfYearlyGpa() {
        return halfYearlyGpa;
    }

    public void setHalfYearlyGpa(float halfYearlyGpa) {
        this.halfYearlyGpa = halfYearlyGpa;
    }

    public float getYearFinalGpa() {
        return yearFinalGpa;
    }

    public void setYearFinalGpa(float yearFinalGpa) {
        this.yearFinalGpa = yearFinalGpa;
    }

    public float getwAvgMark() {
        return wAvgMark;
    }

    public void setwAvgMark(float wAvgMark) {
        this.wAvgMark = wAvgMark;
    }

    public float getwAvgGpa() {
        return wAvgGpa;
    }

    public void setwAvgGpa(float wAvgGpa) {
        this.wAvgGpa = wAvgGpa;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Result other) {
        if (sortBy == null) {
            // Sort by increasing order of roll
            return Integer.compare(this.roll, other.roll);
        } else if (sortBy.equals("gpa")) {
            // Sort by decreasing order of gpa
            int gpaCompare = Float.compare(other.wAvgGpa, this.wAvgGpa);
            if (gpaCompare != 0) {
                return gpaCompare;
            } else {
                // If gpa is the same, sort by decreasing order of mark
                int markCompare = Float.compare(other.wAvgMark, this.wAvgMark);
                if (markCompare != 0) {
                    return markCompare;
                } else {
                    // If mark is also the same, sort by increasing order of roll
                    return Integer.compare(this.roll, other.roll);
                }
            }
        } else if (sortBy.equals("marks")) {
            // Sort by decreasing order of marks
            int markCompare = Float.compare(other.wAvgMark, this.wAvgMark);
            if (markCompare != 0) {
                return markCompare;
            } else {
                // If marks are the same, sort by decreasing order of gpa
                int gpaCompare = Float.compare(other.wAvgGpa, this.wAvgGpa);
                if (gpaCompare != 0) {
                    return gpaCompare;
                } else {
                    // If gpa is also the same, sort by increasing order of roll
                    return Integer.compare(this.roll, other.roll);
                }
            }
        } else {
            // Default case: sort by increasing order of roll
            return Integer.compare(this.roll, other.roll);
        }
    }
}
