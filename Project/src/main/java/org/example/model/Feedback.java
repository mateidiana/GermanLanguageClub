package org.example.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feedback {
    private Integer id;
    private Teacher teacher;
    private Student student;
    private String feedback;

    public Feedback(Integer id, Teacher teacher, Student student, String feedback) {
        this.id=id;
        this.teacher=teacher;
        this.student=student;
        this.feedback=feedback;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
