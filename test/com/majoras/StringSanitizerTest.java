package com.majoras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSanitizerTest {

    @Test
    void reduceToFirstPositiveIntegerWorksIfIntIsFirst() {
        assertEquals(1,StringSanitizer.reduceToFirstPositiveInteger("1Fiona"));
    }
    @Test
    void reduceToFirstPositiveIntegerWorksIfIntIsMiddle() {
        assertEquals(1,StringSanitizer.reduceToFirstPositiveInteger("Fio1na"));
    }
    @Test
    void reduceToFirstPositiveIntegerWorksIfIntIsLast() {
        assertEquals(1,StringSanitizer.reduceToFirstPositiveInteger("Fiona1"));
    }
    @Test
    void reduceToFirstPositiveIntegerWorksIfIntIsFirstOfTwo() {
        assertEquals(1,StringSanitizer.reduceToFirstPositiveInteger("Fi1ona2"));
    }
    @Test
    void reduceToFirstPositiveIntegerReturnsMinusOneIfNoInt() {
        assertEquals(-1,StringSanitizer.reduceToFirstPositiveInteger("Fiona"));
    }

    @Test
    void replaceWithCommandStringsCapitalizes() {
        assertEquals(StringSanitizer.replaceWithCommandStrings("abc31"),"ABC31");
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsFirst() {
        assertEquals(StringSanitizer.replaceWithCommandStrings(Command.QUIT.commandString()+"Fiona"),Command.QUIT.commandString());
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsMiddle() {
        assertEquals(StringSanitizer.replaceWithCommandStrings("Sydney"+Command.QUIT.commandString()+"Fiona"),Command.QUIT.commandString());
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsLast() {
        assertEquals(StringSanitizer.replaceWithCommandStrings("Fiona"+Command.QUIT.commandString()),Command.QUIT.commandString());
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsFirstOfTwo() {
        assertEquals(StringSanitizer.replaceWithCommandStrings(Command.QUIT.commandString()+"Fiona"+Command.RESTART.commandString()),Command.QUIT.commandString());
    }

    @Test
    void nonAlphasReplacedWithSpaces() {
        assertEquals(StringSanitizer.nonAlphasReplacedWithSpaces("1a////quick^brown\\fox       jumped over the"),"a quick brown fox jumped over the");
    }
}