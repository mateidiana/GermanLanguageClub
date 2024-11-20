package org.example.repo;
import org.example.model.Reading;
import org.example.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Vocabulary} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 * This class ensures that there is only one instance of the repository
 * throughout the application lifecycle.
 */
public class VocabRepository implements IRepository<Vocabulary> {

    /**
     * A list of {@link Vocabulary} objects managed by the repository.
     * This list holds all vocabulary courses in memory.
     */
    private List<Vocabulary> vocabCourses;

    /**
     * A static instance of the {@link VocabRepository} for the singleton pattern.
     * Ensures only one instance of the repository exists.
     */
    private static VocabRepository instance;

    /**
     * Constructs a new {@link VocabRepository} with an empty list of vocabulary courses.
     * This constructor is private to ensure the singleton pattern.
     */
    public VocabRepository() {
        this.vocabCourses=new ArrayList<>();
    }

    /**
     * Retrieves all {@link Vocabulary} objects managed by the repository.
     *
     * @return A list of all {@link Vocabulary} objects in the repository.
     */
    @Override
    public List<Vocabulary> getObjects(){
        return vocabCourses;
    }

    /**
     * Saves a new {@link Vocabulary} object to the repository.
     *
     * @param entity The {@link Vocabulary} object to be saved.
     */
    @Override
    public void save(Vocabulary entity) {
        vocabCourses.add(entity);
    }

    /**
     * Updates an existing {@link Vocabulary} object in the repository.
     * The method will replace the given {@link Vocabulary} object with a new one.
     *
     * @param entity The {@link Vocabulary} object to be updated.
     * @param VocabularyRepl The new {@link Vocabulary} object to replace the old one.
     */
    @Override
    public void update(Vocabulary entity, Vocabulary VocabularyRepl) {
        int index = vocabCourses.indexOf(entity);
        if (index != -1) {
            vocabCourses.set(index, VocabularyRepl);
        }
    }

    /**
     * Deletes a {@link Vocabulary} object from the repository.
     *
     * @param object The {@link Vocabulary} object to be deleted.
     */
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
