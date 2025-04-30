package edu.handong.csee.java.studygroup;

import edu.handong.csee.java.studygroup.analyzers.DataPreprocessor;
import edu.handong.csee.java.studygroup.analyzers.StatisticsManager;
import edu.handong.csee.java.studygroup.datamodel.Student;
import edu.handong.csee.java.studygroup.datamodel.StudyGroup;
import edu.handong.csee.java.studygroup.exceptions.NoCourseNameFoundException;
import edu.handong.csee.java.studygroup.fileio.FileUtils;
import edu.handong.csee.java.studygroup.cli.OptionHandler;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The main class for managing study group data from a CSV file.
 * It supports statistics display and course-specific group extraction and export.
 */
public class StudyGroupManager {

    /**
     * The entry point of the program.
     * @param args command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        StudyGroupManager myRunner = new StudyGroupManager();
        myRunner.run(args);
    }

    /**
     * Runs the main logic of the application.
     * It parses options, reads the input file, processes student/group data,
     * prints statistics, and exports course-specific group data if requested.
     *
     * @param args command-line arguments passed to the program.
     */
    public void run(String[] args) {

        OptionHandler myOptionHandler = new OptionHandler();
        Options options = myOptionHandler.createOption();

        if (myOptionHandler.parseOptions(options, args)) {

            // For -h option: show help message
            if (myOptionHandler.isPrintHelp()) {
                myOptionHandler.printHelp(options);
            }

            // Load study group data file
            String filePath = myOptionHandler.getDataFilePath();
            System.out.println("Loading the study group data file, " + filePath + "...");

            String[] fieldNames = "Group,MemberID,MemberName,Friends,Subjects,Reports,Times".split(",");
            ArrayList<ArrayList<String>> records = FileUtils.readCSVFile(filePath, fieldNames);

            // Convert records to list of Student instances
            ArrayList<Student> students = getStudents(records);

            // Grouping students into study groups
            HashMap<Integer, StudyGroup> groupInfo = DataPreprocessor.getGroupInfo(students);

            System.out.println("The data file is loaded...");
            System.out.println("The number of groups: " + groupInfo.size());
            System.out.println("The number of students: " + students.size());

            // For -s option: print statistics
            if (myOptionHandler.isPrintStatistics()) {
                System.out.println();
                System.out.println("==== Statistics ====");
                StatisticsManager.printGroupStatistics(groupInfo);
            }

            // For -n option: output study group info for a specific course
            if (myOptionHandler.getCourseName() != null) {
                System.out.println();

                try {
                    ArrayList<StudyGroup> groupsForTheCourseName = DataPreprocessor
                            .getGroupInfoByCourseName(groupInfo)
                            .get(myOptionHandler.getNormalizedCourseName());

                    if (groupsForTheCourseName == null) {
                        throw new NoCourseNameFoundException(myOptionHandler.getCourseName());
                    }

                    // Prepare header for output CSV
                    ArrayList<String> header = new ArrayList<>();
                    header.add("Group");
                    header.add("MemberIDs");
                    header.add("MemberNames");
                    header.add("Reports");
                    header.add("Times");

                    // Write group data to output file
                    FileUtils.writeCSVFileByCourseName(filePath, myOptionHandler.getCourseName(), header, groupsForTheCourseName);

                    // Print output to console
                    System.out.println("Group,MemberIDs,MemberNames,Reports,Times");
                    for (StudyGroup group : groupsForTheCourseName) {
                        System.out.print(group.getGroupNo() + ",");
                        System.out.print("\"" + group.getMemberIDs().toString().replaceAll("^\\[|\\]$", "") + "\",");
                        System.out.print("\"" + group.getNames().toString().replaceAll("^\\[|\\]$", "") + "\",");
                        System.out.print(group.getNumOfReports() + ",");
                        System.out.println(group.getStudyMinutes());
                    }
                } catch (NoCourseNameFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Converts CSV records into a list of Student objects.
     *
     * @param records A list of CSV rows where each row contains student-related data.
     * @return A list of Student instances created from the CSV records.
     */
    public ArrayList<Student> getStudents(ArrayList<ArrayList<String>> records) {
        ArrayList<Student> students = new ArrayList<>();

        for (ArrayList<String> record : records) {
            Student student = new Student(record.get(0), record.get(1), record.get(2), record.get(3),
                    record.get(4), record.get(5), record.get(6));
            students.add(student);
        }

        return students;
    }
}
