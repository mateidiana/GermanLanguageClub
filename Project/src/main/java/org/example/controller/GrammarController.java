package org.example.controller;
import org.example.service.GrammarService;
import org.example.model.Exceptions.*;
import org.example.model.*;

import java.util.List;

public class GrammarController {
    private GrammarService grammarService;

    public GrammarController(GrammarService grammarService){this.grammarService=grammarService;}

    public void enroll(int studentId, int courseId){grammarService.enroll(studentId,courseId);}

    public List<Grammar> showEnrolledGrammarCourses(int studentId){return grammarService.showEnrolledGrammarCourses(studentId);}

    public List<Question> practiceGrammar(int studentId, int courseId){return grammarService.practiceGrammar(studentId,courseId);}

    public String handleAnswer(int studentId, int questionId, String answer){return grammarService.handleAnswer(studentId,questionId,answer);}

    public List<Question> practicePastMistakes(int studentId){return grammarService.practicePastMistakes(studentId);}

    public String handlePastMistakesAnswer(int studentId, int questionId, String answer){return grammarService.handlePastMistakesAnswer(studentId,questionId,answer);}

    public List<Grammar> getAvailableGrammarCourses(){return grammarService.getAvailableGrammarCourses();}

    public List<Student> getAllStudents(){return grammarService.getAllStudents();}

    public List<Student> getEnrolledStudents(int courseId){return grammarService.getEnrolledStudents(courseId);}

    public List<Student> showStudentsEnrolledInGrammarCourses(){return grammarService.showStudentsEnrolledInGrammarCourses();}

    public boolean removeCourse(int courseId, int teacherId){return grammarService.removeCourse(courseId,teacherId);}

    public void createQuestion(int courseId, String question, String rightAnswer){grammarService.createQuestion(courseId,question,rightAnswer);}

    public void createOrUpdateGrammarCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        grammarService.createOrUpdateGrammarCourse(courseId,teacherId,courseName,maxStudents);
    }

    public List<Grammar> viewGrammarCoursesTaughtByTeacher(int teacherId){return grammarService.viewGrammarCoursesTaughtByTeacher(teacherId);}

}
