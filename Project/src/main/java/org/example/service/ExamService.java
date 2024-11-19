package org.example.service;
import java.util.*;

import org.example.model.*;
import org.example.repo.ExamRepository;
import org.example.repo.GrammarRepository;
import org.example.repo.StudentRepository;
import org.example.repo.TeacherRepository;
import org.example.service.*;

public class ExamService {
    private ExamRepository examRepo;

    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public ExamService(ExamRepository examRepo, StudentRepository studentRepo) {
        this.examRepo = examRepo;
        this.studentRepo = studentRepo;
    }

    public void takeReadingExam(Integer studentId, Integer examId){
        Student student = studentRepo.getById(studentId);
        Exam exam = examRepo.getById(examId);
        String[][] exercises=exam.getExercises();
        Scanner scanner = new Scanner(System.in);
        String[] exercise;
        int foundCourse=0;
        float score=2;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getCourseName().contains("Reading"))
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
                                    score+=2;
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

                System.out.println("\n\n\nExam complete!\n\n\n");
                System.out.println("Your score: "+ score + "\n\n");
                Map<Integer, Float> readingExamResults=new HashMap<>();
                readingExamResults=student.getReadingResults();
                readingExamResults.put(examId,score);
                student.setReadingResults(readingExamResults);

                List<Student> examined;
                examined=exam.getExaminedStudents();
                examined.add(student);
                exam.setExaminedStudents(examined);

                Map<Student,Float> results;
                results=exam.getResults();
                results.put(student,score);
                exam.setResults(results);
            }
        }
        if (foundCourse==0)
            System.out.println("\n\n\nYou are not enrolled in any reading course!");
    }

    public void showReadingResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> readingExamResults=new HashMap<>();
        readingExamResults=student.getReadingResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : readingExamResults.entrySet()) {
            System.out.println("Reading exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public void takeGrammarExam(Integer studentId, Integer examId){
        Student student = studentRepo.getById(studentId);
        Exam exam = examRepo.getById(examId);
        String []exercise;
        String[][] exercises=exam.getExercises();
        Scanner scanner = new Scanner(System.in);
        int correctAnswers=0;
        //aici e cu string matching merge matricea vietii si fac vf la atribute

        int foundCourse=0;
        for (Course findCourse : student.getCourses()){
            if (findCourse.getCourseName().contains("Grammar"))
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1){
            System.out.println("PLease fill in the gaps with the correct word:");
            for(int i=1; i<10; i++){
                exercise=exercises[i];
                System.out.println("Question "+i+": "+exercise[0]);
                System.out.print("Answer: ");
                String answer=scanner.nextLine();
                if(answer.equals(exercise[1]))
                    correctAnswers++;
            }
            if(correctAnswers>5) System.out.println("You have passed this exam with the grade "+correctAnswers+"!");
            else System.out.println("You have failed this exam with the grade "+correctAnswers+". Do better, loser");
            float grade=(float) correctAnswers;
            Map<Integer, Float> grammarExamResults=new HashMap<>();
            grammarExamResults=student.getGrammarResults();
            grammarExamResults.put(examId,grade);
            student.setGrammarResults(grammarExamResults);

            List<Student> examined;
            examined=exam.getExaminedStudents();
            examined.add(student);
            exam.setExaminedStudents(examined);

            Map<Student,Float> results;
            results=exam.getResults();
            results.put(student,grade);
            exam.setResults(results);
        }
    }

    public void showGrammarResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> grammarExamResults=new HashMap<>();
        grammarExamResults=student.getGrammarResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : grammarExamResults.entrySet()) {
            System.out.println("Grammar exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public void takeVocabExam(Integer studentId, Integer examId){
        Student student = studentRepo.getById(studentId);
        Exam exam = examRepo.getById(examId);

        Scanner scanner = new Scanner(System.in);
        int correctAnswers=0;
        int foundCourse=0;
        Map <String, String> tempother=new HashMap<>();
        for (Course findCourse : student.getCourses()){
            if (findCourse.getCourseName().contains("Vocabulary"))
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1){
            System.out.println("PLease write the correct translation for evey word (capital letter if needed):");
            String placeholderKey=new String();
            String placeholderValue=new String();
            for(int i=1; i<10; i++) {
                List<String> values = new ArrayList<>(exam.getWorter().values());
                Random random = new Random();
                int randomIndex = random.nextInt(values.size());
                String ubung = values.get(randomIndex);
                System.out.println(ubung+": ");
                String answer = scanner.nextLine();
                boolean found = false;
                for (Map.Entry<String, String> entry : exam.getWorter().entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (value.equals(ubung) && key.equals(answer)) {
                        System.out.println("Correct!");
                        found = true;
                    } else {
                        placeholderKey = key;
                        placeholderKey = value;
                    }
                }
                if (found == true)
                    correctAnswers++;
            }
            if(correctAnswers>5) System.out.println("You have passed this practice test with the grade "+correctAnswers+"!");
            else System.out.println("You have failed this practice test with the grade "+correctAnswers+". Do better, loser");
        }

    }
    public void showVocabResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> vocabExamResults=new HashMap<>();
        vocabExamResults=student.getGrammarResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : vocabExamResults.entrySet()) {
            System.out.println("Vocabulary exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public void takeWritingExam(Integer studentId, Integer examId){
        Student student = studentRepo.getById(studentId);
        Exam exam = examRepo.getById(examId);
        Scanner scanner = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        int foundCourse=0;

        for (Course findCourse : student.getCourses()){
            if (findCourse.getCourseName().contains("Vocabulary"))
            {
                foundCourse=1;
                break;}
        }
        if (foundCourse==0){
            System.out.println("\n\n\nYou are not enrolled in this course!");}
        if(foundCourse==1) {
            String exercise=exam.getRequirement();
            System.out.println(exercise);
            String input;


            System.out.println("Enter text (type 0 to stop):");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("Exiting...");
                    break;
                }
                answer.append(input).append("\n");
            }
            Map <Student, String> toBeGraded=exam.getTeacher().getGradeWriting();
            toBeGraded.put(student, answer.toString());
            exam.getTeacher().setGradeWriting(toBeGraded);
            System.out.println("Writing exercise submitted!!!!! Waiting for the grade");
        }
    }

    public void showWritingResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> writingExamResults=new HashMap<>();
        writingExamResults=student.getWritingExamResults();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : writingExamResults.entrySet()) {
            System.out.println("Writing exam id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public void showFeedbackResults(Integer studentId){
        Student student = studentRepo.getById(studentId);
        Map<Integer, Float> writingFeedbackResults=new HashMap<>();
        writingFeedbackResults=student.getWritingFeedback();
        System.out.println("Your past scores: ");
        for (Map.Entry<Integer, Float> entry : writingFeedbackResults.entrySet()) {
            System.out.println("Writing course id: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    void gradeWritings(Integer teacherId, Integer examId){
        Teacher teacher= teacherRepo.getById(teacherId);
        Scanner scanner=new Scanner(System.in);
        Map<Student, String> toGrade=teacher.getGradeWriting();
        while (!toGrade.isEmpty()) {
            Map.Entry<Student, String> entry = toGrade.entrySet().iterator().next();
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
            System.out.println("Input grade: ");
            float grade=scanner.nextFloat();
            Map<Integer, Float> results=key.getWritingExamResults();
            results.put(examId, grade);
            key.setWritingExamResults(results);
            toGrade.remove(key);
        }

    }


    void gradeFeedback(Integer teacherId, Integer courseId){
        Teacher teacher= teacherRepo.getById(teacherId);
        Scanner scanner=new Scanner(System.in);
        Map<Student, String> toGrade=teacher.getFeedbackWriting();
        while (!toGrade.isEmpty()) {
            Map.Entry<Student, String> entry = toGrade.entrySet().iterator().next();
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
            System.out.println("Input grade: ");
            float grade=scanner.nextFloat();
            Map<Integer, Float> results=key.getWritingFeedback();
            results.put(courseId, grade);
            key.setWritingFeedback(results);
            toGrade.remove(key);
        }

    }

    public Map<Integer,Float> showReadingResults1(Integer studentId){
        Student student = studentRepo.getById(studentId);
        return student.getReadingResults();
    }

    public void showAllReadingExams(){
        List<Exam> exams=examRepo.getObjects();
        for (Exam exam:exams)
            if(exam.getExamName().contains("Reading"))
                System.out.println(exam);
    }

    public void showResultsOfAllStudentsOnReadingExam(Integer teacherId){
        for (Exam exam:examRepo.getObjects()){
            if (exam.getExamName().contains("Reading"))
                if(exam.getTeacher().getId()==teacherId)
                    for (Student student:studentRepo.getObjects())
                        for (Integer key : student.getReadingResults().keySet()) {
                            if(key==exam.getId())
                                System.out.println(student+exam.getExamName()+student.getReadingResults().get(key));
                        }
        }
    }


    public void removeReadingExam(Integer teacherId, Integer examId) {
        Exam exam=examRepo.getById(examId);
        if (exam.getTeacher().getId()==teacherId){
            examRepo.delete(exam);
        }
        else{
            System.out.println("You don't have access to this exam!");
        }
    }

    public void createOrUpdateReadingExam(Integer examId, Integer teacherId, String examName){
        int found=0;
        for (Exam exam: examRepo.getObjects()){
            if (exam.getId()==examId)
            {
                found=1;
                updateReadingExam(examId,teacherId,examName);
                return;
            }
        }
        if (found==0){
            createReadingExam(examId,teacherId,examName);
        }
    }


    public void createReadingExam(Integer examId, Integer teacherId,String examName){
        Teacher teacher=teacherRepo.getById(teacherId);
        Exam e1=new Exam(examId,examName,teacher);
        examRepo.save(e1);
    }

    public void updateReadingExam(Integer examId, Integer teacherId,String examName){
        Exam exam=examRepo.getById(examId);
        Teacher teacher=teacherRepo.getById(teacherId);
        Exam e1=new Exam(examId,examName,teacher);
        examRepo.update(exam,e1);
    }

}
