package org.example.controller;
import org.example.service.ReadingService;
import org.example.service.VocabService;

public class VocabController {
    private VocabService vocabService;
    public VocabController(VocabService vocabService){
        this.vocabService=vocabService;
    }

    public void enrollStudent(Integer studentId, Integer courseId) {
        vocabService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    public void practiceVocabulary(Integer studentId, Integer courseId) {
        vocabService.practiceVocabulary(studentId,courseId);
    }

    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        vocabService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        vocabService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        vocabService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    public void reviewPastMistakes(Integer studentId, Integer courseId) {
        vocabService.reviewPastMistakes(studentId, courseId);
    }

    public void deleteCourse(Integer courseId) {
        vocabService.removeCourse(courseId);
        System.out.println("Removed course " + courseId);
    }
    public void viewCourseTaughtByTeacher(Integer teacherId) {
        vocabService.viewCourseTaughtByTeacher(teacherId);
    }
    public void createOrUpdateVocabularyCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        vocabService.createOrUpdateVocabularyCourse(courseId, teacherId, courseName, maxStudents);
    }
    public void deleteVocabularyCourse(Integer courseId, Integer teacherId) {
        vocabService.removeVocabularyCourse(courseId, teacherId);
    }

    public void changeTeacherAccessToVocabCourse(Integer courseId, Integer teacherId){vocabService.changeTeacherAccessToVocabCourse(courseId,teacherId);}

    public void showStudentsEnrolledInVocabCourses(){vocabService.showStudentsEnrolledInVocabCourses();}

}
