package edu.handong.csee.java.studygroup.analyzers;

import java.util.ArrayList;
import java.util.HashMap;

import edu.handong.csee.java.studygroup.datamodel.Student;

public class StatisticsManager {

    public static void printGroupStatistics(HashMap<Integer, ArrayList<Student>> groupInfo) {
        for (Integer groupNo : groupInfo.keySet()) {
            ArrayList<Student> students = groupInfo.get(groupNo);

            int totalReports = 0;
            int totalStudyMinutes = 0;

            for (Student student : students) {
                totalReports += student.getNumOfReports();
                totalStudyMinutes += student.getMinutesForStudy();
            }

            int numberOfStudents = students.size();
            double averageReports = (numberOfStudents == 0) ? 0 : (double)totalReports / numberOfStudents;
            double averageStudyMinutes = (numberOfStudents == 0) ? 0 : (double)totalStudyMinutes / numberOfStudents;

            System.out.println("Group No: " + groupNo);
            System.out.println("- Number of Students: " + numberOfStudents);
            System.out.println("- Average Reports: " + String.format("%.2f", averageReports));
            System.out.println("- Average Study Minutes: " + String.format("%.2f", averageStudyMinutes));
            System.out.println();
        }
    }
}

