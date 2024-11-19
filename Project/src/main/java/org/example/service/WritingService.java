package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.example.model.*;
import org.example.repo.WritingRepository;
import org.example.repo.WritingRepository;
import org.example.repo.StudentRepository;
public class WritingService {
    private WritingRepository writingRepo;

    private StudentRepository studentRepo;

    public WritingService(WritingRepository writingRepo, StudentRepository studentRepo) {
        this.writingRepo = writingRepo;
        this.studentRepo = studentRepo;
    }

    public void enroll(Integer studentId, Integer writingCourseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(writingCourseId);
        studentRepo.delete(student);
        writingRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            writingRepo.save(course);
            studentRepo.save(student);
        }
    }
    public void practiceWriting(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(courseId);

        Scanner scanner = new Scanner(System.in);

        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");
        else{

        }


    }
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
    public List<Writing> getAvailableCourses() {
        return writingRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Writing course = writingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }
}
