package org.example.repo;
import org.example.model.Student;
import org.example.model.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements IRepository<Teacher>{
    private List<Teacher> teachers;
    private static TeacherRepository instance;
    public TeacherRepository() {
        this.teachers=new ArrayList<>();
    }

    @Override
    public List<Teacher> getObjects(){
        return teachers;
    }

    @Override
    public void save(Teacher entity) {
        teachers.add(entity);
    }

    @Override
    public void update(Teacher entity, Teacher TeacherRepl) {
        int index = teachers.indexOf(entity);
        if (index != -1) {
            teachers.set(index, TeacherRepl);
        }
    }

    public Teacher getById(Integer id){
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id)
                return teacher;
        }
        return null;
    }

    @Override
    public void delete(Teacher object) {
        teachers.remove(object);
    }

    public static TeacherRepository getInstance() {
        if (instance == null) {
            instance = new TeacherRepository();
        }
        return instance;

    }
}
