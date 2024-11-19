package org.example.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Course} class represents a course that has an ID, a name, a teacher,
 * a list of enrolled students, available slots for students, and a set of exercises.
 * It provides methods to manage and retrieve course details.
 */
public class Course {
    private Integer id;
    private String courseName;
    private Teacher teacher;
    private List<Student> enrolledStudents;
    private Integer availableSlots;
    private String[][] exercises;

    /**
     * Constructs a new {@code Course} with the specified ID, name, teacher, and maximum number of students.
     * Initializes the list of enrolled students and the exercises.
     *
     * @param id          The unique identifier for the course.
     * @param courseName  The name of the course.
     * @param teacher     The teacher assigned to the course.
     * @param maxStudents The maximum number of students who can enroll in the course.
     */
    public Course(Integer id, String courseName, Teacher teacher, Integer maxStudents) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.availableSlots = maxStudents;
        this.enrolledStudents = new ArrayList<>();
        this.exercises=new String[100][100];
    }

    /**
     * Sets the ID of the course.
     *
     * @param id The new ID of the course.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the ID of the course.
     *
     * @return The ID of the course.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course.
     *
     * @param courseName The new name of the course.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the teacher assigned to the course.
     *
     * @return The teacher of the course.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher for the course.
     *
     * @param teacher The new teacher for the course.
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Gets the list of students enrolled in the course.
     *
     * @return The list of enrolled students.
     */
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Sets the list of students enrolled in the course.
     *
     * @param enrolledStudents The new list of enrolled students.
     */
    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    /**
     * Gets the number of available slots for additional students.
     *
     * @return The number of available slots.
     */
    public Integer getAvailableSlots() {
        return availableSlots;
    }

    /**
     * Sets the number of available slots for additional students.
     *
     * @param availableSlots The new number of available slots.
     */
    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    /**
     * Gets the exercises for the course.
     *
     * @return A two-dimensional array of exercises.
     */
    public String[][] getExercises() {
        return exercises;
    }

    /**
     * Sets the exercises for the course.
     *
     * @param exercises The new two-dimensional array of exercises.
     */
    public void setExercises(String[][] exercises) {
        this.exercises=exercises;
    }

    /**
     * Returns a string representation of the course, including the ID, name, teacher, and available slots.
     *
     * @return A string representation of the course.
     */
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teacher=" + teacher +
                ", availableSlots=" + availableSlots +
                '}';
    }
}
