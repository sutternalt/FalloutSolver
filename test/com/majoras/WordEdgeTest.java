package com.majoras;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordEdgeTest {
    static Word AAAA, AAAB, BBBB;

    @BeforeAll
    static void setup(){
        AAAA = new Word("AAAA");
        AAAB = new Word("AAAB");
        BBBB = new Word("BBBB");
    }

    @Test
    void getLettersInCommonNone() {
        WordEdge wordEdge = new WordEdge(AAAA,BBBB);
        assertEquals(wordEdge.getLettersInCommon(),0);
    }
    @Test
    void getLettersInCommonOne() {
        WordEdge wordEdge = new WordEdge(AAAB,BBBB);
        assertEquals(wordEdge.getLettersInCommon(),1);
    }
    @Test
    void getLettersInCommonLots() {
        WordEdge wordEdge = new WordEdge(AAAB,AAAA);
        assertEquals(wordEdge.getLettersInCommon(),3);
    }
    @Test
    void getLettersInCommonAll() {
        WordEdge wordEdge = new WordEdge(AAAA,AAAA);
        assertEquals(wordEdge.getLettersInCommon(),4);
    }


    @Test
    void compareTo() {
        WordEdge edge1 = new WordEdge(BBBB,AAAB);
        WordEdge edge3 = new WordEdge(AAAA,AAAB);

        assertTrue(edge1.compareTo(edge3) < 0);
    }
}