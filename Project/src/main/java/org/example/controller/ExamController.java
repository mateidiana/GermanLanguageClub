package org.example.controller;
import org.example.service.ExamService;

/**
 * The {@code ExamController} class acts as a mediator between the view and the {@code ExamService}.
 * It provides methods to handle various exam-related operations such as creating, updating, deleting,
 * taking exams, and viewing results.
 *
 * This class delegates the core logic to the {@code ExamService}.
 */
public class ExamController {
    private ExamService examService;

    /**
     * Constructs an {@code ExamController} with the specified {@code ExamService}.
     *
     * @param examService the service layer for managing exam-related operations
     */
    public ExamController(ExamService examService){
        this.examService=examService;
    }

    /**
     * Allows a student to take a reading exam.
     *
     * @param studentId the ID of the student
     * @param examId the ID of the reading exam
     */
    public void takeReadingExam(Integer studentId, Integer examId){
        examService.takeReadingExam(studentId,examId);
    }

    /**
     * Allows a student to take a grammar exam.
     *
     * @param studentId the ID of the student
     * @param examId the ID of the grammar exam
     */

    public void takeGrammarExam(Integer studentId, Integer examId){
        examService.takeGrammarExam(studentId,examId);
    }

    /**
     * Allows a student to take a vocabulary exam.
     *
     * @param studentId the ID of the student
     * @param examId the ID of the vocabulary exam
     */
    public void takeVocabExam(Integer studentId, Integer examId){
        examService.takeVocabExam(studentId,examId);
    }

    /**
     * Displays a student's results for all reading exams.
     *
     * @param studentId the ID of the student
     */
    public void showReadingResults(Integer studentId){examService.showReadingResults(studentId);}

    /**
     * Displays all reading exams available in the system.
     */
    public void showAllReadingExams(){examService.showAllReadingExams();}

    /**
     * Displays a student's results for all grammar exams.
     *
     * @param studentId the ID of the student
     */
    public void showGrammarResults(Integer studentId){examService.showGrammarResults(studentId);}

    /**
     * Displays a student's results for all vocabulary exams.
     *
     * @param studentId the ID of the student
     */
    public void showVocabResults(Integer studentId){examService.showVocabResults(studentId);}

    /**
     * Allows a student to take a writing exam.
     *
     * @param studentId the ID of the student
     * @param examId the ID of the writing exam
     */
    public void takeWritingExam(Integer studentId, Integer examId){
        examService.takeWritingExam(studentId,examId);
    }

    /**
     * Displays a student's results for all writing exams.
     *
     * @param studentId the ID of the student
     */
    public void showWritingResults(Integer studentId){examService.showWritingResults(studentId);}

    /**
     * Displays the results of all students for a specific reading exam, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void showResultsOfAllStudentsOnReadingExam(Integer teacherId){
        examService.showResultsOfAllStudentsOnReadingExam(teacherId);
    }

    /**
     * Creates or updates a reading exam for a specific course.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     */
    public void createOrUpdateReadingExam(Integer courseId, Integer teacherId, String courseName){
        examService.createOrUpdateReadingExam(courseId,teacherId,courseName);
    }

    /**
     * Deletes a specific reading exam, accessible by the teacher.
     *
     * @param examId the ID of the exam to delete
     * @param teacherId the ID of the teacher
     */
    public void deleteReadingExam(Integer examId, Integer teacherId) {
        examService.removeReadingExam(teacherId,examId);
        //System.out.println("Removed course " + courseId);
    }

    /**
     * Creates or updates a writing exam for a specific course.
     *
     * @param examId the ID of the exam
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     */
    public void createOrUpdateWritingExam(Integer examId, Integer teacherId, String courseName) {
        examService.createOrUpdateWritingExam(examId, teacherId, courseName);
    }

    /**
     * Deletes a specific writing exam, accessible by the teacher.
     *
     * @param examId the ID of the exam to delete
     * @param teacherId the ID of the teacher
     */
    public void deleteWritingExam(Integer examId, Integer teacherId) {
        examService.removeWritingExam(teacherId, examId);
        // System.out.println("Removed exam " + examId);
    }

    /**
     * Displays the results of all students for a specific writing exam, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void showResultsOfAllStudentsOnWritingExam(Integer teacherId) {
        examService.showResultsOfAllStudentsOnWritingExam(teacherId);
    }

    /**
     * Grades writing exams, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     * @param examId the ID of the exam to grade
     */
    public void gradeExams(Integer teacherId, Integer examId){
        examService.gradeWritings(teacherId, examId);
    }

    /**
     * Creates or updates a grammar exam for a specific course.
     *
     * @param examId the ID of the exam
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     */
    public void createOrUpdateGrammarExam(Integer examId, Integer teacherId, String courseName) {
        examService.createOrUpdateGrammarExam(examId, teacherId, courseName);
    }

    /**
     * Deletes a specific grammar exam, accessible by the teacher.
     *
     * @param examId the ID of the exam to delete
     * @param teacherId the ID of the teacher
     */
    public void deleteGrammarExam(Integer examId, Integer teacherId) {
        examService.removeGrammarExam(teacherId, examId);
    }

    /**
     * Creates or updates a vocabulary exam for a specific course.
     *
     * @param examId the ID of the exam
     * @param teacherId the ID of the teacher
     * @param courseName the name of the course
     */
    public void createOrUpdateVocabularyExam(Integer examId, Integer teacherId, String courseName) {
        examService.createOrUpdateVocabularyExam(examId, teacherId, courseName);
    }

    /**
     * Deletes a specific vocabulary exam, accessible by the teacher.
     *
     * @param examId the ID of the exam to delete
     * @param teacherId the ID of the teacher
     */
    public void deleteVocabularyExam(Integer examId, Integer teacherId) {
        examService.removeVocabularyExam(teacherId, examId);
    }

    /**
     * Displays the results of all students for a specific vocabulary exam, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void showResultsOfAllStudentsOnVocabularyExam(Integer teacherId) {
        examService.showResultsOfAllStudentsOnVocabularyExam(teacherId);
    }

    /**
     * Displays the results of all students for a specific grammar exam, accessible by the teacher.
     *
     * @param teacherId the ID of the teacher
     */
    public void showResultsOfAllStudentsOnGrammarExam(Integer teacherId) {
        examService.showResultsOfAllStudentsOnGrammarExam(teacherId);
    }

    /**
     * Changes a teacher's access to a specific exam.
     *
     * @param teacherId the ID of the teacher
     * @param examId the ID of the exam
     */
    public void changeTeacherAccessToExam(Integer teacherId, Integer examId){
        examService.changeTeacherAccessToExam(teacherId, examId);
    }
}
