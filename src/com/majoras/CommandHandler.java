package com.majoras;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A message-based command system; stores commands in a FIFO data structure (queue) and executes all of them sequentially
 * upon calling handleCommands
 */
class CommandHandler {

    private Queue<Command> messageQueue;

    /**
     * specifies command strings and adds them to the commandStrings map
     */
    CommandHandler()
    {
        messageQueue = new LinkedBlockingQueue<>();
    }

    /**
     * deals with commands contained in the messageQueue; assumes command is contained in COMMANDS
     */
    void handleCommands() {
        for(Command command : messageQueue)
        {
            command.performCommand();
        }
    }

    /**
     * Checks string to see if it contains a command
     * @param input the string to be checked
     * @return true if input matches a string associated with a command
     */
    static boolean containsACommand(String input)
    {
        return Command.getCommandStringsAsSet().contains(input);
    }

    /**
     * Adds specified command to the message queue
     *
     * Quits the program with status 1 if there are more than Integer.MAX commands added to the queue
     *
     * @param command the Command to be added to the message queue
     */
    void sendCommand(Command command)
    {
        try
        {
            messageQueue.add(command);
        }
        catch(IllegalStateException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    /**
     * Converts a string to the Command of the first matching name string contained in the input.
     * Assumes that said string already contains a command.
     *
     * @param stringWithCommandName the String containing > 0 commands to reduce
     * @return String of the first command found; this matches the name found in the Command enum
     */
    static Command reduceToFirstCommand(String stringWithCommandName)
    {
        //assert string does, in fact, contain a command
        assert CommandHandler.containsACommand(stringWithCommandName) : "Input must contain a command!";

        //search for one of command strings and index by first character placement

        //create map of commands arranged by index of first instance of command found
        TreeMap<Integer, String> containedCommandsByIndex = new TreeMap<>();
        for(String command : Command.allCommandStrings())
        {
            containedCommandsByIndex.put(stringWithCommandName.indexOf(command),command);
        }
        //remove any commands that weren't actually found
        containedCommandsByIndex.remove(-1);

        //reduce string to first command
        return Command.valueOf(containedCommandsByIndex.firstEntry().getValue());
    }

    //for testing only
    Queue<Command> getMessageQueue() {
        return messageQueue;
    }
}
