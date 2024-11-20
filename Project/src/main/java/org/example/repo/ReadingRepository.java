package org.example.repo;
import org.example.model.Reading;
import org.example.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Reading} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 */
public class ReadingRepository implements IRepository<Reading> {

    /**
     * A list of {@link Reading} objects managed by the repository.
     */
    private List<Reading> readingCourses;

    /**
     * Singleton instance of the {@code ReadingRepository}.
     */
    private static ReadingRepository instance;

    /**
     * Constructs a new {@code ReadingRepository}.
     * Initializes the internal list for storing {@link Reading} objects.
     */
    public ReadingRepository() {
        this.readingCourses=new ArrayList<>();
    }

    /**
     * Retrieves all reading courses managed by the repository.
     *
     * @return a {@code List} of all {@link Reading} objects
     */
    @Override
    public List<Reading> getObjects(){
        return readingCourses;
    }

    /**
     * Saves a new {@link Reading} object to the repository.
     *
     * @param entity the {@link Reading} object to be saved
     */
    @Override
    public void save(Reading entity) {
        readingCourses.add(entity);
    }

    /**
     * Updates an existing {@link Reading} object in the repository.
     * Replaces the target {@code entity} with the given {@code ReadingRepl}.
     *
     * @param entity      the {@link Reading} object to be updated
     * @param ReadingRepl the {@link Reading} object to replace the target entity
     */
    @Override
    public void update(Reading entity, Reading ReadingRepl) {
        int index = readingCourses.indexOf(entity);
        if (index != -1) {
            readingCourses.set(index, ReadingRepl);
        }
    }

    /**
     * Deletes a {@link Reading} object from the repository.
     *
     * @param object the {@link Reading} object to be deleted
     */
    @Override
    public void delete(Reading object) {
        readingCourses.remove(object);
    }

    /**
     * Retrieves a {@link Reading} object by its unique identifier.
     *
     * @param id the unique ID of the {@link Reading} object
     * @return the {@link Reading} object with the specified ID, or {@code null} if not found
     */
    public Reading getById(Integer id){
        for (Reading reading : readingCourses) {
            if (reading.getId() == id)
                return reading;
        }
        return null;
    }

    public static ReadingRepository getInstance() {
        if (instance == null) {
            instance = new ReadingRepository();
        }
        return instance;
    }
}
