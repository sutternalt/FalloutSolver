package com.majoras;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An enum used to implement basic UI commands.
 *
 * Glossary:
 * Command: The enum object; eg Command.FWDSLASH
 * Command Name: The name of the enum object; eg "FWDSLASH"
 * CommandString: The string the user enters associated with the command; eg "/"
 */
enum Command {
    FWDSLASH{
        //prompt the user to enter a backslash to do a command
        void performCommand()
        {
            System.out.println("Forward slash(es) detected. Use '\\' insetad.\nRestarting...\n");
            Main.start();
        }
        String commandString()
        {
            return "/";
        }
    },
    QUIT{
        //exit
        void performCommand()
        {
            System.exit(0);
        }
        String commandString()
        {
            return "\\Q";
        }
    },
    RESTART{
        //restart
        void performCommand()
        {
            System.out.println();
            Main.start();
        }
        String commandString()
        {
            return "\\R";
        }
    };


    /**
     *
     * @return the set of CommandStrings associated with all commands;
     */
    static Set<String> getCommandStringsAsSet()
    {
        return Arrays.stream(values())
                .map(Command::commandString)
                .collect(Collectors.toSet());
    }

    /**
     * Performs the command's designated action (ie, designates what the Command actually does)
     */
    abstract void performCommand();

    /**
     *
     * @return the string the user must enter to activate the command
     */
    abstract String commandString();

    /**
     * returns all the commandStrings in Command
     * @return all the commandStrings of all the Commands
     */
    static String[] allCommandStrings()
    {
        return Arrays.stream(Command.class.getEnumConstants()).map(Command::commandString).toArray(String[]::new);
    }
}
