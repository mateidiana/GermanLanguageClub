package org.example.model;

import java.util.ArrayList;
import java.util.List;
public class Teacher extends Person {

    private List<Course> teaches;
    public Teacher(String name, Integer teacherId) {
        super(teacherId, name);
    }

    public List<Course> getTaughtCourses() {
        return teaches;
    }

    public void setTaughtCourses(List<Course> teaches) {
        this.teaches = teaches;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
