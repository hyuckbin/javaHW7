package edu.handong.csee.java.studygroup.exceptions;

public class NoCourseNameFoundException extends Exception {

    public NoCourseNameFoundException() {
        super("No course name was found.");
    }

    public NoCourseNameFoundException(String message) {
        super(message);
    }
}
