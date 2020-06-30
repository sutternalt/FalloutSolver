package com.majoras;
import java.util.*;

/**
 *  obtains and sanitizes text input. Intended for one-line use only; if you need multiple inputs,
 *  create a new InputHandler object
 */
class InputHandler {
    //private boolean inputContainsCommand;

    InputHandler() {
    }

    /**
     * Continuously queries the user for either y, Y, n, or N and parses that into a boolean
     * @return a boolean interpretation of user input
     */
    static boolean getYesNo()
    {
        boolean inputAsBoolean = false;
        Main.inputHandler = new InputHandler();
        String yesNo = Main.inputHandler.getWordsString();
        if(Main.inputHandler.containedCommand())
        {
            CommandHandler.handleCommands(yesNo);
        }
        else if(yesNo.equals("Y")||yesNo.equals("y"))
        {
            inputAsBoolean = true;
        }
        else if (yesNo.equals("N")||yesNo.equals("n"))
        {
            inputAsBoolean = false;
        }
        else
        {
            System.out.println("Please enter either 'Y' or 'N'.");
            inputAsBoolean = getYesNo();

        }
        return inputAsBoolean;
    }

    /**
     * Continuously queries the user for a positive int until the user enters either a positive int or a command
     * @return the positive integer the user eventually entered or -1 if the user entered a command
     */
    int getNum() {

        int inputNumber = -1;

        //get input
        String inputString = getInput();
        //sanitize input
        inputString = StringSanitizer.sanitize(this, inputString);

        //is this command?
        if(!inputContainsCommand){
            inputNumber = StringSanitizer.reduceToFirstPositiveInteger(inputString);

            //is this a positive integer (ie, was the input good)?
            if(inputNumber < 0)
            {
                System.out.println("Please enter only a positive integer!");
                inputNumber = getNum();
            }
            //otherwise, do nothing
        }
        else
        {
            CommandHandler.handleCommands(inputString);
        }

        return inputNumber;
    }

    /**
     * prompts user for input and returns the input string or a command. Eliminates all non A-Z characters. If there
     * was a command, getWordsString sends a command to the command handler. [message passing]
     *
     * @return sanitized input as a string from user or null if an error was encountered
     */
    String getWordsString(){

        String input = getInput();

        if(CommandHandler.isACommand(input))
        {
            Main.getCommandHandler().sendCommand(CommandHandler.reduceToFirstCommand(input));
        }
        else
        {
            input = StringSanitizer.nonAlphasReplacedWithSpaces(input);
        }

        return input;
    }

    /**
     * prompts user for input and returns the input string or the first command found
     *
     * @return sanitized input as a string from user or null if an error was encountered
     */
    String getInput(){
        String input = null;
        System.out.print("> ");

        try {
            input = new Scanner(System.in).nextLine();
            input = StringSanitizer.sanitize(this, input);
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Error: No line found");
        }
        catch(IllegalStateException e)
        {
            System.out.println("Scanner closed unexpectedly");
        }

        return input;
    }
}
