package edu.handong.csee.java.studygroup.fileio;

import edu.handong.csee.java.studygroup.datamodel.StudyGroup;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Collectors import

/**
 * Utility class for handling CSV file input and output operations.
 * Provides methods to read data from a CSV file and write data to a new CSV file.
 */
public class FileUtils {

    /**
     * Reads the CSV file from the given path and returns its data as a list of records.
     *
     * @param path   the path to the CSV file
     * @param header an array of strings that will store the CSV file's header names
     * @return a list of records where each record is an array of strings representing a row in the CSV
     */
    public static ArrayList<ArrayList<String>> readCSVFile(String path, String[] header) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            // Store the CSV header
            List<String> fileHeader = csvParser.getHeaderNames();
            for (int i = 0; i < fileHeader.size(); i++) {
                // Check for null and trim for safety
                if (fileHeader.get(i) != null) {
                    header[i] = fileHeader.get(i).trim();
                } else {
                    header[i] = ""; // Default value if null
                }
            }

            // Read the records from the CSV file
            for (CSVRecord record : csvParser) {
                ArrayList<String> row = new ArrayList<>();
                for (String value : record) {
                    // Check for null and trim for safety
                    row.add(value != null ? value.trim() : "");
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
            // Return empty list in case of error
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while reading CSV file: " + e.getMessage());
            e.printStackTrace();
            // Return empty list in case of error
            return new ArrayList<>();
        }

        return data;
    }

    /**
     * Writes the data to a new CSV file filtered by the given course name.
     * The output file will be named based on the original file name and course name.
     *
     * @param originalFileName the name of the original file
     * @param courseName       the course name to filter the data by
     * @param header           a list of column headers to write to the CSV file
     * @param groups           a list of StudyGroup objects containing the data to write
     */
    public static void writeCSVFileByCourseName(String originalFileName, String courseName, ArrayList<String> header, ArrayList<StudyGroup> groups) {
        // Generate the output file name by removing the .csv extension and adding the course name
        String baseFileName = originalFileName;
        int extensionIndex = originalFileName.lastIndexOf('.');
        if (extensionIndex > 0) {
            baseFileName = originalFileName.substring(0, extensionIndex);
        }
        // Set up the output directory (create if it doesn't exist)
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Create output directory if it doesn't exist
        }

        // Create the output file path
        String outputFileName = outputDir.getPath() + "/" + new File(baseFileName).getName() + "-" + courseName + ".csv";

        try (
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8));
                // CSVPrinter automatically quotes fields containing commas
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])))
        ) {
            // Write the StudyGroup data into the CSV
            for (StudyGroup group : groups) {
                // Convert member IDs to a comma-separated string
                String memberIDsStr = group.getMemberIDs().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));

                // Convert member names to a comma-separated string
                String memberNamesStr = group.getNames().stream()
                        .collect(Collectors.joining(", "));

                // Print each record to the CSV
                csvPrinter.printRecord(
                        group.getGroupNo(), // Group number
                        memberIDsStr, // Member IDs
                        memberNamesStr, // Member names
                        group.getNumOfReports(), // Number of reports
                        group.getStudyMinutes() // Study time
                );
            }
            csvPrinter.flush(); // Ensure all data is written to the file

            // Print a success message after saving the file
            System.out.println("The output file, output/" + new File(outputFileName).getName() + ", is saved!!");

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
