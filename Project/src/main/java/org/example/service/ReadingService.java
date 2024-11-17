package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.Reading;
import org.example.model.Course;
import org.example.model.Student;
import org.example.repo.ReadingRepository;
import org.example.repo.StudentRepository;

public class ReadingService {

    private ReadingRepository readingRepo;

    private StudentRepository studentRepo;

    public ReadingService(ReadingRepository readingRepo, StudentRepository studentRepo) {
        this.readingRepo = readingRepo;
        this.studentRepo = studentRepo;
    }

    public void enroll(Integer studentId, Integer readingCourseId) {
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(readingCourseId);
        studentRepo.delete(student);
        readingRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            readingRepo.save(course);
            studentRepo.save(student);
        }
    }

    public void practiceReading(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(courseId);
        String[][] exercises=course.getExercises();
        Scanner scanner = new Scanner(System.in);
        String[] exercise;
        int foundCourse=0;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                for (int i=0;i<5;i++)
                {
                    Random rand = new Random();
                    exercise=exercises[rand.nextInt(exercises.length)];
                    String status = exercise[1] + "\n" + "\n" + exercise[2] + "\n" + exercise[3] + "\n" + exercise[4] + "\n";
                    System.out.println(status);
                    System.out.println("Your answer: ");
                    char answer = scanner.nextLine().charAt(0);
                    int found=0;

                    for (int j=2;j<=4;j++)
                    {
                        if (exercise[j].charAt(0)==answer && exercise[j].charAt(1)=='.')
                            if (exercise[0].contains(exercise[j].substring(3)) && !exercise[1].contains(exercise[j].substring(3)))
                            {
                                System.out.println("Correct! " + exercise[0] + "  Meaning: " + exercise[5]);
                                found=1;
                                break;
                            }

                    }
                    if (found==0)
                        for (int k=2;k<=4;k++)
                        {
                            if (exercise[0].contains(exercise[k].substring(3)) && !exercise[1].contains(exercise[k].substring(3)))
                                System.out.println("Wrong or invalid! The right answer was " + exercise[k] + " " + exercise[0] +"  Meaning: " + exercise[5]);
                        }

                }

                System.out.println("\n\n\nPractice complete!\n\n\n");

            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");
    }

    public List<Reading> getAvailableCourses() {
        return readingRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Reading course = readingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
//        courseRepo.get(courseId).getEnrolledStudents().forEach(student -> {
//            student.getCourses().removeIf(course -> course.getId().equals(courseId));
//            studentRepo.update(student);
//        });
//        courseRepo.delete(courseId);
    }

    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }
}
