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

        public static HashMap<String, ArrayList<Student>> getGroupInfoByCourseName(HashMap<Integer, ArrayList<Student>> groupInfo) {
            HashMap<String, ArrayList<Student>> courseGroupInfo = new HashMap<>();

            for (ArrayList<Student> students : groupInfo.values()) {
                for (Student student : students) {
                    for (String courseName : student.getCourseNames()) {
                        courseGroupInfo.putIfAbsent(courseName, new ArrayList<>());
                        courseGroupInfo.get(courseName).add(student);
                    }
                }
            }

            return courseGroupInfo;
        }
    }


