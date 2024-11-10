package org.example.repo;
import org.example.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IRepository<Student> {
    private List<Student> students;
    private static StudentRepository instance;
    public StudentRepository() {
        this.students=new ArrayList<>();
    }

    @Override
    public List<Student> getObjects(){
        return students;
    }

    @Override
    public void save(Student entity) {
        students.add(entity);
    }

    @Override
    public void update(Student entity, Student StudentRepl) {
        int index = students.indexOf(entity);
        if (index != -1) {
            students.set(index, StudentRepl);
        }
    }

    @Override
    public void delete(Student object) {
        students.remove(object);
    }

    public Student getById(Integer id){
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }
}
