package org.example.model;

import java.util.ArrayList;
import java.util.List;
public class Teacher extends Person {
    public Teacher(String name, Integer teacherId) {
        super(teacherId, name);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
