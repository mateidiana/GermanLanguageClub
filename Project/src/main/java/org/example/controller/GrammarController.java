package org.example.controller;
import org.example.service.GrammarService;

/**
 * The {@code GrammarController} class handles the operations related to grammar courses.
 * It acts as a bridge between the view and the {@code GrammarService} to manage student enrollment,
 * grammar practice, course management, and viewing relevant information.
 */
public class GrammarController {
    private GrammarService grammarService;
    public GrammarController(GrammarService grammarService){
        this.grammarService=grammarService;
    }

    /**
     * Enrolls a student in a specified grammar course.
     *
     * @param studentId the ID of the student to enroll
     * @param courseId the ID of the course
     */
    public void enrollStudent(Integer studentId, Integer courseId) {
        grammarService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    /**
     * Allows a student to practice grammar for a specified course.
     *
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     */
    public void practiceGrammar(Integer studentId, Integer courseId) {
        grammarService.practiceGrammar(studentId,courseId);
    }

    /**
     * Displays all available grammar courses.
     */
    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        grammarService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays all students registered in the system.
     */
    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        grammarService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays the list of students enrolled in a specific grammar course.
     *
     * @param courseId the ID of the course
     */
    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        grammarService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Deletes a specific grammar course, accessible by a teacher.
     *
     * @param courseId the ID of the course to delete
     * @param teacherId the ID of the teacher responsible for the course
     */
    public void deleteCourse(Integer courseId, Integer teacherId) {
        grammarService.removeCourse(courseId, teacherId);
        System.out.println("Removed course " + courseId);
    }

    /**
     * Allows a student to review their past grammar mistakes for a specific course.
     *
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     */
    public void reviewPastMistakes(Integer studentId, Integer courseId){
        grammarService.reviewPastMistakes(studentId, courseId);
    }

    /**
     * Displays the grammar courses taught by a specific teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId){
        grammarService.viewCourseTaughtByTeacher(teacherId);
    }

    /**
     * Creates or updates a grammar course with the specified details.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     * @param maxStudents the maximum number of students allowed in the course
     */
    public void createOrUpdateGrammarCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        grammarService.createOrUpdateGrammarCourse(courseId, teacherId, courseName, maxStudents);
    }

    /**
     * Changes a teacher's access to a specific grammar course.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void changeTeacherAccessToGrammarCourse(Integer courseId, Integer teacherId){grammarService.changeTeacherAccessToGrammarCourse(courseId,teacherId);}

    /**
     * Displays all students enrolled in grammar courses.
     */
    public void showStudentsEnrolledInGrammarCourses(){grammarService.showStudentsEnrolledInGrammarCourses();}

    /**
     * Displays all grammar courses a student is enrolled in
     * @param studentId identifies a student
     */
    public void showEnrolledGrammarCourses(Integer studentId){grammarService.showEnrolledGrammarCourses(studentId);}

}
