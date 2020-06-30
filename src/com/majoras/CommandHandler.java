package com.majoras;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A message-based command system; stores commands in a FIFO data structure (queue) and executes all of them sequentially
 * upon calling handleCommands
 */
public class CommandHandler {

    private Queue<Command> messageQueue;
    private static Map<Command,String> commandStrings;

    /**
     * specifies command strings and adds them to the commandStrings map
     */
    public CommandHandler()
    {
        commandStrings = new HashMap<>();
        commandStrings.put(Command.fwdSlash,"/");
        commandStrings.put(Command.quit,"\\Q");
        commandStrings.put(Command.restart,"\\R");

        messageQueue = new LinkedBlockingQueue<>();
    }

    /**
     * deals with commands contained in the messageQueue; assumes command is contained in COMMANDS
     */
    void handleCommands() {
        Iterator messageIterator = messageQueue.iterator();
        while (messageIterator.hasNext()) {
            Command currentCommand = (Command)messageIterator.next();
            performCommand(currentCommand);
        }
    }

    /**
     * performs the action associated with a command
     * @param command
     */
    private void performCommand(Command command)
    {
        switch(command) {
            case fwdSlash: {
                System.out.println("Forward slash(es) detected. Use '\\' insetad.\nRestarting...\n");
                Main.start();
                break;
            }
            case quit: {
                System.exit(0);
                break;
            }
            default:
            case restart: {
                System.out.println();
                Main.start();
                break;
            }
        }
    }

    /**
     *
     * @param input
     * @return true if input matches a string associated with a command
     */
    static boolean isACommand(String input)
    {
        return getCommands().contains(input);
    }

    /**
     *
     * @return the set of Strings associated with all commands
     */
    static Set<String> getCommands()
    {
        return new HashSet<String>(commandStrings.values());
    }

    /**
     * Adds specified command to the message queue
     *
     * Assumes commands are contained within the command list; this is guaranteed if you use containsCommand and
     * reduceToFirstCommand to parse strings into commands
     *
     * @param command the Command to be added to the message queue
     * @return true if command added successfully
     */
    boolean sendCommand(Command command)
    {
        assert commandStrings.keySet().contains(command) : "Command not recognized!";
        return messageQueue.offer(command);
    }


    /**
     *
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    boolean reduceToFirstCommand(String input)
    {
        //assert string does, in fact, contain a command
    }

    boolean stringContainsCommand(String input){


        for(Integer index:commandsByIndex.keySet()){
            if(index >= 0) {
                inputHandler.inputContainsCommand = true;
                break;
            }
        }
        return inputContainsCommand;
    }
}
