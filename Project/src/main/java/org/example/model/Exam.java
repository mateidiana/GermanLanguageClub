package org.example.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Exam} class represents an exam, including its ID, name, teacher,
 * list of examined students, exercises, vocabulary (Worter), and student results.
 * It provides methods to manage and access these properties.
 */
public class Exam {
    private Integer id;
    private String examName;
    private Teacher teacher;
    private List<Student> examinedStudents;
    private String[][] exercises;
    Map <String, String> worter=new HashMap<>();
    Map<Student,Float> results = new HashMap<>();
    static String textRequirement;

    /**
     * Constructs a new {@code Exam} with the specified ID, name, and teacher.
     * Initializes the list of examined students, exercises, and results.
     *
     * @param id        The unique identifier for the exam.
     * @param examName  The name of the exam.
     * @param teacher   The teacher administering the exam.
     */
    public Exam(Integer id, String examName, Teacher teacher) {
        this.id = id;
        this.examName = examName;
        this.teacher = teacher;
        this.examinedStudents = new ArrayList<>();
        this.exercises=new String[100][100];
        this.results=new HashMap<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getExaminedStudents() {
        return examinedStudents;
    }

    public void setExaminedStudents(List<Student> examinedStudents) {
        this.examinedStudents = examinedStudents;
    }

    /**
     * Gets the results of the exam.
     *
     * @return A map of students and their corresponding scores.
     */
    public Map<Student,Float> getResults() {
        return results;
    }

    /**
     * Sets the results of the exam.
     *
     * @param results A map of students and their scores.
     */
    public void setResults(Map<Student,Float> results) {
        this.results = results;
    }

    /**
     * Gets the exercises for the reading and grammar exam.
     *
     * @return A two-dimensional array of exercises.
     */
    public String[][] getExercises() {
        return exercises;
    }

    /**
     * Sets the exercises for the reading and grammar exam.
     *
     * @param exercises A two-dimensional array of new exercises.
     */
    public void setExercises(String[][] exercises) {
        this.exercises=exercises;
    }

    /**
     * Gets the vocabulary (Worter) for the exam.
     *
     * @return A map of vocabulary words and their meanings or translations.
     */
    public Map<String, String> getWorter() {
        return worter;
    }

    /**
     * Sets the vocabulary (Worter) for the exam.
     *
     * @param worter A map of vocabulary words and their meanings or translations.
     */
    public void setWorter(Map<String, String> worter) {
        this.worter = worter;
    }
    public void addWort(String key, String value) {
        this.worter.put(key, value);
    }

    /**
     * Gets the static text requirement for the writing exam.
     *
     * @return The requirement text.
     */
    public static String getRequirement(){ return textRequirement;}

    /**
     * Sets the static text requirement for the writing exam.
     *
     * @param text The new requirement text.
     */
    public void setRequirement(String text) {this.textRequirement=text;}

    /**
     * Returns a string representation of the exam, including the ID, name, and teacher.
     *
     * @return A string representation of the exam.
     */
    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", courseName='" + examName + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
