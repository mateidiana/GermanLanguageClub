package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
        StringBuilder answer = new StringBuilder();

        int foundCourse=0;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1) {
            String exercise=course.getRequirement();
            System.out.println(exercise);
            String input;


            System.out.println("Enter text (type 0 to stop):");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("Exiting...");
                    break;
                }
                answer.append(input).append("\n");
            }
            Map <Student, String> toBeGraded=course.getTeacher().getGradeWriting();
            toBeGraded.put(student, answer.toString());
            course.getTeacher().setGradeWriting(toBeGraded);
            System.out.println("Writing exercise submitted!!!!!");
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
    public void showEnrolledWritingCourses(Integer studentId){
        Student student=studentRepo.getById(studentId);
        for (Course course:student.getCourses())
            if (course.getCourseName().contains("Writing"))
                System.out.println(course);
    }

    public void showFeedback(Integer studentId){
        Student student=studentRepo.getById(studentId);
        Map<Integer, Float> writingExamResults=new HashMap<>();
        writingExamResults=student.getWritingExamResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : writingExamResults.entrySet()) {
            System.out.println("Writing exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }
    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }
}
