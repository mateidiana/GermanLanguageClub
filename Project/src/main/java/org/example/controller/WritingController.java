package org.example.controller;

import org.example.service.WritingService;

public class WritingController {
    private WritingService writingService;
    public WritingController(WritingService writingService){
        this.writingService=writingService;
    }

    public void enrollStudent(Integer studentId, Integer courseId) {
        writingService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    public void practiceWritingulary(Integer studentId, Integer courseId) {
        writingService.practiceWriting(studentId,courseId);
    }

    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        writingService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        writingService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        writingService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void deleteCourse(Integer courseId) {
        writingService.removeCourse(courseId);
        System.out.println("Removed course " + courseId);
    }
}
