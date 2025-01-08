package org.example.service;

import org.example.model.*;
import org.example.model.Exceptions.BusinessLogicException;
import org.example.model.Exceptions.EntityNotFoundException;
import org.example.model.Exceptions.ValidationException;
import org.example.repo.IRepository;

import java.util.ArrayList;
import java.util.List;

public class ReadingService {
    private final IRepository<Reading> readingRepo;

    private final IRepository<Student> studentRepo;

    private final IRepository<Teacher> teacherRepo;

    private final IRepository<Question> questionRepo;

    private final IRepository<Book> bookRepo;

    private final IRepository<Enrolled> enrolledRepo;

    private final IRepository<BookBelongsToCourse> bookBelongsRepo;

    private final IRepository<PastMistakes> pastMistakesRepo;

    public ReadingService(IRepository<Reading> readingRepo, IRepository<Student> studentRepo, IRepository<Teacher> teacherRepo, IRepository<Question> questionRepo, IRepository<Book> bookRepo, IRepository<Enrolled> enrolledRepo, IRepository<BookBelongsToCourse> bookBelongsRepo, IRepository<PastMistakes> pastMistakesRepo) {
        this.readingRepo = readingRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.questionRepo = questionRepo;
        this.bookRepo = bookRepo;
        this.enrolledRepo=enrolledRepo;
        this.bookBelongsRepo=bookBelongsRepo;
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

    public Reading getReadingById(int readingId){
        idDataCheck(readingId);
        for (Reading reading : readingRepo.getAll()) {
            if (reading.getId()==readingId)
                return reading;
        }
        throw new EntityNotFoundException("Reading Course not found!");
    }

    public Question getQuestionById(int questionId){
        idDataCheck(questionId);
        for (Question question : questionRepo.getAll()) {
            if (question.getId()==questionId)
                return question;
        }
        throw new EntityNotFoundException("Question not found!");
    }

    public Book getBookById(int bookId){
        idDataCheck(bookId);
        for (Book book:bookRepo.getAll())
            if (book.getId()==bookId)
                return book;
        throw new EntityNotFoundException("Book not found!");
    }

    public List<Student> getEnrolled(int courseId){
        List<Student> enrolled=new ArrayList<>();
        idDataCheck(courseId);
        for(Enrolled enrollment:enrolledRepo.getAll())
            if(enrollment.getCourse()==courseId)
                enrolled.add(getStudentById(enrollment.getStudent()));
        return enrolled;

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

    public List<Question> getExercises(int courseId){
        idDataCheck(courseId);
        List<Question> questions=new ArrayList<>();
        for (Question q:questionRepo.getAll())
            if (q.getReadingId()==courseId)
                questions.add(q);
        return questions;
    }

    public List<Question> getPastMistakes(int studentId){
        idDataCheck(studentId);
        List<Question> pastMistakes=new ArrayList<>();
        for (PastMistakes mistake:pastMistakesRepo.getAll())
            if (mistake.getStudent()==studentId && mistake.getReadingQuestion()!=0)
                for (Question q:questionRepo.getAll())
                    if (q.getId()==mistake.getReadingQuestion())
                        pastMistakes.add(q);
        return pastMistakes;

    }

    public List<Book> getMandatoryBooks(int courseId){
        idDataCheck(courseId);
        List<Book> books=new ArrayList<>();
        for (BookBelongsToCourse belongs:bookBelongsRepo.getAll())
            if (belongs.getReading()==courseId)
                books.add(getBookById(belongs.getId()));
        return books;
    }

    public void enroll(int studentId, int readingCourseId) {
        idDataCheck(studentId);
        idDataCheck(readingCourseId);
        int alreadyEnrolled=0;

        Student student = getStudentById(studentId);
        Reading course = getReadingById(readingCourseId);

        for (Reading reading:getReadingCourses(studentId)){
            if (reading.getId()==readingCourseId)
            {
                alreadyEnrolled=1;
                break;
            }
        }
        if (alreadyEnrolled==0){
            if (course.getAvailableSlots() > getEnrolled(readingCourseId).size()) {

                int nextId=enrolledRepo.getAll().size();
                if (nextId==0)
                    nextId=1;
                else nextId+=1;
                Enrolled enrolled=new Enrolled(nextId,studentId,readingCourseId);
                enrolledRepo.create(enrolled);
            }
            else
                throw new BusinessLogicException("This course has no more available slots!");
        }

    }

    public List<Reading> showEnrolledReadingCourses(int studentId){
        idDataCheck(studentId);
        return getReadingCourses(studentId);
    }

    public List<Question> practiceReading(int studentId, int courseId){
        idDataCheck(studentId);
        idDataCheck(courseId);
        int isEnrolled=0;

        for (Reading reading: getReadingCourses(studentId))
            if (reading.getId()==courseId)
            {
                isEnrolled=1;
                break;
            }
        if (isEnrolled==0)
            throw new BusinessLogicException("You are not enrolled in this course!");
        else{
            return getExercises(courseId);
        }
    }

    public String handleAnswer(int studentId, int questionId, String answer){
        idDataCheck(studentId);
        idDataCheck(questionId);
        stringDataCheck(answer);
        answerDataCheck(answer);

        Question question=getQuestionById(questionId);
        if (answer.equals(question.getRightAnswer()))
            return "Correct!";
        else{
            int nextInt=pastMistakesRepo.getAll().size();
            PastMistakes mistake=new PastMistakes(nextInt+1,studentId,questionId,0);
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
        answerDataCheck(answer);

        Question question=getQuestionById(questionId);
        if (answer.equals(question.getRightAnswer()))
            return "Correct!";
        else
            return "Wrong!";
    }

    public List<Reading> getAvailableReadingCourses(){
        List<Reading> availableCourses=new ArrayList<>();
        for (Reading reading:readingRepo.getAll())
            if (reading.getAvailableSlots()>getEnrolled(reading.getId()).size())
                availableCourses.add(reading);
        return availableCourses;
    }

    public List<Student> getAllStudents() {
        return studentRepo.getAll();
    }

    public List<Student> getEnrolledStudents(int courseId) {
        idDataCheck(courseId);
        return getEnrolled(courseId);
    }

    public List<Student> showStudentsEnrolledInReadingCourses(){
        List<Student> studentList=new ArrayList<>();
        for(Student student:studentRepo.getAll())
            if (!getReadingCourses(student.getId()).isEmpty())
                studentList.add(student);
        return studentList;

    }

    public void deleteQuestionsOfCourse(int courseId){
        for (Question q:questionRepo.getAll())
            if (q.getReadingId()==courseId)
                questionRepo.delete(q.getId());
    }

    public boolean removeCourse(int courseId, int teacherId) {
        idDataCheck(courseId);
        idDataCheck(teacherId);
        Reading course = getReadingById(courseId);
        if (course.getTeacher()==teacherId){
            readingRepo.delete(courseId);
            deleteQuestionsOfCourse(courseId);
            return true;
        }
        return false;
    }

    public void setReadingText(int courseId, String title, String author, String text){
        idDataCheck(courseId);
        stringDataCheck(title);
        stringDataCheck(author);
        stringDataCheck(text);

        Reading reading=getReadingById(courseId);
        reading.setText(text);
        reading.setTextTitle(title);
        reading.setTextAuthor(author);
        readingRepo.update(reading);
    }

    public void createQuestion(int courseId, String question, String rightAnswer){
        idDataCheck(courseId);
        Reading reading=getReadingById(courseId);
        Question q=new Question(questionRepo.getAll().size()+1,question,rightAnswer);
        q.setReadingId(reading.getId());
        questionRepo.create(q);
    }

    public void createOrUpdateReadingCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        idDataCheck(courseId);
        idDataCheck(teacherId);
        stringDataCheck(courseName);
        intDataCheck(maxStudents);



        int found=0;
        for (Reading course: readingRepo.getAll()){
            if (course.getId()==courseId)
            {
                found=1;
                updateReadingCourse(courseId,teacherId,courseName,maxStudents);
                return;
            }
        }
        if (found==0){
            createReadingCourse(courseId,teacherId,courseName,maxStudents);
        }
    }

    public void createReadingCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        Reading r1=new Reading(courseId,courseName,teacherId,maxStudents);
        readingRepo.create(r1);
    }

    public void updateReadingCourse(int courseId, int teacherId, String courseName, Integer maxStudents){
        Reading course=getReadingById(courseId);
        if (getReadingById(courseId).getTeacher()!=teacherId)
            throw new BusinessLogicException("You don't have access to this course!");
        course.setCourseName(courseName);
        course.setTeacher(teacherId);
        course.setAvailableSlots(maxStudents);

        readingRepo.update(course);
    }

    public List<Reading> viewReadingCoursesTaughtByTeacher(int teacherId){
        idDataCheck(teacherId);
        List<Reading> taughtCourses=new ArrayList<>();
        for(Reading course:readingRepo.getAll())
            if (course.getTeacher()==teacherId)
                taughtCourses.add(course);
        return taughtCourses;

    }

    public List<Book> viewMandatoryBooks(int studentId,int courseId){
        idDataCheck(courseId);
        idDataCheck(studentId);
        int isEnrolled=0;

        for (Reading reading: getReadingCourses(studentId))
            if (reading.getId()==courseId)
            {
                isEnrolled=1;
                break;
            }
        if (isEnrolled==0)
            throw new BusinessLogicException("You are not enrolled in this course!");

        for (Reading course:getReadingCourses(studentId))
            if (course.getId()==courseId)
                return getMandatoryBooks(courseId);
        return new ArrayList<>();

    }

    public boolean addMandatoryBook(Integer teacherId, Integer courseId, String title, String author){
        idDataCheck(teacherId);
        idDataCheck(courseId);
        stringDataCheck(title);
        stringDataCheck(author);

        Reading course=getReadingById(courseId);
        int nextId=bookRepo.getAll().size();
        Book book=new Book(nextId+1, title, author);
        bookRepo.create(book);

        if(course.getTeacher()==teacherId)
        {

            int nextIdBook=bookBelongsRepo.getAll().size()+1;
            BookBelongsToCourse bookBelongsToCourse=new BookBelongsToCourse(nextIdBook,courseId,nextId+1);
            bookBelongsRepo.create(bookBelongsToCourse);
            return true;
        }
        else return false;
    }

    public List<Reading> filterCoursesByAvailableSlots(){
        List<Reading> sorted=new ArrayList<>();
        for (Reading reading:readingRepo.getAll())
            if (reading.getAvailableSlots()>getEnrolled(reading.getId()).size())
                sorted.add(reading);
        return sorted;
    }

    public List<Book> sortBooksAlphabeticallyByTitle(int studentId,int courseId){
        int isEnrolled=0;
        for (Reading reading: getReadingCourses(studentId))
            if (reading.getId()==courseId)
            {
                isEnrolled=1;
                break;
            }
        if (isEnrolled==0)
            throw new BusinessLogicException("You are not enrolled in this course!");

        List<Book> allBooks=getMandatoryBooks(courseId);
        if(allBooks.isEmpty())
            throw new BusinessLogicException("There are no books for this course!");
        sortStrings(allBooks);
        return allBooks;
    }

    public void idDataCheck(int id){
        if (id<1)
            throw new ValidationException("Id cannot be less than 1!");
    }

    public void stringDataCheck(String string){
        if (string.isEmpty())
            throw new ValidationException("String cannot be an empty string!");
    }

    public void answerDataCheck(String string){
        if (!string.equals("wahr") && !string.equals("falsch"))
            throw new ValidationException("Invalid answer!");
    }

    public void intDataCheck(int number){
        if (number<1)
            throw new ValidationException("Number cannot be null!");
    }

    public static void sortStrings(List<Book> strings) {
        int n = strings.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareStrings(strings.get(j).getTitle(), strings.get(j + 1).getTitle()) > 0) {
                    Book temp = strings.get(j);
                    strings.set(j, strings.get(j + 1));
                    strings.set(j + 1, temp);
                }
            }
        }
    }

    public static int compareStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);

            if (c1 != c2) {
                return c1 - c2;
            }
        }

        return len1 - len2;
    }

}
