package org.example.service;
import org.example.model.Student;
import org.example.repo.StudentRepository;
public class StudentService {
    private StudentRepository studentRepo;
    public StudentService(StudentRepository studentRepo){
        this.studentRepo=studentRepo;
    }

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
