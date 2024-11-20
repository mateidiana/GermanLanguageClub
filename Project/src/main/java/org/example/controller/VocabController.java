package org.example.controller;
import org.example.service.VocabService;

/**
 * The {@code VocabController} class manages operations related to vocabulary courses.
 * It acts as an intermediary between the view and the {@code VocabService}, handling
 * functionalities such as student enrollment, course management, vocabulary practice,
 * and reviewing student mistakes.
 */
public class VocabController {
    private VocabService vocabService;
    public VocabController(VocabService vocabService){
        this.vocabService=vocabService;
    }

    /**
     * Enrolls a student in a specific vocabulary course.
     *
     * @param studentId the ID of the student
     * @param courseId  the ID of the course
     */
    public void enrollStudent(Integer studentId, Integer courseId) {
        vocabService.enroll(studentId, courseId);
        System.out.println("Enrolled student " + studentId + " with course " + courseId);
    }

    /**
     * Allows a student to practice vocabulary exercises for a specific course.
     *
     * @param studentId the ID of the student
     * @param courseId  the ID of the course
     */
    public void practiceVocabulary(Integer studentId, Integer courseId) {
        vocabService.practiceVocabulary(studentId,courseId);
    }

    /**
     * Displays all available vocabulary courses.
     */
    public void viewCourses() {
        StringBuilder output = new StringBuilder("Available courses:\n");
        vocabService.getAvailableCourses().forEach(course -> output.append(course.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays all students registered in the system.
     */
    public void viewStudents() {
        StringBuilder output = new StringBuilder("Available students:\n");
        vocabService.getAllStudents().forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Displays the list of students enrolled in a specific vocabulary course.
     *
     * @param courseId the ID of the course
     */
    public void viewEnrolled(Integer courseId) {
        StringBuilder output = new StringBuilder("Enrolled students in the course:\n");
        vocabService.getEnrolledStudents(courseId).forEach(student -> output.append(student.toString()).append("\n"));
        System.out.println(output);
    }

    /**
     * Allows a student to review their past vocabulary mistakes for a specific course.
     *
     * @param studentId the ID of the student
     * @param courseId  the ID of the course
     */
    public void reviewPastMistakes(Integer studentId, Integer courseId) {
        vocabService.reviewPastMistakes(studentId, courseId);
    }

    /**
     * Deletes a specific vocabulary course.
     *
     * @param courseId the ID of the course
     */
    public void deleteCourse(Integer courseId) {
        vocabService.removeCourse(courseId);
        System.out.println("Removed course " + courseId);
    }

    /**
     * Displays the vocabulary courses taught by a specific teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId) {
        vocabService.viewCourseTaughtByTeacher(teacherId);
    }

    /**
     * Creates or updates a vocabulary course with the specified details.
     *
     * @param courseId    the ID of the course
     * @param teacherId   the ID of the teacher
     * @param courseName  the name of the course
     * @param maxStudents the maximum number of students allowed in the course
     */
    public void createOrUpdateVocabularyCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        vocabService.createOrUpdateVocabularyCourse(courseId, teacherId, courseName, maxStudents);
    }

    /**
     * Deletes a specific vocabulary course, accessible by a teacher.
     *
     * @param courseId  the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void deleteVocabularyCourse(Integer courseId, Integer teacherId) {
        vocabService.removeVocabularyCourse(courseId, teacherId);
    }

    /**
     * Changes a teacher's access to a specific vocabulary course.
     *
     * @param courseId  the ID of the course
     * @param teacherId the ID of the teacher
     */
    public void changeTeacherAccessToVocabCourse(Integer courseId, Integer teacherId){vocabService.changeTeacherAccessToVocabCourse(courseId,teacherId);}

    /**
     * Displays all students enrolled in any vocabulary courses.
     */
    public void showStudentsEnrolledInVocabCourses(){vocabService.showStudentsEnrolledInVocabCourses();}

    /**
     * Displays all vocabulary courses a student is enrolled in
     * @param studentId identifies a student
     */
    public void showEnrolledVocabCourses(Integer studentId){vocabService.showEnrolledVocabCourses(studentId);}

}
