package edu.handong.csee.java.studygroup.analyzers;

import java.util.HashMap;
import java.util.HashSet;

import edu.handong.csee.java.studygroup.datamodel.StudyGroup;

/**
 * Provides functionality to analyze and display statistics about study groups.
 * This class includes methods to process study group data and print summary information.
 */
public class StatisticsManager {

    /**
     * Prints statistical information for each study group.
     * For each group, it prints the number of students and the number of unique courses being studied.
     *
     * @param groupInfo A map where the key is the group number and the value is the StudyGroup object
     */
    public static void printGroupStatistics(HashMap<Integer, StudyGroup> groupInfo) {
        boolean firstLine = true;
        for (Integer groupNo : groupInfo.keySet()) {
            StudyGroup group = groupInfo.get(groupNo);

            // Estimate the number of students based on the number of member IDs
            int numberOfStudents = group.getMemberIDs().size();

            // Use a HashSet to store unique course names (case-insensitive, trimmed)
            HashSet<String> uniqueCourses = new HashSet<>();
            for (String course : group.getCourseNames()) {
                uniqueCourses.add(course.trim().toLowerCase());
            }

            int coursesName = uniqueCourses.size();

            System.out.printf("Group%d, # of students: %d, # of courses for study: %d\n",
                    groupNo, numberOfStudents, coursesName);
        }
    }
}
