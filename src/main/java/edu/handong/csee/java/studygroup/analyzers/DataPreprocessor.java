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

            StudyGroup group = groupInfo.get(groupNo);
            boolean isNewGroup = (group == null); // 이 학생이 이 그룹의 첫 번째 학생인지 확인

            if (isNewGroup) {
                // 이 그룹의 첫 번째 학생이라면, 새로운 StudyGroup 객체를 생성하고
                // 이 학생의 Reports와 Times 값을 그룹의 초기 값으로 설정합니다.
                group = new StudyGroup(groupNo, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        student.getNumOfReports(), // ✅ 첫 학생의 Reports 값으로 초기 설정
                        student.getMinutesForStudy()); // ✅ 첫 학생의 Times 값으로 초기 설정
                groupInfo.put(groupNo, group);
            }
            // 만약 이 그룹의 첫 번째 학생이 아니라면 (isNewGroup == false),
            // Reports와 Times 값은 첫 번째 학생 정보로 이미 설정되었으므로,
            // 현재 학생의 Reports와 Times 값을 추가로 더하지 않습니다. (CSV에서 같은 그룹 학생은 동일한 Reports/Times 값을 가진다고 가정)


            // ✅ 멤버 ID와 이름 추가 (StudyGroup 내부에서 중복 방지 처리)
            // 이 부분은 그룹의 첫 학생이든 아니든 항상 실행되어야 합니다.
            group.addMemberID(student.getMemberID());
            group.addName(student.getName());


            // ✅ 과목 처리 (지난번 수정으로 올바르게 파싱 및 집계됨)
            // StudyGroupManager에서 student.getCourseNames()에 잘못 담긴 값 중
            // Subjects 열에 해당하는 (두 번째 요소인 인덱스 1) 문자열을 가져와서 올바르게 파싱하여 StudyGroup에 추가합니다.
            ArrayList<String> studentIncorrectlyParsedFields = student.getCourseNames();
            if (studentIncorrectlyParsedFields.size() > 1) {
                String subjectsString = studentIncorrectlyParsedFields.get(1); // Subjects 열 문자열

                // Subjects 문자열을 콤마(,)로 분리하고 개별 과목 추출
                String[] individualCourses = subjectsString.split(",");
                for (String individualCourse : individualCourses) {
                    String trimmedCourse = individualCourse.trim(); // 앞뒤 공백 제거
                    if (!trimmedCourse.isEmpty()) {
                        // StudyGroup의 addCourseName 메소드를 사용하여 중복 검사 및 trim().toLowerCase() 처리하며 추가
                        group.addCourseName(trimmedCourse);
                    }
                }
            }

            // ❌ 기존 Reports와 Times를 누적하여 더하는 로직은 제거합니다.
            // group.setNumOfReports(group.getNumOfReports() + student.getNumOfReports());
            // group.setStudyMinutes(group.getStudyMinutes() + student.getMinutesForStudy());

        }

        return groupInfo;
    }

    public static HashMap<String, ArrayList<StudyGroup>> getGroupInfoByCourseName(HashMap<Integer, StudyGroup> mapGroupInfo) {
        HashMap<String, ArrayList<StudyGroup>> courseGroupInfo = new HashMap<>();

        for (StudyGroup group : mapGroupInfo.values()) {
            // group.getCourseNames()에는 이제 trim().toLowerCase() 기준으로 유일한 과목 이름들이 들어있습니다.
            for (String courseNameInGroup : group.getCourseNames()) {
                // 맵의 키로 사용하기 전에 다시 한번 trim().toLowerCase() 처리 (지난번 수정)
                String normalizedCourseName = courseNameInGroup.trim().toLowerCase();

                // 해당 과목명이 키로 없으면 새 리스트 생성
                courseGroupInfo.putIfAbsent(normalizedCourseName, new ArrayList<>());

                // 과목명에 해당 그룹 추가
                courseGroupInfo.get(normalizedCourseName).add(group);
            }
        }

        return courseGroupInfo;
    }
}
