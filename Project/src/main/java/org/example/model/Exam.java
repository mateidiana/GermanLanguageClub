package org.example.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Exam {
    private Integer id;
    private String examName;
    private Teacher teacher;
    private List<Student> examinedStudents;
    private String[][] exercises;

    Map<Student,Integer> results = new HashMap<>();

    public Exam(Integer id, String examName, Teacher teacher) {
        this.id = id;
        this.examName = examName;
        this.teacher = teacher;
        this.examinedStudents = new ArrayList<>();
        this.exercises=new String[100][100];
        this.results=new HashMap<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getExaminedStudents() {
        return examinedStudents;
    }

    public void setExaminedStudents(List<Student> examinedStudents) {
        this.examinedStudents = examinedStudents;
    }

    public Map<Student,Integer> getResults() {
        return results;
    }

    public void setResults(Map<Student,Integer> results) {
        this.results = results;
    }

    public String[][] getExercises() {
        return exercises;
    }

    public void setExercises(String[][] exercises) {
        this.exercises=exercises;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", courseName='" + examName + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
