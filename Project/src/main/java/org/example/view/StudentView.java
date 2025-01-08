package org.example.view;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import org.example.controller.GrammarController;
import org.example.controller.ReadingController;
import org.example.controller.*;
import org.example.model.*;
import org.example.model.Exceptions.BusinessLogicException;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;

public class StudentView {
    private StudentController studentController;
    private ReadingController readingController;
    private ExamController examController;
    private GrammarController grammarController;
    private VocabularyController vocabController;

    public StudentView(StudentController studentController, ReadingController readingController, ExamController examController, GrammarController grammarController, VocabularyController vocabController){
        this.studentController=studentController;
        this.readingController=readingController;
        this.examController=examController;
        this.grammarController=grammarController;
        this.vocabController=vocabController;

    }

    public void registerOrSignIn(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Select an option:\n\n1. Log in\n2. Register\n3. Exit");
            String option = scanner.nextLine();
            switch(option){
                case "1":
                    int studentId1=readStudentId(scanner);
                    if (studentId1==0) break;
                    String name1=readStudentName(scanner);
                    try{
                        if (studentController.logInAsStudent(studentId1,name1))
                        {
                            System.out.println("Login successful!\n");
                            start(studentId1);
                        }

                        else System.out.println("Account not found!");
                    } catch(ValidationException e){ System.out.println(e.getMessage());}
                    break;

                case "2":
                    int studentId=readStudentId(scanner);
                    if (studentId==0) break;
                    String name=readStudentName(scanner);
                    try{
                        if (studentController.createStudent(studentId,name))
                        {
                            System.out.println("Registration successful!\n");
                            start(studentId);
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

    public void start(int studentId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. Enroll in a course\n2. View your reading courses\n3. View your grammar courses\n4. View your vocabulary courses\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    enrollMenu(studentId);
                    break;
                case "2":
                    readingMenu(studentId);
                    break;
                case "3":
                    grammarMenu(studentId);
                    break;
                case "4":
                    vocabularyMenu(studentId);
                    break;
                default:
            }
        }
    }


    public void readingMenu(int studentId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View the reading courses you are enrolled in\n2. Practice reading\n3. Review past reading mistakes\n4. Take reading exam\n5. View past reading exam scores\n6. View mandatory books sorted alphabetically\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;

                case "1":
                    System.out.println("\n\n\n");
                    try{
                        List<Reading> courses=readingController.showEnrolledReadingCourses(studentId);
                        if (courses.isEmpty())
                            System.out.println("You are not enrolled in any reading course!");
                        else{ System.out.println("You are enrolled in the following courses:");
                            for (Reading reading:courses)
                                System.out.println(reading); }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "2":
                    System.out.println("\n\n\n");
                    int courseId=readCourseId(scanner);
                    String answer;
                    try{
                        List<Question> questions=readingController.practiceReading(studentId,courseId);
                        if (questions.isEmpty())
                            System.out.println("This course doesn't have questions yet!");
                        else{
                            System.out.println("Read the following text and then answer the questions. The answers are either wahr oder falsch.\n");
                            System.out.println(readingController.getReadingById(courseId).getTextTitle());
                            System.out.println(readingController.getReadingById(courseId).getTextAuthor());
                            System.out.println(readingController.getReadingById(courseId).getText());
                            for (Question q:questions)
                            {
                                System.out.println(q);
                                answer=readAnswer(scanner);
                                try{System.out.println(readingController.handleAnswer(studentId,q.getId(),answer)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                            }
                        }

                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "3":
                    System.out.println("\n\n\n");
                    String answer3;
                    try{
                        List<Question> pastMistakes=readingController.practicePastMistakes(studentId);
                        if (pastMistakes.isEmpty())
                            System.out.println("You have no past mistakes yet!");
                        else{
                            System.out.println("Answer the following questions based on your past texts. The answer is either wahr oder falsch.\n");
                            for (Question q:pastMistakes)
                            {
                                System.out.println(q);
                                answer3=readAnswer(scanner);
                                try{System.out.println(readingController.handlePastMistakesAnswer(studentId,q.getId(),answer3)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                        }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "4":
                    System.out.println("\n\n\n");
                    List<ReadingExam> exams=examController.showAllReadingExams();
                    if (exams.isEmpty()){
                        System.out.println("There are no available reading exams!");
                        break;
                    }

                    else{
                        System.out.println("You can take any of the following exams:");
                        for (ReadingExam exam:exams)
                            System.out.println(exam);
                    }

                    int examId=readExamId(scanner);
                    String answer4;
                    float score= 2.0F;

                    try{
                        List<Question> examQuestions=examController.takeReadingExam(studentId,examId);
                        if (examQuestions.isEmpty()) System.out.println("This exam has no questions!");
                        else{
                            System.out.println("Read the following text and then answer the questions. The answers are either wahr oder falsch.\n Eeach question is worth 2 points.\n");
                            System.out.println(examController.getReadingExamById(examId).getTextTitle());
                            System.out.println(examController.getReadingExamById(examId).getTextAuthor());
                            System.out.println(examController.getReadingExamById(examId).getText());
                            for (Question q:examQuestions)
                            {
                                System.out.println(q);
                                answer4=readAnswer(scanner);
                                try{System.out.println(examController.handleReadingAnswer(studentId,q.getId(),answer4)+"\n");
                                    if (examController.handleReadingAnswer(studentId, q.getId(),answer4).equals("Correct!"))
                                        score+=2;

                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                            examController.addReadingResult(studentId,examId,score);
                        }

                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");

                    break;

                case "5":
                    System.out.println("\n\n\n");
                    try {
                        List<ExamResult> results = examController.showReadingExamResults(studentId);

                        if (results.isEmpty())
                            System.out.println("You have taken no reading exams so far!");

                        else{ for (ExamResult result:results) System.out.println("Reading exam with id: "+result.getExam()+" score: "+result.getResult());}

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "6":
                    System.out.println("\n\n\n");
                    int courseId5=readCourseId(scanner);
                    try{
                        List<Book> books=readingController.sortBooksAlphabeticallyByTitle(studentId,courseId5);
                        if (books.isEmpty())
                            System.out.println("This course has no mandatory books yet!");
                        else {
                            System.out.println("The books for this course are:\n");
                            for (Book book:books) System.out.println(book);
                        }
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                default:
            }
        }
    }



    public void grammarMenu(int studentId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View the grammar courses you are enrolled in\n2. Practice grammar\n3. Review past grammar mistakes\n4. Take grammar exam\n5. View past grammar exam scores\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("\n\n\n");
                    try{
                        List<Grammar> courses=grammarController.showEnrolledGrammarCourses(studentId);
                        if (courses.isEmpty())
                            System.out.println("You are not enrolled in any grammar course!");
                        else{ System.out.println("Your grammar courses are:\n");
                            for (Grammar grammar:courses)
                                System.out.println(grammar); }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "2":
                    System.out.println("\n\n\n");
                    int courseId=readCourseId(scanner);
                    String answer;
                    try{
                        List<Question> questions=grammarController.practiceGrammar(studentId,courseId);
                        if (questions.isEmpty())
                            System.out.println("This course has no questions!");
                        else{
                            System.out.println("For each of the following sentences, enter the missing word in the correct form:\n");
                            for (Question q:questions)
                            {
                                System.out.println(q);
                                answer=readAnswer(scanner);
                                try{System.out.println(grammarController.handleAnswer(studentId,q.getId(),answer)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                            }
                        }

                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "3":
                    System.out.println("\n\n\n");
                    String answer3;
                    try{
                        List<Question> pastMistakes=grammarController.practicePastMistakes(studentId);
                        if (pastMistakes.isEmpty())
                            System.out.println("You have no past mistakes yet!");
                        else{
                            System.out.println("For each of the following sentences, enter the missing word in the correct form:\n");
                            for (Question q:pastMistakes)
                            {
                                System.out.println(q);
                                answer3=readAnswer(scanner);
                                try{System.out.println(grammarController.handlePastMistakesAnswer(studentId,q.getId(),answer3)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                        }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "4":
                    System.out.println("\n\n\n");
                    List<GrammarExam> exams=examController.showAllGrammarExams();
                    if (exams.isEmpty()){
                        System.out.println("There are no available grammar exams!");
                        break;
                    }

                    else{
                        System.out.println("You can take any of the following exams:\n");
                        for (GrammarExam exam:exams)
                            System.out.println(exam);
                    }

                    int examId=readExamId(scanner);
                    String answer4;
                    float score= 0.0F;

                    try{
                        List<Question> examQuestions=examController.takeGrammarExam(studentId,examId);
                        if (examQuestions.isEmpty()) System.out.println("This exam has no questions!");
                        else{
                            System.out.println("For each of the following sentences, enter the missing word in the correct form:\nEach answer is worth 1 point.");
                            for (Question q:examQuestions)
                            {
                                System.out.println(q);
                                answer4=readAnswer(scanner);
                                try{System.out.println(examController.handleGrammarAnswer(studentId,q.getId(),answer4)+"\n");
                                    if (examController.handleGrammarAnswer(studentId, q.getId(),answer4).equals("Correct!"))
                                        score+=1;

                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                            examController.addGrammarResult(studentId,examId,score);
                        }

                    }catch(ValidationException | EntityNotFoundException |BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "5":
                    System.out.println("\n\n\n");
                    try {
                        List<ExamResult> results = examController.showGrammarExamResults(studentId);

                        if (results.isEmpty())
                            System.out.println("You have taken no grammar exams so far!");

                        else{ for (ExamResult result:results) System.out.println("Grammar exam with id: "+result.getExam()+" score: "+result.getResult());}

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;
                default:
            }
        }
    }


    public void vocabularyMenu(int studentId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.print("Select an option:\n\n1. View the vocabulary courses you are enrolled in\n2. Practice vocabulary\n3. Review past vocabulary mistakes\n4. Take vocabulary exam\n5. View past vocabulary exam scores\n0. Exit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    continueLoop = false;
                    break;
                case "1":
                    System.out.println("\n\n\n");
                    try{
                        List<Vocabulary> courses=vocabController.showEnrolledVocabCourses(studentId);
                        if (courses.isEmpty())
                            System.out.println("You are not enrolled in any vocabulary course!");
                        else{ System.out.println("Your vocabulary courses are:\n");
                            for (Vocabulary vocab:courses)
                                System.out.println(vocab); }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "2":
                    System.out.println("\n\n\n");
                    int courseId=readCourseId(scanner);
                    String answer;
                    try{
                        List<Word> questions=vocabController.practiceVocabulary(studentId,courseId);
                        if (questions.isEmpty())
                            System.out.println("This course has no questions!");
                        else{
                            System.out.println("For each word, write its meaning in German (capital letter if needed).\n");
                            for (Word q:questions)
                            {
                                System.out.println(q);
                                answer=readAnswer(scanner);
                                try{System.out.println(vocabController.handleAnswer(studentId,q.getId(),answer)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}

                            }
                        }

                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;
                case "3":
                    System.out.println("\n\n\n");
                    String answer3;
                    try{
                        List<Word> pastMistakes=vocabController.practicePastMistakes(studentId);
                        if (pastMistakes.isEmpty())
                            System.out.println("You have no past mistakes yet!");
                        else{
                            System.out.println("For each word, write its meaning in German (capital letter if needed).\n");
                            for (Word q:pastMistakes)
                            {
                                System.out.println(q);
                                answer3=readAnswer(scanner);
                                try{System.out.println(vocabController.handlePastMistakesAnswer(studentId,q.getId(),answer3)+"\n");
                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                        }

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                case "4":
                    System.out.println("\n\n\n");
                    List<VocabularyExam> exams=examController.showAllVocabExams();
                    if (exams.isEmpty()){
                        System.out.println("There are no available vocabulary exams!");
                        break;
                    }

                    else{
                        System.out.println("You can take any of the following exams:\n");
                        for (VocabularyExam exam:exams)
                            System.out.println(exam);
                    }


                    int examId=readExamId(scanner);
                    String answer4;
                    float score= 0.0F;

                    try{
                        List<Word> examQuestions=examController.takeVocabExam(studentId,examId);
                        if (examQuestions.isEmpty()) System.out.println("This exam has no questions!");
                        else{
                            System.out.println("For each word, write its meaning in German (capital letter if needed).\nEach word is worth 1 point.\n");
                            for (Word q:examQuestions)
                            {
                                System.out.println(q);
                                answer4=readAnswer(scanner);
                                try{System.out.println(examController.handleVocabAnswer(studentId,q.getId(),answer4)+"\n");
                                    if (examController.handleVocabAnswer(studentId, q.getId(),answer4).equals("Correct!"))
                                        score+=1;

                                } catch (ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                            }
                            examController.addVocabResult(studentId,examId,score);
                        }

                    }catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;
                case "5":
                    System.out.println("\n\n\n");
                    try {
                        List<ExamResult> results = examController.showVocabExamResults(studentId);

                        if (results.isEmpty())
                            System.out.println("You have taken no vocabulary exams so far!");

                        else{ for (ExamResult result:results) System.out.println("Vocabulary exam with id: "+result.getExam()+" score: "+result.getResult());}

                    } catch(ValidationException | EntityNotFoundException e){ System.out.println(e.getMessage());}
                    System.out.println("\n\n\n");
                    break;

                default:
            }
        }
    }


    public void enrollMenu(int studentId){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option:\n\n1. Enroll in a reading course\n2. Enroll in a grammar course\n3. Enroll in a vocabulary course\n0. Exit\n");

        String option = scanner.nextLine();
        switch (option) {
            case "0":
                break;
            case "1":
                System.out.println("\n\n\n");
                List<Reading> readingCourses = readingController.getAvailableReadingCourses();
                if (readingCourses.isEmpty())
                    System.out.println("There are no available reading courses!");
                else{
                    System.out.println("Available courses:\n");
                    for (Reading course:readingCourses)
                        System.out.println(course);

                    int courseId=readCourseId(scanner);
                    try{
                        readingController.enroll(studentId,courseId);
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                }
                System.out.println("\n\n\n");

                break;
            case "2":
                System.out.println("\n\n\n");
                List<Grammar> grammarCourses = grammarController.getAvailableGrammarCourses();
                if (grammarCourses.isEmpty())
                    System.out.println("There are no available grammar courses!");
                else{
                    System.out.println("Available courses:\n");
                    for (Grammar course:grammarCourses)
                        System.out.println(course);

                    int courseId2=readCourseId(scanner);
                    try{
                        grammarController.enroll(studentId,courseId2);
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                }
                System.out.println("\n\n\n");

                break;
            case "3":
                System.out.println("\n\n\n");
                List<Vocabulary> vocabCourses = vocabController.getAvailableVocabCourses();
                if (vocabCourses.isEmpty())
                    System.out.println("There are no available grammar courses!");
                else{
                    System.out.println("Available courses:\n");
                    for (Vocabulary course:vocabCourses)
                        System.out.println(course);

                    int courseId3=readCourseId(scanner);
                    try{
                        vocabController.enroll(studentId,courseId3);
                    } catch(ValidationException | EntityNotFoundException | BusinessLogicException e){ System.out.println(e.getMessage());}
                }
                System.out.println("\n\n\n");

                break;

            default:
        }
    }


    private static int readStudentId(Scanner scanner) {
        System.out.println("Enter student ID: ");
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){System.out.println("Enter a valid int!"); return 0;}

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

    private static String readAnswer(Scanner scanner) {
        System.out.println("Enter your answer: ");
        return scanner.nextLine();
    }

}

