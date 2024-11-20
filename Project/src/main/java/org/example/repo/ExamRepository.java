package org.example.repo;
import org.example.model.Exam;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Exam} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 */
public class ExamRepository implements IRepository<Exam> {

    /**
     * A list of {@link Exam} objects managed by the repository.
     */
    private List<Exam> exams;

    /**
     * Singleton instance of the {@code ExamRepository}.
     */
    private static ExamRepository instance;

    /**
     * Constructs a new {@code ExamRepository}.
     * Initializes the internal list for storing {@link Exam} objects.
     */
    public ExamRepository() {
        this.exams=new ArrayList<>();
    }

    /**
     * Retrieves all exams managed by the repository.
     *
     * @return a {@code List} of all {@link Exam} objects
     */
    @Override
    public List<Exam> getObjects(){
        return exams;
    }

    /**
     * Saves a new {@link Exam} to the repository.
     *
     * @param entity the {@link Exam} object to be saved
     */
    @Override
    public void save(Exam entity) {
        exams.add(entity);
    }

    /**
     * Updates an existing {@link Exam} in the repository.
     * Replaces the target {@code entity} with the given {@code examRepl}.
     *
     * @param entity   the {@link Exam} object to be updated
     * @param examRepl the {@link Exam} object to replace the target entity
     */
    @Override
    public void update(Exam entity, Exam examRepl) {
        int index = exams.indexOf(entity);
        if (index != -1) {
            exams.set(index, examRepl);
        }
    }

    /**
     * Retrieves an {@link Exam} object by its unique identifier.
     *
     * @param id the unique ID of the {@link Exam}
     * @return the {@link Exam} with the specified ID, or {@code null} if not found
     */
    public Exam getById(Integer id){
        for (Exam exam : exams) {
            if (exam.getId() == id)
                return exam;
        }
        return null;
    }

    /**
     * Deletes an {@link Exam} object from the repository.
     *
     * @param object the {@link Exam} to be deleted
     */
    @Override
    public void delete(Exam object) {
        exams.remove(object);
    }

    public static ExamRepository getInstance() {
        if (instance == null) {
            instance = new ExamRepository();
        }
        return instance;
    }
}



