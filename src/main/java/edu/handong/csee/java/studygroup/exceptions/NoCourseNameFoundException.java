package edu.handong.csee.java.studygroup.exceptions;

public class NoCourseNameFoundException extends Exception {

    public NoCourseNameFoundException() {
        super();
    }

    public NoCourseNameFoundException(String courseName) {
        super("Exception-01: No course name, " + courseName + ", found!");
    }
}
