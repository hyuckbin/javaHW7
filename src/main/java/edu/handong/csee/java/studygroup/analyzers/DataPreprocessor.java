package edu.handong.csee.java.studygroup.analyzers;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPreprocessor {

    public static HashMap<Integer, ArrayList<Student>> getGroupInfo(ArrayList<Student> students) {
        HashMap<Integer, ArrayList<Student>> groupInfo = new HashMap<>();

        for (Student student : students) {
            int groupNo = student.getGroupNo();
            groupInfo.putIfAbsent(groupNo, new ArrayList<>());
            groupInfo.get(groupNo).add(student);
        }

        return groupInfo;
    }

    public static HashMap<String, HashMap<Integer, ArrayList<Student>>> getGroupInfoByCourseName(HashMap<Integer, ArrayList<Student>> groupInfo) {
        HashMap<String, HashMap<Integer, ArrayList<Student>>> courseGroupInfo = new HashMap<>();

        for (Integer groupNo : groupInfo.keySet()) {
            ArrayList<Student> students = groupInfo.get(groupNo);

            for (Student student : students) {
                for (String courseName : student.getCourseNames()) {
                    courseGroupInfo.putIfAbsent(courseName, new HashMap<>());
                    courseGroupInfo.get(courseName).putIfAbsent(groupNo, new ArrayList<>());
                    courseGroupInfo.get(courseName).get(groupNo).add(student);
                }
            }
        }

        return courseGroupInfo;
    }
}
