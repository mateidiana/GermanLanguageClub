package org.example.controller;
import org.example.service.TeacherService;

public class TeacherController {
    private TeacherService teacherService;
    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    public void createTeacher(Integer teacherId, String name){
        teacherService.createTeacher(teacherId,name);
    }
}
