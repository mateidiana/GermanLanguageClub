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
            System.out.print("Select an option:\n\n1. View students\n2. View reading courses\n3. View students enrolled in reading courses\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    readingController.viewStudents();
                    break;
                case "2":
                    readingController.viewCourses();
                    break;
                case "3":
                    readingController.viewEnrolled(readCourseId(scanner));
                    break;
                default:
            }
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
