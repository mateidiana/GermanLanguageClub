package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class Teacher extends Person {

    private List<Course> teaches;
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
