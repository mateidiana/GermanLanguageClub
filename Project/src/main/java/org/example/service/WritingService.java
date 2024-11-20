package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.example.model.Reading;
import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;
import org.example.service.*;
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
    private TeacherRepository teacherRepo;
    public WritingService(WritingRepository writingRepo, StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.writingRepo = writingRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo=teacherRepo;
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
            System.out.println(toBeGraded);
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

    public void viewCourseTaughtByTeacher(Integer teacherId){
        Teacher teacher=teacherRepo.getById(teacherId);
        for(Writing course:writingRepo.getObjects())
            if (course.getTeacher().getId()==teacherId)
                System.out.println(course.getCourseName());
    }
    public void createOrUpdateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        int found = 0;
        for (Writing course : writingRepo.getObjects()) {
            if (course.getId() == courseId) {
                found = 1;
                updateWritingCourse(courseId, teacherId, courseName, maxStudents);
                return;
            }
        }
        if (found == 0) {
            createWritingCourse(courseId, teacherId, courseName, maxStudents);
        }
    }

    public void createWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Teacher teacher = teacherRepo.getById(teacherId);
        Writing w1 = new Writing(courseId, courseName, teacher, maxStudents);
        writingRepo.save(w1);
    }

    public void updateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Writing course = writingRepo.getById(courseId);
        Teacher teacher = teacherRepo.getById(teacherId);
        Writing w1 = new Writing(courseId, courseName, teacher, maxStudents);
        writingRepo.update(course, w1);
    }

    public void removeCourse(Integer courseId, Integer teacherId) {
        Writing course = writingRepo.getById(courseId);
        if (course.getTeacher().getId() == teacherId) {
            writingRepo.delete(course);
        } else {
            System.out.println("You don't have access to this course!");
        }
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
    public void gradeFeedback(Integer teacherId, Integer courseId){
        Teacher teacher= teacherRepo.getById(teacherId);
        Writing course= writingRepo.getById(courseId);
        Scanner scanner=new Scanner(System.in);
        Map<Student, String> toGrade=teacher.getFeedbackWriting();
        System.out.println(toGrade);
        while (!toGrade.isEmpty()) {
            Map.Entry<Student, String> entry = toGrade.entrySet().iterator().next();
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
            System.out.println("Input grade: ");
            float grade=scanner.nextFloat();
            Map<Integer, Float> results=key.getWritingFeedback();
            results.put(courseId, grade);
            key.setWritingFeedback(results);
            toGrade.remove(key);
        }
}

    public void changeTeacherAccessToWritingCourse(Integer courseId, Integer teacherId){
        Writing course=writingRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    public void getTeacherById(Integer teacherId){
        Teacher teacher=teacherRepo.getById(teacherId);
        System.out.println(teacher);
    }

}


