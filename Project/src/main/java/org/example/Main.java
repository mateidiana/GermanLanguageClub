package org.example;

import java.util.Scanner;
import java.util.stream.IntStream;

import org.example.controller.ExamController;
import org.example.controller.ReadingController;
import org.example.repo.ExamRepository;
import org.example.service.ExamService;
import org.example.service.ReadingService;
import org.example.model.Reading;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.Exam;
import org.example.repo.StudentRepository;
import org.example.repo.ReadingRepository;
import org.example.view.StudentView;
import org.example.view.TeacherView;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {

        ReadingRepository readingRepo = createInMemoryCourseRepository();
        StudentRepository studentRepo = createInMemoryStudentRepository();
        ExamRepository examRepo = createInMemoryExamRepository();

        ReadingService readingService = new ReadingService(readingRepo, studentRepo);
        ReadingController readingController = new ReadingController(readingService);

        ExamService examService = new ExamService(examRepo,studentRepo);
        ExamController examController = new ExamController(examService);

        readingController.enrollStudent(1,6);
        StudentView studentView = new StudentView(readingController,examController);
        TeacherView teacherView = new TeacherView(readingController);

        View view = new View(studentView,teacherView);
        view.start();

    }

    private static StudentRepository createInMemoryStudentRepository() {
        StudentRepository studentRepo = new StudentRepository();
        IntStream.range(1, 6).forEach(i -> studentRepo.save(new Student("Student" + i, i)));
        return studentRepo;
    }

    private static ReadingRepository createInMemoryCourseRepository() {
        ReadingRepository readingRepo = new ReadingRepository();
        readingRepo.save(new Reading(1, "Reading1", new Teacher("Teacher1", 1), 25));
        readingRepo.save(new Reading(2, "Reading2", new Teacher("Teacher2", 2), 25));
        readingRepo.save(new Reading(3, "Reading3", new Teacher("Teacher3", 3), 25));
        readingRepo.save(new Reading(4, "Reading4", new Teacher("Teacher4", 4), 25));
        readingRepo.save(new Reading(5, "Reading5", new Teacher("Teacher5", 5), 25));

        Reading r1=new Reading(6,"Reading6",new Teacher("Teacher6",6),20);


        String[][] exercises = {
                {"Du brauchst Hilfe.", "Du _ Hilfe.", "a. brauchst", "b. braucht", "c. brauche", "You need help.", "a. brauchst"},
                {"Eine rote Jacke.", "Eine _ Jacke.", "a. rote", "b. roten", "c. roter", "A red jacket.", "a. rote"},
                {"Ich muss nach Berlin fahren.", "Ich muss _ Berlin fahren.", "a. in", "b. nach", "c. auf", "I have to go to Berlin.", "b. nach"},
                {"Wir haben eine große Küche.", "Wir _ eine große Küche.", "a. haben", "b. habe", "c. hassen", "We have a big kitchen.", "a. haben"},
                {"Ich brauche einen neuen Rucksack.", "Ich brauche einen _ Rucksack.", "a. nehmen", "b. neuen", "c. nachbaren", "I need a new backpack.", "b. neuen"},
                {"Ich bin aus Frankfurt.", "Ich _ aus Frankfurt.", "a. bin", "b. bist", "c. bim", "I am from Frankfurt.", "a. bin"},
                {"Hallo, ich bin Luca.", "Hallo, _ bin Luca.", "a. ich", "b. du", "c. er", "Hello, I am Luca.", "a. ich"},
                {"Laura wohnt in Italien.", "Laura _ in Italien.", "a. wir", "b. wahnt", "c. wohnt", "Laura lives in Italy.", "c. wohnt"},
                {"Sie sind von hier.", "Sie _ von hier.", "a. seien", "b. sind", "c. sinnen", "They are from here.", "b. sind"},
                {"Du sprichst Italienisch.", "Du _ Italienisch", "a. sprichst", "b. sprechst", "c. sprich", "You speak Italian.", "a. sprichst"},
                {"Magst du Englisch studieren?", "Magst du Englisch _ ?", "a. studieren", "b. studierst", "c. studierest", "Do you like to study English?", "a. studieren"},
                {"Er arbeitet am Donnerstag.", "Er _ am Donnerstag.", "a. arbeitest", "b. arbeite", "c. arbeitet", "He works on Thursday.", "c. arbeitet"},
                {"Du hast einen Hund, nicht?", "Du hast _ Hund, nicht?", "a. ein", "b. einen", "c. einer", "You have a dog, right?", "b. einen"},
                {"Ich mag diese Wohnung.", "Ich mag _ Wohnung.", "a. dieses", "b. dieser", "c. diese", "I like this living space.", "c. diese"}
        };

        r1.setExercises(exercises);
        readingRepo.save(r1);
        return readingRepo;
    }

    private static ExamRepository createInMemoryExamRepository(){
        ExamRepository examRepo=new ExamRepository();
        Exam exam1=new Exam(1,"ReadingExam1",new Teacher("Teacher1",1));
        String[][] exercises = {
                {"Du brauchst Hilfe.", "Du _ Hilfe.", "a. brauchst", "b. braucht", "c. brauche", "You need help.", "a. brauchst"},
                {"Eine rote Jacke.", "Eine _ Jacke.", "a. rote", "b. roten", "c. roter", "A red jacket.", "a. rote"},
                {"Ich muss nach Berlin fahren.", "Ich muss _ Berlin fahren.", "a. in", "b. nach", "c. auf", "I have to go to Berlin.", "b. nach"},
                {"Wir haben eine große Küche.", "Wir _ eine große Küche.", "a. haben", "b. habe", "c. hassen", "We have a big kitchen.", "a. haben"},
                {"Ich brauche einen neuen Rucksack.", "Ich brauche einen _ Rucksack.", "a. nehmen", "b. neuen", "c. nachbaren", "I need a new backpack.", "b. neuen"},
                {"Ich bin aus Frankfurt.", "Ich _ aus Frankfurt.", "a. bin", "b. bist", "c. bim", "I am from Frankfurt.", "a. bin"},
                {"Hallo, ich bin Luca.", "Hallo, _ bin Luca.", "a. ich", "b. du", "c. er", "Hello, I am Luca.", "a. ich"},
                {"Laura wohnt in Italien.", "Laura _ in Italien.", "a. wir", "b. wahnt", "c. wohnt", "Laura lives in Italy.", "c. wohnt"},
                {"Sie sind von hier.", "Sie _ von hier.", "a. seien", "b. sind", "c. sinnen", "They are from here.", "b. sind"},
                {"Du sprichst Italienisch.", "Du _ Italienisch", "a. sprichst", "b. sprechst", "c. sprich", "You speak Italian.", "a. sprichst"},
                {"Magst du Englisch studieren?", "Magst du Englisch _ ?", "a. studieren", "b. studierst", "c. studierest", "Do you like to study English?", "a. studieren"},
                {"Er arbeitet am Donnerstag.", "Er _ am Donnerstag.", "a. arbeitest", "b. arbeite", "c. arbeitet", "He works on Thursday.", "c. arbeitet"},
                {"Du hast einen Hund, nicht?", "Du hast _ Hund, nicht?", "a. ein", "b. einen", "c. einer", "You have a dog, right?", "b. einen"},
                {"Ich mag diese Wohnung.", "Ich mag _ Wohnung.", "a. dieses", "b. dieser", "c. diese", "I like this living space.", "c. diese"}
        };
        exam1.setExercises(exercises);
        examRepo.save(exam1);
        return examRepo;
    }
}
