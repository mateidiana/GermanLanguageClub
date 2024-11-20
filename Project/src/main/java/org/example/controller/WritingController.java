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

    public void practiceWriting(Integer studentId, Integer courseId) {
        writingService.practiceWriting(studentId,courseId);
    }
    public void showEnrolledCourses(Integer studentId) {
        writingService.showEnrolledWritingCourses(studentId);
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


    public void viewCourseTaughtByTeacher(Integer teacherId){
        writingService.viewCourseTaughtByTeacher(teacherId);
    }
    public void gradePractice(Integer teacherId, Integer courseId){
        writingService.gradeFeedback(teacherId, courseId);
    }

    public void createOrUpdateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        writingService.createOrUpdateWritingCourse(courseId, teacherId, courseName, maxStudents);
    }

    public void deleteCourse(Integer courseId, Integer teacherId) {
        writingService.removeCourse(courseId, teacherId);
    }

    public void getFeedback(Integer studentId){
        writingService.showFeedback(studentId);
    }

    public void changeTeacherAccessToWritingCourse(Integer courseId, Integer teacherId){writingService.changeTeacherAccessToWritingCourse(courseId,teacherId);}

    public void getTeacherById(Integer teacherId){writingService.getTeacherById(teacherId);}
}
