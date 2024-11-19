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

    public void showEnrolledCourses(Integer studentId) {
        readingService.showEnrolledReadingCourses(studentId);
    }

    public void practiceReading(Integer studentId, Integer courseId) {
        readingService.practiceReading(studentId,courseId);
    }

    public void reviewPastMistakes(Integer studentId) {
        readingService.reviewPastReadingMistakes(studentId);
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

    public void deleteCourse(Integer courseId, Integer teacherId) {
        readingService.removeCourse(courseId,teacherId);
        //System.out.println("Removed course " + courseId);
    }

    public void changeTeacherAccess(Integer courseId, Integer teacherId){
        readingService.changeTeacherAccessToCourse(courseId,teacherId);
    }

    public void createOrUpdateReadingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents){
        readingService.createOrUpdateReadingCourse(courseId,teacherId,courseName,maxStudents);
    }

    public void viewCourseTaughtByTeacher(Integer teacherId){
        readingService.viewCourseTaughtByTeacher(teacherId);
    }

    public void addMandatoryBook(Integer teacherId, Integer courseId,String book){
        readingService.addMandatoryBook(teacherId,courseId,book);
    }

    public void viewMandatoryBooks(Integer studentId, Integer courseId){readingService.viewMandatoryBooks(studentId,courseId);}
}
