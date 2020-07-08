package com.majoras;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A set of utility methods for taking input strings and putting them into an expected format
 */
class StringSanitizer {
    /**
     * reduces a string to a positive integer chain if possible; otherwise, returns -1
     *
     * This method will return the first positive integer chain it finds to the exclusion of all else.
     *
     * @param input any string (preferably one containing a number)
     * @return a positive integer or -1 if an error occured
     */
    static int reduceToFirstPositiveInteger(String input)
    {
        int reducedNumber = -1;


        //check if string contains non-positive-integer characters
        String shouldBeEmpty = input.replaceAll("[0-9]", "");
        boolean stringContainsNonNumberCharacters = (!shouldBeEmpty.equals(""));

        //if so, find the first string of positive integers (ie, one that does not start with a '-'
        if(stringContainsNonNumberCharacters)
        {
            //split string by instances of a number
            Pattern numbers = Pattern.compile("[0-9]");
            Matcher matcher = numbers.matcher(input);

            boolean matchStartsWithNegative = true;
            boolean matchExists = matcher.find();
            String match = null;

            while(matchExists && matchStartsWithNegative)
            {
                match = matcher.group();
                if(matcher.start() != 0) {
                    matchStartsWithNegative = (input.charAt(matcher.start() - 1) == '-');
                }
                else
                {
                    matchStartsWithNegative = false; //a number starting at index 0 is necessarily positive
                }
                matchExists = matcher.find();
            }

            if(match != null)
            {
                reducedNumber = Integer.parseInt(match);
            }
            //otherwise, return -1; input was "bad" and did not contain a positive int
        }
        //otherwise, the string already is a positive integer, so return that
        else
        {
            reducedNumber = Integer.parseInt(input);
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
        String cleanString = dirtyString.replaceAll("[^A-Z |a-z]"," ");
        //compress multiple spaces to single space
        cleanString = cleanString.replaceAll("  +"," ");
        return cleanString.strip();
    }
}
