package org.example.service;

import java.util.List;

import org.example.model.Reading;
import org.example.model.Student;
import org.example.repo.ReadingRepository;
import org.example.repo.StudentRepository;

public class ReadingService {

    private ReadingRepository readingRepo;

    private StudentRepository studentRepo;

    public ReadingService(ReadingRepository readingRepo, StudentRepository studentRepo) {
        this.readingRepo = readingRepo;
        this.studentRepo = studentRepo;
    }

    public void enroll(Integer studentId, Integer readingCourseId) {
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(readingCourseId);
        studentRepo.delete(student);
        readingRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            readingRepo.save(course);
            studentRepo.save(student);
        }
    }

    public List<Reading> getAvailableCourses() {
        return readingRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Reading course = readingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
//        courseRepo.get(courseId).getEnrolledStudents().forEach(student -> {
//            student.getCourses().removeIf(course -> course.getId().equals(courseId));
//            studentRepo.update(student);
//        });
//        courseRepo.delete(courseId);
    }

    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
}
