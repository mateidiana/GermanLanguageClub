package org.example.model;

public class Grammar extends Course{
    public Grammar(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    @Override
    public String toString() {
        return "Grammar course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
