package org.example.repo;
import org.example.model.Reading;
import org.example.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;
public class VocabRepository implements IRepository<Vocabulary> {
    private List<Vocabulary> vocabCourses;
    private static VocabRepository instance;
    public VocabRepository() {
        this.vocabCourses=new ArrayList<>();
    }

    @Override
    public List<Vocabulary> getObjects(){
        return vocabCourses;
    }

    @Override
    public void save(Vocabulary entity) {
        vocabCourses.add(entity);
    }

    @Override
    public void update(Vocabulary entity, Vocabulary VocabularyRepl) {
        int index = vocabCourses.indexOf(entity);
        if (index != -1) {
            vocabCourses.set(index, VocabularyRepl);
        }
    }

    @Override
    public void delete(Vocabulary object) {
        vocabCourses.remove(object);
    }

    public Vocabulary getById(Integer id){
        for (Vocabulary vocab : vocabCourses) {
            if (vocab.getId() == id)
                return vocab;
        }
        return null;
    }

    public static VocabRepository getInstance() {
        if (instance == null) {
            instance = new VocabRepository();
        }
        return instance;
    }
}
