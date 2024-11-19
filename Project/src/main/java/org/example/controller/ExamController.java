package org.example.controller;
import org.example.service.ExamService;

public class ExamController {
    private ExamService examService;

    public ExamController(ExamService examService){
        this.examService=examService;
    }

    public void takeReadingExam(Integer studentId, Integer examId){
        examService.takeReadingExam(studentId,examId);
    }

    public void takeGrammarExam(Integer studentId, Integer examId){
        examService.takeGrammarExam(studentId,examId);
    }

    public void takeVocabExam(Integer studentId, Integer examId){
        examService.takeVocabExam(studentId,examId);
    }

    public void showReadingResults(Integer studentId){examService.showReadingResults(studentId);}

    public void showAllReadingExams(){examService.showAllReadingExams();}

    public void showGrammarResults(Integer studentId){examService.showGrammarResults(studentId);}

    public void showVocabResults(Integer studentId){examService.showVocabResults(studentId);}

    public void takeWritingExam(Integer studentId, Integer examId){
        examService.takeWritingExam(studentId,examId);
    }

    public void showWritingResults(Integer studentId){examService.showWritingResults(studentId);}

    public void showResultsOfAllStudentsOnReadingExam(Integer teacherId){
        examService.showResultsOfAllStudentsOnReadingExam(teacherId);
    }

    public void createOrUpdateReadingExam(Integer courseId, Integer teacherId, String courseName){
        examService.createOrUpdateReadingExam(courseId,teacherId,courseName);
    }

    public void deleteReadingExam(Integer examId, Integer teacherId) {
        examService.removeReadingExam(examId,teacherId);
        //System.out.println("Removed course " + courseId);
    }
}
