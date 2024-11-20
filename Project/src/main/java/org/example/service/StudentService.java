package org.example.service;
import org.example.model.Student;
import org.example.repo.StudentRepository;

/**
 * Service class that provides business logic related to {@link Student} objects.
 * It interacts with the {@link StudentRepository} to perform operations like creating a new student.
 *
 * This class handles the logic for ensuring that a student ID is unique before creating and saving the student.
 */
public class StudentService {

    /**
     * The repository used to access and manipulate {@link Student} objects.
     */
    private StudentRepository studentRepo;

    /**
     * Constructs a new {@link StudentService} with a given {@link StudentRepository}.
     * This constructor is used for dependency injection of the repository.
     *
     * @param studentRepo The {@link StudentRepository} used for managing student data.
     */
    public StudentService(StudentRepository studentRepo){
        this.studentRepo=studentRepo;
    }

    /**
     * Creates a new {@link Student} if the provided student ID is not already in use.
     * It checks if the student ID exists in the repository and if not, saves the new student.
     *
     * @param studentId The ID of the student to be created.
     * @param name The name of the student to be created.
     */
    public void createStudent(Integer studentId, String name){
        for(Student student:studentRepo.getObjects())
            if (student.getId()==studentId){
                System.out.println("Id already in use!");
                return;
            }

        Student student = new Student(name,studentId);
        studentRepo.save(student);
        System.out.println("Registration successful!");
    }
}
