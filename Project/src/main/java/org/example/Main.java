package org.example;

import java.util.Scanner;
import java.util.stream.IntStream;
import org.example.controller.ReadingController;
import org.example.service.ReadingService;
import org.example.model.Reading;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.repo.StudentRepository;
import org.example.repo.ReadingRepository;
import org.example.view.StudentView;
import org.example.view.TeacherView;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {

        ReadingRepository readingRepo = createInMemoryCourseRepository();
        StudentRepository studentRepo = createInMemoryStudentRepository();

        ReadingService readingService = new ReadingService(readingRepo, studentRepo);
        ReadingController readingController = new ReadingController(readingService);

        StudentView studentView = new StudentView(readingController);
        TeacherView teacherView = new TeacherView(readingController);
        //studentView.start();
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
                {"Du brauchst Hilfe.", "Du _ Hilfe.", "a. brauchst", "b. braucht", "c. brauche", "You need help."},
                {"Eine rote Jacke.", "Eine _ Jacke .", "a. rote", "b. roten", "c. roter", "A red jacket."},
                {"Ich muss nach Berlin fahren.", "Ich muss _ Berlin fahren", "a. in", "b. nach", "c. auf", "I have to go to Berlin."},
                {"Wir haben eine große Küche.", "Wir _ eine große Küche.", "a. haben", "b. habe", "c. hassen", "We have a big kitchen."},
                {"Ich brauche einen neuen Rucksack.", "Ich brauche einen _ Rucksack.", "a. nehmen", "b. neuen", "c. nachbaren", "I need a new backpack."},
                {"Ich bin aus Frankfurt.", "Ich _ aus Frankfurt.", "a. bin", "b. bist", "c. bim", "I am from Frankfurt."},
                {"Hallo, ich bin Luca.", "Hallo, _ bin Luca.", "a. ich", "b. du", "c. er", "Hello, I am Luca."},
                {"Laura wohnt in Italien.", "Laura _ in Italien.", "a. wir", "b. wahnt", "c. wohnt", "Laura lives in Italy."},
                {"Sie sind von hier.", "Sie _ von hier.", "a. seien", "b. sind", "c. sinnen", "They are from here."},
                {"Du sprichst Italienisch.", "Du _ Italienisch", "a. sprichst", "b. sprechst", "c. sprich", "You speak Italian."},
                {"Magst du Englisch studieren?", "Magst du Englisch _ ?", "a. studieren", "b. studierst", "c. studierest", "Do you like to study English?"},
                {"Er arbeitet am Donnerstag.", "Er _ am Donnerstag.", "a. arbeitest", "b. arbeite", "c. arbeitet", "He works on Thursday."},
                {"Du hast einen Hund, nicht?", "Du hast _ Hund, nicht?", "a. ein", "b. einen", "c. einer", "You have a dog, right?"},
                {"Ich mag diese Wohnung.", "Ich mag _ Wohnung.", "a. dieses", "b. dieser", "c. diese", "I like this living space."}
        };

        r1.setExercises(exercises);
        readingRepo.save(r1);
        return readingRepo;
    }
}
