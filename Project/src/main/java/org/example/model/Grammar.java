package org.example.model;

/**
 * The {@code Grammar} class represents a specialized type of {@code Course} focused on grammar.
 * It extends the {@code Course} class, inheriting its properties and methods.
 * This class customizes the {@code Course} class to represent a grammar-specific course.
 */
public class Grammar extends Course{

    /**
     * Constructs a new {@code Grammar} course with the specified ID, name, teacher,
     * and maximum number of students.
     *
     * @param id          The unique identifier for the grammar course.
     * @param courseName  The name of the grammar course.
     * @param teacher     The teacher assigned to the grammar course.
     * @param maxStudents The maximum number of students who can enroll in the course.
     */
    public Grammar(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    /**
     * Returns a string representation of the grammar course, including the course ID and name.
     *
     * @return A string representation of the grammar course.
     */
    @Override
    public String toString() {
        return "Grammar course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
