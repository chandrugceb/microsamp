package com.chan.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TriviaQuestoinArrayAccessTest {
    TriviaQuestoinArrayAccess triviaQuestoinArrayAccess;
    @Before
    public void setUp() throws Exception {
        triviaQuestoinArrayAccess = new TriviaQuestoinArrayAccess();
    }

    @After
    public void tearDown() throws Exception {
        triviaQuestoinArrayAccess=null;
    }

    @Test
    public void getQuestionByIndex() {
        TriviaQuestion triviaQuestion = triviaQuestoinArrayAccess.getQuestionByIndex(0);
        assertTrue(triviaQuestion.getCorrectAnswer()=="C");
    }

    @Test
    public void getQuestionById() {
        TriviaQuestion triviaQuestion = triviaQuestoinArrayAccess.getQuestionById(0);
        assertTrue(triviaQuestion.getCorrectAnswer()=="C");
    }

    @Test
    public void getRandomQuestion() {
        TriviaQuestion triviaQuestion = triviaQuestoinArrayAccess.getRandomQuestion();
        assertTrue("ABCD".contains(triviaQuestion.getCorrectAnswer()));
    }

    @Test
    public void getQuestionList() {
        List<TriviaQuestion> triviaQuestionList = triviaQuestoinArrayAccess.getQuestionList(1);
        assertTrue(triviaQuestionList.get(0).getId()==1);
    }

    @Test
    public void getSpecifiedQuestionList() {
        List<TriviaQuestion> triviaQuestionList = triviaQuestoinArrayAccess.getSpecifiedQuestionList(1,2,5);
        assertTrue(triviaQuestionList.size()==3);
    }

    @Test
    public void getQuestionListSize() {
        assertTrue(triviaQuestoinArrayAccess.getQuestionListSize()==11);
    }
}