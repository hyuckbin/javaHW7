package edu.handong.csee.java.studygroup.datamodel;

import java.util.ArrayList;

/**
 * Represents a student in a study group, including personal and academic details.
 */
public class Student {
    private int groupNo;
    private int memberID;
    private String name;
    private ArrayList<String> courseNames;
    private int numOfReports;
    private int minutesForStudy;

    /**
     * Default constructor initializing the course list.
     */
    public Student() {
        this.courseNames = new ArrayList<>();
    }

    /**
     * Constructs a Student object from string inputs, parsing integers as needed.
     *
     * @param groupNo          the group number as a string
     * @param memberID         the member ID as a string
     * @param name             the name of the student
     * @param course1          first course name
     * @param course2          second course name
     * @param numOfReports     number of reports as a string
     * @param minutesForStudy  study minutes as a string
     */
    public Student(String groupNo, String memberID, String name, String course1, String course2, String numOfReports, String minutesForStudy) {
        this.groupNo = Integer.parseInt(groupNo);
        this.memberID = Integer.parseInt(memberID);
        this.name = name;
        this.courseNames = new ArrayList<>();
        addCourseName(course1);
        addCourseName(course2);
        this.numOfReports = Integer.parseInt(numOfReports);
        this.minutesForStudy = Integer.parseInt(minutesForStudy);
    }

    /**
     * Constructs a Student object with specified fields.
     *
     * @param groupNo          the group number
     * @param memberID         the member ID
     * @param name             the name of the student
     * @param courseNames      the list of course names
     * @param numOfReports     number of reports
     * @param minutesForStudy  study minutes
     */
    public Student(int groupNo, int memberID, String name, ArrayList<String> courseNames, int numOfReports, int minutesForStudy) {
        this.groupNo = groupNo;
        this.memberID = memberID;
        this.name = name;
        this.courseNames = courseNames;
        this.numOfReports = numOfReports;
        this.minutesForStudy = minutesForStudy;
    }

    /**
     * @return the group number
     */
    public int getGroupNo() {
        return groupNo;
    }

    /**
     * Sets the group number.
     *
     * @param groupNo the group number to set
     */
    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * @return the member ID
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * Sets the member ID.
     *
     * @param memberID the member ID to set
     */
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    /**
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the list of course names
     */
    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    /**
     * Sets the list of course names.
     *
     * @param courseNames the course names to set
     */
    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames = courseNames;
    }

    /**
     * Adds a course name to the list if it's not a duplicate (case-insensitive).
     *
     * @param courseName the course name to add
     */
    public void addCourseName(String courseName) {
        String normalized = courseName.trim().toLowerCase();
        for (String existing : this.courseNames) {
            if (existing.trim().toLowerCase().equals(normalized)) return;
        }
        this.courseNames.add(courseName);
    }

    /**
     * @return the number of reports submitted by the student
     */
    public int getNumOfReports() {
        return numOfReports;
    }

    /**
     * Sets the number of reports.
     *
     * @param numOfReports the number of reports to set
     */
    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }

    /**
     * @return the total minutes the student has studied
     */
    public int getMinutesForStudy() {
        return minutesForStudy;
    }

    /**
     * Sets the total study minutes.
     *
     * @param minutesForStudy the study minutes to set
     */
    public void setMinutesForStudy(int minutesForStudy) {
        this.minutesForStudy = minutesForStudy;
    }

}
