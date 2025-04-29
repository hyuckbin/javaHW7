package edu.handong.csee.java.studygroup.datamodel;

import java.util.ArrayList;

public class StudyGroup {
    private int groupNo;
    private ArrayList<Integer> memberIDs;
    private ArrayList<String> names;
    private ArrayList<String> courseNames;
    private int numOfReports;
    private int studyMinutes;

    public StudyGroup() {
        this.memberIDs = new ArrayList<>();
        this.names = new ArrayList<>();
        this.courseNames = new ArrayList<>();
    }

    public StudyGroup(int groupNo, ArrayList<Integer> memberIDs, ArrayList<String> names, ArrayList<String> courseNames, int numOfReports, int studyMinutes) {
        this.groupNo = groupNo;
        this.memberIDs = memberIDs;
        this.names = names;
        this.courseNames = courseNames;
        this.numOfReports = numOfReports;
        this.studyMinutes = studyMinutes;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public ArrayList<Integer> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(ArrayList<Integer> memberIDs) {
        this.memberIDs = memberIDs;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames = courseNames;
    }

    public void addCourseName(String courseName) {
        if (!this.courseNames.contains(courseName)) {
            this.courseNames.add(courseName);
        }
    }

    public int getNumOfReports() {
        return numOfReports;
    }

    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }

    public int getStudyMinutes() {
        return studyMinutes;
    }

    public void setStudyMinutes(int studyMinutes) {
        this.studyMinutes = studyMinutes;
    }
}
