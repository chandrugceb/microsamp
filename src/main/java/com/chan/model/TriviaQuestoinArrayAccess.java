package com.chan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TriviaQuestoinArrayAccess implements TriviaQuestionAccessible {
    private ArrayList<TriviaQuestion> questionList;
    private static final int MAX_NUMBER_OF_QUESTIONS_PER_PAGE = 10;
    public TriviaQuestoinArrayAccess(){
        this.loadQuestionArray();
    }
    private void loadQuestionArray(){
        questionList = new ArrayList<>();
        questionList.add((new TriviaQuestionBuilder())
                .setId(0)
                .setQuestion("How many feet are in a mile?")
                .setAnswerA("5260")
                .setAnswerB("5270")
                .setAnswerC("5280")
                .setAnswerD("5290")
                .setCorrectAnswer("C")
                .setHint("The altitude of Denver, Colorado")
                .setLastUpdated(new Date())
                .build());
        questionList.add((new TriviaQuestionBuilder())
                .setId(1)
                .setQuestion("What was the first toy advertised on television?")
                .setAnswerA("The Rubix Cube")
                .setAnswerB("Mr. Potato Head")
                .setAnswerC("Barbie")
                .setAnswerD("A hula hoop")
                .setCorrectAnswer("B")
                .setHint("Use your head on this one")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(2)
                .setQuestion("The martial art of kung fu originated in which country?")
                .setAnswerA("Viet Nam")
                .setAnswerB("United States")
                .setAnswerC("Japan")
                .setAnswerD("China")
                .setCorrectAnswer("D")
                .setHint("Name most likely derives from the name of the Qin dynasty")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(3)
                .setQuestion("Which 1979 film included a spaceship called Nostromo?")
                .setAnswerA("The Emperor Strikes Back")
                .setAnswerB("Alien")
                .setAnswerC("The Black Hole")
                .setAnswerD("Star Trek: The Motion Picture")
                .setCorrectAnswer("B")
                .setHint("Not from this world")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(4)
                .setQuestion("Which country lies on the border between Spain and France?")
                .setAnswerA("Andorra")
                .setAnswerB("Luxemborg")
                .setAnswerC("England")
                .setAnswerD("Portugal")
                .setCorrectAnswer("A")
                .setHint("Go with your first guess")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(5)
                .setQuestion("CERN launched the very first website in what year?")
                .setAnswerA("1985")
                .setAnswerB("1960")
                .setAnswerC("1990")
                .setAnswerD("1995")
                .setCorrectAnswer("C")
                .setHint("Not before Star Wars")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(6)
                .setQuestion("What is the largest animal currently on Earth?")
                .setAnswerA("Elephant")
                .setAnswerB("Polar Bear")
                .setAnswerC("Blue Whale")
                .setAnswerD("Box Jellifish")
                .setCorrectAnswer("C")
                .setHint("Stick to the seas")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(7)
                .setQuestion("What was first feature length animated film?")
                .setAnswerA("Akira")
                .setAnswerB("Snow White and the Seven Dwarfs")
                .setAnswerC("Cinderella")
                .setAnswerD("Bambi")
                .setCorrectAnswer("B")
                .setHint("Bad apples")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(8)
                .setQuestion("The assasination that is said to have lead to World War I, occured in what city?")
                .setAnswerA("Paris")
                .setAnswerB("Sarajevo")
                .setAnswerC("Belgrade")
                .setAnswerD("Rome")
                .setCorrectAnswer("B")
                .setHint("Go east")
                .setLastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .setId(9)
                .setQuestion("World War I flying ace Manfred von Richthofen is known by what nickname?")
                .setAnswerA("Snoopy")
                .setAnswerB("Bob")
                .setAnswerC("The Manchurian Candidate")
                .setAnswerD("The Red Baron")
                .setCorrectAnswer("D")
                .setHint("Royalty")
                .setLastUpdated(new Date())
                .build()
        );

        questionList.add((new TriviaQuestionBuilder())
                .setId(10)
                .setQuestion("The Lone Star State is the nickname for which U.S. State?")
                .setAnswerA("California")
                .setAnswerB("Colorado")
                .setAnswerC("Texas")
                .setAnswerD("Alaska")
                .setCorrectAnswer("C")
                .setHint("Don't 'mess' this one up")
                .setLastUpdated(new Date())
                .build()
        );
    }
    @Override
    public TriviaQuestion getQuestionByIndex(long index) {
        return ((int)index < questionList.size())? questionList.get((int) index) : null;
    }

    @Override
    public TriviaQuestion getQuestionById(long id) {
        for(TriviaQuestion question : questionList){
            if(question.getId() == id){
                return question;
            }
        }
        return null;
    }

    @Override
    public TriviaQuestion getRandomQuestion() {
        return questionList.get(new Random().nextInt(questionList.size()));
    }

    @Override
    public List<TriviaQuestion> getQuestionList(long offset) {
        long start = offset;
        if(start<0){
            start = 0;
        } else if (start >= questionList.size()){
            start = questionList.size();
        }
        long end = start + MAX_NUMBER_OF_QUESTIONS_PER_PAGE;
        if(end >= questionList.size()){
            end = questionList.size();
        }
        return questionList.subList((int)start,(int)end);
    }

    @Override
    public List<TriviaQuestion> getSpecifiedQuestionList(long... id) {
        ArrayList<TriviaQuestion> returnList = new ArrayList<>();
        for(long curId: id){
            returnList.add(getQuestionById(curId));
        }
        return returnList;
    }

    @Override
    public long getQuestionListSize() {
        return questionList.size();
    }
}
