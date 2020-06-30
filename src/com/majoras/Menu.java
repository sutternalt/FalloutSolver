package com.majoras;

import java.util.Set;

public class Menu {
    /**
     * displays the main menu, version info, and COMMANDS
     */
    static void displayMenu()
    {
        System.out.println("   //Fallout Solver\\\\");
        System.out.println("         v." + Main.VERSION );
        System.out.println(QUITCOMMAND + " to quit, "+ RESTARTCOMMAND +" to start\n" + "=========================\n");
    }

    /**
     * Prompts the user to select from a list of words
     *
     * This function arbitrarily limits the number of possible matching words to 100, which seems reasonably
     * out of bounds
     * @param words the list of words the user must select from
     * @return the word the user selected
     */
    static Word wordSelector(Set<Word> words)
    {
        final int LOWERLIMIT = 0;
        final int REASONABLELIMITOFMATCHINGWORDS = 100;

        Word wordSelected;
        Word[] wordsAsArray = new Word[REASONABLELIMITOFMATCHINGWORDS];
        int maxIndex = LOWERLIMIT;

        //In addition to displaying a list of words, create an array of words from the set
        for(Word word:words)
        {
            System.out.println(maxIndex + ". " + word.getLabel());
            wordsAsArray[maxIndex] = word;
            maxIndex++;
        }
        Main.inputHandler = new InputHandler();
        int inputNum = Main.inputHandler.getNum();
        if(inputNum < LOWERLIMIT || maxIndex < inputNum)
        {
            System.out.println("Selection not found. Try again:");
            return wordSelector(words);
        }

        wordSelected = wordsAsArray[inputNum];
        return wordSelected;
    }
}
