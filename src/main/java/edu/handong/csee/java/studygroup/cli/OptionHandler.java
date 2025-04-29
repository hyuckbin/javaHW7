package edu.handong.csee.java.studygroup.cli;

import org.apache.commons.cli.*;

public class OptionHandler {
    private String courseName;
    private String dataFilePath;
    private boolean printHelp;
    private boolean printStatistics;

    public OptionHandler() {
        this.courseName = "";
        this.dataFilePath = "";
        this.printHelp = false;
        this.printStatistics = false;
    }

    public Options createOption() {
        Options options = new Options();

        Option helpOption = new Option("h", "help", false, "Print out the help page.");
        options.addOption(helpOption);

        Option fileOption = new Option("f", "filepath", true, "Set the data file path.");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        Option statsOption = new Option("s", "statistics", false, "Print out the statistics of the study group data.");
        options.addOption(statsOption);

        Option courseNameOption = new Option("n", "cname", true, "Set a course name to print out group information based on course names.");
        courseNameOption.setArgName("course-name");
        options.addOption(courseNameOption);

        return options;
    }

    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                this.printHelp = true;
                return false;
            }

            if (cmd.hasOption("f")) {
                this.dataFilePath = cmd.getOptionValue("f");
            }
            if (cmd.hasOption("s")) {
                this.printStatistics = true;
            }

            if (cmd.hasOption("n")) {
                this.courseName = cmd.getOptionValue("n");
            }

        } catch (ParseException e) {
            System.out.println("Error parsing options: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Study group analysis program";
        String footer = System.lineSeparator() + "Please report issues at the issue report system.";
        formatter.printHelp("SGAnalyzer", header, options, footer, true);

        System.exit(0);
    }

    // Getter and Setter methods
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

