package org.example.view;
import java.util.Scanner;
import java.util.stream.IntStream;
import org.example.controller.ReadingController;

public class TeacherView {
    private ReadingController readingController;
    public TeacherView(ReadingController readingController){
        this.readingController=readingController;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. Register as new teacher\n2. Create a course\n3. View reading courses\n4. View writing courses\n5. View grammar courses\n6. View vocabulary courses\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("To be implemented");
                    break;
                case "2":
                    createCourseMenu();
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
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in reading courses\n3. Create/modify a reading course\n4. Delete a reading course\n5. Create/modify a reading exam\n6. Delete a reading exam\n7. View the results on exams\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    readingController.viewCourses();
                    break;
                case "2":
                    readingController.viewStudents();
                    break;
                case "3":
                    System.out.println("To be implemented");
                    break;
                case "4":
                    System.out.println("To be implemented1");
                    break;
                case "5":
                    System.out.println("To be implemented2");
                    break;
                case "6":
                    System.out.println("To be implemented3");
                    break;
                case "7":
                    System.out.println("To be implemented4");
                    break;
                default:
            }
        }
    }

    public void writingMenu(){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in writing courses\n3. Create/modify a writing course\n4. Delete a writing course\n5. Create/modify a writing exam\n6. Delete a writing exam\n7. View the results on exams\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("To be implemented5");
                    break;
                case "2":
                    System.out.println("To be implemented6");
                    break;
                case "3":
                    System.out.println("To be implemented");
                    break;
                case "4":
                    System.out.println("To be implemented1");
                    break;
                case "5":
                    System.out.println("To be implemented2");
                    break;
                case "6":
                    System.out.println("To be implemented3");
                    break;
                case "7":
                    System.out.println("To be implemented4");
                    break;
                default:
            }
        }
    }

    public void grammarMenu(){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in grammar courses\n3. Create/modify a grammar course\n4. Delete a grammar course\n5. Create/modify a grammar exam\n6. Delete a grammar exam\n7. View the results on exams\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("To be implemented");
                    break;
                case "2":
                    System.out.println("To be implemented8");
                    break;
                case "3":
                    System.out.println("To be implemented9");
                    break;
                case "4":
                    System.out.println("To be implemented1");
                    break;
                case "5":
                    System.out.println("To be implemented2");
                    break;
                case "6":
                    System.out.println("To be implemented3");
                    break;
                case "7":
                    System.out.println("To be implemented4");
                    break;
                default:
            }
        }
    }

    public void vocabularyMenu(){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in vocabulary courses\n3. Create/modify a vocabulary course\n4. Delete a vocabulary course\n5. Create/modify a vocabulary exam\n6. Delete a vocabulary exam\n7. View the results on exams\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("To be implemented7");
                    break;
                case "2":
                    System.out.println("To be implemented8");
                    break;
                case "3":
                    System.out.println("To be implemented");
                    break;
                case "4":
                    System.out.println("To be implemented1");
                    break;
                case "5":
                    System.out.println("To be implemented2");
                    break;
                case "6":
                    System.out.println("To be implemented3");
                    break;
                case "7":
                    System.out.println("To be implemented4");
                    break;
                default:
            }
        }
    }

    public void createCourseMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option:\n\n1. Create a reading course\n2. Create a writing course\n3. Create a grammar course\n4. Create a vocabulary course\n0. Exit\n");

        String option = scanner.nextLine();
        switch (option) {
            case "0":
                break;
            case "1":
                System.out.println("To be implemented1");
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

    private static int readCourseId(Scanner scanner) {
        System.out.print("Enter course ID: ");
        return Integer.parseInt(scanner.nextLine());
    }



}
