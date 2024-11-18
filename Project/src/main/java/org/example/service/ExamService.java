package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.Exam;
import org.example.model.Course;
import org.example.model.Student;
import org.example.repo.ExamRepository;
import org.example.repo.StudentRepository;

public class ExamService {
    private ExamRepository examRepo;

    private StudentRepository studentRepo;

    public ExamService(ExamRepository examRepo, StudentRepository studentRepo) {
        this.examRepo = examRepo;
        this.studentRepo = studentRepo;
    }

    public void takeReadingExam(Integer studentId, Integer examId){
        Student student = studentRepo.getById(studentId);
        Exam exam = examRepo.getById(examId);
        String[][] exercises=exam.getExercises();
        Scanner scanner = new Scanner(System.in);
        String[] exercise;
        int foundCourse=0;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getCourseName().contains("Reading"))
            {
                foundCourse=1;
                for (int i=0;i<5;i++)
                {
                    Random rand = new Random();
                    exercise=exercises[rand.nextInt(exercises.length)];
                    String status = "\n\n"+exercise[1] + "\n" + "\n" + exercise[2] + "\n" + exercise[3] + "\n" + exercise[4] + "\n";
                    System.out.println(status);
                    System.out.println("Your answer: ");
                    char answer = scanner.nextLine().charAt(0);
                    int found=0;

                    if (answer=='a' || answer=='b' || answer=='c')
                    {
                        for (int j=2;j<=4;j++)
                        {
                            if (exercise[j].charAt(0)==answer && exercise[j].charAt(1)=='.')
                                if (exercise[j] == exercise[6])
                                {
                                    System.out.println("Correct! " + exercise[0] + "  Meaning: " + exercise[5]);
                                    found=1;
                                    break;
                                }
                        }
                        if (found==0)
                        {
                            System.out.println("Wrong! The right answer was " + exercise[6] + "  " + exercise[0] +"  Meaning: " + exercise[5]);

                        }
                    }
                    else
                        System.out.println("Invalid choice!");
                }
                System.out.println("\n\n\nExam complete!\n\n\n");
            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in any reading course!");
    }
}
