package org.example.repo;

import java.util.List;

/**
 * A generic repository interface that defines basic CRUD (Create, Read, Update, Delete) operations
 * for managing objects of type {@code T}.
 *
 * @param <T> the type of objects managed by this repository
 */
public interface IRepository<T> {

    /**
     * Retrieves all objects managed by the repository.
     *
     * @return a {@code List} of all objects of type {@code T}
     */
    List<T> getObjects();

    /**
     * Saves a new object to the repository.
     *
     * @param entity the object of type {@code T} to be saved
     */
    void save(T entity);

    /**
     * Updates an existing object in the repository based on the given action.
     *
     * @param entity the original object of type {@code T} to be updated
     * @param action the new object of type {@code T} representing the update action
     */
    void update(T entity, T action);

    /**
     * Deletes an object from the repository.
     *
     * @param object the object of type {@code T} to be deleted
     */
    void delete(T object);
}
