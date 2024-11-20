package org.example.repo;
import org.example.model.Grammar;
import org.example.model.Student;
import org.example.model.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Teacher} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 */
public class TeacherRepository implements IRepository<Teacher>{
    /**
     * A list of {@link Teacher} objects managed by the repository.
     */
    private List<Teacher> teachers;

    /**
     * A static instance of the {@link TeacherRepository} for the singleton pattern.
     * Ensures only one instance of the repository exists.
     */
    private static TeacherRepository instance;

    /**
     * Constructs a new {@code TeacherRepository}.
     * Initializes the internal list for storing {@link Teacher} objects.
     */
    public TeacherRepository() {
        this.teachers=new ArrayList<>();
    }

    /**
     * Retrieves all {@link Teacher} objects in the repository.
     *
     * @return A list of all {@link Teacher} objects.
     */
    @Override
    public List<Teacher> getObjects(){
        return teachers;
    }

    /**
     * Saves a new {@link Teacher} object to the repository.
     *
     * @param entity The {@link Teacher} object to save.
     */
    @Override
    public void save(Teacher entity) {
        teachers.add(entity);
    }

    /**
     * Updates an existing {@link Teacher} object in the repository.
     * The {@code entity} object is replaced by the {@code TeacherRepl} object
     * at the same index in the list.
     *
     * @param entity The {@link Teacher} object to be updated.
     * @param TeacherRepl The {@link Teacher} object to replace the original.
     */
    @Override
    public void update(Teacher entity, Teacher TeacherRepl) {
        int index = teachers.indexOf(entity);
        if (index != -1) {
            teachers.set(index, TeacherRepl);
        }
    }

    /**
     * Retrieves a {@link Teacher} object by its unique identifier.
     *
     * @param id The unique identifier of the {@link Teacher} to retrieve.
     * @return The {@link Teacher} object with the specified ID, or {@code null} if no such object exists.
     */
    public Teacher getById(Integer id){
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id)
                return teacher;
        }
        return null;
    }

    /**
     * Deletes a {@link Teacher} object from the repository.
     *
     * @param object The {@link Teacher} object to delete.
     */
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
