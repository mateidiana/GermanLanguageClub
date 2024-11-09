package org.example.model;
import java.util.ArrayList;
import java.util.List;
public class Writing extends Course {
    public Writing(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    @Override
    public String toString() {
        return "Writing course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
