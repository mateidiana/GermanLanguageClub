package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.*;
import org.example.repo.GrammarRepository;
import org.example.repo.StudentRepository;


public class GrammarService {
    private GrammarRepository grammarRepo;

    private StudentRepository studentRepo;

    public GrammarService(GrammarRepository grammarRepo, StudentRepository studentRepo) {
        this.grammarRepo = grammarRepo;
        this.studentRepo = studentRepo;
    }

    public void enroll(Integer studentId, Integer grammarCourseId) {
        Student student = studentRepo.getById(studentId);
        Grammar course = grammarRepo.getById(grammarCourseId);
        studentRepo.delete(student);
        grammarRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            grammarRepo.save(course);
            studentRepo.save(student);
        }
    }
    public void practiceGrammar(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Grammar course = grammarRepo.getById(courseId);
        String exercise;
        String[][] exercises=course.getExercises();
        Scanner scanner = new Scanner(System.in);
        int correctAnswers=0;
        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                break;}
            else if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}}
        if(foundCourse==1){
            System.out.println("PLease fill in the gaps with the correct word:");
            for(int i=1; i<10; i++){
                exercise=exercises[i][0];
                System.out.println("Question "+i+": "+exercise);
                System.out.print("Answer: ");
                String answer=scanner.nextLine();
                if(answer.toLowerCase()==exercises[i][1])
                    correctAnswers++;
            }
            if(correctAnswers>5) System.out.println("You have passed this test with the grade "+correctAnswers+"!");
            else System.out.println("You have failed this test with the grade "+correctAnswers+". Do better, loser");
        }

    }
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
    public List<Grammar> getAvailableCourses() {
        return grammarRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Grammar course = grammarRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }
}
