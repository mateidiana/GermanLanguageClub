package org.example.model;
import java.util.Map;
import java.util.HashMap;

/**
 * The {@code Vocabulary} class represents a specialized type of {@code Course} focused on vocabulary learning.
 * It extends the {@code Course} class and adds specific features to manage a vocabulary list.
 */
public class Vocabulary extends Course{

    /** A map that holds vocabulary words and their meanings */
    Map <String, String> worter=new HashMap<>();

    /**
     * Constructs a new {@code Vocabulary} course with the specified ID, name, teacher,
     * and maximum number of students.
     *
     * @param id          The unique identifier for the vocabulary course.
     * @param courseName  The name of the vocabulary course.
     * @param teacher     The teacher assigned to the vocabulary course.
     * @param maxStudents The maximum number of students who can enroll in the course.
     */
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
    public Map<String, String> getWorter() {
        return worter;
    }

    public void setWorter(Map<String, String> worter) {
        this.worter = worter;
    }
    public void addWort(String key, String value) {
        this.worter.put(key, value);
    }
}
