package edu.handong.csee.java.studygroup.analyzers;

import edu.handong.csee.java.studygroup.datamodel.Student;
import edu.handong.csee.java.studygroup.datamodel.StudyGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPreprocessor {

    public static HashMap<Integer, StudyGroup> getGroupInfo(ArrayList<Student> students) {
        HashMap<Integer, StudyGroup> groupInfo = new HashMap<>();

        for (Student student : students) {
            int groupNo = student.getGroupNo();

            // 그룹이 이미 존재하는지 확인
            StudyGroup group = groupInfo.get(groupNo);
            if (group == null) {
                // 새 StudyGroup을 생성하여 추가
                group = new StudyGroup(groupNo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0, 0);
                groupInfo.put(groupNo, group);
            }

            // 그룹에 학생 추가
            group.getMemberIDs().add(student.getMemberID());
            group.getNames().add(student.getName());
            group.getCourseNames().addAll(student.getCourseNames());
            group.setNumOfReports(group.getNumOfReports() + student.getNumOfReports());
            group.setStudyMinutes(group.getStudyMinutes() + student.getMinutesForStudy());
        }

        return groupInfo;
    }

    public static HashMap<String, ArrayList<StudyGroup>> getGroupInfoByCourseName(HashMap<Integer, StudyGroup> mapGroupInfo) {
        HashMap<String, ArrayList<StudyGroup>> courseGroupInfo = new HashMap<>();

        for (StudyGroup group : mapGroupInfo.values()) {
            for (String courseName : group.getCourseNames()) {

                // 해당 과목명이 키로 없으면 새 리스트 생성
                courseGroupInfo.putIfAbsent(courseName, new ArrayList<>());

                // 과목명에 해당 그룹 추가
                courseGroupInfo.get(courseName).add(group);
            }
        }

        return courseGroupInfo;
    }

}


