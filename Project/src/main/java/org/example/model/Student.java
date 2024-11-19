package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * The {@code Student} class extends {@code Person} and represents a student who can enroll in courses
 * and maintain a record of various exam results, feedback, and past mistakes.
 * It provides methods to access and manipulate these records.
 */
public class Student extends Person {
    private final List<Course> courses = new ArrayList<>();
    private String[][] pastMistakes;
    private String[][] pastGrammarMistakes;
    private Map<String, String> pastVocabMistakes=new HashMap<>();

    private Map<Integer, Float> readingExamResults=new HashMap<>();

    private Map<Integer, Float> grammarExamResults=new HashMap<>();
    private Map<Integer, Float> vocabExamResults=new HashMap<>();
    private Map<Integer, Float> writingExamResults=new HashMap<>();
    private Map<Integer, Float> writingFeedback=new HashMap<>();
    public Student(String name, Integer studentId) {
        super(studentId, name);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String[][] getPastMistakes() {
        return pastMistakes;
    }

    public void setPastMistakes(String[][] pastMistakes){
        this.pastMistakes=pastMistakes;
    }

    public Map<Integer,Float> getReadingResults() {
        return readingExamResults;
    }

    public void setReadingResults(Map<Integer,Float> results) {
        this.readingExamResults = results;
    }

    public String[][] getPastGrammarMistakes() {
        return pastGrammarMistakes;
    }

    public void setPastGrammarMistakes(String[][] pastGrammarMistakes){
        this.pastGrammarMistakes=pastGrammarMistakes;
    }

    public Map<String, String> getPastVocabMistakes(){return pastVocabMistakes;}

    public void setPastVocabMistakes(Map<String, String> vocabs){this.pastVocabMistakes=vocabs;}

    public Map<Integer, Float> getGrammarResults() {
        return grammarExamResults;
    }

    public void setGrammarResults(Map<Integer, Float> grammarExamResults) {
        if (grammarExamResults != null) {
            this.grammarExamResults = grammarExamResults;
        } else {
            throw new IllegalArgumentException("Grammar exam results cannot be null.");
        }
    }

    public Map<Integer, Float> getVocabResults() {
        return vocabExamResults;
    }

    public void setVocabResults(Map<Integer, Float> vocabExamResults) {
        if (vocabExamResults != null) {
            this.vocabExamResults = vocabExamResults;
        } else {
            throw new IllegalArgumentException("Vocabulary exam results cannot be null.");
        }
    }

    // Getter and Setter for writingExamResults
    public Map<Integer, Float> getWritingExamResults() {
        return writingExamResults;
    }

    public void setWritingExamResults(Map<Integer, Float> writingExamResults) {
        if (writingExamResults != null) {
            this.writingExamResults = writingExamResults;
        } else {
            throw new IllegalArgumentException("writingExamResults map cannot be null.");
        }
    }

    // Getter and Setter for writingFeedback
    public Map<Integer, Float> getWritingFeedback() {
        return writingFeedback;
    }

    public void setWritingFeedback(Map<Integer, Float> writingFeedback) {
        if (writingFeedback != null) {
            this.writingFeedback = writingFeedback;
        } else {
            throw new IllegalArgumentException("writingFeedback map cannot be null.");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
