package org.example.service;

import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.*;
import org.example.repo.GrammarRepository;
import org.example.repo.StudentRepository;
import org.example.model.Student;
import org.example.repo.TeacherRepository;

public class GrammarService {
    private GrammarRepository grammarRepo;

    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;
    public GrammarService(GrammarRepository grammarRepo, StudentRepository studentRepo) {
        this.grammarRepo = grammarRepo;
        this.studentRepo = studentRepo;
    }
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
            for(int i=1; i<10; i++){
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

    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
    public List<Grammar> getAvailableCourses() {
        return grammarRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Grammar course = grammarRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }
}
