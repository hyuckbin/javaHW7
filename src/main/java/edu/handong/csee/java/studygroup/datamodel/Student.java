package edu.handong.csee.java.studygroup.datamodel;

import java.util.ArrayList;

public class Student {
    private int groupNo;
    private int memberID;
    private String name;
    private ArrayList<String> courseNames;
    private int numOfReports;
    private int minutesForStudy;


    public Student() {
        this.courseNames = new ArrayList<>();
    }

    public Student(String groupNo, String memberID, String name, String course1, String course2, String numOfReports, String minutesForStudy) {
        this.groupNo = Integer.parseInt(groupNo);
        this.memberID = Integer.parseInt(memberID);
        this.name = name;
        this.courseNames = new ArrayList<>();
        this.courseNames.add(course1);
        this.courseNames.add(course2);
        this.numOfReports = Integer.parseInt(numOfReports);
        this.minutesForStudy = Integer.parseInt(minutesForStudy);
    }
g
    public Student(int groupNo, int memberID, String name, ArrayList<String> courseNames, int numOfReports, int minutesForStudy) {
        this.groupNo = groupNo;
        this.memberID = memberID;
        this.name = name;
        this.courseNames = courseNames;
        this.numOfReports = numOfReports;
        this.minutesForStudy = minutesForStudy;
    }

    // Getter and Setter methods

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames = courseNames;
    }

    public int getNumOfReports() {
        return numOfReports;
    }

    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }

    public int getMinutesForStudy() {
        return minutesForStudy;
    }

    public void setMinutesForStudy(int minutesForStudy) {
        this.minutesForStudy = minutesForStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNo=" + groupNo +
                ", memberID=" + memberID +
                ", name='" + name + '\'' +
                ", courseNames=" + courseNames +
                ", numOfReports=" + numOfReports +
                ", minutesForStudy=" + minutesForStudy +
                '}';
    }
}
