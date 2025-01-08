package org.example.service;

import org.example.model.*;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;
import org.example.model.Exceptions.BusinessLogicException;
import org.example.repo.IRepository;

import java.util.ArrayList;
import java.util.List;

public class GrammarService {
    private final IRepository<Grammar> grammarRepo;

    private final IRepository<Student> studentRepo;

    private final IRepository<Teacher> teacherRepo;

    private final IRepository<Question> questionRepo;

    private final IRepository<Enrolled> enrolledRepo;

    private final IRepository<PastMistakes> pastMistakesRepo;


    public GrammarService(IRepository<Grammar> grammarRepo, IRepository<Student> studentRepo, IRepository<Teacher> teacherRepo, IRepository<Question> questionRepo, IRepository<Enrolled> enrolledRepo, IRepository<PastMistakes> pastMistakesRepo) {
        this.grammarRepo = grammarRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.questionRepo = questionRepo;
        this.enrolledRepo = enrolledRepo;
        this.pastMistakesRepo=pastMistakesRepo;
    }

    public Student getStudentById(int studentId){
        idDataCheck(studentId);
        for (Student student : studentRepo.getAll()) {
            if (student.getId()==studentId)
                return student;
        }
        throw new EntityNotFoundException("Student not found!");
    }

    public Teacher getTeacherById(int teacherId){
        idDataCheck(teacherId);
        for (Teacher teacher : teacherRepo.getAll()) {
            if (teacher.getId()==teacherId)
                return teacher;
        }
        throw new EntityNotFoundException("Teacher not found!");
    }

    public Grammar getGrammarById(int grammarId){
        idDataCheck(grammarId);
        for (Grammar grammar : grammarRepo.getAll()) {
            if (grammar.getId()==grammarId)
                return grammar;
        }
        throw new EntityNotFoundException("Grammar course not found!");
    }

    public Question getQuestionById(int questionId){
        idDataCheck(questionId);
        for (Question question : questionRepo.getAll()) {
            if (question.getId()==questionId)
                return question;
        }
        throw new EntityNotFoundException("Question not found!");
    }

    public List<Student> getEnrolled(int courseId){
        List<Student> enrolled=new ArrayList<>();
        idDataCheck(courseId);
        for(Enrolled enrollment:enrolledRepo.getAll())
            if(enrollment.getCourse()==courseId)
                enrolled.add(getStudentById(enrollment.getStudent()));
        return enrolled;

    }

    public List<Grammar> getGrammarCourses(int studentId){
        idDataCheck(studentId);
        List<Grammar> grammarCourses=new ArrayList<>();
        for (Grammar grammar:grammarRepo.getAll())
            for (Enrolled enrolled:enrolledRepo.getAll())
                if (grammar.getId()==enrolled.getCourse()&&enrolled.getStudent()==studentId)
                    grammarCourses.add(grammar);
        return grammarCourses;
    }

    public List<Question> getExercises(int courseId){
        List<Question> questions=new ArrayList<>();
        for (Question q:questionRepo.getAll())
            if (q.getGrammarId()==courseId)
                questions.add(q);
        return questions;
    }

    public List<Question> getPastMistakes(int studentId){
        idDataCheck(studentId);
        List<Question> pastMistakes=new ArrayList<>();
        for (PastMistakes mistake:pastMistakesRepo.getAll())
            if (mistake.getStudent()==studentId && mistake.getGrammarQuestion()!=0)
                for (Question q:questionRepo.getAll())
                    if (q.getId()==mistake.getGrammarQuestion())
                        pastMistakes.add(q);
        return pastMistakes;

    }

    public void enroll(int studentId, int grammarCourseId) {
        idDataCheck(studentId);
        idDataCheck(grammarCourseId);
        int alreadyEnrolled=0;

        Student student = getStudentById(studentId);
        Grammar course = getGrammarById(grammarCourseId);

        for (Course course1:getGrammarCourses(studentId)){
            if (course1.getId() == grammarCourseId) {
                alreadyEnrolled = 1;
                break;
            }

        }

        if (alreadyEnrolled==0){
            if (course.getAvailableSlots() > getEnrolled(grammarCourseId).size()) {

                int nextId=enrolledRepo.getAll().size();
                if (nextId==0)
                    nextId=1;
                else nextId+=1;
                Enrolled enrolled=new Enrolled(nextId,studentId,grammarCourseId);
                enrolledRepo.create(enrolled);
            }
            else
                throw new BusinessLogicException("This course has no more available slots!");
        }

    }

    public List<Grammar> showEnrolledGrammarCourses(int studentId){
        idDataCheck(studentId);
        return getGrammarCourses(studentId);
    }

    public List<Question> practiceGrammar(int studentId, int courseId) {
        idDataCheck(studentId);
        idDataCheck(courseId);

        int foundCourse=0;
        for (Grammar findCourse : getGrammarCourses(studentId)){
            if (findCourse.getId()==courseId) {
                foundCourse=1;
                break;
            }
        }
        if (foundCourse==0){
            throw new BusinessLogicException("You are not enrolled in this course!");
        }
        return getExercises(courseId);
    }

    public String handleAnswer(int studentId, int questionId, String answer){
        idDataCheck(studentId);
        idDataCheck(questionId);
        stringDataCheck(answer);


        Question question=getQuestionById(questionId);
        if (answer.equals(question.getRightAnswer()))
            return "Correct!";
        else {
            int nextInt=pastMistakesRepo.getAll().size();
            PastMistakes mistake=new PastMistakes(nextInt+1,studentId,0,questionId);
            pastMistakesRepo.create(mistake);
            return "Wrong!";
        }

    }

    public List<Question> practicePastMistakes(int studentId){
        idDataCheck(studentId);
        return getPastMistakes(studentId);
    }
    public String handlePastMistakesAnswer(int studentId, int questionId, String answer){
        idDataCheck(studentId);
        idDataCheck(questionId);
        stringDataCheck(answer);

        Question question=getQuestionById(questionId);
        if (answer.equals(question.getRightAnswer()))
            return "Correct!";
        else
            return "Wrong!";
    }

    public List<Grammar> getAvailableGrammarCourses(){
        List<Grammar> availableCourses=new ArrayList<>();
        for (Grammar grammar:grammarRepo.getAll())
            if (grammar.getAvailableSlots()>getEnrolled(grammar.getId()).size())
                availableCourses.add(grammar);
        return availableCourses;
    }

    public List<Student> getAllStudents() {
        return studentRepo.getAll();
    }

    public List<Student> getEnrolledStudents(int courseId) {
        idDataCheck(courseId);
        return getEnrolled(courseId);
    }

    public List<Student> showStudentsEnrolledInGrammarCourses(){
        List<Student> studentList=new ArrayList<>();
        for(Student student:studentRepo.getAll())
            if (!getGrammarCourses(student.getId()).isEmpty())
                studentList.add(student);
        return studentList;
    }

    public void deleteQuestionsOfCourse(int courseId){
        for (Question q:questionRepo.getAll())
            if (q.getGrammarId()==courseId){

                for (PastMistakes mistakes:pastMistakesRepo.getAll())
                    if (mistakes.getGrammarQuestion()==q.getId())
                        pastMistakesRepo.delete(mistakes.getId());
                questionRepo.delete(q.getId());
            }

    }

    public void deleteEnrollment(int courseId){
        for (Enrolled enrolled:enrolledRepo.getAll())
            if (enrolled.getCourse()==courseId)
                enrolledRepo.delete(enrolled.getId());
    }


    public boolean removeCourse(int courseId, int teacherId) {
        idDataCheck(courseId);
        idDataCheck(teacherId);
        Grammar course = getGrammarById(courseId);
        if (course.getTeacher()==teacherId){
            deleteQuestionsOfCourse(courseId);
            deleteEnrollment(courseId);
            grammarRepo.delete(courseId);

            return true;
        }
        else
            return false;
    }

    public void createQuestion(int courseId, String question, String rightAnswer){
        idDataCheck(courseId);
        Grammar grammar=getGrammarById(courseId);
        Question q=new Question(questionRepo.getAll().size()+1,question,rightAnswer);
        q.setGrammarId(grammar.getId());
        questionRepo.create(q);
    }

    public void createOrUpdateGrammarCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        idDataCheck(courseId);
        idDataCheck(teacherId);
        stringDataCheck(courseName);
        intDataCheck(maxStudents);



        int found=0;
        for (Grammar course: grammarRepo.getAll()){
            if (course.getId()==courseId)
            {
                found=1;
                updateGrammarCourse(courseId,teacherId,courseName,maxStudents);
                return;
            }
        }
        if (found==0){
            createGrammarCourse(courseId,teacherId,courseName,maxStudents);
        }
    }

    public void createGrammarCourse(int courseId, int teacherId, String courseName, Integer maxStudents) {
        Grammar g1 = new Grammar(courseId, courseName, teacherId, maxStudents);
        grammarRepo.create(g1);

    }

    public void updateGrammarCourse(int courseId, int teacherId, String courseName, Integer maxStudents) {
        if (getGrammarById(courseId).getTeacher()!=teacherId)
            throw new BusinessLogicException("You don't have access to this course!");
        Grammar g1 = getGrammarById(courseId);
        g1.setTeacher(teacherId);
        g1.setCourseName(courseName);
        g1.setAvailableSlots(maxStudents);
        grammarRepo.update(g1);
    }

    public List<Grammar> viewGrammarCoursesTaughtByTeacher(int teacherId){
        idDataCheck(teacherId);
        List<Grammar> taughtCourses=new ArrayList<>();
        for(Grammar course:grammarRepo.getAll())
            if (course.getTeacher()==teacherId)
                taughtCourses.add(course);
        return taughtCourses;

    }


    public void idDataCheck(int id){
        if (id<1)
            throw new ValidationException("Id cannot be less than 1!");
    }

    public void stringDataCheck(String string){
        if (string.isEmpty())
            throw new ValidationException("Name cannot be an empty string!");
    }


    public void intDataCheck(int number){
        if (number<1)
            throw new ValidationException("Number cannot be null!");
    }
}
