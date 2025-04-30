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

    public ArrayList<Integer>  getMemberIDs() {
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
        this.courseNames.clear();  // 기존 목록 비우기 (기존에 설정된 과목들을 초기화)
        for (String courseName : courseNames) {
            addCourseName(courseName);  // addCourseName 호출로 중복 검사
        }
    }

    public void addMemberID(int id) {
        if (!this.memberIDs.contains(id)) {
            this.memberIDs.add(id);
        }
    }

    public void addName(String name) {
        if (!this.names.contains(name)) {
            this.names.add(name);
        }
    }


    public void addCourseName(String courseName) {
        String normalized = courseName.trim().toLowerCase();
        for(String existing : this.courseNames) {
            if(existing.trim().toLowerCase().equals(normalized)) return;
        }
        this.courseNames.add(courseName);
    }
    public void addCoursesFromStudent(ArrayList<String> studentCourseNames) {
        for (String courseName : studentCourseNames) {
            addCourseName(courseName);
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

    @Override
    public String toString() {
        return "StudyGroup{" +
                "groupNo=" + groupNo +
                ", memberIDs=" + memberIDs +
                ", names=" + names +
                ", courseNames=" + courseNames +
                ", numOfReports=" + numOfReports +
                ", studyMinutes=" + studyMinutes +
                '}';
    }
}
