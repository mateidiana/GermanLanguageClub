package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.*;

import org.example.model.*;
import org.example.model.Vocabulary;
import org.example.repo.TeacherRepository;
import org.example.repo.VocabRepository;
import org.example.repo.VocabRepository;
import org.example.repo.StudentRepository;
public class VocabService {
    private VocabRepository vocabRepo;

    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;
    public VocabService(VocabRepository vocabRepo, StudentRepository studentRepo, TeacherRepository teacherRepo ) {
        this.vocabRepo = vocabRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo= teacherRepo;
    }

    public void enroll(Integer studentId, Integer vocabCourseId) {
        Student student = studentRepo.getById(studentId);
        Vocabulary course = vocabRepo.getById(vocabCourseId);
        studentRepo.delete(student);
        vocabRepo.delete(course);
        if (course.getAvailableSlots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            vocabRepo.save(course);
            studentRepo.save(student);
        }
    }

    public void practiceVocabulary(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Vocabulary course = vocabRepo.getById(courseId);

        Scanner scanner = new Scanner(System.in);
        int correctAnswers=0;
        int foundCourse=0;
        Map <String, String> tempother=new HashMap<>();
        for (Course findCourse : student.getCourses()){
            if (findCourse.getId()==course.getId())
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
            for(int i=0; i<10; i++){
                List<String> values = new ArrayList<>(course.getWorter().values());
                Random random = new Random();
                int randomIndex = random.nextInt(values.size());
                String ubung = values.get(randomIndex);
                System.out.println(ubung+": ");
                String answer=scanner.nextLine();
                boolean found=false;
                for (Map.Entry<String, String> entry : course.getWorter().entrySet()) {
                    String key=entry.getKey();
                    String value=entry.getValue();
                    if(value.equals(ubung) && key.equals(answer)){
                        System.out.println("Correct!");
                        found=true;
                    }
                    else{
                        placeholderKey=key;
                        placeholderKey=value;
                    }
                }
                if(found==true)
                    correctAnswers++;
                else{
                    tempother=student.getPastVocabMistakes();
                    tempother.put(placeholderKey, placeholderValue);
                    student.setPastVocabMistakes(tempother);
                }
            }
            if(correctAnswers>5) System.out.println("You have passed this practice test with the grade "+correctAnswers+"!");
            else System.out.println("You have failed this practice test with the grade "+correctAnswers+". Do better, loser");
        }


    }
    public void reviewPastMistakes(Integer studentId, Integer courseId) {
        Student student = studentRepo.getById(studentId);
        Vocabulary course = vocabRepo.getById(courseId);
        Map <String, String> tempother=new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int foundCourse = 0;
        for (Course findCourse : student.getCourses()) {
            if (findCourse.getId() == course.getId()) {
                foundCourse = 1;
                break;
            }
        }
        if (foundCourse == 0) {
            System.out.println("\n\n\nYou are not enrolled in this course!");
        }
        if (foundCourse == 1) {
            System.out.println("PLease fill in the gaps with the correct word(use capital letter when noun):");
            String placeholderKey = new String();
            String placeholderValue = new String();
            for (Map.Entry<String, String> entry : course.getWorter().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(value+": ");
                String answer = scanner.nextLine();
                if (answer == key) {
                    System.out.println("Correct!");
                } else {
                    tempother.put(key, value);
                }
            }
            student.setPastVocabMistakes(tempother);
        }
    }

    public List<Vocabulary> getAvailableCourses() {
        return vocabRepo.getObjects();
    }

    public List<Student> getEnrolledStudents(Integer courseId) {
        Vocabulary course = vocabRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesnt do jackshit yet
    }

    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }

    public void viewCourseTaughtByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepo.getById(teacherId);
        for (Vocabulary course : vocabRepo.getObjects()) {
            if (course.getTeacher().getId() == teacherId) {
                System.out.println(course.getCourseName());
            }
        }
    }
    public void createOrUpdateVocabularyCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        int found = 0;
        for (Vocabulary course : vocabRepo.getObjects()) {
            if (course.getId() == courseId) {
                found = 1;
                updateVocabularyCourse(courseId, teacherId, courseName, maxStudents);
                return;
            }
        }
        if (found == 0) {
            createVocabularyCourse(courseId, teacherId, courseName, maxStudents);
        }
    }

    public void createVocabularyCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Teacher teacher = teacherRepo.getById(teacherId);
        Vocabulary v1 = new Vocabulary(courseId, courseName, teacher, maxStudents);
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
        v1.setWorter(vocabularyExercises);
        vocabRepo.save(v1);
    }

    public void updateVocabularyCourse(Integer courseId, Integer teacherId, String courseName, Integer maxStudents) {
        Vocabulary course = vocabRepo.getById(courseId);
        Teacher teacher = teacherRepo.getById(teacherId);
        Vocabulary v1 = new Vocabulary(courseId, courseName, teacher, maxStudents);
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
        v1.setWorter(vocabularyExercises);
        vocabRepo.update(course, v1);
    }

    public void removeVocabularyCourse(Integer courseId, Integer teacherId) {
        Vocabulary course = vocabRepo.getById(courseId);
        if (course.getTeacher().getId() == teacherId) {
            vocabRepo.delete(course);
        } else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void changeTeacherAccessToVocabCourse(Integer courseId, Integer teacherId){
        Vocabulary course=vocabRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    public void showStudentsEnrolledInVocabCourses(){
        for(Student student:studentRepo.getObjects())
            for(Course course:student.getCourses())
                if(course.getCourseName().contains("Vocab"))
                {
                    System.out.println(student);
                    break;
                }
    }

}
