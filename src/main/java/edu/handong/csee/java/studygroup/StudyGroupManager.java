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

public class StudyGroupManager {

    public static void main(String[] args) {
        StudyGroupManager myRunner = new StudyGroupManager();
        myRunner.run(args);
    }

    public void run(String[] args) {

        OptionHandler myOptionHandler = new OptionHandler();
        Options options = myOptionHandler.createOption();

        if (myOptionHandler.parseOptions(options, args)) {

            // for - h option
            if (myOptionHandler.isPrintHelp()) {
                myOptionHandler.printHelp(options);
            }

            // read a file and get the String array list for records in the csv file.
            String filePath = myOptionHandler.getDataFilePath();

            System.out.println("Loading the study group data file, " + filePath + "...");

            String[] fieldNames = "Group,MemberID,MemberName,Friends,Subjects,Reports,Times".split(",");
            ArrayList<ArrayList<String>> records = FileUtils.readCSVFile(filePath,fieldNames);

            // get array list for Student instances from lines.
            ArrayList<Student> students = getStudents(records);

            // get hash map for group info
            HashMap<Integer, StudyGroup> groupInfo = DataPreprocessor.getGroupInfo(students);

            System.out.println("The data file is loaded...");
            System.out.println("The number of groups: " + groupInfo.size());
            System.out.println("The number of students: " + students.size());

            // for -s option
            if (myOptionHandler.isPrintStatistics()) {
                System.out.println();
                System.out.println("==== Statistics ====");
                StatisticsManager.printGroupStatistics(groupInfo);
            }

            // for -n option
            if (myOptionHandler.getCourseName() != null) {

                // print out an empty line
                System.out.println();

                try {
                    ArrayList<StudyGroup> groupsForTheCourseName = DataPreprocessor.getGroupInfoByCourseName(groupInfo)
                            .get(myOptionHandler.getCourseName());

                    if (groupsForTheCourseName == null) {
                        throw new NoCourseNameFoundException(myOptionHandler.getCourseName());
                    }

                    ArrayList<String> header = new ArrayList<String>();
                    header.add("Group");
                    header.add("MemberIDs");
                    header.add("MemberNames");
                    header.add("Reports");
                    header.add("Times");
                    // save
                    FileUtils.writeCSVFileByCourseName(filePath, myOptionHandler.getCourseName(), header,
                            groupsForTheCourseName);

                    // print
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

    public ArrayList<Student> getStudents(ArrayList<ArrayList<String>> records){
        ArrayList<Student> students = new ArrayList<>();

        for (ArrayList<String > record:records) {
            Student student = new Student(record.get(0),record.get(1),record.get(2),record.get(3)
                    ,record.get(4),record.get(5),record.get(6));
            students.add(student);
        }

        return students;
    }
}