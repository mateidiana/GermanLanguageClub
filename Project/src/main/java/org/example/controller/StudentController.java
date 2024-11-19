package org.example.controller;
import org.example.service.StudentService;
import org.example.service.TeacherService;

public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    public void createStudent(Integer studentId, String name){
        studentService.createStudent(studentId,name);
    }
}
