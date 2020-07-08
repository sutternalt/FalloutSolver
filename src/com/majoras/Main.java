package com.majoras;

/**
 * FalloutSolver
 *
 * A tool to help solve Fallout's computer hacking puzzles
 * Originally designed, written, tested, and maintained by Andrew Majoras
 */
public class Main {

    static final double VERSION = 0.8; //(Alpha) as of 7-8-20
    private static CommandHandler commandHandler;

    public static void main(String[] args) {
        //initialize
        commandHandler = new CommandHandler();

        //display
        start();
        //check for input
    }

    /**
     * resets all variables, displays the menu, waits for input
     */
    static void start(){
        Menu.displayMenu();
        PuzzleSolver solver = new PuzzleSolver();

        //determine word with the most unique letters-in-common's
        //solvePuzzle(that word)
        solver.solvePuzzle(solver.getFirstWord());
    }

    static CommandHandler getCommandHandler()
    {
        return commandHandler;
    }

    static double getVersion()
    {
        return VERSION;
    }

    //for testing purposes only; initializes commandHandler
    static void testInitCommandHandler()
    {
        assert commandHandler == null : "Only use this method for testing!";
        commandHandler = new CommandHandler();
    }
}
