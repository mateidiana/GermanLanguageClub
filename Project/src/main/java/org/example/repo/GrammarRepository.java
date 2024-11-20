package org.example.repo;
import org.example.model.Grammar;
import org.example.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Grammar} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 */
public class GrammarRepository implements IRepository<Grammar> {

    /**
     * A list of {@link Grammar} objects managed by the repository.
     */
    private List<Grammar> grammarCourses;

    /**
     * Singleton instance of the {@code GrammarRepository}.
     */
    private static GrammarRepository instance;

    /**
     * Constructs a new {@code GrammarRepository}.
     * Initializes the internal list for storing {@link Grammar} objects.
     */
    public GrammarRepository() {
        this.grammarCourses=new ArrayList<>();
    }

    /**
     * Retrieves all grammar courses managed by the repository.
     *
     * @return a {@code List} of all {@link Grammar} objects
     */
    @Override
    public List<Grammar> getObjects(){
        return grammarCourses;
    }

    /**
     * Saves a new {@link Grammar} object to the repository.
     *
     * @param entity the {@link Grammar} object to be saved
     */
    @Override
    public void save(Grammar entity) {
        grammarCourses.add(entity);
    }

    /**
     * Updates an existing {@link Grammar} object in the repository.
     * Replaces the target {@code entity} with the given {@code grammarRepl}.
     *
     * @param entity      the {@link Grammar} object to be updated
     * @param grammarRepl the {@link Grammar} object to replace the target entity
     */
    @Override
    public void update(Grammar entity, Grammar grammarRepl) {
        int index = grammarCourses.indexOf(entity);
        if (index != -1) {
            grammarCourses.set(index, grammarRepl);
        }
    }

    /**
     * Retrieves a {@link Grammar} object by its unique identifier.
     *
     * @param id the unique ID of the {@link Grammar} object
     * @return the {@link Grammar} object with the specified ID, or {@code null} if not found
     */
    public Grammar getById(Integer id){
        for (Grammar gram : grammarCourses) {
            if (gram.getId() == id)
                return gram;
        }
        return null;
    }

    /**
     * Deletes a {@link Grammar} object from the repository.
     *
     * @param object the {@link Grammar} object to be deleted
     */
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
