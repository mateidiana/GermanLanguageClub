package org.example.controller;
import org.example.service.ReadingService;
import org.example.model.Exceptions.*;
import org.example.model.*;

import java.util.List;

public class ReadingController {
    private ReadingService readingService;

    public ReadingController(ReadingService readingService){this.readingService=readingService;}

    public Reading getReadingById(int id){return readingService.getReadingById(id);}

    public Question getQuestionById(int id){return readingService.getQuestionById(id);}

    public void enroll(int studentId, int courseId){readingService.enroll(studentId,courseId);}

    public List<Reading> showEnrolledReadingCourses(int studentId){return readingService.showEnrolledReadingCourses(studentId);}

    public List<Question> practiceReading(int studentId, int courseId){return readingService.practiceReading(studentId,courseId);}

    public String handleAnswer(int studentId, int questionId, String answer){return readingService.handleAnswer(studentId,questionId,answer);}

    public List<Question> practicePastMistakes(int studentId){return readingService.practicePastMistakes(studentId);}

    public String handlePastMistakesAnswer(int studentId, int questionId, String answer){return readingService.handlePastMistakesAnswer(studentId,questionId,answer);}

    public List<Reading> getAvailableReadingCourses(){return readingService.getAvailableReadingCourses();}

    public List<Student> getAllStudents(){return readingService.getAllStudents();}

    public List<Student> getEnrolledStudents(int courseId){return readingService.getEnrolledStudents(courseId);}

    public List<Student> showStudentsEnrolledInReadingCourses(){return readingService.showStudentsEnrolledInReadingCourses();}

    public boolean removeCourse(int courseId, int teacherId){return readingService.removeCourse(courseId,teacherId);}

    public void setReadingText(int courseId, String title, String author, String text){readingService.setReadingText(courseId,title,author,text);}

    public void createQuestion(int courseId, String question, String rightAnswer){readingService.createQuestion(courseId,question,rightAnswer);}

    public void createOrUpdateReadingCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        readingService.createOrUpdateReadingCourse(courseId,teacherId,courseName,maxStudents);
    }

    public List<Reading> viewReadingCoursesTaughtByTeacher(int teacherId){return readingService.viewReadingCoursesTaughtByTeacher(teacherId);}

    public List<Book> viewMandatoryBooks(int studentId, int courseId){return readingService.viewMandatoryBooks(studentId,courseId);}

    public boolean addMandatoryBook(Integer teacherId, Integer courseId, String title, String author){return readingService.addMandatoryBook(teacherId,courseId,title,author);}

    List<Reading> filterCoursesByAvailableSlots(){return readingService.filterCoursesByAvailableSlots();}

    public List<Book> sortBooksAlphabeticallyByTitle(int studentId,int courseId){return readingService.sortBooksAlphabeticallyByTitle(studentId,courseId);}
}
