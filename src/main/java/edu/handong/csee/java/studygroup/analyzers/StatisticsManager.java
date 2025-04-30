package edu.handong.csee.java.studygroup.analyzers;

import java.util.HashMap;
import java.util.HashSet;

import edu.handong.csee.java.studygroup.datamodel.StudyGroup;

public class StatisticsManager {

    public static void printGroupStatistics(HashMap<Integer, StudyGroup> groupInfo) {
        for (Integer groupNo : groupInfo.keySet()) {
            StudyGroup group = groupInfo.get(groupNo);

            // int totalReports = group.getNumOfReports(); // 사용되지 않음
            // int totalStudyMinutes = group.getStudyMinutes(); // 사용되지 않음
            int numberOfStudents = group.getMemberIDs().size(); // 학생 수는 memberIDs 리스트 길이로 추정

            HashSet<String> uniqueCourses = new HashSet<>();

            // 각 그룹이 다루는 고유한 과목들을 trim() 및 toLowerCase() 처리하여 HashSet에 추가
            for (String course : group.getCourseNames()) {
                uniqueCourses.add(course.trim().toLowerCase()); // <-- 이 부분을 수정합니다.
            }

            int coursesName = uniqueCourses.size();


            System.out.printf("Group %d, # of  students: %d, # of courses for study : %d", groupNo, numberOfStudents, coursesName);

            System.out.println();
        }

    }
}

