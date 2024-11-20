package org.example.controller;
import org.example.service.ReadingService;

/**
 * The {@code ReadingController} class manages operations related to reading courses.
 * It interacts with the {@code ReadingService} to handle functionalities such as
 * student enrollment, course management, practicing reading exercises, and viewing related details.
 */
public class ReadingController {
    private ReadingService readingService;

    public ReadingController(ReadingService readingService){
        this.readingService=readingService;
    }

    /**
     * Enrolls a student in a specified reading course.
     *
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     */
    public void enrollStudent(Integer studentId, Integer courseId) {
        readingService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    /**
     * Displays the reading courses a student is enrolled in.
     *
     * @param studentId the ID of the student
     */
    public void showEnrolledCourses(Integer studentId) {
        readingService.showEnrolledReadingCourses(studentId);
    }

    /**
     * Allows a student to practice reading exercises for a specific course.
     *
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     */
    public void practiceReading(Integer studentId, Integer courseId) {
        readingService.practiceReading(studentId,courseId);
    }

    /**
     * Allows a student to review their past reading mistakes.
     *
     * @param studentId the ID of the student
     */
    public void reviewPastMistakes(Integer studentId) {
        readingService.reviewPastReadingMistakes(studentId);
    }

    /**
     * Displays all available reading courses.
     */
    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        readingService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays all students registered in the system.
     */
    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        readingService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays the list of students enrolled in a specific reading course.
     *
     * @param courseId the ID of the course
     */
    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        readingService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Deletes a specified reading course, accessible by the teacher.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void deleteCourse(Integer courseId, Integer teacherId) {
        readingService.removeCourse(courseId,teacherId);
        //System.out.println("Removed course " + courseId);
    }

    /**
     * Changes a teacher's access to a specific reading course.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void changeTeacherAccess(Integer courseId, Integer teacherId){
        readingService.changeTeacherAccessToCourse(courseId,teacherId);
    }

    /**
     * Creates or updates a reading course with the specified details.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     * @param maxStudents the maximum number of students allowed in the course
     * @param exerciseSet the ID of the exercise set associated with the course
     */
    public void createOrUpdateReadingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents, Integer exerciseSet){
        readingService.createOrUpdateReadingCourse(courseId,teacherId,courseName,maxStudents,exerciseSet);
    }

    /**
     * Displays the reading courses taught by a specific teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId){
        readingService.viewCourseTaughtByTeacher(teacherId);
    }

    /**
     * Adds a mandatory book to a specific reading course, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     * @param courseId the ID of the course
     * @param book the name of the book to add
     */
    public void addMandatoryBook(Integer teacherId, Integer courseId,String book){
        readingService.addMandatoryBook(teacherId,courseId,book);
    }

    /**
     * Displays the mandatory books for a specific reading course, accessible by a student.
     *
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     */
    public void viewMandatoryBooks(Integer studentId, Integer courseId){readingService.viewMandatoryBooks(studentId,courseId);}

    /**
     * Displays all students enrolled in any reading courses.
     */
    public void showStudentsEnrolledInReadingCourses(){readingService.showStudentsEnrolledInReadingCourses();}
}
