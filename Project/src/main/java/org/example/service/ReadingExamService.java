package org.example.service;
import java.util.*;

import org.example.model.*;
import org.example.model.Exceptions.BusinessLogicException;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;
import org.example.repo.IRepository;

public class ReadingExamService {
    private final IRepository<ReadingExam> readingExamRepo;

    private final IRepository<Reading> readingRepo;

    private final IRepository<Enrolled> enrolledRepo;

    private final IRepository<Student> studentRepo;

    private final IRepository<Teacher> teacherRepo;

    private final IRepository<Question> questionRepo;

    private final IRepository<ExamResult> examResultRepo;

    public ReadingExamService(IRepository<ReadingExam> readingExamRepo, IRepository<Student> studentRepo, IRepository<Teacher> teacherRepo, IRepository<Question> questionRepo, IRepository<ExamResult> examResultRepo, IRepository<Reading> readingRepo, IRepository<Enrolled> enrolledRepo) {
        this.readingExamRepo = readingExamRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.questionRepo = questionRepo;
        this.examResultRepo=examResultRepo;
        this.readingRepo=readingRepo;
        this.enrolledRepo=enrolledRepo;
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

    public ReadingExam getReadingExamById(int readingId){
        idDataCheck(readingId);
        for (ReadingExam readingExam : readingExamRepo.getAll()) {
            if (readingExam.getId()==readingId)
                return readingExam;
        }
        throw new EntityNotFoundException("Reading exam not found!");
    }

    public Question getQuestionById(int questionId){
        idDataCheck(questionId);
        for (Question question : questionRepo.getAll()) {
            if (question.getId()==questionId)
                return question;
        }
        throw new EntityNotFoundException("Question not found!");
    }

    public ExamResult getExamResultById(int resultId){
        idDataCheck(resultId);
        for (ExamResult result:examResultRepo.getAll())
        {
            if (result.getId()==resultId)
                return result;
        }
        throw new EntityNotFoundException("Exam Result not found!");
    }

    public List<Question> getExercises(int examId){
        List<Question> questions=new ArrayList<>();
        for (Question q:questionRepo.getAll())
            if (q.getReadingExamId()==examId)
                questions.add(q);
        return questions;
    }

    public List<ExamResult> getResults(int studentId){
        List<ExamResult> results=new ArrayList<>();
        for (ExamResult result:examResultRepo.getAll())
            if (result.getStudent()==studentId)
                results.add(result);
        return results;
    }

    public List<Reading> getReadingCourses(int studentId){
        idDataCheck(studentId);
        List<Reading> readingCourses=new ArrayList<>();
        for (Reading reading:readingRepo.getAll())
            for (Enrolled enrolled:enrolledRepo.getAll())
                if (reading.getId()==enrolled.getCourse()&&enrolled.getStudent()==studentId)
                    readingCourses.add(reading);
        return readingCourses;
    }

    public List<Question> takeReadingExam(int studentId, int examId){
        idDataCheck(studentId);
        idDataCheck(examId);
        if (getReadingCourses(studentId).isEmpty())
            throw new BusinessLogicException("You are not enrolled in any reading exam!");
        return getExercises(examId);
    }

    public String handleAnswer(int studentId, int questionId, String answer){
        idDataCheck(studentId);
        idDataCheck(questionId);
        stringDataCheck(answer);
        answerDataCheck(answer);
        Question question=getQuestionById(questionId);
        if (answer.equals(question.getRightAnswer()))
            return "Correct!";
        else
            return "Wrong!";
    }

    public List<ExamResult> showReadingExamResults(int studentId){
        idDataCheck(studentId);
        List<ExamResult> allResults=getResults(studentId);
        List<ExamResult> allReadingResults=new ArrayList<>();
        for (ExamResult result:allResults)
            if (getReadingExamById(result.getExam())!=null)
                allReadingResults.add(result);
        return allReadingResults;
    }

    public void addResult(int studentId, int examId, Float result){
        idDataCheck(studentId);
        idDataCheck(examId);
        int nextId=examResultRepo.getAll().size()+1;
        ExamResult examResult=new ExamResult(nextId, examId, result, studentId);
        examResultRepo.create(examResult);
    }

    public List<ReadingExam> showAllReadingExams(){
        return readingExamRepo.getAll();
    }

    public List<ReadingExam> examsOfATeacher(int teacherId){
        idDataCheck(teacherId);
        List<ReadingExam> exams=new ArrayList<>();
        for (ReadingExam exam:readingExamRepo.getAll())
            if (exam.getTeacher()==teacherId)
                exams.add(exam);
        return exams;
    }

    //show results of all students on a reading exam of a teacher
    public List<ExamResult> showAllResultsOfTeacherExam(int teacherId, int examId){
        idDataCheck(teacherId);
        idDataCheck(examId);
        List<ExamResult> allReadingResults=new ArrayList<>();
        if (getReadingExamById(examId).getTeacher()==teacherId){
            for (Student student:studentRepo.getAll())
                for (ExamResult result:getResults(student.getId()))
                    if(result.getExam()==examId)
                        allReadingResults.add(result);
        }
        return allReadingResults;

    }

    public void deleteQuestionsOfExam(int examId){
        for (Question q:questionRepo.getAll())
            if (q.getReadingExamId()==examId)
                questionRepo.delete(q.getId());
    }

    public boolean removeReadingExam(int examId, int teacherId) {
        idDataCheck(teacherId);
        idDataCheck(examId);
        ReadingExam exam = getReadingExamById(examId);
        if (exam.getTeacher()==teacherId){
            readingExamRepo.delete(examId);
            deleteQuestionsOfExam(examId);
            return true;
        }
        else
            return false;
    }

    public void setReadingText(int courseId, String title, String author, String text){
        idDataCheck(courseId);
        stringDataCheck(title);
        stringDataCheck(author);
        stringDataCheck(text);

        ReadingExam reading=getReadingExamById(courseId);
        reading.setText(text);
        reading.setTextTitle(title);
        reading.setTextAuthor(author);
        readingExamRepo.update(reading);
    }

    public void createQuestion(int courseId, String question, String rightAnswer){
        idDataCheck(courseId);
        ReadingExam reading=getReadingExamById(courseId);
        Question q=new Question(questionRepo.getAll().size()+1,question,rightAnswer);
        q.setReadingId(reading.getId());
        questionRepo.create(q);
    }

    public void createOrUpdateReadingExam(int examId, int teacherId, String examName){
        idDataCheck(teacherId);
        idDataCheck(examId);
        stringDataCheck(examName);


        int found=0;
        for (ReadingExam exam: readingExamRepo.getAll()){
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

    public void createReadingExam(int examId, int teacherId, String examName){
        ReadingExam e1=new ReadingExam(examId,examName,teacherId);
        readingExamRepo.create(e1);
    }

    public void updateReadingExam(int examId, int teacherId,String courseName){
        ReadingExam exam=getReadingExamById(examId);

        if (getReadingExamById(examId).getTeacher()!=teacherId)
            throw new BusinessLogicException("You don't have access to this course!");
        exam.setExamName(courseName);
        exam.setTeacher(teacherId);

        readingExamRepo.update(exam);
    }


    public List<Student> filterStudentsByPassingGradeOnReadingExam(int teacherId, int examId){
        idDataCheck(examId);
        if (getReadingExamById(examId).getTeacher()!=teacherId)
            throw new BusinessLogicException("You don't have access to this exam!");
        List<Student> filteredStud=new ArrayList<>();
        for (Student stud:studentRepo.getAll())
            for (ExamResult result:getResults(stud.getId()))
                if (result.getExam()==examId)
                    if (result.getResult()>=5.0)
                        filteredStud.add(stud);
        return filteredStud;
    }


    public String getText(int examId){
        idDataCheck(examId);
        return getReadingExamById(examId).getText();
    }

    public void idDataCheck(int id){
        if (id<1)
            throw new ValidationException("Id cannot be less than 1!");
    }

    public void stringDataCheck(String string){
        if (string.isEmpty())
            throw new ValidationException("Name cannot be an empty string!");
    }

    public void answerDataCheck(String string){
        if (!string.equals("wahr") && !string.equals("falsch"))
            throw new ValidationException("Invalid answer!");
    }

    public void intDataCheck(int number){
        if (number<1)
            throw new ValidationException("Number cannot be null!");
    }

}
