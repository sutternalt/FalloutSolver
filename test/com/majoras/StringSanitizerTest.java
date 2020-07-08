package com.majoras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSanitizerTest {

    @Test
    void reduceToFirstPositiveIntegerWorksIfIntIsAlone() {
        assertEquals(1,StringSanitizer.reduceToFirstPositiveInteger("1"));
    }
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
        assertEquals("ABC31",StringSanitizer.replaceWithCommandStrings("abc31"));
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsAlone() {
        assertEquals(Command.QUIT.commandString(),StringSanitizer.replaceWithCommandStrings(Command.QUIT.commandString()));
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsFirst() {
        assertEquals(Command.QUIT.commandString(),StringSanitizer.replaceWithCommandStrings(Command.QUIT.commandString()+"Fiona"));
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsMiddle() {
        assertEquals(Command.QUIT.commandString(),StringSanitizer.replaceWithCommandStrings("Sydney"+Command.QUIT.commandString()+"Fiona"));
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsLast() {
        assertEquals(Command.QUIT.commandString(),StringSanitizer.replaceWithCommandStrings("Fiona"+Command.QUIT.commandString()));
    }
    @Test
    void replaceWithCommandStringsWorksIfCommandIsFirstOfTwo() {
        assertEquals(Command.QUIT.commandString(),StringSanitizer.replaceWithCommandStrings(Command.QUIT.commandString()+"Fiona"+Command.RESTART.commandString()));
    }

    @Test
    void nonAlphasReplacedWithSpaces() {
        assertEquals("a quick brown fox jumped over the",StringSanitizer.nonAlphasReplacedWithSpaces("1a////quick^brown\\fox       jumped over the"));
    }
}