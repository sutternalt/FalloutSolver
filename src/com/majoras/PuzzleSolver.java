package com.majoras;

import java.util.Objects;
import java.util.Set;

/**
 * Class used to manage the logic of actually solving the fallout computer-hacking puzzles
 */
class PuzzleSolver {

    private WordGraph wordGraph;

    /**
     * sets up wordGraph by prompting the user; initializes the InputHandler
     */
    PuzzleSolver()
    {
        //get set of words

        System.out.println("Type list of words, separated by spaces, and then press Enter.");

        String sanitizedInput = "";
        while(sanitizedInput.equals(""))
        {
            sanitizedInput = InputHandler.getWordsString();
            System.out.println("Read as: " + sanitizedInput);
            if (sanitizedInput.equals("")) {
                System.out.println("Empty input detected. Try again.");
            }
        }

        //handle COMMANDS
        Main.getCommandHandler().handleCommands();

        //parse string into array
        String[] wordArray = sanitizedInput.split(" ");

        //create graph
        wordGraph = new WordGraph(wordArray);
    }

    /**
     * recursively prompts the user to try new words and report back how many letters in common until the puzzle is
     * solved
     * @param word the current Word the method is analyzing. This Word must be part of wordGraph
     */
    void solvePuzzle(Word word)
    {
        assert (wordGraph.contains(word.getLabel())) : "Word not in wordgraph!";
        CommandHandler commandHandler = Main.getCommandHandler();

        //ask user to try the word
        System.out.println("Number of letters in common with "+word.getLabel()+"?");
        //get user input indicating how many letters input word has in common with the answer
        int letterInCommon = InputHandler.getNum();
        commandHandler.handleCommands();

        //did that solve the puzzle?
        Set<Word> possibleNewMatches = wordGraph.getWordsFrom(word.getLabel(),letterInCommon);
        if(possibleNewMatches.size()>0) {
            System.out.print("Try ");
            possibleNewMatches.forEach(match -> System.out.print(match.getLabel() + " "));
            System.out.println("\b. Did it work? [Y/N]");
            boolean itWorked = InputHandler.getYesNo();
            commandHandler.handleCommands();
            //if so, congratulate the user and display the menu again
            if (itWorked) {
                triggerSuccess();
            }

            //if not, solvePuzzle(Word)
            else {
                Word triedWord;
                if (possibleNewMatches.size() > 1) {
                    System.out.println("Which word did you try?");
                    triedWord = Menu.wordSelector(possibleNewMatches);
                    commandHandler.handleCommands();
                } else {
                    triedWord = (Word) possibleNewMatches.toArray()[0];
                }
                solvePuzzle(triedWord);
            }
        }
        else if(word.getLabel().length() == letterInCommon) //The word has the same number of matching letters as the correct word
        {
            triggerSuccess();
        }
        else //There were no matches found
        {
            logicalFallacyRestart();
        }
    }

    /**
     * Triggered if the user found the correct word.
     */
    private void triggerSuccess()
    {
        System.out.println("Yay!\n");
        Main.start();
    }

    /**
     * Designed to be triggered when user input leads to a logical fallacy
     */
    private void logicalFallacyRestart()
    {
        System.out.println("Something went wrong - make sure you entered everything correctly.\nRestarting....\n\n");
        Main.start();
    }

    Word getFirstWord()
    {
        return wordGraph.getBestWord();
    }
}
