package com.majoras;
import java.util.*;

/**
 *  obtains and sanitizes text input. Intended for one-line use only; if you need multiple inputs,
 *  create a new InputHandler object
 */
class InputHandler {
    /**
     * Continuously queries the user for either y, Y, n, or N and parses that into a boolean
     * @return a boolean interpretation of user input or false if a command is found
     */
    static boolean getYesNo()
    {
        String yesNo = getWordsString();
        boolean inputAsBoolean;
        if(CommandHandler.containsACommand(yesNo))
        {
            Main.getCommandHandler().sendCommand(CommandHandler.reduceToFirstCommand(yesNo));
            inputAsBoolean = false;
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
            return getYesNo();
        }
        return inputAsBoolean;
    }

    /**
     * Continuously queries the user for a positive int until the user enters either a positive int or a command
     * @return the positive integer the user eventually entered or -1 if the user entered a command
     */
    static int getNum() {

        int inputNumber = -1;

        //get input
        String inputString = getInput();
        //sanitize input
        inputString = StringSanitizer.replaceWithCommandStrings(inputString);

        //is this command?
        if(!CommandHandler.containsACommand(inputString)){
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
            Main.getCommandHandler().sendCommand(CommandHandler.reduceToFirstCommand(inputString));
        }

        return inputNumber;
    }

    /**
     * prompts user for input and returns the input string or a command. Eliminates all non A-Z characters. If there
     * was a command, getWordsString sends a command to the command handler. [message passing]
     *
     * @return sanitized input as a string from user or null if an error was encountered
     */
    static String getWordsString(){

        String input = getInput();

        if(CommandHandler.containsACommand(input))
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
    private static String getInput(){
        String input = null;
        System.out.print("> ");

        try {
            input = new Scanner(System.in).nextLine();
            input = StringSanitizer.replaceWithCommandStrings(input);
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
