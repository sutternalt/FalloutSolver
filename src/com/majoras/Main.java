//TODO: unit tests (should've made those first, damnit)
//TODO: comprehensive tests

package com.majoras;

public class Main {

    static final double VERSION = 0.1;
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
}
