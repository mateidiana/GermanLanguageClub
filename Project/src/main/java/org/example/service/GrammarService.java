package org.example.service;

import java.util.Scanner;
import java.util.List;

import org.example.model.*;
import org.example.repo.GrammarRepository;
import org.example.repo.StudentRepository;
import org.example.model.Student;
import org.example.repo.TeacherRepository;

/**
 * Service class that provides business logic related to {@link Grammar} objects.
 * It interacts with the {@link GrammarRepository}, {@link StudentRepository}, {@link TeacherRepository} to perform operations
 * like manipulating grammar courses.
 */
public class GrammarService {
    private GrammarRepository grammarRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public GrammarService(GrammarRepository grammarRepo, StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.grammarRepo = grammarRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo=teacherRepo;
    }

    /**
     * Updates a student's past mistakes in form of a matrix
     * @param originalMatrix Refers to a student's past mistakes
     * @param newRow Refers to the latest exercise added
     * @return updated past mistakes
     */
    public static String[][] appendRow(String[][] originalMatrix, String[] newRow) {
        if (originalMatrix==null||originalMatrix.length==0)
        {
            String[][] newMatrix = new String[1][100];
            newMatrix[0]=newRow;
            return newMatrix;
        }

        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        String[][] newMatrix = new String[numRows + 1][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                newMatrix[i][j] = originalMatrix[i][j];
            }
        }

        for (int j = 0; j < numCols; j++) {
            newMatrix[numRows][j] = newRow[j];
        }

        return newMatrix;
    }

    /**
     * Enrolls a student in a specific grammar course
     * @param studentId refers to the student to be enrolled
     * @param grammarCourseId refers to the id of the course the student is being enrolled in
     */
    public void enroll(Integer studentId, Integer grammarCourseId) {
        Student student = studentRepo.getById(studentId);
        Grammar course = grammarRepo.getById(grammarCourseId);
        studentRepo.delete(student);
        grammarRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            grammarRepo.save(course);
            studentRepo.save(student);
        }
    }

    /**
     * A student can practice past mistakes
     * @param studentId Refers to a specific student
     * @param courseId Refers to the course a student made mistakes in
     */
    public void reviewPastMistakes(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Grammar course = grammarRepo.getById(courseId);
        String[][] newMistakes=new String[1][2];
        String[][] pastGrammarMistakes = student.getPastGrammarMistakes();
        String[] exercise;
        Scanner scanner = new Scanner(System.in);
        int foundCourse = 0;
        for (Course findCourse : student.getCourses()) {
            if (findCourse.getId() == course.getId()) {
                foundCourse = 1;
                break;
            }
        }
        if (foundCourse == 0)
            System.out.println("\n\n\nYou are not enrolled in this course!");
        if (foundCourse == 1) {
            System.out.println("PLease fill in the gaps with the correct word:");
            for (int i = 1; i <student.getPastGrammarMistakes().length; i++) {
                exercise = pastGrammarMistakes[i];
                System.out.println("Question " + i + ": " + exercise[0]);
                System.out.print("Answer: ");
                String answer = scanner.nextLine();
                if (!answer.equals(exercise[1]))
                    newMistakes=appendRow(newMistakes, exercise);
            }
            student.setPastGrammarMistakes(newMistakes);
        }
    }

    /**
     * A student can practice German grammar by completing sentences with the correct form. Wrong answers
     * can be reviewed later
     * @param studentId Refers to a student who practices grammar
     * @param courseId Refers to the course the student practices in
     */
    public void practiceGrammar(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Grammar course = grammarRepo.getById(courseId);
        String []exercise;
        String[][] exercises=course.getExercises();
        Scanner scanner = new Scanner(System.in);
        int correctAnswers=0;
        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1){
            System.out.println("PLease fill in the gaps with the correct word:");
            for(int i=0; i<10; i++){
                exercise=exercises[i];
                System.out.println("Question "+i+": "+exercise[0]);
                System.out.print("Answer: ");
                String answer=scanner.nextLine();
                if(answer.equals(exercise[1])) {
                    correctAnswers++;
                    System.out.println("Corect coaie!");
                }
                else{
                    System.out.println(answer+" "+exercise[1]+" pt debug");
                    student.setPastGrammarMistakes(appendRow(student.getPastGrammarMistakes(), exercise ));
                }
            }
            if(correctAnswers>5) System.out.println("You have passed this practice test with the grade "+correctAnswers+"!");
            else System.out.println("You have failed this practice test with the grade "+correctAnswers+". Do better, loser");
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
     * @return all grammar courses
     */
    public List<Grammar> getAvailableCourses() {
        return grammarRepo.getObjects();
    }

    /**
     *
     * @param courseId Refers to a specific grammar course
     * @return all students enrolled in a grammar course
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Grammar course = grammarRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * A teacher can remove a grammar course
     * @param courseId Refers to a specific course
     * @param teacherId Refers to the teacher who removes the course
     */
    public void removeCourse(Integer courseId, Integer teacherId) {
        Grammar course = grammarRepo.getById(courseId);
        if (course.getTeacher().getId() == teacherId) {
            grammarRepo.delete(course);
        } else {
            System.out.println("You don't have access to this course!");
        }
    }

    /**
     * A teacher can view the courses taught by them
     * @param teacherId refers to a specific teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepo.getById(teacherId);
        for (Grammar course : grammarRepo.getObjects()) {
            if (course.getTeacher().getId() == teacherId) {
                System.out.println(course.getCourseName());
            }
        }
    }

    /**
     * A teacher can either create or update a grammar course if the course already exists
     * @param courseId refers to the course id that is to be updated or created
     * @param teacherId refers to the teacher that updates the course
     * @param courseName refers to the updated course name
     * @param maxStudents refers to the maximum number of students that can enroll
     */
    public void createOrUpdateGrammarCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        int found = 0;
        for (Grammar course : grammarRepo.getObjects()) {
            if (course.getId() == courseId) {
                found = 1;
                updateGrammarCourse(courseId, teacherId, courseName, maxStudents);
                return;
            }
        }
        if (found == 0) {
            createGrammarCourse(courseId, teacherId, courseName, maxStudents);
        }
    }

    public void createGrammarCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Teacher teacher = teacherRepo.getById(teacherId);
        Grammar g1 = new Grammar(courseId, courseName, teacher, maxStudents);
        String [][] grammarExercises={
                { "Du (brauchen) _ Hilfe.", "brauchst" },
                { "Ich bin _ Hause.", "zu" },
                { "Er trägt _.", "bei" },
                { "Diana (setzen)_ sich auf das Sofa.", "setzt" },
                { "Stefi klettert auf _ Baum.", "den" },
                { "Ich (besuchen) _ diese Kirche.", "besuche" },
                { "Wir spielen DOTA in _ Klasse.", "der" },
                { "Mama kocht immer (lecker)_ Essen", "leckeres" },
                { "Der Ball ist unter _ Tisch gerollt.", "den" },
                { "Mein Mann kommt immer betrunken _ Hause.", "nach" }
        };
        g1.setExercises(grammarExercises);
        grammarRepo.save(g1);
    }

    public void updateGrammarCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Grammar course = grammarRepo.getById(courseId);
        Teacher teacher = teacherRepo.getById(teacherId);
        Grammar g1 = new Grammar(courseId, courseName, teacher, maxStudents);
        String [][] grammarExercises={
                { "Du (brauchen) _ Hilfe.", "brauchst" },
                { "Ich bin _ Hause.", "zu" },
                { "Er trägt _.", "bei" },
                { "Diana (setzen)_ sich auf das Sofa.", "setzt" },
                { "Stefi klettert auf _ Baum.", "den" },
                { "Ich (besuchen) _ diese Kirche.", "besuche" },
                { "Wir spielen DOTA in _ Klasse.", "der" },
                { "Mama kocht immer (lecker)_ Essen", "leckeres" },
                { "Der Ball ist unter _ Tisch gerollt.", "den" },
                { "Mein Mann kommt immer betrunken _ Hause.", "nach" }
        };
        g1.setExercises(grammarExercises);
        grammarRepo.update(course, g1);
    }

    /**
     * Replaces the teacher of a grammar course with another
     * @param teacherId New teacher responsible for grammar course
     * @param courseId Exam whose teacher is being replaced
     */
    public void changeTeacherAccessToGrammarCourse(Integer courseId, Integer teacherId){
        Grammar course=grammarRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    /**
     * Shows all students enrolled in grammar courses
     */
    public void showStudentsEnrolledInGrammarCourses(){
        for(Student student:studentRepo.getObjects())
            for(Course course:student.getCourses())
                if(course.getCourseName().contains("Grammar"))
                {
                    System.out.println(student);
                    break;
                }
    }

    /**
     * Displays all grammar courses a student is enrolled in
     * @param studentId identifies a student
     */
    public void showEnrolledGrammarCourses(Integer studentId){
        Student student = studentRepo.getById(studentId);
        for (Course course:student.getCourses())
            if (course.getCourseName().contains("Grammar"))
                System.out.println(course);
    }

}
