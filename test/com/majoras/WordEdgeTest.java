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
        assertEquals(0,wordEdge.getLettersInCommon());
    }
    @Test
    void getLettersInCommonOne() {
        WordEdge wordEdge = new WordEdge(AAAB,BBBB);
        assertEquals(1,wordEdge.getLettersInCommon());
    }
    @Test
    void getLettersInCommonLots() {
        WordEdge wordEdge = new WordEdge(AAAB,AAAA);
        assertEquals(3,wordEdge.getLettersInCommon());
    }
    @Test
    void getLettersInCommonAll() {
        WordEdge wordEdge = new WordEdge(AAAA,AAAA);
        assertEquals(4,wordEdge.getLettersInCommon());
    }


    @Test
    void compareTo() {
        WordEdge edge1 = new WordEdge(BBBB,AAAB);
        WordEdge edge3 = new WordEdge(AAAA,AAAB);

        assertTrue(edge1.compareTo(edge3) < 0);
    }
}