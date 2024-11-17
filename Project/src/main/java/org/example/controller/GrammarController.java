package org.example.controller;
import org.example.service.GrammarService;

public class GrammarController {
    private GrammarService grammarService;
    public GrammarController(GrammarService grammarService){
        this.grammarService=grammarService;
    }

    public void enrollStudent(Integer studentId, Integer courseId) {
        grammarService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    public void practiceGrammarulary(Integer studentId, Integer courseId) {
        grammarService.practiceGrammar(studentId,courseId);
    }

    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        grammarService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        grammarService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        grammarService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void deleteCourse(Integer courseId) {
        grammarService.removeCourse(courseId);
        System.out.println("Removed course " + courseId);
    }
}