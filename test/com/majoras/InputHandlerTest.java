package com.majoras;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    private void inputStreamWith(String string)
    {
        ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
        System.setIn(in);
    }

    private boolean testYesNoWith(String input)
    {
        InputStream sysInBackup = System.in;

        inputStreamWith(input);

        boolean test = InputHandler.getYesNo();
        System.setIn(sysInBackup);
        return test;
    }

    private int testGetNum(String input)
    {
        InputStream sysInBackup = System.in;

        inputStreamWith(input);

        int number = InputHandler.getNum();
        System.setIn(sysInBackup);
        return number;
    }

    @Test
    void getYesNoTrueOnYes() {
        assertTrue(testYesNoWith("Yes"));
    }
    @Test
    void getYesNoTrueOnY() {
        assertTrue(testYesNoWith("Y"));
    }
    @Test
    void getYesNoTrueOny() {
        assertTrue(testYesNoWith("y"));
    }
    @Test
    void getYesNoFalseOnNo() {
        assertFalse(testYesNoWith("No"));
    }
    @Test
    void getYesNoFalseOnN() {
        assertFalse(testYesNoWith("N"));
    }
    @Test
    void getYesNoFalseOnn() {
        assertFalse(testYesNoWith("n"));
    }

    @Test
    void getNumWorksOnNum() {
        assertEquals(testGetNum("5"),5);
    }
    @Test
    void getNumGivesMinusOneOnCommand(){
        assertEquals(testGetNum(Command.QUIT.commandString()),-1);
    }


    @Test
    void getWordsString() {
        assertTrue(false);
    }
}