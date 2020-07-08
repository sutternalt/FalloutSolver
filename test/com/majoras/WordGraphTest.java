package com.majoras;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordGraphTest {

    static WordGraph wordGraph;
    static Word AAAAA;
    static Word BBBBB;
    static Word ACCCC;
    static Word AABBD;
    static Word AAABC;

    @BeforeAll
    static void setup()
    {
        String[] words = {"AAAAA","BBBBB","ACCCC","AABBD","AAABC"};
        AAAAA = new Word("AAAAA");
        BBBBB = new Word("BBBBB");
        ACCCC = new Word("ACCCC");
        AABBD = new Word("AABBD");
        AAABC = new Word("AAABC");

        wordGraph = new WordGraph(words);
    }

    @Test
    void getWordsFrom() {
        assertEquals(ACCCC,wordGraph.getWordsFrom("AAAAA",1).toArray()[0]);
    }

    @Test
    void getBestWord() {
        assertEquals(AAAAA,wordGraph.getBestWord());
    }

    @Test
    void contains() {
        assertTrue(wordGraph.contains(AAABC.getLabel()));
    }
}