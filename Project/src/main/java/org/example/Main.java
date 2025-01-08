package org.example;
import java.util.Scanner;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;
import org.example.repo.*;
import org.example.service.*;
import org.example.controller.*;
import org.example.model.*;
import org.example.view.StudentView;
import org.example.view.TeacherView;
import org.example.view.View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a repository:\n\n1. In Memory Repository\n2. In File Repository\n3. In Database Repository\n4. Exit\n");
        String option = scanner.nextLine();
        switch (option) {
            case "4":
                break;
            case "1":
                IRepository<Student> studentRepo=new InMemoryRepository<>();
                IRepository<Teacher> teacherRepo=new InMemoryRepository<>();
                IRepository<Reading> readingRepo=new InMemoryRepository<>();
                IRepository<Grammar> grammarRepo=new InMemoryRepository<>();
                IRepository<Vocabulary> vocabRepo=new InMemoryRepository<>();
                IRepository<ReadingExam> readingExamRepo=new InMemoryRepository<>();
                IRepository<GrammarExam> grammarExamRepo=new InMemoryRepository<>();
                IRepository<VocabularyExam> vocabExamRepo=new InMemoryRepository<>();
                IRepository<ExamResult> examResultRepo=new InMemoryRepository<>();
                IRepository<Question> questionRepo=new InMemoryRepository<>();
                IRepository<Word> wordRepo=new InMemoryRepository<>();
                IRepository<Book> bookRepo=new InMemoryRepository<>();
                IRepository<Enrolled> enrolledRepo = new InMemoryRepository<>();
                IRepository<BookBelongsToCourse> bookBelongsRepo = new InMemoryRepository<>();
                IRepository<PastMistakes> pastMistakesRepo=new InMemoryRepository<>();
                IRepository<PastVocabMistakes> pastVocabMistakesRepo=new InMemoryRepository<>();

                Student student1=new Student("Student1",1);
                Student student2=new Student("Student2",2);
                Student student3=new Student("Student3",3);
                studentRepo.create(student1);
                studentRepo.create(student2);
                studentRepo.create(student3);

                Teacher teacher1=new Teacher("Teacher1",1);
                Teacher teacher2=new Teacher("Teacher2",2);
                Teacher teacher3=new Teacher("Teacher3",3);

                teacherRepo.create(teacher1);
                teacherRepo.create(teacher2);
                teacherRepo.create(teacher3);

                Question question1=new Question(1,"Der Diener kann auf alle Fragen des Ich-Erzählers antworten.","falsch");
                Question question2=new Question(2,"Der Ich-Erzähler nimmt einen Essvorrat.","falsch");
                Question question3=new Question(3,"Der Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.","wahr");
                Question question4=new Question(4,"Die Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.","wahr");

                Question question15=new Question(15,"Der Ich Erzähler wird von einem Geier angegriffen.","wahr");
                Question question16=new Question(16,"Der Herr versucht gleich dem Ich Erzähler zu helfen.","falsch");
                Question question17=new Question(17,"Der Ich Erzähler stirbt am Ende.","wahr");
                Question question18=new Question(18,"Die Parabel kann bedeuten, dass der Tod in einer verzweifelten Situation eine Befreiung ist.","wahr");

                question1.setReadingId(1);
                question2.setReadingId(1);
                question3.setReadingId(1);
                question4.setReadingId(1);

                question15.setReadingExamId(1);
                question16.setReadingExamId(1);
                question17.setReadingExamId(1);
                question18.setReadingExamId(1);

                questionRepo.create(question1);
                questionRepo.create(question2);
                questionRepo.create(question3);
                questionRepo.create(question4);

                questionRepo.create(question15);
                questionRepo.create(question16);
                questionRepo.create(question17);
                questionRepo.create(question18);

                Question question5=new Question(5,"Du (brauchen) _ Hilfe.","brauchst");
                Question question6=new Question(6,"Ich bin _ Hause.","zu");
                Question question7=new Question(7,"Er trägt _.","bei");
                Question question8=new Question(8,"Diana (setzen)_ sich auf das Sofa.","setzt");
                Question question9=new Question(9,"Stefi klettert auf _ Baum.","den");
                Question question10=new Question(10,"Ich (besuchen) _ diese Kirche.","besuche");
                Question question11=new Question(11,"Wir spielen DOTA in _ Klasse.","der");
                Question question12=new Question(12,"Mama kocht immer (lecker)_ Essen","leckeres");
                Question question13=new Question(13,"Der Ball ist unter _ Tisch gerollt.","den");
                Question question14=new Question(14,"Mein Mann kommt immer betrunken _ Hause.","nach");

                Question question19=new Question(19,"Du (sein) _ hier.","bist");
                Question question20=new Question(20,"Er kauft _ (einkaufen).","ein");
                Question question21=new Question(21,"Sie (entscheiden) _ sich.","entscheidet");
                Question question22=new Question(22,"Markus (verschwinden)_ aus der Sichtweite.","verschwindet");
                Question question23=new Question(23,"Ich (unterhalten) _ mich mit ihm gerne.","unterhalte");
                Question question24=new Question(24,"Wir (besuchen) _ dieses Museum.","besuchen");
                Question question25=new Question(25,"Sie (verstehen,sg) _ die Sprache nicht","versteht");
                Question question26=new Question(26,"Mama kocht immer (lecker)_ Essen","leckeres");
                Question question27=new Question(27,"Der Ball ist unter _ Tisch gerollt.","den");
                Question question28=new Question(28,"Nach der Schule gehe ich direkt _ Hause","nach");

                question5.setGrammarId(10);
                question6.setGrammarId(10);
                question7.setGrammarId(10);
                question8.setGrammarId(10);
                question9.setGrammarId(10);
                question10.setGrammarId(10);
                question11.setGrammarId(10);
                question12.setGrammarId(10);
                question13.setGrammarId(10);
                question14.setGrammarId(10);

                question19.setGrammarExamId(2);
                question20.setGrammarExamId(2);
                question21.setGrammarExamId(2);
                question22.setGrammarExamId(2);
                question23.setGrammarExamId(2);
                question24.setGrammarExamId(2);
                question25.setGrammarExamId(2);
                question26.setGrammarExamId(2);
                question27.setGrammarExamId(2);
                question28.setGrammarExamId(2);

                questionRepo.create(question5);
                questionRepo.create(question6);
                questionRepo.create(question7);
                questionRepo.create(question8);
                questionRepo.create(question9);
                questionRepo.create(question10);
                questionRepo.create(question11);
                questionRepo.create(question12);
                questionRepo.create(question13);
                questionRepo.create(question14);

                questionRepo.create(question19);
                questionRepo.create(question20);
                questionRepo.create(question21);
                questionRepo.create(question22);
                questionRepo.create(question23);
                questionRepo.create(question24);
                questionRepo.create(question25);
                questionRepo.create(question26);
                questionRepo.create(question27);
                questionRepo.create(question28);

                Word word1=new Word(1,"dog","Hund");
                Word word2=new Word(2,"cat","Katze");
                Word word3=new Word(3,"apple","Apfel");
                Word word4=new Word(4,"book","Buch");
                Word word5=new Word(5,"house","Haus");
                Word word6=new Word(6,"car","Auto");
                Word word7=new Word(7,"tree","Baum");
                Word word8=new Word(8,"flower","Blume");
                Word word9=new Word(9,"fish","Fish");
                Word word10=new Word(10,"dog","Hund");

                Word word11=new Word(11,"fence","Zaun");
                Word word12=new Word(12,"decision","Entscheidung");
                Word word13=new Word(13,"window","Fenster");
                Word word14=new Word(14,"spider","Spinne");
                Word word15=new Word(15,"skirt","Rock");
                Word word16=new Word(16,"song","Lied");
                Word word17=new Word(17,"boring","langweilig");
                Word word18=new Word(18,"knife","Messer");
                Word word19=new Word(19,"river","Fluss");
                Word word20=new Word(20,"answer","Antwort");

                word1.setVocabId(15);
                word2.setVocabId(15);
                word3.setVocabId(15);
                word4.setVocabId(15);
                word5.setVocabId(15);
                word6.setVocabId(15);
                word7.setVocabId(15);
                word8.setVocabId(15);
                word9.setVocabId(15);
                word10.setVocabId(15);

                word11.setVocabExamId(3);
                word12.setVocabExamId(3);
                word13.setVocabExamId(3);
                word14.setVocabExamId(3);
                word15.setVocabExamId(3);
                word16.setVocabExamId(3);
                word17.setVocabExamId(3);
                word18.setVocabExamId(3);
                word19.setVocabExamId(3);
                word20.setVocabExamId(3);

                wordRepo.create(word1);
                wordRepo.create(word2);
                wordRepo.create(word3);
                wordRepo.create(word4);
                wordRepo.create(word5);
                wordRepo.create(word6);
                wordRepo.create(word7);
                wordRepo.create(word8);
                wordRepo.create(word9);
                wordRepo.create(word10);

                wordRepo.create(word11);
                wordRepo.create(word12);
                wordRepo.create(word13);
                wordRepo.create(word14);
                wordRepo.create(word15);
                wordRepo.create(word16);
                wordRepo.create(word17);
                wordRepo.create(word18);
                wordRepo.create(word19);
                wordRepo.create(word20);

                Book book1=new Book(1,"Das Schloss", "Franz Kafka");
                Book book2=new Book(2,"Die Verwandlung", "Franz Kafka");
                bookRepo.create(book1);
                bookRepo.create(book2);

                Reading reading1=new Reading(1,"Reading1",teacher1.getId(),25);
                reading1.setText("Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"");
                reading1.setTextAuthor("Franz Kafka");
                reading1.setTextTitle("Der Aufbruch");
                readingRepo.create(reading1);

                BookBelongsToCourse bookBelongsToCourse1=new BookBelongsToCourse(1,1,1);
                BookBelongsToCourse bookBelongsToCourse2=new BookBelongsToCourse(2,1,2);
                bookBelongsRepo.create(bookBelongsToCourse1);
                bookBelongsRepo.create(bookBelongsToCourse2);


                ReadingExam readingExam1=new ReadingExam(1,"Reading Exam 1",teacher1.getId());
                readingExam1.setText("Es war ein Geier, der hackte in meine Füße. Stiefel und Strümpfe hatte er schon aufgerissen, nun hackte er schon in die Füße selbst.\n" +
                        "Immer schlug er zu, flog dann unruhig mehrmals um mich und setzte dann die Arbeit fort. Es kam ein Herr vorüber, sah ein Weilchen zu und fragte\n" +
                        "dann, warum ich den Geier dulde. »Ich bin ja wehrlos«, sagte ich, »er kam und fing zu hacken an, da wollte ich ihn natürlich wegtreiben, versuchte\n" +
                        "ihn sogar zu würgen, aber ein solches Tier hat große Kräfte, auch wollte er mir schon ins Gesicht springen, da opferte ich lieber die Füße. Nun sind\n" +
                        "sie schon fast zerrissen.« »Daß Sie sich so quälen lassen«, sagte der Herr, »ein Schuß und der Geier ist erledigt.« »Ist das so?« fragte ich, und wollen\n" +
                        "Sie das besorgen?« »Gern«, sagte der Herr, »ich muß nur nach Hause gehn und mein Gewehr holen. Können Sie noch eine halbe Stunde warten?« »Das weiß ich\n" +
                        "nicht«, sagte ich und stand eine Weile starr vor Schmerz, dann sagte ich: »Bitte, versuchen Sie es für jeden Fall.« »Gut«, sagte der Herr, »ich werde\n" +
                        "mich beeilen.« Der Geier hatte während des Gespräches ruhig zugehört und die Blicke zwischen mir und dem Herrn wandern lassen. Jetzt sah ich, daß er\n" +
                        "alles verstanden hatte, er flog auf, weit beugte er sich zurück, um genug Schwung zu bekommen und stieß dann wie ein Speerwerfer den Schnabel durch meinen\n" +
                        "Mund tief in mich. Zurückfallend fühlte ich befreit, wie er in meinem alle Tiefen füllenden, alle Ufer überfließenden Blut unrettbar ertrank.");
                readingExam1.setTextAuthor("Franz Kafka");
                readingExam1.setTextTitle("Der Geier");
                readingExamRepo.create(readingExam1);


                Grammar grammar1=new Grammar(10,"Grammar1",teacher1.getId(),30);
                grammarRepo.create(grammar1);


                GrammarExam grammarExam1=new GrammarExam(2,"Grammar exam 1", teacher1.getId());
                grammarExamRepo.create(grammarExam1);

                Vocabulary vocabulary1=new Vocabulary(15,"Vocabulary Course 1",teacher1.getId(),15);
                vocabRepo.create(vocabulary1);

                VocabularyExam vocabularyExam1=new VocabularyExam(3,"Vocabulary Exam 1", teacher1.getId());
                vocabExamRepo.create(vocabularyExam1);

                StudentService studentService=new StudentService(studentRepo);
                TeacherService teacherService=new TeacherService(teacherRepo);
                ReadingService readingService=new ReadingService(readingRepo,studentRepo,teacherRepo,questionRepo,bookRepo,enrolledRepo,bookBelongsRepo,pastMistakesRepo);
                GrammarService grammarService=new GrammarService(grammarRepo,studentRepo,teacherRepo,questionRepo, enrolledRepo,pastMistakesRepo);
                VocabService vocabService=new VocabService(vocabRepo,studentRepo,teacherRepo,wordRepo,enrolledRepo,pastVocabMistakesRepo);
                ReadingExamService readingExamService=new ReadingExamService(readingExamRepo,studentRepo,teacherRepo,questionRepo,examResultRepo,readingRepo,enrolledRepo);
                GrammarExamService grammarExamService=new GrammarExamService(grammarExamRepo,studentRepo,teacherRepo,questionRepo,examResultRepo,grammarRepo,enrolledRepo);
                VocabularyExamService vocabularyExamService=new VocabularyExamService(vocabExamRepo,studentRepo,teacherRepo,wordRepo,examResultRepo,vocabRepo,enrolledRepo);

                StudentController studentController=new StudentController(studentService);
                TeacherController teacherController=new TeacherController(teacherService);
                ReadingController readingController=new ReadingController(readingService);
                GrammarController grammarController=new GrammarController(grammarService);
                VocabularyController vocabularyController=new VocabularyController(vocabService);
                ExamController examController=new ExamController(readingExamService,grammarExamService,vocabularyExamService);

                StudentView studentView=new StudentView(studentController,readingController,examController,grammarController,vocabularyController);
                TeacherView teacherView=new TeacherView(teacherController,readingController,vocabularyController,grammarController,examController);

                View view = new View(studentView,teacherView);
                view.start();

                break;
            case "2":

                IRepository<Student> studentRepoFile=InFileRepository.getInstance(Student.class,"student.dat");
                IRepository<Teacher> teacherRepoFile=InFileRepository.getInstance(Teacher.class,"teacher.dat");
                IRepository<Reading> readingRepoFile=InFileRepository.getInstance(Reading.class,"reading.dat");
                IRepository<Grammar> grammarRepoFile=InFileRepository.getInstance(Grammar.class,"grammar.dat");
                IRepository<Vocabulary> vocabRepoFile=InFileRepository.getInstance(Vocabulary.class,"vocab.dat");
                IRepository<ReadingExam> readingExamRepoFile=InFileRepository.getInstance(ReadingExam.class,"readingexam.dat");
                IRepository<GrammarExam> grammarExamRepoFile=InFileRepository.getInstance(GrammarExam.class,"grammarexam.dat");
                IRepository<VocabularyExam> vocabExamRepoFile=InFileRepository.getInstance(VocabularyExam.class,"vocabexam.dat");
                IRepository<ExamResult> examResultRepoFile=InFileRepository.getInstance(ExamResult.class,"examresult.dat");
                IRepository<Question> questionRepoFile=InFileRepository.getInstance(Question.class,"question.dat");
                IRepository<Word> wordRepoFile=InFileRepository.getInstance(Word.class,"word.dat");
                IRepository<Book> bookRepoFile=InFileRepository.getInstance(Book.class,"book.dat");
                IRepository<Enrolled> enrolledRepoFile = InFileRepository.getInstance(Enrolled.class,"enrolled.dat");
                IRepository<BookBelongsToCourse> bookBelongsRepoFile = InFileRepository.getInstance(BookBelongsToCourse.class,"bookbelongstocourse.dat");
                IRepository<PastMistakes> pastMistakesRepoFile=InFileRepository.getInstance(PastMistakes.class,"pastmistakes.dat");
                IRepository<PastVocabMistakes> pastVocabMistakesRepoFile=InFileRepository.getInstance(PastVocabMistakes.class,"pastvocabmistakes.dat");

//                Student student1=new Student("Student1",1);
//                Student student2=new Student("Student2",2);
//                Student student3=new Student("Student3",3);
//                studentRepoFile.create(student1);
//                studentRepoFile.create(student2);
//                studentRepoFile.create(student3);
//
//                Teacher teacher1=new Teacher("Teacher1",1);
//                Teacher teacher2=new Teacher("Teacher2",2);
//                Teacher teacher3=new Teacher("Teacher3",3);
//
//                teacherRepoFile.create(teacher1);
//                teacherRepoFile.create(teacher2);
//                teacherRepoFile.create(teacher3);
//
//                Question question1=new Question(1,"Der Diener kann auf alle Fragen des Ich-Erzählers antworten.","falsch");
//                Question question2=new Question(2,"Der Ich-Erzähler nimmt einen Essvorrat.","falsch");
//                Question question3=new Question(3,"Der Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.","wahr");
//                Question question4=new Question(4,"Die Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.","wahr");
//
//                Question question15=new Question(15,"Der Ich Erzähler wird von einem Geier angegriffen.","wahr");
//                Question question16=new Question(16,"Der Herr versucht gleich dem Ich Erzähler zu helfen.","falsch");
//                Question question17=new Question(17,"Der Ich Erzähler stirbt am Ende.","wahr");
//                Question question18=new Question(18,"Die Parabel kann bedeuten, dass der Tod in einer verzweifelten Situation eine Befreiung ist.","wahr");
//
//                question1.setReadingId(1);
//                question2.setReadingId(1);
//                question3.setReadingId(1);
//                question4.setReadingId(1);
//
//                question15.setReadingExamId(1);
//                question16.setReadingExamId(1);
//                question17.setReadingExamId(1);
//                question18.setReadingExamId(1);
//
//                questionRepoFile.create(question1);
//                questionRepoFile.create(question2);
//                questionRepoFile.create(question3);
//                questionRepoFile.create(question4);
//
//                questionRepoFile.create(question15);
//                questionRepoFile.create(question16);
//                questionRepoFile.create(question17);
//                questionRepoFile.create(question18);
//
//                Question question5=new Question(5,"Du (brauchen) _ Hilfe.","brauchst");
//                Question question6=new Question(6,"Ich bin _ Hause.","zu");
//                Question question7=new Question(7,"Er trägt _.","bei");
//                Question question8=new Question(8,"Diana (setzen)_ sich auf das Sofa.","setzt");
//                Question question9=new Question(9,"Stefi klettert auf _ Baum.","den");
//                Question question10=new Question(10,"Ich (besuchen) _ diese Kirche.","besuche");
//                Question question11=new Question(11,"Wir spielen DOTA in _ Klasse.","der");
//                Question question12=new Question(12,"Mama kocht immer (lecker)_ Essen","leckeres");
//                Question question13=new Question(13,"Der Ball ist unter _ Tisch gerollt.","den");
//                Question question14=new Question(14,"Mein Mann kommt immer betrunken _ Hause.","nach");
//
//                Question question19=new Question(19,"Du (sein) _ hier.","bist");
//                Question question20=new Question(20,"Er kauft _ (einkaufen).","ein");
//                Question question21=new Question(21,"Sie (entscheiden) _ sich.","entscheidet");
//                Question question22=new Question(22,"Markus (verschwinden)_ aus der Sichtweite.","verschwindet");
//                Question question23=new Question(23,"Ich (unterhalten) _ mich mit ihm gerne.","unterhalte");
//                Question question24=new Question(24,"Wir (besuchen) _ dieses Museum.","besuchen");
//                Question question25=new Question(25,"Sie (verstehen,sg) _ die Sprache nicht","versteht");
//                Question question26=new Question(26,"Mama kocht immer (lecker)_ Essen","leckeres");
//                Question question27=new Question(27,"Der Ball ist unter _ Tisch gerollt.","den");
//                Question question28=new Question(28,"Nach der Schule gehe ich direkt _ Hause","nach");
//
//                question5.setGrammarId(10);
//                question6.setGrammarId(10);
//                question7.setGrammarId(10);
//                question8.setGrammarId(10);
//                question9.setGrammarId(10);
//                question10.setGrammarId(10);
//                question11.setGrammarId(10);
//                question12.setGrammarId(10);
//                question13.setGrammarId(10);
//                question14.setGrammarId(10);
//
//                question19.setGrammarExamId(2);
//                question20.setGrammarExamId(2);
//                question21.setGrammarExamId(2);
//                question22.setGrammarExamId(2);
//                question23.setGrammarExamId(2);
//                question24.setGrammarExamId(2);
//                question25.setGrammarExamId(2);
//                question26.setGrammarExamId(2);
//                question27.setGrammarExamId(2);
//                question28.setGrammarExamId(2);
//
//                questionRepoFile.create(question5);
//                questionRepoFile.create(question6);
//                questionRepoFile.create(question7);
//                questionRepoFile.create(question8);
//                questionRepoFile.create(question9);
//                questionRepoFile.create(question10);
//                questionRepoFile.create(question11);
//                questionRepoFile.create(question12);
//                questionRepoFile.create(question13);
//                questionRepoFile.create(question14);
//
//                questionRepoFile.create(question19);
//                questionRepoFile.create(question20);
//                questionRepoFile.create(question21);
//                questionRepoFile.create(question22);
//                questionRepoFile.create(question23);
//                questionRepoFile.create(question24);
//                questionRepoFile.create(question25);
//                questionRepoFile.create(question26);
//                questionRepoFile.create(question27);
//                questionRepoFile.create(question28);
//
//                Word word1=new Word(1,"dog","Hund");
//                Word word2=new Word(2,"cat","Katze");
//                Word word3=new Word(3,"apple","Apfel");
//                Word word4=new Word(4,"book","Buch");
//                Word word5=new Word(5,"house","Haus");
//                Word word6=new Word(6,"car","Auto");
//                Word word7=new Word(7,"tree","Baum");
//                Word word8=new Word(8,"flower","Blume");
//                Word word9=new Word(9,"fish","Fish");
//                Word word10=new Word(10,"dog","Hund");
//
//                Word word11=new Word(11,"fence","Zaun");
//                Word word12=new Word(12,"decision","Entscheidung");
//                Word word13=new Word(13,"window","Fenster");
//                Word word14=new Word(14,"spider","Spinne");
//                Word word15=new Word(15,"skirt","Rock");
//                Word word16=new Word(16,"song","Lied");
//                Word word17=new Word(17,"boring","langweilig");
//                Word word18=new Word(18,"knife","Messer");
//                Word word19=new Word(19,"river","Fluss");
//                Word word20=new Word(20,"answer","Antwort");
//
//                word1.setVocabId(15);
//                word2.setVocabId(15);
//                word3.setVocabId(15);
//                word4.setVocabId(15);
//                word5.setVocabId(15);
//                word6.setVocabId(15);
//                word7.setVocabId(15);
//                word8.setVocabId(15);
//                word9.setVocabId(15);
//                word10.setVocabId(15);
//
//                word11.setVocabExamId(3);
//                word12.setVocabExamId(3);
//                word13.setVocabExamId(3);
//                word14.setVocabExamId(3);
//                word15.setVocabExamId(3);
//                word16.setVocabExamId(3);
//                word17.setVocabExamId(3);
//                word18.setVocabExamId(3);
//                word19.setVocabExamId(3);
//                word20.setVocabExamId(3);
//
//                wordRepoFile.create(word1);
//                wordRepoFile.create(word2);
//                wordRepoFile.create(word3);
//                wordRepoFile.create(word4);
//                wordRepoFile.create(word5);
//                wordRepoFile.create(word6);
//                wordRepoFile.create(word7);
//                wordRepoFile.create(word8);
//                wordRepoFile.create(word9);
//                wordRepoFile.create(word10);
//
//                wordRepoFile.create(word11);
//                wordRepoFile.create(word12);
//                wordRepoFile.create(word13);
//                wordRepoFile.create(word14);
//                wordRepoFile.create(word15);
//                wordRepoFile.create(word16);
//                wordRepoFile.create(word17);
//                wordRepoFile.create(word18);
//                wordRepoFile.create(word19);
//                wordRepoFile.create(word20);
//
//                Book book1=new Book(1,"Das Schloss", "Franz Kafka");
//                Book book2=new Book(2,"Die Verwandlung", "Franz Kafka");
//                bookRepoFile.create(book1);
//                bookRepoFile.create(book2);
//
//                Reading reading1=new Reading(1,"Reading1",teacher1.getId(),25);
//                reading1.setText("Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"");
//                reading1.setTextAuthor("Franz Kafka");
//                reading1.setTextTitle("Der Aufbruch");
//                readingRepoFile.create(reading1);
//
//                BookBelongsToCourse bookBelongsToCourse1=new BookBelongsToCourse(1,1,1);
//                BookBelongsToCourse bookBelongsToCourse2=new BookBelongsToCourse(2,1,2);
//                bookBelongsRepoFile.create(bookBelongsToCourse1);
//                bookBelongsRepoFile.create(bookBelongsToCourse2);
//
//
//                ReadingExam readingExam1=new ReadingExam(1,"Reading Exam 1",teacher1.getId());
//                readingExam1.setText("Es war ein Geier, der hackte in meine Füße. Stiefel und Strümpfe hatte er schon aufgerissen, nun hackte er schon in die Füße selbst.\n" +
//                        "Immer schlug er zu, flog dann unruhig mehrmals um mich und setzte dann die Arbeit fort. Es kam ein Herr vorüber, sah ein Weilchen zu und fragte\n" +
//                        "dann, warum ich den Geier dulde. »Ich bin ja wehrlos«, sagte ich, »er kam und fing zu hacken an, da wollte ich ihn natürlich wegtreiben, versuchte\n" +
//                        "ihn sogar zu würgen, aber ein solches Tier hat große Kräfte, auch wollte er mir schon ins Gesicht springen, da opferte ich lieber die Füße. Nun sind\n" +
//                        "sie schon fast zerrissen.« »Daß Sie sich so quälen lassen«, sagte der Herr, »ein Schuß und der Geier ist erledigt.« »Ist das so?« fragte ich, und wollen\n" +
//                        "Sie das besorgen?« »Gern«, sagte der Herr, »ich muß nur nach Hause gehn und mein Gewehr holen. Können Sie noch eine halbe Stunde warten?« »Das weiß ich\n" +
//                        "nicht«, sagte ich und stand eine Weile starr vor Schmerz, dann sagte ich: »Bitte, versuchen Sie es für jeden Fall.« »Gut«, sagte der Herr, »ich werde\n" +
//                        "mich beeilen.« Der Geier hatte während des Gespräches ruhig zugehört und die Blicke zwischen mir und dem Herrn wandern lassen. Jetzt sah ich, daß er\n" +
//                        "alles verstanden hatte, er flog auf, weit beugte er sich zurück, um genug Schwung zu bekommen und stieß dann wie ein Speerwerfer den Schnabel durch meinen\n" +
//                        "Mund tief in mich. Zurückfallend fühlte ich befreit, wie er in meinem alle Tiefen füllenden, alle Ufer überfließenden Blut unrettbar ertrank.");
//                readingExam1.setTextAuthor("Franz Kafka");
//                readingExam1.setTextTitle("Der Geier");
//                readingExamRepoFile.create(readingExam1);
//
//
//                Grammar grammar1=new Grammar(10,"Grammar1",teacher1.getId(),30);
//                grammarRepoFile.create(grammar1);
//
//
//                GrammarExam grammarExam1=new GrammarExam(2,"Grammar exam 1", teacher1.getId());
//                grammarExamRepoFile.create(grammarExam1);
//
//                Vocabulary vocabulary1=new Vocabulary(15,"Vocabulary Course 1",teacher1.getId(),15);
//                vocabRepoFile.create(vocabulary1);
//
//                VocabularyExam vocabularyExam1=new VocabularyExam(3,"Vocabulary Exam 1", teacher1.getId());
//                vocabExamRepoFile.create(vocabularyExam1);


                StudentService studentServiceFile=new StudentService(studentRepoFile);
                TeacherService teacherServiceFile=new TeacherService(teacherRepoFile);
                ReadingService readingServiceFile=new ReadingService(readingRepoFile,studentRepoFile,teacherRepoFile,questionRepoFile,bookRepoFile,enrolledRepoFile,bookBelongsRepoFile,pastMistakesRepoFile);
                GrammarService grammarServiceFile=new GrammarService(grammarRepoFile,studentRepoFile,teacherRepoFile,questionRepoFile, enrolledRepoFile,pastMistakesRepoFile);
                VocabService vocabServiceFile=new VocabService(vocabRepoFile,studentRepoFile,teacherRepoFile,wordRepoFile,enrolledRepoFile,pastVocabMistakesRepoFile);
                ReadingExamService readingExamServiceFile=new ReadingExamService(readingExamRepoFile,studentRepoFile,teacherRepoFile,questionRepoFile,examResultRepoFile,readingRepoFile,enrolledRepoFile);
                GrammarExamService grammarExamServiceFile=new GrammarExamService(grammarExamRepoFile,studentRepoFile,teacherRepoFile,questionRepoFile,examResultRepoFile,grammarRepoFile,enrolledRepoFile);
                VocabularyExamService vocabularyExamServiceFile=new VocabularyExamService(vocabExamRepoFile,studentRepoFile,teacherRepoFile,wordRepoFile,examResultRepoFile,vocabRepoFile,enrolledRepoFile);

                StudentController studentControllerFile=new StudentController(studentServiceFile);
                TeacherController teacherControllerFile=new TeacherController(teacherServiceFile);
                ReadingController readingControllerFile=new ReadingController(readingServiceFile);
                GrammarController grammarControllerFile=new GrammarController(grammarServiceFile);
                VocabularyController vocabularyControllerFile=new VocabularyController(vocabServiceFile);
                ExamController examControllerFile=new ExamController(readingExamServiceFile,grammarExamServiceFile,vocabularyExamServiceFile);

                StudentView studentViewFile=new StudentView(studentControllerFile,readingControllerFile,examControllerFile,grammarControllerFile,vocabularyControllerFile);
                TeacherView teacherViewFile=new TeacherView(teacherControllerFile,readingControllerFile,vocabularyControllerFile,grammarControllerFile,examControllerFile);

                View viewFile = new View(studentViewFile,teacherViewFile);
                viewFile.start();
                break;

            case "3":
                IRepository<Teacher> teacherRepoDB=new TeacherDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Student> studentRepoDB=new StudentDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Reading> readingRepoDB=new ReadingDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Grammar> grammarRepoDB=new GrammarDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Vocabulary> vocabRepoDB=new VocabularyDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<ReadingExam> readingExamRepoDB=new ReadingExamDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<GrammarExam> grammarExamRepoDB=new GrammarExamDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<VocabularyExam> vocabExamRepoDB=new VocabularyExamDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<ExamResult> examResultRepoDB=new ExamResultDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Question> questionRepoDB=new QuestionDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Word> wordRepoDB=new WordDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Book> bookRepoDB=new BookDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<Enrolled> enrolledRepoDB = new EnrolledDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<BookBelongsToCourse> bookBelongsRepoDB = new BookBelongsToCourseDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<PastMistakes> pastMistakesRepoDB=new PastMistakesDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");
                IRepository<PastVocabMistakes> pastVocabMistakesRepoDB=new PastVocabMistakesDBRepository("jdbc:mysql://127.0.0.1:3306/germanlanguageclub","root","Bill4761");


//                Student student1=new Student("Student1",1);
//                Student student2=new Student("Student2",2);
//                Student student3=new Student("Student3",3);
//                studentRepoDB.create(student1);
//                studentRepoDB.create(student2);
//                studentRepoDB.create(student3);
//
//                Teacher teacher1=new Teacher("Teacher1",1);
//                Teacher teacher2=new Teacher("Teacher2",2);
//                Teacher teacher3=new Teacher("Teacher3",3);
//
//                teacherRepoDB.create(teacher1);
//                teacherRepoDB.create(teacher2);
//                teacherRepoDB.create(teacher3);
//
//                Question question1=new Question(1,"Der Diener kann auf alle Fragen des Ich-Erzählers antworten.","falsch");
//                Question question2=new Question(2,"Der Ich-Erzähler nimmt einen Essvorrat.","falsch");
//                Question question3=new Question(3,"Der Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.","wahr");
//                Question question4=new Question(4,"Die Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.","wahr");
//
//                Question question15=new Question(15,"Der Ich Erzähler wird von einem Geier angegriffen.","wahr");
//                Question question16=new Question(16,"Der Herr versucht gleich dem Ich Erzähler zu helfen.","falsch");
//                Question question17=new Question(17,"Der Ich Erzähler stirbt am Ende.","wahr");
//                Question question18=new Question(18,"Die Parabel kann bedeuten, dass der Tod in einer verzweifelten Situation eine Befreiung ist.","wahr");
//
//                question1.setReadingId(1);
//                question2.setReadingId(1);
//                question3.setReadingId(1);
//                question4.setReadingId(1);
//
//                question15.setReadingExamId(1);
//                question16.setReadingExamId(1);
//                question17.setReadingExamId(1);
//                question18.setReadingExamId(1);
//
//                questionRepoDB.create(question1);
//                questionRepoDB.create(question2);
//                questionRepoDB.create(question3);
//                questionRepoDB.create(question4);
//
//                questionRepoDB.create(question15);
//                questionRepoDB.create(question16);
//                questionRepoDB.create(question17);
//                questionRepoDB.create(question18);
//
//                Question question5=new Question(5,"Du (brauchen) _ Hilfe.","brauchst");
//                Question question6=new Question(6,"Ich bin _ Hause.","zu");
//                Question question7=new Question(7,"Er trägt _.","bei");
//                Question question8=new Question(8,"Diana (setzen)_ sich auf das Sofa.","setzt");
//                Question question9=new Question(9,"Stefi klettert auf _ Baum.","den");
//                Question question10=new Question(10,"Ich (besuchen) _ diese Kirche.","besuche");
//                Question question11=new Question(11,"Wir spielen DOTA in _ Klasse.","der");
//                Question question12=new Question(12,"Mama kocht immer (lecker)_ Essen","leckeres");
//                Question question13=new Question(13,"Der Ball ist unter _ Tisch gerollt.","den");
//                Question question14=new Question(14,"Mein Mann kommt immer betrunken _ Hause.","nach");
//
//                Question question19=new Question(19,"Du (sein) _ hier.","bist");
//                Question question20=new Question(20,"Er kauft _ (einkaufen).","ein");
//                Question question21=new Question(21,"Sie (entscheiden) _ sich.","entscheidet");
//                Question question22=new Question(22,"Markus (verschwinden)_ aus der Sichtweite.","verschwindet");
//                Question question23=new Question(23,"Ich (unterhalten) _ mich mit ihm gerne.","unterhalte");
//                Question question24=new Question(24,"Wir (besuchen) _ dieses Museum.","besuchen");
//                Question question25=new Question(25,"Sie (verstehen,sg) _ die Sprache nicht","versteht");
//                Question question26=new Question(26,"Mama kocht immer (lecker)_ Essen","leckeres");
//                Question question27=new Question(27,"Der Ball ist unter _ Tisch gerollt.","den");
//                Question question28=new Question(28,"Nach der Schule gehe ich direkt _ Hause","nach");
//
//                question5.setGrammarId(10);
//                question6.setGrammarId(10);
//                question7.setGrammarId(10);
//                question8.setGrammarId(10);
//                question9.setGrammarId(10);
//                question10.setGrammarId(10);
//                question11.setGrammarId(10);
//                question12.setGrammarId(10);
//                question13.setGrammarId(10);
//                question14.setGrammarId(10);
//
//                question19.setGrammarExamId(2);
//                question20.setGrammarExamId(2);
//                question21.setGrammarExamId(2);
//                question22.setGrammarExamId(2);
//                question23.setGrammarExamId(2);
//                question24.setGrammarExamId(2);
//                question25.setGrammarExamId(2);
//                question26.setGrammarExamId(2);
//                question27.setGrammarExamId(2);
//                question28.setGrammarExamId(2);
//
//                questionRepoDB.create(question5);
//                questionRepoDB.create(question6);
//                questionRepoDB.create(question7);
//                questionRepoDB.create(question8);
//                questionRepoDB.create(question9);
//                questionRepoDB.create(question10);
//                questionRepoDB.create(question11);
//                questionRepoDB.create(question12);
//                questionRepoDB.create(question13);
//                questionRepoDB.create(question14);
//
//                questionRepoDB.create(question19);
//                questionRepoDB.create(question20);
//                questionRepoDB.create(question21);
//                questionRepoDB.create(question22);
//                questionRepoDB.create(question23);
//                questionRepoDB.create(question24);
//                questionRepoDB.create(question25);
//                questionRepoDB.create(question26);
//                questionRepoDB.create(question27);
//                questionRepoDB.create(question28);
//
//                Word word1=new Word(1,"dog","Hund");
//                Word word2=new Word(2,"cat","Katze");
//                Word word3=new Word(3,"apple","Apfel");
//                Word word4=new Word(4,"book","Buch");
//                Word word5=new Word(5,"house","Haus");
//                Word word6=new Word(6,"car","Auto");
//                Word word7=new Word(7,"tree","Baum");
//                Word word8=new Word(8,"flower","Blume");
//                Word word9=new Word(9,"fish","Fish");
//                Word word10=new Word(10,"dog","Hund");
//
//                Word word11=new Word(11,"fence","Zaun");
//                Word word12=new Word(12,"decision","Entscheidung");
//                Word word13=new Word(13,"window","Fenster");
//                Word word14=new Word(14,"spider","Spinne");
//                Word word15=new Word(15,"skirt","Rock");
//                Word word16=new Word(16,"song","Lied");
//                Word word17=new Word(17,"boring","langweilig");
//                Word word18=new Word(18,"knife","Messer");
//                Word word19=new Word(19,"river","Fluss");
//                Word word20=new Word(20,"answer","Antwort");
//
//                word1.setVocabId(15);
//                word2.setVocabId(15);
//                word3.setVocabId(15);
//                word4.setVocabId(15);
//                word5.setVocabId(15);
//                word6.setVocabId(15);
//                word7.setVocabId(15);
//                word8.setVocabId(15);
//                word9.setVocabId(15);
//                word10.setVocabId(15);
//
//                word11.setVocabExamId(3);
//                word12.setVocabExamId(3);
//                word13.setVocabExamId(3);
//                word14.setVocabExamId(3);
//                word15.setVocabExamId(3);
//                word16.setVocabExamId(3);
//                word17.setVocabExamId(3);
//                word18.setVocabExamId(3);
//                word19.setVocabExamId(3);
//                word20.setVocabExamId(3);
//
//                wordRepoDB.create(word1);
//                wordRepoDB.create(word2);
//                wordRepoDB.create(word3);
//                wordRepoDB.create(word4);
//                wordRepoDB.create(word5);
//                wordRepoDB.create(word6);
//                wordRepoDB.create(word7);
//                wordRepoDB.create(word8);
//                wordRepoDB.create(word9);
//                wordRepoDB.create(word10);
//
//                wordRepoDB.create(word11);
//                wordRepoDB.create(word12);
//                wordRepoDB.create(word13);
//                wordRepoDB.create(word14);
//                wordRepoDB.create(word15);
//                wordRepoDB.create(word16);
//                wordRepoDB.create(word17);
//                wordRepoDB.create(word18);
//                wordRepoDB.create(word19);
//                wordRepoDB.create(word20);
//
//                Book book1=new Book(1,"Das Schloss", "Franz Kafka");
//                Book book2=new Book(2,"Die Verwandlung", "Franz Kafka");
//                bookRepoDB.create(book1);
//                bookRepoDB.create(book2);
//
//                Reading reading1=new Reading(1,"Reading1",teacher1.getId(),25);
//                reading1.setText("Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"");
//                reading1.setTextAuthor("Franz Kafka");
//                reading1.setTextTitle("Der Aufbruch");
//                readingRepoDB.create(reading1);
//
//                BookBelongsToCourse bookBelongsToCourse1=new BookBelongsToCourse(1,1,1);
//                BookBelongsToCourse bookBelongsToCourse2=new BookBelongsToCourse(2,1,2);
//                bookBelongsRepoDB.create(bookBelongsToCourse1);
//                bookBelongsRepoDB.create(bookBelongsToCourse2);
//
//
//                ReadingExam readingExam1=new ReadingExam(1,"Reading Exam 1",teacher1.getId());
//                readingExam1.setText("Es war ein Geier, der hackte in meine Füße. Stiefel und Strümpfe hatte er schon aufgerissen, nun hackte er schon in die Füße selbst.\n" +
//                        "Immer schlug er zu, flog dann unruhig mehrmals um mich und setzte dann die Arbeit fort. Es kam ein Herr vorüber, sah ein Weilchen zu und fragte\n" +
//                        "dann, warum ich den Geier dulde. »Ich bin ja wehrlos«, sagte ich, »er kam und fing zu hacken an, da wollte ich ihn natürlich wegtreiben, versuchte\n" +
//                        "ihn sogar zu würgen, aber ein solches Tier hat große Kräfte, auch wollte er mir schon ins Gesicht springen, da opferte ich lieber die Füße. Nun sind\n" +
//                        "sie schon fast zerrissen.« »Daß Sie sich so quälen lassen«, sagte der Herr, »ein Schuß und der Geier ist erledigt.« »Ist das so?« fragte ich, und wollen\n" +
//                        "Sie das besorgen?« »Gern«, sagte der Herr, »ich muß nur nach Hause gehn und mein Gewehr holen. Können Sie noch eine halbe Stunde warten?« »Das weiß ich\n" +
//                        "nicht«, sagte ich und stand eine Weile starr vor Schmerz, dann sagte ich: »Bitte, versuchen Sie es für jeden Fall.« »Gut«, sagte der Herr, »ich werde\n" +
//                        "mich beeilen.« Der Geier hatte während des Gespräches ruhig zugehört und die Blicke zwischen mir und dem Herrn wandern lassen. Jetzt sah ich, daß er\n" +
//                        "alles verstanden hatte, er flog auf, weit beugte er sich zurück, um genug Schwung zu bekommen und stieß dann wie ein Speerwerfer den Schnabel durch meinen\n" +
//                        "Mund tief in mich. Zurückfallend fühlte ich befreit, wie er in meinem alle Tiefen füllenden, alle Ufer überfließenden Blut unrettbar ertrank.");
//                readingExam1.setTextAuthor("Franz Kafka");
//                readingExam1.setTextTitle("Der Geier");
//                readingExamRepoDB.create(readingExam1);
//
//
//                Grammar grammar1=new Grammar(10,"Grammar1",teacher1.getId(),30);
//                grammarRepoDB.create(grammar1);
//
//
//                GrammarExam grammarExam1=new GrammarExam(2,"Grammar exam 1", teacher1.getId());
//                grammarExamRepoDB.create(grammarExam1);
//
//                Vocabulary vocabulary1=new Vocabulary(15,"Vocabulary Course 1",teacher1.getId(),15);
//                vocabRepoDB.create(vocabulary1);
//
//                VocabularyExam vocabularyExam1=new VocabularyExam(3,"Vocabulary Exam 1", teacher1.getId());
//                vocabExamRepoDB.create(vocabularyExam1);


                StudentService studentServiceDB=new StudentService(studentRepoDB);
                TeacherService teacherServiceDB=new TeacherService(teacherRepoDB);
                ReadingService readingServiceDB=new ReadingService(readingRepoDB,studentRepoDB,teacherRepoDB,questionRepoDB,bookRepoDB,enrolledRepoDB,bookBelongsRepoDB,pastMistakesRepoDB);
                GrammarService grammarServiceDB=new GrammarService(grammarRepoDB,studentRepoDB,teacherRepoDB,questionRepoDB, enrolledRepoDB,pastMistakesRepoDB);
                VocabService vocabServiceDB=new VocabService(vocabRepoDB,studentRepoDB,teacherRepoDB,wordRepoDB,enrolledRepoDB,pastVocabMistakesRepoDB);
                ReadingExamService readingExamServiceDB=new ReadingExamService(readingExamRepoDB,studentRepoDB,teacherRepoDB,questionRepoDB,examResultRepoDB,readingRepoDB,enrolledRepoDB);
                GrammarExamService grammarExamServiceDB=new GrammarExamService(grammarExamRepoDB,studentRepoDB,teacherRepoDB,questionRepoDB,examResultRepoDB,grammarRepoDB,enrolledRepoDB);
                VocabularyExamService vocabularyExamServiceDB=new VocabularyExamService(vocabExamRepoDB,studentRepoDB,teacherRepoDB,wordRepoDB,examResultRepoDB,vocabRepoDB,enrolledRepoDB);

                StudentController studentControllerDB=new StudentController(studentServiceDB);
                TeacherController teacherControllerDB=new TeacherController(teacherServiceDB);
                ReadingController readingControllerDB=new ReadingController(readingServiceDB);
                GrammarController grammarControllerDB=new GrammarController(grammarServiceDB);
                VocabularyController vocabularyControllerDB=new VocabularyController(vocabServiceDB);
                ExamController examControllerDB=new ExamController(readingExamServiceDB,grammarExamServiceDB,vocabularyExamServiceDB);

                StudentView studentViewDB=new StudentView(studentControllerDB,readingControllerDB,examControllerDB,grammarControllerDB,vocabularyControllerDB);
                TeacherView teacherViewDB=new TeacherView(teacherControllerDB,readingControllerDB,vocabularyControllerDB,grammarControllerDB,examControllerDB);

                View viewDB = new View(studentViewDB,teacherViewDB);
                viewDB.start();
                break;
            default:
        }
    }
}
