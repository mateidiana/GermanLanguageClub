package org.example.repo;
import org.example.model.Grammar;

import java.util.ArrayList;
import java.util.List;

public class GrammarRepository implements IRepository<Grammar> {
    private List<Grammar> grammarCourses;
    private static GrammarRepository instance;
    private GrammarRepository() {
        this.grammarCourses=new ArrayList<>();
    }

    @Override
    public List<Grammar> getObjects(){
        return grammarCourses;
    }

    @Override
    public void save(Grammar entity) {
        grammarCourses.add(entity);
    }

    @Override
    public void update(Grammar entity, Grammar grammarRepl) {
        int index = grammarCourses.indexOf(entity);
        if (index != -1) {
            grammarCourses.set(index, grammarRepl);
        }
    }

    @Override
    public void delete(Grammar object) {
        grammarCourses.remove(object);
    }

    public static GrammarRepository getInstance() {
        if (instance == null) {
            instance = new GrammarRepository();
        }
        return instance;
    }
}
