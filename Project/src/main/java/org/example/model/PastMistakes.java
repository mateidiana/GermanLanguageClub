package org.example.model;

public class PastMistakes extends Entity{
    private int studentId;
    private int readingId;
    private int grammarId;

    public PastMistakes(int id, int studentId, int readingQuestionId, int grammarQuestionId){
        super(id);
        this.studentId=studentId;
        this.readingId=readingQuestionId;
        this.grammarId=grammarQuestionId;
    }

    public int getStudent(){return studentId;}
    public void setStudent(int studentId){this.studentId=studentId;}

    public int getReadingQuestion(){return readingId;}
    public void setReadingQuestion(int questionId){this.readingId=questionId;}

    public int getGrammarQuestion(){return grammarId;}
    public void setGrammarQuestionId(int questionId){this.grammarId=questionId;}
}

