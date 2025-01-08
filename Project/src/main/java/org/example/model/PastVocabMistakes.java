package org.example.model;

public class PastVocabMistakes extends Entity{
    private int studentId;
    private int wordId;

    public PastVocabMistakes(int id, int studentId, int wordId){
        super(id);
        this.studentId=studentId;
        this.wordId=wordId;
    }

    public int getStudent(){return studentId;}
    public void setStudent(int studentId){this.studentId=studentId;}

    public int getWord(){return wordId;}
    public void setWordId(int questionId){this.wordId=questionId;}

}
