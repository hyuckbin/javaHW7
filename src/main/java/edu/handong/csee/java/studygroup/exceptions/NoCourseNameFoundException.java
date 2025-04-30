package edu.handong.csee.java.studygroup.exceptions;

/**
 * Custom exception thrown when a specified course name is not found in the data.
 *
 * <p>This exception is typically used in scenarios where a user queries a course name
 * using the {@code -n} option, but that course name does not exist in the input dataset.</p>
 *
 */
public class NoCourseNameFoundException extends Exception {

    /**
     * Default constructor for NoCourseNameFoundException.
     */
    public NoCourseNameFoundException() {
        super();
    }

    /**
     * Constructs the exception with a custom message that includes the missing course name.
     *
     * @param courseName the course name that was not found
     */
    public NoCourseNameFoundException(String courseName) {
        super("Exception-01: No course name " +"("+ courseName +")"+ " found!");
    }
}




