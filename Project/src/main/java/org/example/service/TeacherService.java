package org.example.service;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;
public class TeacherService {
    private TeacherRepository teacherRepo;
    public TeacherService(TeacherRepository teacherRepo){
        this.teacherRepo=teacherRepo;
    }

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
