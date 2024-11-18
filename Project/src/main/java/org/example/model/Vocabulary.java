package org.example.model;
import java.util.Map;
import java.util.HashMap;

public class Vocabulary extends Course{
    Map <String, String> worter=new HashMap<>();
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
