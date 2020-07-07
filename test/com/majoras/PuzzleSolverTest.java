package com.majoras;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleSolverTest {

    static String[] WORDS = {"AAAAA", "BBBBB", "ACCCC", "AABBD", "AAABC"};
    static String ANSWER = "AABBD";
    static String FIRSTWORD = "AAAAA";

    static Word answerWord;

    @BeforeAll
    static void setup()
    {
        answerWord = new Word(ANSWER);

    }

    @Test
    void solvePuzzle1() {
        assertTrue(false); //isn't this not a nunit test, technically?
    }
}