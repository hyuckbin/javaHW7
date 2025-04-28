package edu.handong.csee.java.studygroup.cli;

import org.apache.commons.cli.*;

public class OptionHandler {

    private String courseName;
    private String dataFilePath;
    private boolean printHelp;
    private boolean printStatistics;

    public Options createOption() {
        Options options = new Options();

        options.addOption(Option.builder("c")
                .longOpt("course")
                .desc("Target course name")
                .hasArg()
                .argName("Course Name")
                .required(false)
                .build());

        options.addOption(Option.builder("i")
                .longOpt("input")
                .desc("Input data file path")
                .hasArg()
                .argName("Input Path")
                .required(true)
                .build());

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("Show help message")
                .required(false)
                .build());

        options.addOption(Option.builder("s")
                .longOpt("statistics")
                .desc("Print group statistics")
                .required(false)
                .build());

        return options;
    }

    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            courseName = cmd.getOptionValue("c");
            dataFilePath = cmd.getOptionValue("i");

            printHelp = cmd.hasOption("h");
            printStatistics = cmd.hasOption("s");

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options);
            return false;
        }

        return true;
    }

    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("StudyGroupManager", options);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public boolean isPrintHelp() {
        return printHelp;
    }

    public void setPrintHelp(boolean printHelp) {
        this.printHelp = printHelp;
    }

    public boolean isPrintStatistics() {
        return printStatistics;
    }

    public void setPrintStatistics(boolean printStatistics) {
        this.printStatistics = printStatistics;
    }
}
