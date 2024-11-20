package org.example.controller;

import org.example.service.WritingService;

/**
 * The {@code WritingController} class manages operations related to writing courses.
 * It serves as a bridge between the view and the {@code WritingService}, handling functionalities
 * such as student enrollment, writing practice, course management, grading, and feedback.
 */
public class WritingController {
    private WritingService writingService;
    public WritingController(WritingService writingService){
        this.writingService=writingService;
    }

    /**
     * Enrolls a student in a specific writing course.
     *
     * @param studentId the ID of the student
     * @param courseId  the ID of the course
     */
    public void enrollStudent(Integer studentId, Integer courseId) {
        writingService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    /**
     * Allows a student to practice writing exercises for a specific course.
     *
     * @param studentId the ID of the student
     * @param courseId  the ID of the course
     */
    public void practiceWriting(Integer studentId, Integer courseId) {
        writingService.practiceWriting(studentId,courseId);
    }

    /**
     * Displays the writing courses a student is enrolled in.
     *
     * @param studentId the ID of the student
     */
    public void showEnrolledCourses(Integer studentId) {
        writingService.showEnrolledWritingCourses(studentId);
    }

    /**
     * Displays all available writing courses.
     */
    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        writingService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays all students registered in the system.
     */
    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        writingService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays the students enrolled in a specific writing course.
     *
     * @param courseId the ID of the course
     */
    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        writingService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays the writing courses taught by a specific teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId){
        writingService.viewCourseTaughtByTeacher(teacherId);
    }

    /**
     * Allows a teacher to grade and provide feedback for writing exercises in a specific course.
     *
     * @param teacherId the ID of the teacher
     * @param courseId  the ID of the course
     */
    public void gradePractice(Integer teacherId, Integer courseId){
        writingService.gradeFeedback(teacherId, courseId);
    }

    /**
     * Creates or updates a writing course with the specified details.
     *
     * @param courseId    the ID of the course
     * @param teacherId   the ID of the teacher
     * @param courseName  the name of the course
     * @param maxStudents the maximum number of students allowed in the course
     */
    public void createOrUpdateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        writingService.createOrUpdateWritingCourse(courseId, teacherId, courseName, maxStudents);
    }

    /**
     * Deletes a specific writing course, accessible by a teacher.
     *
     * @param courseId  the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void deleteCourse(Integer courseId, Integer teacherId) {
        writingService.removeCourse(courseId, teacherId);
    }

    /**
     * Displays feedback for a student's writing exercises.
     *
     * @param studentId the ID of the student
     */
    public void getFeedback(Integer studentId){
        writingService.showFeedback(studentId);
    }

    /**
     * Changes a teacher's access to a specific writing course.
     *
     * @param courseId  the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void changeTeacherAccessToWritingCourse(Integer courseId, Integer teacherId){writingService.changeTeacherAccessToWritingCourse(courseId,teacherId);}

    public void getTeacherById(Integer teacherId){writingService.getTeacherById(teacherId);}

    /**
     * Displays all students enrolled in any writing courses.
     */
    public void showStudentsEnrolledInWritingCourses(){writingService.showStudentsEnrolledInWritingCourses();}
}
