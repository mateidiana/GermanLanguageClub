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

    public void practiceGrammar(Integer studentId, Integer courseId) {
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

    public void deleteCourse(Integer courseId, Integer teacherId) {
        grammarService.removeCourse(courseId, teacherId);
        System.out.println("Removed course " + courseId);
    }

    public void reviewPastMistakes(Integer studentId, Integer courseId){
        grammarService.reviewPastMistakes(studentId, courseId);
    }
    public void viewCourseTaughtByTeacher(Integer teacherId){
        grammarService.viewCourseTaughtByTeacher(teacherId);
    }
    public void createOrUpdateGrammarCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        grammarService.createOrUpdateGrammarCourse(courseId, teacherId, courseName, maxStudents);
    }

    public void changeTeacherAccessToGrammarCourse(Integer courseId, Integer teacherId){grammarService.changeTeacherAccessToGrammarCourse(courseId,teacherId);}

    public void showStudentsEnrolledInGrammarCourses(){grammarService.showStudentsEnrolledInGrammarCourses();}

}
