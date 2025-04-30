package edu.handong.csee.java.studygroup.cli;

import org.apache.commons.cli.*;

/**
 * Handles command-line options for the Study Group Analyzer program.
 * This class uses Apache Commons CLI to define and parse command-line arguments.
 */
public class OptionHandler {
    private String courseName;
    private String normalizedCourseName;
    private String dataFilePath;
    private boolean printHelp;
    private boolean printStatistics;

    /**
     * Default constructor that initializes all fields.
     */
    public OptionHandler() {
        this.courseName = "";
        this.dataFilePath = "";
        this.printHelp = false;
        this.printStatistics = false;
    }

    /**
     * Creates and defines the available command-line options.
     *
     * @return Options object containing all the CLI options
     */
    public Options createOption() {
        Options options = new Options();

        Option helpOption = new Option("h", "help", false, "Print out the help page.");
        options.addOption(helpOption);

        Option fileOption = new Option("f", "filepath", true, "Set the data file path.");
        fileOption.setArgName("file-path");
        options.addOption(fileOption);

        Option statsOption = new Option("s", "statistics", false, "Print out the statistics of the study group data.");
        options.addOption(statsOption);

        Option courseNameOption = new Option("n", "cname", true, "Set a course name so it will print out group information based on course names. In addition, it saves a csv file about the results.");
        courseNameOption.setArgName("course-name");
        options.addOption(courseNameOption);

        return options;
    }

    /**
     * Parses the provided command-line arguments based on defined options.
     *
     * @param options the CLI options to parse
     * @param args    the command-line arguments
     * @return true if parsing is successful and required options are provided; false otherwise
     */
    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args, true);

            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length > 0) {
                printHelp(options);
                return false;
            }

            if (cmd.hasOption("h")) {
                this.printHelp = true;
                printHelp(options);
                return false;
            }

            if (cmd.hasOption("f")) {
                this.dataFilePath = cmd.getOptionValue("f");
            } else {
                System.out.println("Error: Missing required option -f <file-path>");
                return false;
            }

            if (cmd.hasOption("s")) {
                this.printStatistics = true;
            }

            if (cmd.hasOption("n")) {
                String rawCourseName = cmd.getOptionValue("n");
                if (rawCourseName != null) {
                    this.courseName = rawCourseName.trim();
                    this.normalizedCourseName = rawCourseName.trim().toLowerCase();
                } else {
                    this.courseName = null;
                    this.normalizedCourseName = null;
                }
            } else {
                this.courseName = null;
                this.normalizedCourseName = null;
            }

        } catch (ParseException e) {
            System.out.println("Error parsing options: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Prints the help message that describes how to use the CLI options.
     *
     * @param options the CLI options to describe
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setSyntaxPrefix("usage: ");
        String header = "Study group analysis program";
        String footer = "Please report issues at the issue report system.";
        formatter.printHelp("SGAnalyzer -f <file-path> [-h] [-n <course-name>] [-s]", header, options, "\n" + footer, false);
    }

    /** @return the course name as provided by the user */
    public String getCourseName() {
        return courseName;
    }

    /** @return the normalized (lowercased) course name for internal comparison */
    public String getNormalizedCourseName() {
        return normalizedCourseName;
    }

    /**
     * Sets the original course name.
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /** @return the file path provided via CLI */
    public String getDataFilePath() {
        return dataFilePath;
    }

    /**
     * Sets the data file path.
     * @param dataFilePath path to the CSV data file
     */
    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /** @return true if help option is set */
    public boolean isPrintHelp() {
        return printHelp;
    }

    /**
     * Sets whether the help message should be printed.
     * @param printHelp true to print help
     */
    public void setPrintHelp(boolean printHelp) {
        this.printHelp = printHelp;
    }

    /** @return true if statistics option is set */
    public boolean isPrintStatistics() {
        return printStatistics;
    }

    /**
     * Sets whether to print statistics.
     * @param printStatistics true to print statistics
     */
    public void setPrintStatistics(boolean printStatistics) {
        this.printStatistics = printStatistics;
    }
}
