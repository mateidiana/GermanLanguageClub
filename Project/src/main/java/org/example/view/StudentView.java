package org.example.view;
import java.util.Scanner;
import java.util.stream.IntStream;

import org.example.controller.GrammarController;
import org.example.controller.ReadingController;
import org.example.controller.*;

import org.example.service.ReadingService;
import org.example.model.Reading;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.ReadingRepository;

public class StudentView {
    private StudentController studentController;
    private ReadingController readingController;
    private ExamController examController;
    private GrammarController grammarController;
    private VocabController vocabController;
    private WritingController writingController;
    public StudentView(StudentController studentController, ReadingController readingController, ExamController examController, GrammarController grammarController){ //,VocabController vocabController
        this.studentController=studentController;
        this.readingController=readingController;
        this.examController=examController;
        this.grammarController=grammarController;
        //this.vocabController=vocabController;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. Register as new student\n2. Enroll in a course\n3. View your reading courses\n4. View your writing courses\n5. View your grammar courses\n6. View your vocabulary courses\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    studentController.createStudent(readStudentId(scanner),readStudentName(scanner));
                    break;
                case "2":
                    enrollMenu();
                    break;
                case "3":
                    readingMenu();
                    break;
                case "4":
                    writingMenu();
                    break;
                case "5":
                    grammarMenu();
                    break;
                case "6":
                    vocabularyMenu();
                    break;
                default:
            }
        }
    }

    public void readingMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your reading courses\n2. Practice reading\n3. Review past mistakes\n4. Take reading exam\n5. View past exam scores\n6. View mandatory books\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    readingController.showEnrolledCourses(readStudentId(scanner));
                    break;
                case "2":
                    readingController.practiceReading(readStudentId(scanner),readCourseId(scanner));
                    break;
                case "3":
                    readingController.reviewPastMistakes(readStudentId(scanner));
                    break;
                case "4":
                    examController.showAllReadingExams();
                    examController.takeReadingExam(readStudentId(scanner),readExamId(scanner));
                    break;
                case "5":
                    examController.showReadingResults(readStudentId(scanner));
                    break;
                case "6":
                    readingController.viewMandatoryBooks(readStudentId(scanner),readCourseId(scanner));
                    break;
                default:
            }
        }
    }

    public void writingMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your writing courses\n2. Practice writing\n3. Review past mistakes\n4. Take writing exam\n5. View past exam scores\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("To be implemented");
                    break;
                case "2":
                    System.out.println("To be implemented1");
                    break;
                case "3":
                    System.out.println("To be implemented2");
                    break;
                case "4":
                    System.out.println("To be implemented3");
                    break;
                case "5":
                    System.out.println("To be implemented4");
                    break;
                default:
            }
        }
    }

    public void grammarMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your grammar courses\n2. Practice grammar\n3. Review past mistakes\n4. Take grammar exam\n5. View past exam scores\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    grammarController.viewCourses();
                    break;
                case "2":
                    grammarController.practiceGrammar(readStudentId(scanner),readCourseId(scanner));
                    break;
                case "3":
                    grammarController.reviewPastMistakes(readStudentId(scanner),readCourseId(scanner));
                    break;
                case "4": examController.takeGrammarExam(readStudentId(scanner),readExamId(scanner));
                    break;
                case "5":
                    examController.showGrammarResults(readStudentId(scanner));
                    break;
                default:
            }
        }
    }

    public void vocabularyMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your vocabulary courses\n2. Practice vocabulary\n3. Review past mistakes\n4. Take vocabulary exam\n5. View past exam scores\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    vocabController.viewCourses();
                    break;
                case "2":
                    vocabController.practiceVocabulary(readStudentId(scanner),readCourseId(scanner));
                    break;
                case "3":
                    vocabController.reviewPastMistakes(readStudentId(scanner),readCourseId(scanner));
                    break;
                case "4":
                    examController.takeVocabExam(readStudentId(scanner),readExamId(scanner));
                    break;
                case "5":
                    examController.showVocabResults(readStudentId(scanner));
                    break;
                default:
            }
        }
    }

    public void enrollMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option:\n\n1. Enroll in a reading course\n2. Enroll in a writing course\n3. Enroll in a grammar course\n4. Enroll in a vocabulary course\n0. Exit\n");

        String option = scanner.nextLine();
        switch (option) {
            case "0":
                break;
            case "1":
                readingController.viewCourses();
                readingController.enrollStudent(readStudentId(scanner),readCourseId(scanner));
                break;
            case "2":
                System.out.println("To be implemented2");
                break;
            case "3":
                System.out.println("To be implemented3");
                break;
            case "4":
                System.out.println("To be implemented4");
                break;
            default:
        }
    }

    private static int readStudentId(Scanner scanner) {
        System.out.println("Enter student ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String readStudentName(Scanner scanner) {
        System.out.println("Enter student name: ");
        return scanner.nextLine();
    }

    private static int readCourseId(Scanner scanner) {
        System.out.print("Enter course ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int readExamId(Scanner scanner) {
        System.out.print("Enter exam ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
