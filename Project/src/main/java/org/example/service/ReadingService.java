package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import org.example.model.*;
import org.example.repo.GrammarRepository;
import org.example.repo.ReadingRepository;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;

/**
 * Service class that provides business logic related to {@link Reading} objects.
 * It interacts with the {@link ReadingRepository}, {@link StudentRepository}, {@link TeacherRepository} to perform operations
 * like manipulating reading courses.
 */
public class ReadingService {

    private ReadingRepository readingRepo;

    private StudentRepository studentRepo;

    private TeacherRepository teacherRepo;

    public ReadingService(ReadingRepository readingRepo, StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.readingRepo = readingRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    /**
     * Enrolls a student in a specific reading course
     * @param studentId refers to the student to be enrolled
     * @param readingCourseId refers to the id of the course the student is being enrolled in
     */
    public void enroll(Integer studentId, Integer readingCourseId) {
        int alreadyEnrolled=0;
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(readingCourseId);
        for (Course course1:student.getCourses()){
            if (course1.getId()==course.getId())
                alreadyEnrolled=1;
        }
        if (alreadyEnrolled==0){
            studentRepo.delete(student);
            readingRepo.delete(course);
            if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
                course.getEnrolledStudents().add(student);
                student.getCourses().add(course);
                readingRepo.save(course);
                studentRepo.save(student);
            }
        }

    }

    /**
     * Updates a student's past mistakes in form of a matrix
     * @param originalMatrix Refers to a student's past mistakes
     * @param newRow Refers to the latest exercise added
     * @return updated past mistakes
     */
    public static String[][] appendRow(String[][] originalMatrix, String[] newRow) {
        if (originalMatrix==null||originalMatrix.length==0)
        {
            String[][] newMatrix = new String[1][100];
            newMatrix[0]=newRow;
            return newMatrix;
        }

        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        String[][] newMatrix = new String[numRows + 1][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                newMatrix[i][j] = originalMatrix[i][j];
            }
        }

        for (int j = 0; j < numCols; j++) {
            newMatrix[numRows][j] = newRow[j];
        }

        return newMatrix;
    }

    /**
     * A student can practice German reading by answering text related questions. Wrong answers
     * can be reviewed later
     * @param studentId Refers to a student who practices reading
     * @param courseId Refers to the course the student practices in
     */
    public void practiceReading(Integer studentId, Integer courseId){
        System.out.println("\n\nLese den folgenden Text durch und beantworte die Fragen\n\n");
        Student student = studentRepo.getById(studentId);
        Reading course = readingRepo.getById(courseId);
        String[][] exercises=course.getExercises();
        Scanner scanner = new Scanner(System.in);
        String[] exercise;
        int foundCourse=0;
        int mistakeCounter=0;
        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
            {
                foundCourse=1;
                System.out.println(exercises[0][0]);
                System.out.println(exercises[1][0]);
                for (int i=2;i<6;i++)
                {
                    exercise=exercises[i];
                    System.out.println(exercise[0]+exercise[1]+"\n"+exercise[2]);
                    System.out.println("Your answer: ");
                    char answer = scanner.nextLine().charAt(0);
                    int found=0;

                    if (answer=='a' || answer=='b')
                    {
                        for (int j=1;j<=2;j++)
                        {
                            if (exercise[j].charAt(0)==answer && exercise[j].charAt(1)=='.')
                                if (exercise[j] == exercise[3])
                                {
                                    System.out.println("Correct! " + exercise[3]);
                                    found=1;
                                    break;
                                }
                        }
                        if (found==0)
                        {
                            System.out.println("Wrong! The right answer was " + exercise[3]);
                            mistakeCounter+=1;
                            if (mistakeCounter==1)
                            {
                                student.setPastMistakes(appendRow(student.getPastMistakes(),exercises[0]));
                                student.setPastMistakes(appendRow(student.getPastMistakes(),exercises[1]));
                            }
                            student.setPastMistakes(appendRow(student.getPastMistakes(),exercise));
                        }
                    }
                    else
                        System.out.println("Invalid choice!");
                }
                System.out.println("\n\n\nPractice complete!\n\n\n");
            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in this course!");

    }

    /**
     * A student can practice past reading mistakes
     * @param studentId Refers to a specific student
     */
    public void reviewPastReadingMistakes(Integer studentId){
        Scanner scanner = new Scanner(System.in);
        Student student = studentRepo.getById(studentId);
        String[][] pastMistakes=student.getPastMistakes();
        int numRows = pastMistakes.length;

        for (int i=0;i<numRows;i++){
            if (i==0||i==1)
                System.out.println(pastMistakes[i][0]);
            else{
                String[] exercise = pastMistakes[i];
                System.out.println(exercise[0]+exercise[1]+"\n"+exercise[2]);
                System.out.println("Your answer: ");
                char answer = scanner.nextLine().charAt(0);
                int found=0;

                if (answer=='a' || answer=='b')
                {
                    for (int j=1;j<=2;j++)
                    {
                        if (exercise[j].charAt(0)==answer && exercise[j].charAt(1)=='.')
                            if (exercise[j] == exercise[3])
                            {
                                System.out.println("Correct! " + exercise[3]);
                                found=1;
                                break;
                            }
                    }
                    if (found==0)
                    {
                        System.out.println("Wrong! The right answer was " + exercise[3]);
                    }
                }
                else
                    System.out.println("Invalid choice!");
            }
        }
        System.out.println("\n\n\nReview complete!\n\n\n");
    }

    /**
     *
     * @return all reading courses
     */
    public List<Reading> getAvailableCourses() {
        return readingRepo.getObjects();
    }

    /**
     *
     * @param courseId Refers to a specific reading course
     * @return all students enrolled in a reading course
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Reading course = readingRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * Shows all reading courses a student is enrolled in
     * @param studentId identifies a student
     */
    public void showEnrolledReadingCourses(Integer studentId){
        Student student = studentRepo.getById(studentId);
        for (Course course:student.getCourses())
            if (course.getCourseName().contains("Reading"))
                System.out.println(course);
    }

    /**
     * Shows all students enrolled in at least one reading course
     */
    public void showStudentsEnrolledInReadingCourses(){
        for(Student student:studentRepo.getObjects())
            for(Course course:student.getCourses())
                if(course.getCourseName().contains("Reading"))
                {
                    System.out.println(student);
                    break;
                }
    }

    /**
     * A teacher can remove a reading course
     * @param courseId Refers to a specific course
     * @param teacherId Refers to the teacher who removes the course
     */
    public void removeCourse(Integer courseId, Integer teacherId) {
        Reading course=readingRepo.getById(courseId);
        if (course.getTeacher().getId()==teacherId){
            readingRepo.delete(course);
        }
        else{
            System.out.println("You don't have access to this course!");
        }
    }

    /**
     * A teacher can either create or update a reading course if the course already exists
     * @param courseId refers to the course id that is to be updated or created
     * @param teacherId refers to the teacher that updates the course
     * @param courseName refers to the updated course name
     * @param maxStudents refers to the maximum number of students that can enroll
     * @param exerciseSet refers to the set of exercises the new course obtains
     */
    public void createOrUpdateReadingCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents, Integer exerciseSet){
        int found=0;
        for (Reading course: readingRepo.getObjects()){
            if (course.getId()==courseId)
            {
                found=1;
                updateReadingCourse(courseId,teacherId,courseName,maxStudents, exerciseSet);
                return;
            }
        }
        if (found==0){
            createReadingCourse(courseId,teacherId,courseName,maxStudents, exerciseSet);
        }
    }

    public void createReadingCourse(Integer courseId, Integer teacherId,String courseName, Integer maxStudents, Integer exerciseSet){
        Teacher teacher=teacherRepo.getById(teacherId);
        Reading r1=new Reading(courseId,courseName,teacher,maxStudents);
        if(exerciseSet==1)
        {
            String[][] readingExercises = {
                    {"Der Aufbruch\n" + "Franz Kafka","","",""},
                    {"Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"", "", "", ""},
                    {"\n\nDer Diener kann auf alle Fragen des Ich-Erzählers antworten.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                    {"\n\nDer Ich-Erzähler nimmt einen Essvorrat.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                    {"\n\nDer Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                    {"\n\nDie Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
            };
            r1.setExercises(readingExercises);
        }
        if (exerciseSet==2)
        {
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
            r1.setExercises(readingExercises1);
        }
        if(exerciseSet==3)
        {
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
            r1.setExercises(readingExercises2);
        }
        if(exerciseSet==4)
        {
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
            r1.setExercises(readingExercises3);
        }
        if(exerciseSet==5)
        {
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
            r1.setExercises(readingExercises4);
        }
        readingRepo.save(r1);
    }

    public void updateReadingCourse(Integer courseId, Integer teacherId,String courseName, Integer maxStudents, Integer exerciseSet){
        Reading course=readingRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        Reading r1=new Reading(courseId,courseName,teacher,maxStudents);

        if(exerciseSet==1)
        {
            String[][] readingExercises = {
                    {"Der Aufbruch\n" + "Franz Kafka","","",""},
                    {"Ich befahl mein Pferd aus dem Stall zu holen. Der Diener verstand mich nicht.\nIch ging selbst in den Stall, sattelte mein Pferd und bestieg es. In der Ferne hörte ich eine Trompete blasen,\nich fragte ihn, was das bedeute. Er wusste nichts und hatte nichts gehört. Beim Tore hielt er mich auf und fragte:\n\"Wohin reitest du, Herr?\" \"Ich weiß es nicht,\" sagte ich, \"nur weg von hier. Immerfort weg von hier, nur so kann ich\nmein Ziel erreichen.\" \"Du kennst also dein Ziel?\" fragte er. \"Ja,\" antwortete ich, \"ich sagte es doch: »Weg-von-hier«,\ndas ist mein Ziel.\" \"Du hast keinen Essvorrat mit,\" sagte er. \"Ich brauche keinen,\" sagte ich, \"die Reise ist so lang,\ndass ich verhungern muss, wenn ich auf dem Weg nichts bekomme. Kein Essvorrat kann mich retten. Es ist ja zum Glück eine\nwahrhaft ungeheure Reise.\"", "", "", ""},
                    {"\n\nDer Diener kann auf alle Fragen des Ich-Erzählers antworten.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                    {"\n\nDer Ich-Erzähler nimmt einen Essvorrat.\n\n", "a. wahr", "b. falsch", "b. falsch"},
                    {"\n\nDer Ich-Erzähler unternimmt eine Reise, deren Dauer undefiniert ist.\n\n", "a. wahr", "b. falsch", "a. wahr"},
                    {"\n\nDie Parabel kann eine Metapher für das Unbekannte des Lebens darstellen.\n\n", "a. wahr", "b. falsch", "a. wahr"},
            };
            r1.setExercises(readingExercises);
        }
        if (exerciseSet==2)
        {
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
            r1.setExercises(readingExercises1);
        }
        if(exerciseSet==3)
        {
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
            r1.setExercises(readingExercises2);
        }
        if(exerciseSet==4)
        {
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
            r1.setExercises(readingExercises3);
        }
        if(exerciseSet==5)
        {
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
            r1.setExercises(readingExercises4);
        }

        readingRepo.update(course,r1);
    }

    /**
     * Replaces the teacher of a reading course with another
     * @param teacherId New teacher responsible for reading course
     * @param courseId Exam whose teacher is being replaced
     */
    public void changeTeacherAccessToCourse(Integer courseId, Integer teacherId){
        Reading course=readingRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    /**
     * Show all reading courses of a teacher
     * @param teacherId refers to a teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId){
        Teacher teacher=teacherRepo.getById(teacherId);
        for(Reading course:readingRepo.getObjects())
            if (course.getTeacher().getId()==teacherId)
                System.out.println(course.getCourseName());
    }

    /**
     * Shows all mandatory books for a reading course
     * @param studentId identifies a student
     * @param courseId identifies a reading course
     */
    public void viewMandatoryBooks(Integer studentId, Integer courseId){
        Reading course=readingRepo.getById(courseId);
        for (String book:course.getMandatoryBooks()){
            System.out.println(book);
        }
    }

    /**
     *
     * @return all students
     */
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }

    /**
     * Adds a new mandatory books for a reading course
     * @param teacherId identifies a teacher
     * @param courseId identifies a course
     * @param book refers to a book title
     */
    public void addMandatoryBook(Integer teacherId, Integer courseId,String book){
        Reading course=readingRepo.getById(courseId);
        if(course.getTeacher().getId()==teacherId)
        {
            course.getMandatoryBooks().add(book);
        }
        else System.out.println("You don t have access to this course");
    }
}
