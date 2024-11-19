package org.example.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Reading} class represents a specialized type of {@code Course} focused on reading.
 * It extends the {@code Course} class and adds specific features related to reading,
 * such as a list of mandatory books required for the course.
 */
public class Reading extends Course{

    /** A list of mandatory books that students are required to read for the course */
    private List<String> mandatoryBooks = new ArrayList<>();

    /**
     * Constructs a new {@code Reading} course with the specified ID, name, teacher,
     * and maximum number of students.
     *
     * @param id          The unique identifier for the reading course.
     * @param courseName  The name of the reading course.
     * @param teacher     The teacher assigned to the reading course.
     * @param maxStudents The maximum number of students who can enroll in the course.
     */
    public Reading(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    /**
     * Gets the list of mandatory books required for the reading course.
     *
     * @return A list of mandatory book titles.
     */
    public List<String> getMandatoryBooks() {
        return mandatoryBooks;
    }

    /**
     * Sets the list of mandatory books required for the reading course.
     *
     * @param mandatoryBooks A list of new mandatory book titles.
     */
    public void setMandatoryBooks(List<String> mandatoryBooks) {
        this.mandatoryBooks=mandatoryBooks;
    }

    /**
     * Returns a string representation of the reading course, including the course ID and name.
     *
     * @return A string representation of the reading course.
     */
    @Override
    public String toString() {
        return "Reading course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
