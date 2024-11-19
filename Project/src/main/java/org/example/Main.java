package org.example;

import java.util.Scanner;
import java.util.stream.IntStream;

import org.example.controller.ExamController;
import org.example.controller.ReadingController;
import org.example.controller.StudentController;
import org.example.controller.TeacherController;
import org.example.repo.*;
import org.example.service.*;
import org.example.model.Reading;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.Exam;
import org.example.view.StudentView;
import org.example.view.TeacherView;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {

        ReadingRepository readingRepo = createInMemoryCourseRepository();
        StudentRepository studentRepo = createInMemoryStudentRepository();
        TeacherRepository teacherRepo = createInMemoryTeacherRepository();
        ExamRepository examRepo = createInMemoryExamRepository();

        ////////
        //WritingRepository writrepo=new WritingRepository();
        //WritingService wrs=new WritingService(writrepo, studentRepo);
        ////// sa modifici constructorul dupa stefipls  in provate iar :3
        //String testare=wrs.chatGPT("hello chat gpt!");
        //String testare2=wrs.extractContentFromResponse(testare);
        //System.out.println(testare2);

        StudentService studentService = new StudentService(studentRepo);
        StudentController studentController = new StudentController(studentService);

        TeacherService teacherService = new TeacherService(teacherRepo);
        TeacherController teacherController = new TeacherController(teacherService);

        ReadingService readingService = new ReadingService(readingRepo, studentRepo);
        ReadingController readingController = new ReadingController(readingService);

        ExamService examService = new ExamService(examRepo,studentRepo);
        ExamController examController = new ExamController(examService);

        readingController.enrollStudent(1,6);
        StudentView studentView = new StudentView(studentController,readingController,examController);
        TeacherView teacherView = new TeacherView(teacherController,readingController);

        View view = new View(studentView,teacherView);
        view.start();

    }

    private static StudentRepository createInMemoryStudentRepository() {
        StudentRepository studentRepo = new StudentRepository();
        IntStream.range(1, 6).forEach(i -> studentRepo.save(new Student("Student" + i, i)));
        return studentRepo;
    }

    private static TeacherRepository createInMemoryTeacherRepository() {
        TeacherRepository teacherRepo = new TeacherRepository();
        IntStream.range(7, 10).forEach(i -> teacherRepo.save(new Teacher("Teacher" + i, i)));
        return teacherRepo;
    }

    private static ReadingRepository createInMemoryCourseRepository() {
        ReadingRepository readingRepo = new ReadingRepository();
        readingRepo.save(new Reading(1, "Reading1", new Teacher("Teacher1", 1), 25));
        readingRepo.save(new Reading(2, "Reading2", new Teacher("Teacher2", 2), 25));
        readingRepo.save(new Reading(3, "Reading3", new Teacher("Teacher3", 3), 25));
        readingRepo.save(new Reading(4, "Reading4", new Teacher("Teacher4", 4), 25));
        readingRepo.save(new Reading(5, "Reading5", new Teacher("Teacher5", 5), 25));

        Reading r1=new Reading(6,"Reading6",new Teacher("Teacher6",6),20);


//        String[][] exercises = {
//                {"Du brauchst Hilfe.", "Du _ Hilfe.", "a. brauchst", "b. braucht", "c. brauche", "You need help.", "a. brauchst"},
//                {"Eine rote Jacke.", "Eine _ Jacke.", "a. rote", "b. roten", "c. roter", "A red jacket.", "a. rote"},
//                {"Ich muss nach Berlin fahren.", "Ich muss _ Berlin fahren.", "a. in", "b. nach", "c. auf", "I have to go to Berlin.", "b. nach"},
//                {"Wir haben eine große Küche.", "Wir _ eine große Küche.", "a. haben", "b. habe", "c. hassen", "We have a big kitchen.", "a. haben"},
//                {"Ich brauche einen neuen Rucksack.", "Ich brauche einen _ Rucksack.", "a. nehmen", "b. neuen", "c. nachbaren", "I need a new backpack.", "b. neuen"},
//                {"Ich bin aus Frankfurt.", "Ich _ aus Frankfurt.", "a. bin", "b. bist", "c. bim", "I am from Frankfurt.", "a. bin"},
//                {"Hallo, ich bin Luca.", "Hallo, _ bin Luca.", "a. ich", "b. du", "c. er", "Hello, I am Luca.", "a. ich"},
//                {"Laura wohnt in Italien.", "Laura _ in Italien.", "a. wir", "b. wahnt", "c. wohnt", "Laura lives in Italy.", "c. wohnt"},
//                {"Sie sind von hier.", "Sie _ von hier.", "a. seien", "b. sind", "c. sinnen", "They are from here.", "b. sind"},
//                {"Du sprichst Italienisch.", "Du _ Italienisch", "a. sprichst", "b. sprechst", "c. sprich", "You speak Italian.", "a. sprichst"},
//                {"Magst du Englisch studieren?", "Magst du Englisch _ ?", "a. studieren", "b. studierst", "c. studierest", "Do you like to study English?", "a. studieren"},
//                {"Er arbeitet am Donnerstag.", "Er _ am Donnerstag.", "a. arbeitest", "b. arbeite", "c. arbeitet", "He works on Thursday.", "c. arbeitet"},
//                {"Du hast einen Hund, nicht?", "Du hast _ Hund, nicht?", "a. ein", "b. einen", "c. einer", "You have a dog, right?", "b. einen"},
//                {"Ich mag diese Wohnung.", "Ich mag _ Wohnung.", "a. dieses", "b. dieser", "c. diese", "I like this living space.", "c. diese"}
//        };

        String[][] readingExercises = {
                {"Der Aufbruch\n" + "Franz Kafka","","",""},
                {"Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"", "", "", ""},
                {"\n\nDer Diener kann auf alle Fragen des Ich-Erzählers antworten.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler nimmt einen Essvorrat.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
        };

        String[][] readingExercises1 = {
                {"Der Geier\n" + "Franz Kafka","","",""},
                {"Es war ein Geier, der hackte in meine Füße. Stiefel und Strümpfe hatte er schon aufgerissen, nun hackte er schon in die Füße selbst.\n" +
                        "Immer schlug er zu, flog dann unruhig mehrmals um mich und setzte dann die Arbeit fort. Es kam ein Herr vorüber, sah ein Weilchen zu und fragte\n" +
                        "dann, warum ich den Geier dulde. »Ich bin ja wehrlos«, sagte ich, »er kam und fing zu hacken an, da wollte ich ihn natürlich wegtreiben, versuchte\n" +
                        "ihn sogar zu würgen, aber ein solches Tier hat große Kräfte, auch wollte er mir schon ins Gesicht springen, da opferte ich lieber die Füße. Nun sind\n" +
                        "sie schon fast zerrissen.« »Daß Sie sich so quälen lassen«, sagte der Herr, »ein Schuß und der Geier ist erledigt.« »Ist das so?« fragte ich, und wollen\n" +
                        "Sie das besorgen?« »Gern«, sagte der Herr, »ich muß nur nach Hause gehn und mein Gewehr holen. Können Sie noch eine halbe Stunde warten?« »Das weiß ich\n" +
                        "nicht«, sagte ich und stand eine Weile starr vor Schmerz, dann sagte ich: »Bitte, versuchen Sie es für jeden Fall.« »Gut«, sagte der Herr, »ich werde\n" +
                        "mich beeilen.« Der Geier hatte während des Gespräches ruhig zugehört und die Blicke zwischen mir und dem Herrn wandern lassen. Jetzt sah ich, daß er\n" +
                        "alles verstanden hatte, er flog auf, weit beugte er sich zurück, um genug Schwung zu bekommen und stieß dann wie ein Speerwerfer den Schnabel durch meinen\n" +
                        "Mund tief in mich. Zurückfallend fühlte ich befreit, wie er in meinem alle Tiefen füllenden, alle Ufer überfließenden Blut unrettbar ertrank.", "", "", ""},
                {"\n\nDer Ich Erzähler wird von einem Geier angegriffen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDer Herr versucht gleich dem Ich Erzähler zu helfen.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich Erzähler stirbt am Ende.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann bedeuten, dass der Tod in einer verzweifelten Situation eine Befreiung ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
        };

        String[][] readingExercises2 = {
                {"Der Steuermann\n" + "Franz Kafka","","",""},
                {"\"Bin ich nicht Steuermann?\" rief ich. \"du?\" fragte ein dunkler hoch gewachsener Mann und strich sich mit der Hand über die Augen,\n" +
                        "als verscheuche er einen Traum. Ich war am Steuer gestanden in der dunklen Nacht, die schwachbrennende Laterne über meinem Kopf, und nun\n" +
                        "war dieser Mann gekommen und wollte mich beiseiteschieben. Und da ich nicht wich, setzte er mir den Fuß auf die Brust und trat mich\n" +
                        "langsam nieder, während ich noch immer an den Stäben des Steuerrades hing und beim Niederfallen es ganz herumriss. Da aber fasste es der Mann,\n" +
                        "brachte es in Ordnung, mich aber stieß er weg. Doch ich besann mich bald, lief zu der Luke, die in den Mannschaftsraum führte und rief:\n" +
                        "\"Mannschaft! Kameraden! Kommt schnell! Ein Fremder hat mich vom Steuer vertrieben!\" Langsam kamen sie, stiegen auf aus der Schiffstreppe,\n" +
                        "schwankende müde mächtige Gestalten. \"Bin ich der Steuermann?\" fragte ich. Sie nickten, aber Blicke hatten sie nur für den Fremden, im Halbkreis standen\n" +
                        "sie um ihn herum und, als er befehlend sagte: \"Stört mich nicht\", sammelten sie sich, nickten mir zu und zogen wieder die Schiffstreppe hinab.\n" +
                        "Was ist das für Volk! Denken sie auch oder schlurfen sie nur sinnlos über die Erde?", "", "", ""},
                {"\n\nDie Kameraden sehen den Ich Erzähler als den richtigen Steuermann an.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich Erzähler leistet keinen Gegenstand, vom Fremden ersetzt zu werden.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDie Kameraden kämpfen den Fremden ab.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDie Parabel kann eine Metapher für die Initiativlosigkeit des einfachen Menschen sein.\n\n", "a. wahr", "b. falsch", "a. wahr"}
        };

        String[][] readingExercises3={
                {"Gibs auf\n" + "Franz Kafka","","",""},
                {"Es war sehr früh am Morgen, die Straßen rein und leer, ich ging zum Bahnhof. Als ich eine Turmuhr mit meiner Uhr verglich, sah ich,\n" +
                        "dass es schon viel später war, als ich geglaubt hatte, ich musste mich sehr beeilen, der Schrecken über diese Entdeckung ließ mich im Weg unsicher\n" +
                        "werden, ich kannte mich in dieser Stadt noch nicht sehr gut aus, glücklicherweise war ein Schutzmann in der Nähe, ich lief zu ihm und fragte ihn\n" +
                        "atemlos nach dem Weg. Er lächelte und sagte: \"Von mir willst du den Weg erfahren?\" \"Ja\", sagte ich, \"da ich ihn selbst nicht finden kann.\" \"Gibs auf,\n" +
                        "gibs auf\", sagte er und wandte sich mit einem großen Schwunge ab, so wie Leute, die mit ihrem Lachen allein sein wollen.", "", "", ""},
                {"\n\nDer Ich Erzähler wandert am Morgen zum Rathaus.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Schutzmann kann dem Erzähler helfen.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Schutzmann kennt den Weg nicht.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann eine Metapher für die Kontrollosigkeit des Lebens sein.\n\n", "a. wahr", "b. falsch", "a. wahr"}
        };

        String[][] readingExercises4={
                {"Kleine Fabel\n" + "Franz Kafka","","",""},
                {"\"Ach\", sagte die Maus, \"die Welt wird enger mit jedem Tag. Zuerst war sie so breit, dass ich Angst hatte, ich lief weiter und war glücklich,\n" +
                        "dass ich endlich rechts und links in der Ferne Mauern sah, aber diese langen Mauern eilen so schnell aufeinander zu, dass ich schon im letzten\n" +
                        "Zimmer bin, und dort im Winkel steht die Falle, in die ich laufe.\" - \"Du musst nur die Laufrichtung ändern\", sagte die Katze und fraß sie.", "", "", ""},
                {"\n\nDie Maus wird von der Katze gefressen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Umwelt der Maus verengt sich mit jedem Zimmer.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann bedeuten, dass Menschen sich willig das Leben zerstören.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann bedeuten, dass die Komplizierung eine Rettung darstellen kann.\n\n", "a. wahr", "b. falsch", "b. falsch"}
        };

        r1.setExercises(readingExercises);
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

        String[][] readingExercises = {
                {"Der Aufbruch\n" + "Franz Kafka","","",""},
                {"Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"", "", "", ""},
                {"\n\nDer Diener kann auf alle Fragen des Ich-Erzählers antworten.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler nimmt einen Essvorrat.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
        };

        exam1.setExercises(readingExercises);
        examRepo.save(exam1);
        return examRepo;
    }
}
