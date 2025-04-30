package edu.handong.csee.java.studygroup.cli;

import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;

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
         fileOption.setArgName("file-path");
        options.addOption(fileOption);

        Option statsOption = new Option("s", "statistics", false, "Print out the statistics of the study group data.");
        options.addOption(statsOption);

        Option courseNameOption = new Option("n", "cname", true, "Set a course name so it will print out group information based on course names. In addition, it saves a csv file about the results.");
            courseNameOption.setArgName("course-name");
        options.addOption(courseNameOption);

        return options;
    }

    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();  // DefaultParser로 변경
        try {
            CommandLine cmd = parser.parse(options, args,true);

            String[] remainingArgs = cmd.getArgs();
            if (remainingArgs.length > 0) {
                printHelp(options);
                return false;
            }

            // -h (help) 옵션 처리
            if (cmd.hasOption("h")) {
                this.printHelp = true;
                printHelp(options);
                return false;  // 도움말을 출력하고 종료
            }

            // -f (filepath) 옵션 처리 (필수 옵션)
            if (cmd.hasOption("f")) {
                this.dataFilePath = cmd.getOptionValue("f");
            } else {
                System.out.println("Error: Missing required option -f <file-path>");
                return false;  // 필수 옵션이 없으면 종료
            }

            // -s (statistics) 옵션 처리
            if (cmd.hasOption("s")) {
                this.printStatistics = true;
            }

            // -n (course name) 옵션 처리
            if (cmd.hasOption("n")) {
                String rawCourseName = cmd.getOptionValue("n");
                if (rawCourseName != null) {
                    this.courseName = rawCourseName.trim().toLowerCase();
                } else {
                    this.courseName = null; // 값이 없을 경우 null
                }

            }else{
                this.courseName = null;
            }

        } catch (ParseException e) {
            System.out.println("Error parsing options: " + e.getMessage());
            return false;
        }

        return true;
    }

    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setSyntaxPrefix("usage: ");
        String header = "Study group analysis program";
        String footer = "Please report issues at the issue report system.";
        formatter.printHelp("SGAnalyzer -f <file-path> [-h] [-n <course-name>] [-s]", header, options,"\n" + footer, false);
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
