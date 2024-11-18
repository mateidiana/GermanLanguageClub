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
        int alreadyEnrolled=0;
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(readingCourseId);
        for (Course course1:student.getCourses()){
            if (course1.getId()==course.getId())
                alreadyEnrolled=1;
        }
        if (alreadyEnrolled==0){
            studentRepo.delete(student);
            readingRepo.delete(course);
            if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
                course.getEnrolledStudents().add(student);
                student.getCourses().add(course);
                readingRepo.save(course);
                studentRepo.save(student);
            }
        }

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

                            student.setPastMistakes(appendRow(student.getPastMistakes(),exercise));
                        }
                    }
                    else
                        System.out.println("Invalid choice!");
                }
                System.out.println("\n\n\nPractice complete!\n\n\n");
            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");
    }

    public void reviewPastMistakes(Integer studentId){

        Scanner scanner = new Scanner(System.in);
        Student student = studentRepo.getById(studentId);
        String[][] pastMistakes=student.getPastMistakes();
        int numRows = pastMistakes.length;

        for (int i=0;i<numRows;i++)
        {
            String[] exercise=pastMistakes[i];
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
        System.out.println("\n\n\nReview complete!\n\n\n");
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
