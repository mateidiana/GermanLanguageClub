package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

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
        float score=2;

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
                                    score+=2;
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
                System.out.println("Your score: "+ score + "\n\n");
                Map<Integer, Float> readingExamResults=new HashMap<>();
                readingExamResults=student.getReadingResults();
                readingExamResults.put(examId,score);
                student.setReadingResults(readingExamResults);

                List<Student> examined;
                examined=exam.getExaminedStudents();
                examined.add(student);
                exam.setExaminedStudents(examined);

                Map<Student,Float> results;
                results=exam.getResults();
                results.put(student,score);
                exam.setResults(results);
            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in any reading course!");
    }

    public void showReadingResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> readingExamResults=new HashMap<>();
        readingExamResults=student.getReadingResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : readingExamResults.entrySet()) {
            System.out.println("Reading exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public Map<Integer,Float> showReadingResults1(Integer studentId){
        Student student = studentRepo.getById(studentId);
        return student.getReadingResults();
    }

    public void showAllReadingExams(){
        List<Exam> exams=examRepo.getObjects();
        for (Exam exam:exams)
            if(exam.getExamName().contains("Reading"))
                System.out.println(exam);
    }
}
