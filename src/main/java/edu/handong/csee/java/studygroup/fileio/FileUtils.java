package edu.handong.csee.java.studygroup.fileio;

import edu.handong.csee.java.studygroup.datamodel.StudyGroup;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Collectors 임포트

public class FileUtils {

    // CSV 파일을 읽고 데이터를 반환하는 메서드 (수정 없음)
    public static ArrayList<ArrayList<String>> readCSVFile(String path, String[] header) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (
                Reader reader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            // CSV 헤더 저장
            List<String> fileHeader = csvParser.getHeaderNames();
            for (int i = 0; i < fileHeader.size(); i++) {
                // null 체크 및 trim() 추가 (안정성 향상)
                if (fileHeader.get(i) != null) {
                    header[i] = fileHeader.get(i).trim();
                } else {
                    header[i] = ""; // 혹은 다른 기본값
                }
            }

            // 레코드 읽기
            for (CSVRecord record : csvParser) {
                ArrayList<String> row = new ArrayList<>();
                for (String value : record) {
                    // null 체크 및 trim() 추가 (안정성 향상)
                    row.add(value != null ? value.trim() : "");
                }
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
            // 파일을 읽지 못하면 빈 리스트 반환
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while reading CSV file: " + e.getMessage());
            e.printStackTrace();
            // 예상치 못한 오류 발생 시 빈 리스트 반환
            return new ArrayList<>();
        }


        return data;
    }

    // 과목별로 데이터를 CSV 파일로 저장하는 메서드
    public static void writeCSVFileByCourseName(String originalFileName, String courseName, ArrayList<String> header, ArrayList<StudyGroup> groups) {
        // 출력 파일 이름 생성: 원본 파일 이름에서 .csv 확장자를 제거하고 "_[과목 이름].csv" 추가
        // 예: study-group-statistics.csv -> study-group-statistics_Computer Vision.csv
        String baseFileName = originalFileName;
        int extensionIndex = originalFileName.lastIndexOf('.');
        if (extensionIndex > 0) {
            baseFileName = originalFileName.substring(0, extensionIndex);
        }
        // 출력 파일이 저장될 경로 설정 (output 폴더 생성)
        File outputDir = new File("output");
        if (!outputDir.exists()){
            outputDir.mkdirs(); // output 폴더가 없으면 생성
        }
        String outputFileName = outputDir.getPath() + "/" + new File(baseFileName).getName() + "_" + courseName + ".csv"; // output 폴더 안에 파일 생성

        try (
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8));
                // CSVFormat.DEFAULT는 보통 콤마가 포함된 필드를 자동으로 큰따옴표로 묶어줍니다.
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])))
        ) {
            for (StudyGroup group : groups) {
                // MemberIDs를 "ID1, ID2, ID3" 형식의 문자열로 만듭니다.
                String memberIDsStr = group.getMemberIDs().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")); // ✅ 콤마와 공백으로 구분

                // MemberNames를 "Name1, Name2, Name3" 형식의 문자열로 만듭니다.
                String memberNamesStr = group.getNames().stream()
                        .collect(Collectors.joining(", ")); // ✅ 콤마와 공백으로 구분


                // CSV 레코드를 출력합니다. 헤더와 일치하도록 5개 필드만 씁니다.
                csvPrinter.printRecord(
                        group.getGroupNo(), // Group
                        memberIDsStr, // MemberIDs
                        memberNamesStr, // MemberNames
                        group.getNumOfReports(), // Reports
                        group.getStudyMinutes() // Times
                        // ❌ 여기에 'courseName' 필드는 추가하지 않습니다. (헤더와 불일치)
                );
            }
            csvPrinter.flush(); // 버퍼에 남은 데이터를 파일에 씁니다.

            // ✅ 파일 저장이 성공한 후에 메시지를 출력합니다.
            // StudyGroupManager에서 출력하는 것처럼 "output/" 경로를 포함하여 출력합니다.
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
