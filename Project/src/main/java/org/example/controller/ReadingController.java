package org.example.controller;
import org.example.service.ReadingService;
public class ReadingController {
    private ReadingService readingService;

    public ReadingController(ReadingService readingService){
        this.readingService=readingService;
    }

    public void enrollStudent(Integer studentId, Integer courseId) {
        readingService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    public void practiceReading(Integer studentId, Integer courseId) {
        readingService.practiceReading(studentId,courseId);
    }

    public void reviewPastMistakes(Integer studentId) {
        readingService.reviewPastMistakes(studentId);
    }

    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        readingService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        readingService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        readingService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void deleteCourse(Integer courseId) {
        readingService.removeCourse(courseId);
        System.out.println("Removed course " + courseId);
    }
}
