package org.example.model;
import java.util.ArrayList;
import java.util.List;
public class Writing extends Course {
    public Writing(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }
    static String textRequirement;
    @Override
    public String toString() {
        return "Writing course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
    public static String getRequirement(){ return textRequirement;}
    public void setRequirement(String text) {this.textRequirement=text;}
}
