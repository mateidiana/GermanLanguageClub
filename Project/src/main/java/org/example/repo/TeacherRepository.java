package org.example.repo;
import org.example.model.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements IRepository<Teacher>{
    private List<Teacher> teachers;
    private static TeacherRepository instance;
    private TeacherRepository() {
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
