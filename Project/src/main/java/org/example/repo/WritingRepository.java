package org.example.repo;
import org.example.model.Writing;

import java.util.ArrayList;
import java.util.List;

public class WritingRepository implements IRepository<Writing> {
    private List<Writing> writingCourses;
    private static WritingRepository instance;
    private WritingRepository() {
        this.writingCourses=new ArrayList<>();
    }

    @Override
    public List<Writing> getObjects(){
        return writingCourses;
    }

    @Override
    public void save(Writing entity) {
        writingCourses.add(entity);
    }

    @Override
    public void update(Writing entity, Writing WritingRepl) {
        int index = writingCourses.indexOf(entity);
        if (index != -1) {
            writingCourses.set(index, WritingRepl);
        }
    }

    @Override
    public void delete(Writing object) {
        writingCourses.remove(object);
    }

    public static WritingRepository getInstance() {
        if (instance == null) {
            instance = new WritingRepository();
        }
        return instance;
    }
}
