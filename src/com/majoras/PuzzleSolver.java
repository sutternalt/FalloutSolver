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
        String sanitizedInput = InputHandler.getWordsString();
        System.out.println("Read as: " + sanitizedInput);
        if(Objects.isNull(sanitizedInput)){
            Main.start();
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
        assert (wordGraph.contains(word)) : "Word not in wordgraph!";

        //ask user to try the word
        System.out.println("Try "+word.getLabel()+". Number of letters in common?");
        //get user input indicating how many letters input word has in common with the answer
        int letterInCommon = InputHandler.getNum();
        //did that solve the puzzle?
        Set<Word> possibleNewMatches = wordGraph.getWordsFrom(word,letterInCommon);
        System.out.print("Try ");
        possibleNewMatches.forEach(match -> System.out.print(match.getLabel() + " "));
        System.out.println("\b. Did it work? [Y/N]");
        boolean itWorked = InputHandler.getYesNo();
        //if so, congratulate the user and display the menu again
        if(itWorked)
        {
            System.out.println("Yay!\n");
            Main.start();
        }

        //if not, solvePuzzle(Word)
        else
        {
            Word triedWord;
            if(possibleNewMatches.size() > 1)
            {
                System.out.println("Which word did you try?");
                triedWord = Menu.wordSelector(possibleNewMatches);
            }
            else
            {
                triedWord = (Word)possibleNewMatches.toArray()[0];
            }
            solvePuzzle(triedWord);
        }
    }

    Word getFirstWord()
    {
        return wordGraph.getBestWord();
    }
}
