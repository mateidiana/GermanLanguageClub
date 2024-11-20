package org.example.controller;
import org.example.service.StudentService;

/**
 * The {@code StudentController} class manages operations related to students.
 * It serves as a bridge between the view and the {@code StudentService}, handling
 * functionalities such as creating and managing student information.
 */
public class StudentController {
    private StudentService studentService;

    /**
     * Constructs a {@code StudentController} with the specified {@code StudentService}.
     *
     * @param studentService the service layer for managing student-related operations
     */
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    /**
     * Creates a new student with the specified ID and name.
     *
     * @param studentId the unique identifier for the student
     * @param name the name of the student
     */
    public void createStudent(Integer studentId, String name){
        studentService.createStudent(studentId,name);
    }
}
