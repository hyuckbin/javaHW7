package edu.handong.csee.java.studygroup.fileio;

import edu.handong.csee.java.studygroup.analyzers.DataPreprocessor;
import edu.handong.csee.java.studygroup.analyzers.StatisticsManager;
import edu.handong.csee.java.studygroup.datamodel.Student;
import edu.handong.csee.java.studygroup.datamodel.StudyGroup;
import edu.handong.csee.java.studygroup.exceptions.NoCourseNameFoundException;
import edu.handong.csee.java.studygroup.fileio.FileUtils;
import edu.handong.csee.java.studygroup.cli.OptionHandler;
import org.apache.commons.cli.Options;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {

    public static ArrayList<ArrayList<String>> readCSVFile(String filename, String[] header) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                ArrayList<String> row = new ArrayList<>();
                for (String field : fields) {
                    row.add(field.trim());
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void writeCSVFileByCourseName(String filename, String courseName, ArrayList<Student> students, ArrayList<StudyGroup> groups) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            bw.write("GroupNo,MemberID,Name,CourseName,NumOfReports,MinutesForStudy");
            bw.newLine();

            for (Student student : students) {
                if (student.getCourseNames().contains(courseName)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(student.getGroupNo()).append(",");
                    sb.append(student.getMemberID()).append(",");
                    sb.append(student.getName()).append(",");
                    sb.append(courseName).append(",");
                    sb.append(student.getNumOfReports()).append(",");
                    sb.append(student.getMinutesForStudy());

                    bw.write(sb.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
