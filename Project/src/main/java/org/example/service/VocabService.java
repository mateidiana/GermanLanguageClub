package org.example.service;
import java.util.Scanner;
import java.util.List;
import java.util.*;

import org.example.model.*;
import org.example.model.Vocabulary;
import org.example.repo.ReadingRepository;
import org.example.repo.TeacherRepository;
import org.example.repo.VocabRepository;
import org.example.repo.StudentRepository;

/**
 * Service class that provides business logic related to {@link Vocabulary} objects.
 * It interacts with the {@link VocabRepository}, {@link StudentRepository}, {@link TeacherRepository} to perform operations
 * like manipulating reading courses.
 */
public class VocabService {
    private VocabRepository vocabRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;
    public VocabService(VocabRepository vocabRepo, StudentRepository studentRepo, TeacherRepository teacherRepo ) {
        this.vocabRepo = vocabRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo= teacherRepo;
    }

    /**
     * Enrolls a student in a specific vocabulary course
     * @param studentId refers to the student to be enrolled
     * @param vocabCourseId refers to the id of the course the student is being enrolled in
     */
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

    /**
     * A student can practice German vocabulary by writing the meaning of words in English. Wrong answers
     * can be reviewed later
     * @param studentId Refers to a student who practices vocabulary
     * @param courseId Refers to the course the student practices in
     */
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

    /**
     * A student can practice past vocabulary mistakes
     * @param studentId Refers to a specific student
     */
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

    /**
     *
     * @return all vocabulary courses
     */
    public List<Vocabulary> getAvailableCourses() {
        return vocabRepo.getObjects();
    }

    /**
     *
     * @param courseId Refers to a specific vocabulary course
     * @return all students enrolled in a vocabulary course
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Vocabulary course = vocabRepo.getById(courseId);
        return course.getEnrolledStudents();
    }

    public void removeCourse(Integer courseId) {
        //this doesn't do anything yet
    }

    /**
     *
     * @return all students
     */
    public List<Student> getAllStudents() {
        return studentRepo.getObjects();
    }

    /**
     * Show all vocabulary courses of a teacher
     * @param teacherId refers to a teacher
     */
    public void viewCourseTaughtByTeacher(Integer teacherId) {
        Teacher teacher = teacherRepo.getById(teacherId);
        for (Vocabulary course : vocabRepo.getObjects()) {
            if (course.getTeacher().getId() == teacherId) {
                System.out.println(course.getCourseName());
            }
        }
    }

    /**
     * A teacher can either create or update a vocabulary course if the course already exists
     * @param courseId refers to the course id that is to be updated or created
     * @param teacherId refers to the teacher that updates the course
     * @param courseName refers to the updated course name
     * @param maxStudents refers to the maximum number of students that can enroll
     */
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

    /**
     * A teacher can remove a vocabulary course
     * @param courseId Refers to a specific course
     * @param teacherId Refers to the teacher who removes the course
     */
    public void removeVocabularyCourse(Integer courseId, Integer teacherId) {
        Vocabulary course = vocabRepo.getById(courseId);
        if (course.getTeacher().getId() == teacherId) {
            vocabRepo.delete(course);
        } else {
            System.out.println("You don't have access to this course!");
        }
    }

    /**
     * Replaces the teacher of a vocabulary course with another
     * @param teacherId New teacher responsible for vocabulary course
     * @param courseId Exam whose teacher is being replaced
     */
    public void changeTeacherAccessToVocabCourse(Integer courseId, Integer teacherId){
        Vocabulary course=vocabRepo.getById(courseId);
        Teacher teacher=teacherRepo.getById(teacherId);
        course.setTeacher(teacher);
    }

    /**
     * Shows all students enrolled in at least one vocabulary course
     */
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
