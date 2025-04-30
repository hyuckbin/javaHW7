package edu.handong.csee.java.studygroup.analyzers;

import edu.handong.csee.java.studygroup.datamodel.Student;
import edu.handong.csee.java.studygroup.datamodel.StudyGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides preprocessing functionalities for student and study group data.
 * This class is responsible for organizing students into groups and mapping groups by course names.
 */
public class DataPreprocessor {

    /**
     * Organizes students into study groups based on their group number.
     * Initializes StudyGroup objects with the first student in each group and processes member IDs, names, and courses.
     *
     * @param students The list of students to be grouped
     * @return A map where the key is the group number and the value is the corresponding StudyGroup object
     */
    public static HashMap<Integer, StudyGroup> getGroupInfo(ArrayList<Student> students) {
        HashMap<Integer, StudyGroup> groupInfo = new HashMap<>();

        for (Student student : students) {
            int groupNo = student.getGroupNo();

            StudyGroup group = groupInfo.get(groupNo);
            boolean isNewGroup = (group == null);

            if (isNewGroup) {
                group = new StudyGroup(groupNo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        student.getNumOfReports(),
                        student.getMinutesForStudy());
                groupInfo.put(groupNo, group);
            }

            group.addMemberID(student.getMemberID());
            group.addName(student.getName());

            ArrayList<String> studentIncorrectlyParsedFields = student.getCourseNames();
            if (studentIncorrectlyParsedFields.size() > 1) {
                String subjectsString = studentIncorrectlyParsedFields.get(1);
                String[] individualCourses = subjectsString.split(",");
                for (String individualCourse : individualCourses) {
                    String trimmedCourse = individualCourse.trim();
                    if (!trimmedCourse.isEmpty()) {
                        group.addCourseName(trimmedCourse);
                    }
                }
            }
        }

        return groupInfo;
    }

    /**
     * Maps study groups to course names. Each course name is associated with a list of study groups that study it.
     *
     * @param mapGroupInfo A map of group numbers to StudyGroup objects
     * @return A map where the key is a normalized course name, and the value is a list of StudyGroups studying that course
     */
    public static HashMap<String, ArrayList<StudyGroup>> getGroupInfoByCourseName(HashMap<Integer, StudyGroup> mapGroupInfo) {
        HashMap<String, ArrayList<StudyGroup>> courseGroupInfo = new HashMap<>();

        for (StudyGroup group : mapGroupInfo.values()) {
            for (String courseNameInGroup : group.getCourseNames()) {
                String mapKey = courseNameInGroup.trim().toLowerCase();
                courseGroupInfo.putIfAbsent(mapKey, new ArrayList<>());
                courseGroupInfo.get(mapKey).add(group);
            }
        }

        return courseGroupInfo;
    }
}
