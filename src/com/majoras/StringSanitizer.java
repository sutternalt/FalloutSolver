package com.majoras;

import java.util.Set;
import java.util.TreeMap;

public class StringSanitizer {


    /**
     * reduces a string to a positive integer chain if possible; otherwise, returns -1
     * @param input any string (preferably one containing a number)
     * @return a positive integer or -1 if an error occured
     */
    static int reduceToFirstPositiveInteger(String input)
    {
        int reducedNumber = -1;

        //parse string for integers
        String shouldBeEmpty = input.replaceAll("[0-9]", "");
        boolean stringContainsNonNumberCharacters = (!shouldBeEmpty.equals(""));

        //return -1 if input contained symbols not contained in [0-9]
        if(!stringContainsNonNumberCharacters)
        {
            //turn string into integer
            try {
                reducedNumber = Integer.parseInt(input); //numberformatexception must indicate a null string, given preprocessing

                //if reducedNumber is <0 or null, reducednumber is now -1
                if (reducedNumber < 0)
                {
                    reducedNumber = -1;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Empty input detected.");
                //do nothing, as the default return value of this function already indicates an error
            }
        }

        return reducedNumber;
    }

    /**
     * takes a "dirty" string and ensures it follows formatting
     *
     * specifically: if RESTART or QUIT COMMANDS are detected, sanitize shortens the string to said command on its own;
     * if characters other than spaces or letters are detected, sanitize replaces them with spaces
     * finally, sanitize replaces all letters with capital versions
     *
     * @param inputHandler
     * @param dirtyString unsanitized string
     * @return sanitized string
     */
    static String sanitize(InputHandler inputHandler, String dirtyString)
    {
        //capitalize all letters
        String cleanString = dirtyString.toUpperCase();
        //if string contains command, shorten string to command and return it, skipping any other processing
        cleanString = replaceWithCommands(inputHandler, cleanString, Main.getCommandHandler().getCommands());
        //if string contains characters other than letters and spaces, replace them with spaces

        return cleanString;
    }

    /**
     * replaces a string with specified commands if string contains said commands
     *
     * replaces with the first command it finds
     * @param inputHandler
     * @param input String to be checked/replaced
     * @return a string consisting of either the first occurence of any of [commands] or the original input, unaltered
     **/
    private static String replaceWithCommands(InputHandler inputHandler, String input, Set<String> commands)
    {
        String commandReplacedString = input;
        TreeMap<Integer,String> commandsByIndex = new TreeMap<>(); //keys are index of first occurence, vals are commands

        //create map by searching input for commands
        for(String command : commands)
        {
            commandsByIndex.put(input.indexOf(command),command);
        }

        //clear commandsByIndex of commands that weren't found
        commandsByIndex.remove(-1);

        //if map is empty, make dummy first value of -1 to prevent null reference errors
        if(commandsByIndex.firstEntry() == null)
        {
            commandsByIndex.put(-1,"BAD");
        }
        int firstKey = commandsByIndex.firstEntry().getKey();

        //search map for first command, if one exists, and replace string with first command
        if(firstKey != -1)
        {
            commandReplacedString = commandsByIndex.get(firstKey);
        }

        return commandReplacedString;
    }

    /**
     * replaces all characters that are not A-Z (uppercase) with a single space
     *
     * This method also shortens multiple spaces to single spaces (eg "ABAEeeee.. FEFIrerr    feGEge" -> "ABAE FEFI GE"
     * @param dirtyString string to be replaced
     * @return a string containing only capital letters and single spaces
     */
    static String nonAlphasReplacedWithSpaces(String dirtyString)
    {
        //replace with spaces
        String cleanString = dirtyString.replaceAll("[^A-Z ]"," ");
        //compress multiple spaces to single space
        cleanString = cleanString.replaceAll("  +"," ");
        return cleanString;
    }
}
