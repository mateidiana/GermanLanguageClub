package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class Student extends Person {
    private final List<Course> courses = new ArrayList<>();
    private String[][] pastMistakes;
    private String[][] pastGrammarMistakes;
    private Map<String, String> pastVocabMistakes=new HashMap<>();

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

    public String[][] getPastGrammarMistakes() {
        return pastGrammarMistakes;
    }

    public void setPastGrammarMistakes(String[][] pastGrammarMistakes){
        this.pastGrammarMistakes=pastGrammarMistakes;
    }



    public Map<String, String> getPastVocabMistakes(){return pastVocabMistakes;}

    public void setPastVocabMistakes(Map<String, String> vocabs){this.pastVocabMistakes=vocabs;}
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
