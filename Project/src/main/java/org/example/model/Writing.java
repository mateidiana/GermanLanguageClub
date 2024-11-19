package org.example.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Writing} class represents a specialized type of {@code Course} focused on writing skills.
 * It extends the {@code Course} class and introduces a specific requirement for writing assignments.
 */
public class Writing extends Course {

    /**
     * Constructs a new {@code Writing} course with the specified ID, name, teacher,
     * and maximum number of students.
     *
     * @param id          The unique identifier for the writing course.
     * @param courseName  The name of the writing course.
     * @param teacher     The teacher assigned to the writing course.
     * @param maxStudents The maximum number of students who can enroll in the course.
     */
    public Writing(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    /**
     * Gets the static text requirement for writing assignments.
     * return The text requirement for writing assignments.
     */
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
