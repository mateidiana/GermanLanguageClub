package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.*;
import org.example.model.Vocabulary;
import org.example.repo.VocabRepository;
import org.example.repo.VocabRepository;
import org.example.repo.StudentRepository;
public class VocabService {
    private VocabRepository vocabRepo;

    private StudentRepository studentRepo;

    public VocabService(VocabRepository vocabRepo, StudentRepository studentRepo) {
        this.vocabRepo = vocabRepo;
        this.studentRepo = studentRepo;
    }

    public void enroll(Integer studentId, Integer vocabCourseId) {
        Student student = studentRepo.getById(studentId);
        Vocabulary course = vocabRepo.getById(vocabCourseId);
        studentRepo.delete(student);
        vocabRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            vocabRepo.save(course);
            studentRepo.save(student);
        }
    }
    public void practiceVocabulary(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Vocabulary course = vocabRepo.getById(courseId);

        Scanner scanner = new Scanner(System.in);

        int foundCourse=0;
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");



    }

    public List<Vocabulary> getAvailableCourses() {
        return vocabRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Vocabulary course = vocabRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }

    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
}
