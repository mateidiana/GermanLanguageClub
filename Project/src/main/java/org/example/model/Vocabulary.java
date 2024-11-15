package org.example.model;

public class Vocabulary extends Course{
    public Vocabulary(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    @Override
    public String toString() {
        return "Vocabulary course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
