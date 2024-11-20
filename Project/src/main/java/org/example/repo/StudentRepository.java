package org.example.repo;
import org.example.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton repository class for managing {@link Student} objects.
 * Implements the {@link IRepository} interface to provide CRUD operations.
 */
public class StudentRepository implements IRepository<Student> {

    /**
     * A list of {@link Student} objects managed by the repository.
     */
    private List<Student> students;

    /**
     * Singleton instance of the {@code StudentRepository}.
     */
    private static StudentRepository instance;

    /**
     * Constructs a new {@code StudentRepository}.
     * Initializes the internal list for storing {@link Student} objects.
     */
    public StudentRepository() {
        this.students=new ArrayList<>();
    }

    /**
     * Retrieves all students managed by the repository.
     *
     * @return a {@code List} of all {@link Student} objects
     */
    @Override
    public List<Student> getObjects(){
        return students;
    }

    /**
     * Saves a new {@link Student} object to the repository.
     *
     * @param entity the {@link Student} object to be saved
     */
    @Override
    public void save(Student entity) {
        students.add(entity);
    }

    /**
     * Updates an existing {@link Student} object in the repository.
     * Replaces the target {@code entity} with the given {@code StudentRepl}.
     *
     * @param entity      the {@link Student} object to be updated
     * @param StudentRepl the {@link Student} object to replace the target entity
     */
    @Override
    public void update(Student entity, Student StudentRepl) {
        int index = students.indexOf(entity);
        if (index != -1) {
            students.set(index, StudentRepl);
        }
    }

    /**
     * Deletes a {@link Student} object from the repository.
     *
     * @param object the {@link Student} object to be deleted
     */
    @Override
    public void delete(Student object) {
        students.remove(object);
    }

    /**
     * Retrieves a {@link Student} object by its unique identifier.
     *
     * @param id the unique ID of the {@link Student}
     * @return the {@link Student} object with the specified ID, or {@code null} if not found
     */
    public Student getById(Integer id){
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }
}
