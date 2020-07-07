package com.majoras;

/**
 * A set of utility methods for taking input strings and putting them into an expected format
 */
class StringSanitizer {
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
     * takes a "dirty" string and replaces it with capitalized commandStrings, if it contains any
     *
     * specifically: if Commands are detected, this method shortens the string to the first commandString on its own;
     * this method will always capitalize the input.
     *
     * @param dirtyString raw input string
     * @return either a capitalized original string or one of the command strings (found in the enum Command)
     */
    static String replaceWithCommandStrings(String dirtyString)
    {
        //capitalize all letters; this allows users to enter commands in lowercase and the system will still execute them
        String cleanString = dirtyString.toUpperCase();
        //if string contains command, shorten string to command and return it, skipping any other processing
        if(CommandHandler.containsACommand(dirtyString))
        {
            return CommandHandler.reduceToFirstCommand(dirtyString).commandString();
        }
        //if string contains characters other than letters and spaces, replace them with spaces

        return cleanString;
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
