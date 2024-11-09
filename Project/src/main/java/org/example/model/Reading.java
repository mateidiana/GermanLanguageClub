package org.example.model;
import java.util.ArrayList;
import java.util.List;
public class Reading extends Course{

    private List<String> mandatoryBooks = new ArrayList<>();
    public Reading(Integer id, String courseName, Teacher teacher, Integer maxStudents)
    {
        super(id, courseName, teacher, maxStudents);
    }

    public List<String> getMandatoryBooks() {
        return mandatoryBooks;
    }

    public void setMandatoryBooks(List<String> mandatoryBooks) {
        this.mandatoryBooks=mandatoryBooks;
    }

    @Override
    public String toString() {
        return "Reading course{" +
                "id=" + this.getId() +
                ", name='" + this.getCourseName() + '\'' +
                '}';
    }
}
