package com.majoras;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void displayMenu() {
        Menu.displayMenu();
        System.out.println("Is this correct? [y/n]");
        assertTrue(InputHandler.getYesNo());
    }

    @Test
    void wordSelector() {
        Word word1 = new Word("First");
        Word word2 = new Word("Second");
        Word word3 = new Word("Third");
        HashSet<Word> words = new HashSet<>();
        words.add(word1);
        words.add(word2);
        words.add(word3);


        Word selectedWord = Menu.wordSelector(words);
        System.out.println("Selected word: "+selectedWord.getLabel());
        System.out.println("Is this correct? [y/n]");
        assertTrue(InputHandler.getYesNo());
    }
}