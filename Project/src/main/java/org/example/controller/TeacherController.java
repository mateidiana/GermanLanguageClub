package org.example.controller;
import org.example.service.TeacherService;

/**
 * The {@code TeacherController} class manages operations related to teachers.
 * It acts as an intermediary between the view and the {@code TeacherService}, handling
 * functionalities such as creating and managing teacher information.
 */
public class TeacherController {
    private TeacherService teacherService;

    /**
     * Constructs a {@code TeacherController} with the specified {@code TeacherService}.
     *
     * @param teacherService the service layer for managing teacher-related operations
     */
    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    /**
     * Creates a new teacher with the specified ID and name.
     *
     * @param teacherId the unique identifier for the teacher
     * @param name the name of the teacher
     */
    public void createTeacher(Integer teacherId, String name){
        teacherService.createTeacher(teacherId,name);
    }
}
