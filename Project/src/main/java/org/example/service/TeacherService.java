package org.example.service;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;
/**
 * Service class that provides business logic related to {@link Teacher} objects.
 * It interacts with the {@link TeacherRepository} to perform operations like creating a new teacher.
 * This class handles the logic for ensuring that a teacher ID is unique before creating and saving the teacher.
 */
public class TeacherService {

    /**
     * The repository used to access and manipulate {@link Teacher} objects.
     */
    private TeacherRepository teacherRepo;

    /**
     * Constructs a new {@link TeacherService} with a given {@link TeacherRepository}.
     * This constructor is used for dependency injection of the repository.
     *
     * @param teacherRepo The {@link TeacherRepository} used for managing teacher data.
     */
    public TeacherService(TeacherRepository teacherRepo){
        this.teacherRepo=teacherRepo;
    }

    /**
     * Creates a new {@link Teacher} if the provided teacher ID is not already in use.
     * It checks if the teacher ID exists in the repository and if not, saves the new teacher.
     *
     * @param teacherId The ID of the teacher to be created.
     * @param name The name of the teacher to be created.
     */
    public void createTeacher(Integer teacherId, String name){
        for(Teacher teacher:teacherRepo.getObjects())
            if (teacher.getId()==teacherId){
                System.out.println("Id already in use!");
                return;
            }

        Teacher teacher = new Teacher(name,teacherId);
        teacherRepo.save(teacher);
        System.out.println("Registration successful!");
    }
}
