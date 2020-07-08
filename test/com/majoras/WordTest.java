package com.majoras;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    static WordGraph wordGraph;
    ////////////Word    LIC's : Unique LIC's
    static Word AAAAA; //0123 : 4
    static Word BBBBB; //0021 : 3
    static Word ACCCC; //1012 : 3
    static Word AABBD; //2213 : 3
    static Word AAABC; //3123 : 3
    static HashSet<Word> words;

    @BeforeAll
    static void setup()
    {
        AAAAA = new Word("AAAAA");
        BBBBB = new Word("BBBBB");
        ACCCC = new Word("ACCCC");
        AABBD = new Word("AABBD");
        AAABC = new Word("AAABC");

        words = new HashSet<>();
        words.add(AAAAA);
        words.add(BBBBB);
        words.add(ACCCC);
        words.add(AABBD);
        words.add(AAABC);

        for(Word outerWord : words)
        {
            for(Word innerWord : words)
            {
                outerWord.addEdge(innerWord);
            }
        }
    }

    @Test
    void compareToGreater() {
        assertTrue(AAAAA.compareTo(BBBBB) > 0);
    }
    @Test
    void compareToLesser() {
        assertTrue(BBBBB.compareTo(AAAAA) < 0);
    }
    @Test
    void compareToEqual() {
        assertEquals(0,AABBD.compareTo(AAABC));
    }

    @Test
    void lettersInCommon() {
        assertEquals(0,AAAAA.lettersInCommon(BBBBB));
        assertEquals(1,AAAAA.lettersInCommon(ACCCC));
        assertEquals(3,AAAAA.lettersInCommon(AAABC));
        assertEquals(5,AAAAA.lettersInCommon(AAAAA));
    }

    @Test
    void getNumUnique_LettersInCommon_s() {
        assertEquals(4,AAAAA.getNumUnique_LettersInCommon_s());
        assertEquals(3,BBBBB.getNumUnique_LettersInCommon_s());
    }
}