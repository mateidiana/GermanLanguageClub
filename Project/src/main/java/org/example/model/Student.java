package org.example.model;

import java.util.ArrayList;
import java.util.List;
public class Student extends Person {
    private final List<Course> courses = new ArrayList<>();
    private String[][] pastMistakes;

    public Student(String name, Integer studentId) {
        super(studentId, name);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String[][] getPastMistakes() {
        return pastMistakes;
    }

    public void setPastMistakes(String[][] pastMistakes){
        this.pastMistakes=pastMistakes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
