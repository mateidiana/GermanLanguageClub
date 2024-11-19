package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * The {@code Teacher} class represents a teacher who can teach multiple courses
 * and provide grades and feedback for writing assignments. This class extends
 * the {@code Person} class, inheriting its properties and methods.
 */
public class Teacher extends Person {

    private List<Course> teaches;

    /**
     * Constructs a new {@code Teacher} with the specified name and ID.
     *
     * @param name      The name of the teacher.
     * @param teacherId The unique identifier for the teacher.
     */
    public Teacher(String name, Integer teacherId) {
        super(teacherId, name);
    }

    public List<Course> getTaughtCourses() {
        return teaches;
    }

    public void setTaughtCourses(List<Course> teaches) {
        this.teaches = teaches;
    }

    private Map<Student, String> gradeWriting=new HashMap<>();
    private Map<Student, String> feedbackWriting=new HashMap<>();
    public Map<Student, String> getGradeWriting() {
        return gradeWriting;
    }

    public void setGradeWriting(Map<Student, String> gradeWriting) {
            this.gradeWriting = gradeWriting;

    }
    public Map<Student, String> getFeedbackWriting() {
        return feedbackWriting;
    }

    public void setFeedbackWriting(Map<Student, String> feedbackWriting) {
        if (feedbackWriting != null) {
            this.feedbackWriting = feedbackWriting;
        } else {
            throw new IllegalArgumentException("feedbackWriting map cannot be null.");
        }
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
