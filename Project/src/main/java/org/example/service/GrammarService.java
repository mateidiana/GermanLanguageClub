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

        Scanner scanner = new Scanner(System.in);

        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");



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
