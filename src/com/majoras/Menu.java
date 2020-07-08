package com.majoras;

import java.util.Set;

class Menu {

    /**
     * displays the main menu, version info, and COMMANDS
     */
    static void displayMenu()
    {
        System.out.println("   //Fallout Solver\\\\");
        System.out.println("         v." + Main.VERSION );
        System.out.println(Command.QUIT.commandString() + " to quit, "+ Command.RESTART.commandString() +" to start\n" + "=========================\n");
    }

    /**
     * Prompts the user to select from a list of words
     *
     * @param words the list of words the user must select from
     * @return the word the user selected
     */
    static Word wordSelector(Set<Word> words)
    {
        final int MINSIZEOFSET = 0;
        final int SETSIZE = words.size();

        Word wordSelected;
        Word[] wordsAsArray = new Word[SETSIZE];
        int maxIndex = MINSIZEOFSET;

        //In addition to displaying a list of words, create an array of words from the set
        for(Word word:words)
        {
            System.out.println(maxIndex + ". " + word.getLabel());
            wordsAsArray[maxIndex] = word;
            maxIndex++;
        }

        int inputNum = InputHandler.getNum();
        if(inputNum < MINSIZEOFSET || maxIndex < inputNum)
        {
            System.out.println("Selection not found. Try again:");
            return wordSelector(words);
        }

        wordSelected = wordsAsArray[inputNum];
        return wordSelected;
    }
}
