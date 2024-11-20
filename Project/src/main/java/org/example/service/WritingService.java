package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;

import org.example.model.*;
import org.example.repo.VocabRepository;
import org.example.repo.WritingRepository;

/**
 * Service class that provides business logic related to {@link Writing} objects.
 * It interacts with the {@link WritingRepository}, {@link StudentRepository}, {@link TeacherRepository} to perform operations
 * like manipulating reading courses.
 */
public class WritingService {
    private WritingRepository writingRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public WritingService(WritingRepository writingRepo, StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.writingRepo = writingRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo=teacherRepo;
    }

    /**
     * Enrolls a student in a specific writing course
     * @param studentId refers to the student to be enrolled
     * @param writingCourseId refers to the id of the course the student is being enrolled in
     */
    public void enroll(Integer studentId, Integer writingCourseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(writingCourseId);
        studentRepo.delete(student);
        writingRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            writingRepo.save(course);
            studentRepo.save(student);
        }
    }

    /**
     * A student can practice writing by writing a text on a given topic. The answer is reviewed by a teacher
     * @param studentId Refers to a student who practices writing
     * @param courseId Refers to the course the student practices in
     */
    public void practiceWriting(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Writing course = writingRepo.getById(courseId);

        Scanner scanner = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        int foundCourse=0;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1) {
            String exercise=course.getRequirement();
            System.out.println(exercise);
            String input;


            System.out.println("Enter text (type 0 to stop):");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("Exiting...");
                    break;
                }
                answer.append(input).append("\n");
            }
            Map <Student, String> toBeGraded=course.getTeacher().getFeedbackWriting();
            toBeGraded.put(student, answer.toString());
            course.getTeacher().setFeedbackWriting(toBeGraded);
            System.out.println(course.getTeacher().getFeedbackWriting());
            System.out.println("Writing exercise submitted!!!!!");
        }

    }

    /**
     *
     * @return all students
     */
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }

    /**
     *
     * @return all writing courses
     */
    public List<Writing> getAvailableCourses() {
        return writingRepo.getObjects();
    }

    /**
     *
     * @param courseId Refers to a specific writing course
     * @return all students enrolled in a vocabulary course
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Writing course = writingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * Shows all students enrolled in at least one writing course
     */
    public void showEnrolledWritingCourses(Integer studentId){
        Student student=studentRepo.getById(studentId);
        for (Course course:student.getCourses())
            if (course.getCourseName().contains("Writing"))
                System.out.println(course);
    }

    /**
     * Show all writing courses of a teacher
     * @param teacherId refers to a teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId){
        Teacher teacher=teacherRepo.getById(teacherId);
        for(Writing course:writingRepo.getObjects())
            if (course.getTeacher().getId()==teacherId)
                System.out.println(course.getCourseName());
    }

    /**
     * A teacher can either create or update a writing course if the course already exists
     * @param courseId refers to the course id that is to be updated or created
     * @param teacherId refers to the teacher that updates the course
     * @param courseName refers to the updated course name
     * @param maxStudents refers to the maximum number of students that can enroll
     */
    public void createOrUpdateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        int found = 0;
        for (Writing course : writingRepo.getObjects()) {
            if (course.getId() == courseId) {
                found = 1;
                updateWritingCourse(courseId, teacherId, courseName, maxStudents);
                return;
            }
        }
        if (found == 0) {
            createWritingCourse(courseId, teacherId, courseName, maxStudents);
        }
    }


    public void createWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Teacher teacher = teacherRepo.getById(teacherId);
        Writing w1 = new Writing(courseId, courseName, teacher, maxStudents);
        String exercise="Schreibe einen Text über den Frühling. :3";
        w1.setRequirement(exercise);
        writingRepo.save(w1);
    }

    public void updateWritingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Writing course = writingRepo.getById(courseId);
        Teacher teacher = teacherRepo.getById(teacherId);
        Writing w1 = new Writing(courseId, courseName, teacher, maxStudents);
        String exercise="Schreibe einen Text über den Frühling. :3";
        w1.setRequirement(exercise);
        writingRepo.update(course, w1);
    }

    /**
     * A teacher can remove a writing course
     * @param courseId Refers to a specific course
     * @param teacherId Refers to the teacher who removes the course
     */
    public void removeCourse(Integer courseId, Integer teacherId) {
        Writing course = writingRepo.getById(courseId);
        if (course.getTeacher().getId() == teacherId) {
            writingRepo.delete(course);
        } else {
            System.out.println("You don't have access to this course!");
        }
    }

    /**
     * Shows the teacher's feedback on a student's writing practice
     * @param studentId identifies a student
     */
    public void showFeedback(Integer studentId){
        Student student=studentRepo.getById(studentId);
        Map<Integer, Float> writingFeedback=new HashMap<>();
        writingFeedback=student.getWritingFeedback();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : writingFeedback.entrySet()) {
            System.out.println("Writing exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    /**
     * A teacher can give a feedback for every student who practiced their writing course
     * @param teacherId identifies a teacher
     * @param courseId identifies a course
     */
    public void gradeFeedback(Integer teacherId, Integer courseId){
        Teacher teacher= teacherRepo.getById(teacherId);
        Writing course= writingRepo.getById(courseId);
        Scanner scanner=new Scanner(System.in);
        Map<Student, String> toGrade=teacher.getFeedbackWriting();
        System.out.println(toGrade);
        System.out.println(course.getTeacher().getFeedbackWriting());
        while (!toGrade.isEmpty()) {
            Map.Entry<Student, String> entry = toGrade.entrySet().iterator().next();
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
            System.out.println("Input grade: ");
            float grade=scanner.nextFloat();
            Map<Integer, Float> results=key.getWritingFeedback();
            results.put(courseId, grade);
            key.setWritingFeedback(results);
            toGrade.remove(key);
        }
        System.out.println();
}

    /**
     * Replaces the teacher of a writing course with another
     * @param teacherId New teacher responsible for writing course
     * @param courseId Exam whose teacher is being replaced
     */
    public void changeTeacherAccessToWritingCourse(Integer courseId, Integer teacherId){
        Writing course=writingRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    public void getTeacherById(Integer teacherId){
        Teacher teacher=teacherRepo.getById(teacherId);
        System.out.println(teacher);
    }

    /**
     * Shows all students enrolled in at least one writing course
     */
    public void showStudentsEnrolledInWritingCourses(){
        for(Student student:studentRepo.getObjects())
            for(Course course:student.getCourses())
                if(course.getCourseName().contains("Writing"))
                {
                    System.out.println(student);
                    break;
                }
    }

}


