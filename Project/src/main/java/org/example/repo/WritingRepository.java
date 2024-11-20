package org.example.repo;
import org.example.model.Grammar;
import org.example.model.Writing;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Writing} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations (Create, Read, Update, Delete).
 * This class ensures that there is only one instance of the repository throughout the application lifecycle.
 * The repository stores and manages {@link Writing} objects in memory.
 */
public class WritingRepository implements IRepository<Writing> {

    /**
     * A list of {@link Writing} objects managed by the repository.
     * This list holds all writing courses in memory.
     */
    private List<Writing> writingCourses;

    /**
     * A static instance of the {@link WritingRepository} for the singleton pattern.
     * Ensures only one instance of the repository exists.
     */
    private static WritingRepository instance;

    /**
     * Constructs a new {@link WritingRepository} with an empty list of writing courses.
     * This constructor is private to ensure the singleton pattern.
     */
    public WritingRepository() {
        this.writingCourses=new ArrayList<>();
    }

    /**
     * Retrieves all {@link Writing} objects managed by the repository.
     *
     * @return A list of all {@link Writing} objects in the repository.
     */
    @Override
    public List<Writing> getObjects(){
        return writingCourses;
    }

    /**
     * Saves a new {@link Writing} object to the repository.
     *
     * @param entity The {@link Writing} object to be saved.
     */
    @Override
    public void save(Writing entity) {
        writingCourses.add(entity);
    }

    /**
     * Updates an existing {@link Writing} object in the repository.
     * The method will replace the given {@link Writing} object with a new one.
     *
     * @param entity The {@link Writing} object to be updated.
     * @param WritingRepl The new {@link Writing} object to replace the old one.
     */
    @Override
    public void update(Writing entity, Writing WritingRepl) {
        int index = writingCourses.indexOf(entity);
        if (index != -1) {
            writingCourses.set(index, WritingRepl);
        }
    }

    /**
     * Retrieves a {@link Writing} object by its ID.
     *
     * @param id The ID of the {@link Writing} object to be retrieved.
     * @return The {@link Writing} object with the specified ID, or {@code null} if not found.
     */
    public Writing getById(Integer id){
        for (Writing write : writingCourses) {
            if (write.getId() == id)
                return write;
        }
        return null;
    }

    /**
     * Deletes a {@link Writing} object from the repository.
     *
     * @param object The {@link Writing} object to be deleted.
     */
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
