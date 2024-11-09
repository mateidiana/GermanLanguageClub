package org.example.model;
import java.util.ArrayList;
import java.util.List;
public class Course {
    private Integer id;
    private String courseName;
    private Teacher teacher;
    private List<Student> enrolledStudents;
    private Integer availableSlots;
    private String[][] exercises;

    public Course(Integer id, String courseName, Teacher teacher, Integer maxStudents) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.availableSlots = maxStudents;
        this.enrolledStudents = new ArrayList<>();
        this.exercises=new String[100][100];
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String[][] getExercises() {
        return exercises;
    }

    public void setExercises(String[][] exercises) {
        this.exercises=exercises;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teacher=" + teacher +
                ", availableSlots=" + availableSlots +
                '}';
    }
}
