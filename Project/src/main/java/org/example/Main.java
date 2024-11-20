package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.List;
import org.example.controller.*;
import org.example.repo.*;
import org.example.service.*;
import org.example.model.Reading;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.*;
import org.example.view.StudentView;
import org.example.view.TeacherView;
import org.example.view.View;
import java.util.Map;
import java.util.HashMap;

/**
 * Application entry point
 * Initializes all repositories, services and controllers
 */
public class Main {
    public static void main(String[] args) {

        ReadingRepository readingRepo = createInMemoryReadingRepository();
        StudentRepository studentRepo = createInMemoryStudentRepository();
        TeacherRepository teacherRepo = createInMemoryTeacherRepository();
        GrammarRepository grammarRepo= createInMemoryGrammarRepository();
        VocabRepository vocabRepo= createInMemoryVocabRepository();
        ExamRepository examRepo = createInMemoryExamRepository();
        WritingRepository writingRepo= createInMemoryWritingRepository();

        GrammarService grammarService= new GrammarService(grammarRepo, studentRepo, teacherRepo);
        GrammarController grammarController = new GrammarController(grammarService);

        StudentService studentService = new StudentService(studentRepo);
        StudentController studentController = new StudentController(studentService);

        TeacherService teacherService = new TeacherService(teacherRepo);
        TeacherController teacherController = new TeacherController(teacherService);

        ReadingService readingService = new ReadingService(readingRepo, studentRepo, teacherRepo);
        ReadingController readingController = new ReadingController(readingService);

        ExamService examService = new ExamService(examRepo,studentRepo, teacherRepo);
        ExamController examController = new ExamController(examService);

        VocabService vocabService= new VocabService(vocabRepo, studentRepo, teacherRepo);
        VocabController vocabController = new VocabController(vocabService);

        WritingService writingService = new WritingService(writingRepo, studentRepo,teacherRepo);
        WritingController writingController = new WritingController(writingService);

        readingController.changeTeacherAccess(1,1);
        readingController.changeTeacherAccess(2,1);
        readingController.changeTeacherAccess(3,1);
        readingController.changeTeacherAccess(4,1);
        readingController.changeTeacherAccess(5,1);
        readingController.changeTeacherAccess(6,1);
        writingController.changeTeacherAccessToWritingCourse(30,1);
        vocabController.changeTeacherAccessToVocabCourse(20,1);
        grammarController.changeTeacherAccessToGrammarCourse(10,1);
        examController.changeTeacherAccessToExam(1, 4);

        readingController.enrollStudent(1,6);
        grammarController.enrollStudent(1,10);
        vocabController.enrollStudent(1, 20);
        writingController.enrollStudent(1, 30);


        StudentView studentView = new StudentView(studentController,readingController,examController, grammarController, vocabController, writingController);
        TeacherView teacherView = new TeacherView(teacherController,readingController, writingController,vocabController,grammarController,examController);

        View view = new View(studentView,teacherView);
        view.start();

    }

    /**
     *
     * @return a student repository in memory
     */
    private static StudentRepository createInMemoryStudentRepository() {
        StudentRepository studentRepo = new StudentRepository();
        IntStream.range(1, 6).forEach(i -> studentRepo.save(new Student("Student" + i, i)));
        return studentRepo;
    }

    /**
     *
     * @return a teacher repository in memory
     */
    private static TeacherRepository createInMemoryTeacherRepository() {
        TeacherRepository teacherRepo = new TeacherRepository();
        IntStream.range(1, 6).forEach(i -> teacherRepo.save(new Teacher("Teacher" + i, i)));

        Teacher teacher1=new Teacher("teacher111",111);
        teacherRepo.save(teacher1);
        return teacherRepo;
    }

    /**
     *
     * @return a reading courses repository in memory
     */
    private static ReadingRepository createInMemoryReadingRepository() {
        ReadingRepository readingRepo = new ReadingRepository();

        Reading r2=new Reading(1, "Reading1", new Teacher("Teacher11", 11), 25);
        Reading r3=new Reading(2, "Reading2", new Teacher("Teacher21", 21), 25);
        Reading r4=new Reading(3, "Reading3", new Teacher("Teacher31", 31), 25);
        Reading r5=new Reading(4, "Reading4", new Teacher("Teacher41", 41), 25);
        Reading r6=new Reading(5, "Reading5", new Teacher("Teacher51", 51), 25);
        Reading r1=new Reading(6, "Reading6", new Teacher("Teacher6",  61),  20);


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
        List<String> books=new ArrayList<>();
        books.add("Die Verwandlung");
        r1.setExercises(readingExercises);
        r2.setExercises(readingExercises1);
        r3.setExercises(readingExercises2);
        r4.setExercises(readingExercises3);
        r5.setExercises(readingExercises4);
        r6.setExercises(readingExercises);
        r1.setMandatoryBooks(books);
        readingRepo.save(r1);
        readingRepo.save(r2);
        readingRepo.save(r3);
        readingRepo.save(r4);
        readingRepo.save(r5);
        readingRepo.save(r6);
        return readingRepo;
    }

    /**
     *
     * @return a repository for grammar courses in memory
     */
    private static GrammarRepository createInMemoryGrammarRepository() {
        Grammar g1 = new Grammar(10, "Grammar1", new Teacher("Teacher1", 31), 25);
        Grammar g2 = new Grammar(11, "Grammar2", new Teacher("Teacher2", 32), 30);
        Grammar g3 = new Grammar(12, "Grammar3", new Teacher("Teacher3", 33), 35);
        Grammar g4 = new Grammar(13, "Grammar4", new Teacher("Teacher4", 34), 40);
        Grammar g5 = new Grammar(14, "Grammar5", new Teacher("Teacher5", 35), 45);

        GrammarRepository grammarRepo=new GrammarRepository();
        String [][] grammarExercises={
                { "Du (brauchen) _ Hilfe.", "brauchst" },
                { "Ich bin _ Hause.", "zu" },
                { "Er trägt _.", "bei" },
                { "Diana (setzen)_ sich auf das Sofa.", "setzt" },
                { "Stefi klettert auf _ Baum.", "den" },
                { "Ich (besuchen) _ diese Kirche.", "besuche" },
                { "Wir spielen DOTA in _ Klasse.", "der" },
                { "Mama kocht immer (lecker)_ Essen", "leckeres" },
                { "Der Ball ist unter _ Tisch gerollt.", "den" },
                { "Mein Mann kommt immer betrunken _ Hause.", "nach" }
        };
        g1.setExercises(grammarExercises);
        grammarRepo.save(g1);

        g2.setExercises(grammarExercises);
        grammarRepo.save(g2);

        g3.setExercises(grammarExercises);
        grammarRepo.save(g3);

        g4.setExercises(grammarExercises);
        grammarRepo.save(g4);

        g5.setExercises(grammarExercises);
        grammarRepo.save(g5);

        return grammarRepo;
    }

    /**
     *
     * @return a repository for vocabulary courses in memory
     */
    private static VocabRepository createInMemoryVocabRepository(){
        Vocabulary v1 = new Vocabulary(20, "Vocabulary1", new Teacher("Teacher1", 31), 25);
        Vocabulary v2 = new Vocabulary(21, "Vocabulary2", new Teacher("Teacher2", 32), 30);
        Vocabulary v3 = new Vocabulary(22, "Vocabulary3", new Teacher("Teacher3", 33), 35);
        Vocabulary v4 = new Vocabulary(23, "Vocabulary4", new Teacher("Teacher4", 34), 40);
        Vocabulary v5 = new Vocabulary(24, "Vocabulary5", new Teacher("Teacher5", 35), 45);

        VocabRepository vocabularyRepo = new VocabRepository();
        Map<String, String> vocabularyExercises = new HashMap<>();

        vocabularyExercises.put("Hund", "dog");
        vocabularyExercises.put("Katze", "cat");
        vocabularyExercises.put("Apfel", "apple");
        vocabularyExercises.put("Buch", "book");
        vocabularyExercises.put("Haus", "house");
        vocabularyExercises.put("Auto", "car");
        vocabularyExercises.put("Baum", "tree");
        vocabularyExercises.put("Blume", "flower");
        vocabularyExercises.put("Fisch", "fish");
        vocabularyExercises.put("Brot", "bread");
        vocabularyExercises.put("Schule", "school");
        vocabularyExercises.put("Stuhl", "chair");
        vocabularyExercises.put("Tisch", "table");
        vocabularyExercises.put("Fenster", "window");
        vocabularyExercises.put("Tür", "door");
        vocabularyExercises.put("Sonne", "sun");
        vocabularyExercises.put("Mond", "moon");
        vocabularyExercises.put("Wasser", "water");
        vocabularyExercises.put("Feuer", "fire");
        vocabularyExercises.put("Freund", "friend");
        vocabularyExercises.put("schnell", "fast");
        vocabularyExercises.put("langsam", "slow");
        vocabularyExercises.put("klein", "small");
        vocabularyExercises.put("groß", "big");
        vocabularyExercises.put("alt", "old");
        vocabularyExercises.put("jung", "young");
        vocabularyExercises.put("kalt", "cold");
        vocabularyExercises.put("heiß", "hot");
        vocabularyExercises.put("glücklich", "happy");
        vocabularyExercises.put("traurig", "sad");
        vocabularyExercises.put("laufen", "to run");
        vocabularyExercises.put("springen", "to jump");
        vocabularyExercises.put("essen", "to eat");
        vocabularyExercises.put("trinken", "to drink");
        vocabularyExercises.put("schlafen", "to sleep");
        vocabularyExercises.put("sprechen", "to speak");
        vocabularyExercises.put("lesen", "to read");
        vocabularyExercises.put("schreiben", "to write");
        vocabularyExercises.put("lernen", "to learn");
        vocabularyExercises.put("arbeiten", "to work");


        v1.setWorter(vocabularyExercises);
        vocabularyRepo.save(v1);

        v2.setWorter(vocabularyExercises);
        vocabularyRepo.save(v2);

        v3.setWorter(vocabularyExercises);
        vocabularyRepo.save(v3);

        v4.setWorter(vocabularyExercises);
        vocabularyRepo.save(v4);

        v5.setWorter(vocabularyExercises);
        vocabularyRepo.save(v5);

        return vocabularyRepo;

    }

    /**
     *
     * @return a repository for writing courses in memory
     */
    private static WritingRepository createInMemoryWritingRepository() {
        WritingRepository writingRepo=new WritingRepository();
        Writing w1=new Writing(30,"Writing1",new Teacher("Teacher11", 111), 69);
        Writing w2=new Writing(31,"Writing2",new Teacher("Teacher12", 2), 69);
        Writing w3=new Writing(32,"Writing3",new Teacher("Teacher13", 2), 69);
        Writing w4=new Writing(33,"Writing4",new Teacher("Teacher14", 2), 69);
        Writing w5=new Writing(34,"Writing5",new Teacher("Teacher15", 2), 69);
        String requirement = "Schreibe einen Text über den Frühling. :3";

        w1.setRequirement(requirement);
        writingRepo.save(w1);

        w2.setRequirement(requirement);
        writingRepo.save(w2);

        w3.setRequirement(requirement);
        writingRepo.save(w3);

        w4.setRequirement(requirement);
        writingRepo.save(w4);

        w5.setRequirement(requirement);
        writingRepo.save(w5);

        return writingRepo;
    }

    /**
     *
     * @return a repository for exams in memory
     */
    private static ExamRepository createInMemoryExamRepository(){
        ExamRepository examRepo=new ExamRepository();
        Exam exam1=new Exam(1,"ReadingExam1",new Teacher("ExamTeacher1",2));
        Exam exam2=new Exam(2,"GrammarExam1",new Teacher("ExamTeacher2",2));
        Exam exam3=new Exam(3,"VocabularyExam1",new Teacher("ExamTeacher3",2));
        Exam exam4=new Exam(4,"WritingExam1",new Teacher("ExamTeacher4",2));

        String [][] grammarExercises={
                { "Du (brauchen) _ Hilfe.", "brauchst" },
                { "Ich bin _ Hause.", "zu" },
                { "Er trägt _.", "bei" },
                { "Diana (setzen)_ sich auf das Sofa.", "setzt" },
                { "Stefi klettert auf _ Baum.", "den" },
                { "Ich (besuchen) _ diese Kirche.", "besuche" },
                { "Wir spielen DOTA in _ Klasse.", "der" },
                { "Mama kocht immer (lecker)_ Essen", "leckeres" },
                { "Der Ball ist unter _ Tisch gerollt.", "den" },
                { "Mein Mann kommt immer betrunken _ Hause.", "nach" }
        };

        String[][] readingExercises = {
                {"Der Aufbruch\n" + "Franz Kafka","","",""},
                {"Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"", "", "", ""},
                {"\n\nDer Diener kann auf alle Fragen des Ich-Erzählers antworten.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler nimmt einen Essvorrat.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                {"\n\nDer Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                {"\n\nDie Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
        };

        Map<String, String> vocabularyExercises = new HashMap<>();

        vocabularyExercises.put("Hund", "dog");
        vocabularyExercises.put("Katze", "cat");
        vocabularyExercises.put("Apfel", "apple");
        vocabularyExercises.put("Buch", "book");
        vocabularyExercises.put("Haus", "house");
        vocabularyExercises.put("Auto", "car");
        vocabularyExercises.put("Baum", "tree");
        vocabularyExercises.put("Blume", "flower");
        vocabularyExercises.put("Fisch", "fish");
        vocabularyExercises.put("Brot", "bread");
        vocabularyExercises.put("Schule", "school");
        vocabularyExercises.put("Stuhl", "chair");
        vocabularyExercises.put("Tisch", "table");
        vocabularyExercises.put("Fenster", "window");
        vocabularyExercises.put("Tür", "door");
        vocabularyExercises.put("Sonne", "sun");
        vocabularyExercises.put("Mond", "moon");
        vocabularyExercises.put("Wasser", "water");
        vocabularyExercises.put("Feuer", "fire");
        vocabularyExercises.put("Freund", "friend");
        vocabularyExercises.put("schnell", "fast");
        vocabularyExercises.put("langsam", "slow");
        vocabularyExercises.put("klein", "small");
        vocabularyExercises.put("groß", "big");
        vocabularyExercises.put("alt", "old");
        vocabularyExercises.put("jung", "young");
        vocabularyExercises.put("kalt", "cold");
        vocabularyExercises.put("heiß", "hot");
        vocabularyExercises.put("glücklich", "happy");
        vocabularyExercises.put("traurig", "sad");
        vocabularyExercises.put("laufen", "to run");
        vocabularyExercises.put("springen", "to jump");
        vocabularyExercises.put("essen", "to eat");
        vocabularyExercises.put("trinken", "to drink");
        vocabularyExercises.put("schlafen", "to sleep");
        vocabularyExercises.put("sprechen", "to speak");
        vocabularyExercises.put("lesen", "to read");
        vocabularyExercises.put("schreiben", "to write");
        vocabularyExercises.put("lernen", "to learn");
        vocabularyExercises.put("arbeiten", "to work");

        String exercise="Schreibe einen Text über den Frühling. :3";

        exam1.setExercises(readingExercises);
        examRepo.save(exam1);
        exam2.setExercises(grammarExercises);
        examRepo.save(exam2);
        exam3.setWorter(vocabularyExercises);
        examRepo.save(exam3);
        exam4.setRequirement(exercise);
        examRepo.save(exam4);
        return examRepo;
    }
}
