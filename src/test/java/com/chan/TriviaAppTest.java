package com.chan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriviaAppTest {

    TriviaApp triviaApp;
    @Before
    public void setUp() throws Exception {
        triviaApp = new TriviaApp();
    }

    @After
    public void tearDown() throws Exception {
        triviaApp = null;
    }

    @Test
    public void getDirectory() {
        assertTrue(triviaApp != null);
    }
}