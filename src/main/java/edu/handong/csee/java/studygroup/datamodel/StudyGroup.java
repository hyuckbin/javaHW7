package edu.handong.csee.java.studygroup.datamodel;

import java.util.ArrayList;

/**
 * Represents a study group containing members, their names, course names,
 * number of reports submitted, and total study minutes.
 */
public class StudyGroup {
    private int groupNo;
    private ArrayList<Integer> memberIDs;
    private ArrayList<String> names;
    private ArrayList<String> courseNames;
    private int numOfReports;
    private int studyMinutes;

    /**
     * Default constructor initializing empty member lists.
     */
    public StudyGroup() {
        this.memberIDs = new ArrayList<>();
        this.names = new ArrayList<>();
        this.courseNames = new ArrayList<>();
    }

    /**
     * Constructor initializing all fields.
     *
     * @param groupNo       Group number
     * @param memberIDs     List of member IDs
     * @param names         List of member names
     * @param courseNames   List of course names
     * @param numOfReports  Number of reports submitted
     * @param studyMinutes  Total study minutes
     */
    public StudyGroup(int groupNo, ArrayList<Integer> memberIDs, ArrayList<String> names,
                      ArrayList<String> courseNames, int numOfReports, int studyMinutes) {
        this.groupNo = groupNo;
        this.memberIDs = memberIDs;
        this.names = names;
        this.courseNames = courseNames;
        this.numOfReports = numOfReports;
        this.studyMinutes = studyMinutes;
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
     * @return the list of member IDs
     */
    public ArrayList<Integer> getMemberIDs() {
        return memberIDs;
    }

    /**
     * Sets the list of member IDs.
     *
     * @param memberIDs the member IDs to set
     */
    public void setMemberIDs(ArrayList<Integer> memberIDs) {
        this.memberIDs = memberIDs;
    }

    /**
     * @return the list of member names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * Sets the list of member names.
     *
     * @param names the member names to set
     */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    /**
     * @return the list of course names
     */
    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    /**
     * Sets the list of course names while avoiding duplicates (case-insensitive).
     *
     * @param courseNames the course names to set
     */
    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames.clear();
        for (String courseName : courseNames) {
            addCourseName(courseName);
        }
    }

    /**
     * Adds a member ID to the group.
     *
     * @param id the member ID to add
     */
    public void addMemberID(int id) {
        this.memberIDs.add(id);
    }

    /**
     * Adds a member name to the group.
     *
     * @param name the member name to add
     */
    public void addName(String name) {
        this.names.add(name);
    }

    /**
     * Adds a course name if it's not already included (case-insensitive).
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
     * Adds multiple course names from a student while avoiding duplicates.
     *
     * @param studentCourseNames list of course names from a student
     */
    public void addCoursesFromStudent(ArrayList<String> studentCourseNames) {
        for (String courseName : studentCourseNames) {
            addCourseName(courseName);
        }
    }

    /**
     * @return the number of reports submitted
     */
    public int getNumOfReports() {
        return numOfReports;
    }

    /**
     * Sets the number of reports submitted.
     *
     * @param numOfReports the number of reports to set
     */
    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }

    /**
     * @return the total study minutes
     */
    public int getStudyMinutes() {
        return studyMinutes;
    }

    /**
     * Sets the total study minutes.
     *
     * @param studyMinutes the study minutes to set
     */
    public void setStudyMinutes(int studyMinutes) {
        this.studyMinutes = studyMinutes;
    }

    /**
     * Returns a string representation of the StudyGroup.
     *
     * @return string containing group information
     */
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
