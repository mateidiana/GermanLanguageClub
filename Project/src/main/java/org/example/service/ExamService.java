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
                System.out.println(exercises[0][0]);
                System.out.println(exercises[1][0]);
                for (int i=2;i<6;i++)
                {
                    exercise=exercises[i];
                    System.out.println(exercise[0]+exercise[1]+"\n"+exercise[2]);
                    System.out.println("Your answer: ");
                    char answer = scanner.nextLine().charAt(0);
                    int found=0;

                    if (answer=='a' || answer=='b')
                    {
                        for (int j=1;j<=2;j++)
                        {
                            if (exercise[j].charAt(0)==answer && exercise[j].charAt(1)=='.')
                                if (exercise[j] == exercise[3])
                                {
                                    System.out.println("Correct! " + exercise[3]);
                                    found=1;
                                    break;
                                }
                        }
                        if (found==0)
                        {
                            System.out.println("Wrong! The right answer was " + exercise[3]);
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
