package org.example.view;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
import org.example.controller.*;
import org.example.model.*;
import org.example.model.Exceptions.BusinessLogicException;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;


public class TeacherView {
    private TeacherController teacherController;
    private ReadingController readingController;
    private VocabularyController vocabController;
    private GrammarController grammarController;
    private ExamController examController;

    public TeacherView(TeacherController teacherController,ReadingController readingController, VocabularyController vocabController, GrammarController grammarController, ExamController examController){
        this.teacherController=teacherController;
        this.readingController=readingController;
        this.vocabController=vocabController;
        this.grammarController=grammarController;
        this.examController=examController;
    }

    public void registerOrSignIn(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Select an option:\n\n1. Log in\n2. Register\n3. Exit");
            String option = scanner.nextLine();
            switch(option){
                case "1":
                    int teacherId1=readTeacherId(scanner);
                    String name1=readTeacherName(scanner);
                    try{
                        if (teacherController.logInAsTeacher(teacherId1,name1))
                        {
                            System.out.println("Login successful!");
                            start(teacherId1);
                        }

                        else System.out.println("Account not found! Name and/or id invalid!");
                    } catch(ValidationException e){ System.out.println(e.getMessage());}
                    break;

                case "2":
                    int teacherId=readTeacherId(scanner);
                    String name=readTeacherName(scanner);
                    try{
                        if (teacherController.createTeacher(teacherId,name))
                        {
                            System.out.println("Registration successful!");
                            start(teacherId);
                        }

                        else System.out.println("Id already in use!");

                    } catch(ValidationException e){ System.out.println(e.getMessage());}
                    break;

                case "3":
                    continueLoop=false;
                    break;
                default:
            }
        }

    }

    public void start(int teacherId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View reading courses\n2. View grammar courses\n3. View vocabulary courses\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;

                case "1":
                    readingMenu(teacherId);
                    break;
                case "2":
                    grammarMenu(teacherId);
                    break;
                case "3":
                    vocabularyMenu(teacherId);
                    break;
                default:
            }
        }
    }


    public void readingMenu(int teacherId){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in reading courses\n3. Create/modify a reading course\n4. Delete a reading course\n5. Create/modify a reading exam\n6. Delete a reading exam\n7. View the results on exams\n8. Add a mandatory book\n9. Filter students by passing grade on exam\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("\n\n\n");
                    try{
                        List<Reading> readingCourses=readingController.viewReadingCoursesTaughtByTeacher(teacherId);
                        if (readingCourses.isEmpty())
                            System.out.println("You don't have any reading courses!");
                        else{System.out.println("Your courses are:\n");
                            for (Reading course:readingCourses)
                                System.out.println(course);}

                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "2":
                    System.out.println("\n\n\n");

                    List<Student> enrolledStud=readingController.showStudentsEnrolledInReadingCourses();
                    if (enrolledStud.isEmpty())
                        System.out.println("No students are enrolled in reading coures yet!");
                    else { System.out.println("The following students are enrolled in one or more reading courses:\n");
                        for(Student stud:enrolledStud)
                            System.out.println(stud);}
                    System.out.println("\n\n\n");

                    break;

                case "3":
                    System.out.println("\n\n\n");

                    int courseId=readCourseId(scanner);
                    String courseName=readCourseName(scanner);
                    int maxStudents=readMaxStudents(scanner);

                    try{
                        readingController.createOrUpdateReadingCourse(courseId,teacherId,courseName,maxStudents);
                        System.out.println("Course successfully added/updated!");
                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}

                    String textTitle=readTextTitle(scanner);
                    String textAuthor=readTextAuthor(scanner);
                    String text=readText(scanner);
                    String question; String answer;

                    try{
                        readingController.setReadingText(courseId,textTitle,textAuthor,text);
                        System.out.println("Text, author and title successfully updated!");
                        System.out.println("Enter 4 questions and their answers for this course");
                        for (int i=0;i<4;i++)
                        {
                            question=readQuestion(scanner);
                            answer=readAnswer(scanner);
                            try{readingController.createQuestion(courseId,question,answer);
                            } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                        }
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                    System.out.println("\n\n\n");

                    break;

                case "4":
                    System.out.println("\n\n\n");

                    int courseId4=readCourseId(scanner);

                    try{
                        if (readingController.removeCourse(courseId4,teacherId))
                            System.out.println("Course deleted successfully!");
                        else
                            System.out.println("You don't have access to this course!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "5":
                    System.out.println("\n\n\n");


                    int examId=readExamId(scanner);

                    String examName=readExamName(scanner);
                    try{
                        examController.createOrUpdateReadingExam(examId,teacherId,examName);
                        System.out.println("Exam successfully created/updated!");
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}


                    String textTitleExam=readTextTitle(scanner);
                    String textAuthorExam=readTextAuthor(scanner);
                    String textExam=readText(scanner);
                    String questionExam; String answerExam;

                    try{
                        examController.setReadingText(examId,textTitleExam,textAuthorExam,textExam);
                        System.out.println("Text, author and title successfully updated!");
                        System.out.println("Enter 4 questions and their answers for this exam");
                        for (int i=0;i<4;i++)
                        {
                            questionExam=readQuestion(scanner);
                            answerExam=readAnswer(scanner);
                            try{readingController.createQuestion(examId,questionExam,answerExam);
                            } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                        }
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "6":
                    System.out.println("\n\n\n");

                    int examId6=readExamId(scanner);

                    try{
                        if (examController.removeReadingExam(examId6,teacherId))
                            System.out.println("Exam deleted successfully!");
                        else
                            System.out.println("You don't have access to this exam!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "7":
                    System.out.println("\n\n\n");

                    int examId7=readExamId(scanner);

                    try{
                        List<ExamResult> results=examController.showAllResultsOfReadingTeacherExam(teacherId,examId7);
                        if (results.isEmpty())
                            System.out.println("No student took this exam yet!");
                        else { System.out.println("The results on this exam so far are:\n");
                            for (ExamResult result:results)
                                System.out.println("Student with id: "+result.getStudent()+" score: "+result.getResult());}
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "8":
                    System.out.println("\n\n\n");


                    int courseId8=readCourseId(scanner);
                    String bookName=readBookName(scanner);
                    String bookAuthor=readBookAuthor(scanner);
                    try {
                        if (readingController.addMandatoryBook(teacherId,courseId8,bookName,bookAuthor))
                            System.out.println("Book added successfully");
                        else System.out.println("You don't have access to this course!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "9":
                    System.out.println("\n\n\n");

                    int examId9=readExamId(scanner);

                    try{
                        List<Student> filtered=examController.filterStudentsByPassingGradeOnReadingExam(teacherId,examId9);
                        if (filtered.isEmpty())
                            System.out.println("No student passed the exam yet!");
                        else {  System.out.println("The following students passed this exam:\n");
                            for (Student stud:filtered) System.out.println(stud);}
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                default:
            }
        }
    }



    public void grammarMenu(int teacherId){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in grammar courses\n3. Create/modify a grammar course\n4. Delete a grammar course\n5. Create/modify a grammar exam\n6. Delete a grammar exam\n7. View the results on exams\n8. Sort students by grade on exam\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("\n\n\n");


                    try{
                        List<Grammar> grammarCourses=grammarController.viewGrammarCoursesTaughtByTeacher(teacherId);
                        if (grammarCourses.isEmpty())
                            System.out.println("You don't have any grammar courses!");
                        else
                        {   System.out.println("You have the following grammar courses:\n");
                            for (Grammar course:grammarCourses)
                                System.out.println(course);}
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "2":
                    System.out.println("\n\n\n");


                    List<Student> enrolledStud=grammarController.showStudentsEnrolledInGrammarCourses();
                    if (enrolledStud.isEmpty())
                        System.out.println("No students are enrolled in grammar courses yet!");
                    else {  System.out.println("The following students are enrolled in any grammar course!");
                        for(Student stud:enrolledStud)
                            System.out.println(stud);}
                    System.out.println("\n\n\n");

                    break;

                case "3":
                    System.out.println("\n\n\n");


                    int courseId=readCourseId(scanner);
                    String courseName=readCourseName(scanner);
                    int maxStudents=readMaxStudents(scanner);

                    try{
                        grammarController.createOrUpdateGrammarCourse(courseId,teacherId,courseName,maxStudents);
                        System.out.println("Course successfully added/updated!");
                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}


                    String question; String answer;
                    System.out.println("Enter 10 grammar questions and their right answers:");
                    for (int i=0;i<10;i++)
                    {
                        question=readQuestion(scanner);
                        answer=readAnswer(scanner);
                        try{grammarController.createQuestion(courseId,question,answer);
                        } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                    }
                    System.out.println("\n\n\n");

                    break;

                case "4":
                    System.out.println("\n\n\n");


                    int courseId4=readCourseId(scanner);

                    try{
                        if (grammarController.removeCourse(courseId4,teacherId))
                            System.out.println("Course deleted successfully!");
                        else
                            System.out.println("You don't have access to this course!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "5":
                    System.out.println("\n\n\n");


                    int examId=readExamId(scanner);
                    String examName=readCourseName(scanner);
                    try{
                        examController.createOrUpdateGrammarExam(examId,teacherId,examName);
                        System.out.println("Exam successfully created/updated!");
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}


                    String questionExam; String answerExam;
                    System.out.println("Enter 10 grammar questions and their right answers:");
                    for (int i=0;i<10;i++)
                    {
                        questionExam=readQuestion(scanner);
                        answerExam=readAnswer(scanner);
                        try{examController.createGrammarQuestion(examId,questionExam,answerExam);
                        } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                    }
                    System.out.println("\n\n\n");


                    break;

                case "6":
                    System.out.println("\n\n\n");


                    int examId6=readExamId(scanner);

                    try{
                        if (examController.removeGrammarExam(examId6,teacherId))
                            System.out.println("Exam deleted successfully!");
                        else
                            System.out.println("You don't have access to this exam!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "7":
                    System.out.println("\n\n\n");


                    int examId7=readExamId(scanner);

                    try{
                        List<ExamResult> results=examController.showAllResultsOfGrammarTeacherExam(teacherId,examId7);
                        if (results.isEmpty())
                            System.out.println("No student took this exam yet!");
                        else { System.out.println("The results are:\n");
                            for (ExamResult result:results)
                                System.out.println("Student with id: "+result.getStudent()+" score: "+result.getResult());}
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "8":
                    System.out.println("\n\n\n");

                    int examId8=readExamId(scanner);
                    try{
                        Map<Student,Float> sorted=examController.sortStudentsByGrade(teacherId,examId8);
                        if(sorted.isEmpty()) System.out.println("No students took this exam yet!");
                        for (Map.Entry<Student, Float> entry : sorted.entrySet()) {
                            System.out.println(entry.getKey() + " -> " + entry.getValue());
                        }
                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                default:
            }
        }
    }


    public void vocabularyMenu(int teacherId){
        Scanner scanner=new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View your courses\n2. View students enrolled in vocabulary courses\n3. Create/modify a vocabulary course\n4. Delete a vocabulary course\n5. Create/modify a vocabulary exam\n6. Delete a vocabulary exam\n7. View the results on exams\n8. Filter students by best grade on exam\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("\n\n\n");


                    try{
                        List<Vocabulary> vocabCourses=vocabController.viewVocabCoursesTaughtByTeacher(teacherId);
                        if (vocabCourses.isEmpty())
                            System.out.println("You don't have any vocabulary courses!");
                        else{
                            System.out.println("Your courses are:\n");
                            for (Vocabulary vocab:vocabCourses)
                                System.out.println(vocab);}
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "2":
                    System.out.println("\n\n\n");


                    List<Student> enrolledStud=vocabController.showStudentsEnrolledInVocabCourses();
                    if (enrolledStud.isEmpty())
                        System.out.println("No students are enrolled in vocabulary courses yet!");
                    else { System.out.println("The following students are enrolled in at least one vocabulary course:\n");
                        for(Student stud:enrolledStud)
                            System.out.println(stud);}
                    System.out.println("\n\n\n");


                    break;

                case "3":
                    System.out.println("\n\n\n");


                    int courseId=readCourseId(scanner);
                    String courseName=readCourseName(scanner);
                    int maxStudents=readMaxStudents(scanner);

                    try{
                        vocabController.createOrUpdateVocabCourse(courseId,teacherId,courseName,maxStudents);
                        System.out.println("Course successfully added/updated!");
                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}


                    String question; String answer;
                    System.out.println("Enter 10 vocabulary words and their meaning:");
                    for (int i=0;i<10;i++)
                    {
                        question=readWord(scanner);
                        answer=readMeaning(scanner);
                        try{vocabController.createQuestion(courseId,question,answer);
                        } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                    }
                    System.out.println("\n\n\n");


                    break;

                case "4":
                    System.out.println("\n\n\n");


                    int courseId4=readCourseId(scanner);

                    try{
                        if (vocabController.removeCourse(courseId4,teacherId))
                            System.out.println("Course deleted successfully!");
                        else
                            System.out.println("You don't have access to this course!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "5":
                    System.out.println("\n\n\n");


                    int examId=readExamId(scanner);
                    String examName=readCourseName(scanner);
                    try{
                        examController.createOrUpdateVocabExam(examId,teacherId,examName);
                        System.out.println("Exam successfully created/updated!");
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}



                    String questionExam; String answerExam;
                    System.out.println("Enter 10 vocabulary words and their meaning:");
                    for (int i=0;i<10;i++)
                    {
                        questionExam=readWord(scanner);
                        answerExam=readMeaning(scanner);
                        try{examController.createVocabQuestion(examId,questionExam,answerExam);
                        } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                    }
                    System.out.println("\n\n\n");

                    break;

                case "6":
                    System.out.println("\n\n\n");

                    int examId6=readExamId(scanner);

                    try{
                        if (examController.removeVocabExam(examId6,teacherId))
                            System.out.println("Exam deleted successfully!");
                        else
                            System.out.println("You don't have access to this exam!");
                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "7":
                    System.out.println("\n\n\n");


                    int examId7=readExamId(scanner);

                    try{
                        List<ExamResult> results=examController.showAllResultsOfVocabTeacherExam(teacherId,examId7);
                        if (results.isEmpty())
                            System.out.println("No student took this exam yet!");
                        else { System.out.println("The results on this exam are:\n");
                            for (ExamResult result:results)
                                System.out.println("Student with id: "+result.getStudent()+" score: "+result.getResult());}
                    }catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");


                    break;

                case "8":
                    System.out.println("\n\n\n");

                    int examId8=readExamId(scanner);
                    try{
                        List<Student> filtered=examController.filterStudentsByBestGrade(teacherId,examId8);
                        if (filtered.isEmpty())
                            System.out.println("No student obtained the maximum grade yet!");
                        else
                        {   System.out.println("The following students got the maximum grade on this exam:\n");
                            for (Student student:filtered)
                                System.out.println(student);}
                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                default:
            }
        }
    }


    private static int readTeacherId(Scanner scanner) {
        System.out.println("Enter teacher ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String readTeacherName(Scanner scanner) {
        System.out.println("Enter teacher name: ");
        return scanner.nextLine();
    }

    private static String readCourseName(Scanner scanner) {
        System.out.println("Enter course name: ");
        return scanner.nextLine();
    }

    private static String readExamName(Scanner scanner) {
        System.out.println("Enter exam name: ");
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

    private static int readMaxStudents(Scanner scanner) {
        System.out.print("Enter max number of students: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String readBookName(Scanner scanner) {
        System.out.println("Enter book name: ");
        return scanner.nextLine();
    }

    private static String readBookAuthor(Scanner scanner) {
        System.out.println("Enter book author: ");
        return scanner.nextLine();
    }

    private static String readTextTitle(Scanner scanner) {
        System.out.println("Enter text title: ");
        return scanner.nextLine();
    }

    private static String readTextAuthor(Scanner scanner){
        System.out.println("Enter text author: ");
        return scanner.nextLine();
    }

    private static String readText(Scanner scanner){
        System.out.println("Enter text (type 0 to stop):");
        String input;
        StringBuilder answer = new StringBuilder();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            answer.append(input).append("\n");
        }
        return answer.toString();
    }

    private static String readQuestion(Scanner scanner){
        System.out.println("Enter question: ");
        return scanner.nextLine();
    }

    private static String readAnswer(Scanner scanner){
        System.out.println("Enter right answer: ");
        return scanner.nextLine();
    }

    private static String readWord(Scanner scanner){
        System.out.println("Enter a word: ");
        return scanner.nextLine();
    }

    private static String readMeaning(Scanner scanner){
        System.out.println("Enter this word's meaning: ");
        return scanner.nextLine();
    }

}
