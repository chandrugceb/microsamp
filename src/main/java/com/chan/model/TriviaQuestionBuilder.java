package com.chan.model;

import java.util.Date;

public class TriviaQuestionBuilder {
    private long id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;
    private String hint;
    private Date lastUpdated;

    public TriviaQuestionBuilder() {
    }

    public TriviaQuestion build(){
        if((id < 0)
                ||("".equals(question))||(question.trim().isEmpty())
                ||("".equals(answerA))||(answerA.trim().isEmpty())
                ||("".equals(answerB))||(answerB.trim().isEmpty())
                ||("".equals(answerC))||(answerC.trim().isEmpty())
                ||("".equals(answerD))||(answerD.trim().isEmpty())
                ||("".equals(correctAnswer))||(correctAnswer.trim().isEmpty())
                ||("".equals(hint))||(hint.trim().isEmpty())
                ||(this.lastUpdated==null)){
            throw new IllegalArgumentException("TriviaQuestion builder is not in Buildable state.");
        }
        return new TriviaQuestion(this.id,
                this.question,
                this.answerA,
                this.answerB,
                this.answerC,
                this.answerD,
                this.correctAnswer,
                this.hint,
                this.lastUpdated);

    }

    public TriviaQuestionBuilder setId(long id) {
        if(id<0){
            throw new IllegalArgumentException("Id can't be less than 0.");
        }
        this.id = id;
        return this;
    }

    public TriviaQuestionBuilder setQuestion(String question) {
        if(question==null||question.trim().isEmpty()){
            throw new IllegalArgumentException("Question can't be Empty or null.");
        }
        this.question = question;
        return this;
    }

    public TriviaQuestionBuilder setAnswerA(String answerA) {
        if(answerA==null||answerA.trim().isEmpty()){
            throw new IllegalArgumentException("answerA can't be Empty or null.");
        }
        this.answerA = answerA;
        return this;
    }

    public TriviaQuestionBuilder setAnswerB(String answerB) {
        if(answerB==null||answerB.trim().isEmpty()){
            throw new IllegalArgumentException("answerB can't be Empty or null.");
        }
        this.answerB = answerB;
        return this;
    }

    public TriviaQuestionBuilder setAnswerC(String answerC) {
        if(answerC==null||answerC.trim().isEmpty()){
            throw new IllegalArgumentException("answerC can't be Empty or null.");
        }
        this.answerC = answerC;
        return this;
    }

    public TriviaQuestionBuilder setAnswerD(String answerD) {
        if(answerD==null||answerD.trim().isEmpty()){
            throw new IllegalArgumentException("answerD can't be Empty or null.");
        }
        this.answerD = answerD;
        return this;
    }

    public TriviaQuestionBuilder setCorrectAnswer(String correctAnswer) {
        if(correctAnswer==null||correctAnswer.trim().isEmpty()){
            throw new IllegalArgumentException("correctAnswer can't be Empty or null.");
        }
        this.correctAnswer = correctAnswer;
        return this;
    }

    public TriviaQuestionBuilder setHint(String hint) {
        if(hint==null||hint.trim().isEmpty()){
            throw new IllegalArgumentException("Hint can't be Empty or null.");
        }
        this.hint = hint;
        return this;
    }

    public TriviaQuestionBuilder setLastUpdated(Date lastUpdated) {
        if(lastUpdated==null){
            throw new IllegalArgumentException("lastUpdated can't be Empty or null.");
        }
        this.lastUpdated = lastUpdated;
        return this;
    }
}
