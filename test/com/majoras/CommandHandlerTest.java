package com.majoras;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    public boolean testCommandSuccessful = false;

    @Test
    void containsACommandTrueOnCommandAlone() {
        assertTrue(CommandHandler.containsACommand(Command.QUIT.commandString()));
    }
    @Test
    void containsACommandTrueOnCommandAsSecond() {
        assertTrue(CommandHandler.containsACommand("Fiona"+Command.QUIT.commandString()));
    }
    @Test
    void containsACommandTrueOnCommandAsFirst() {
        assertTrue(CommandHandler.containsACommand(Command.QUIT.commandString()+"Fiona"));
    }
    @Test
    void containsACommandTrueOnCommandAsMiddle() {
        assertTrue(CommandHandler.containsACommand("Fiona"+Command.QUIT.commandString()+"Sydney"));
    }
    @Test
    void containsACommandFalseOnNoCommand(){
        String testString = "Fiona";
        assert(!Arrays.stream(Command.allCommandStrings()).collect(Collectors.toSet()) .contains(testString)) :
                "Change testString to a String that is not a commandString!";
        assertFalse(CommandHandler.containsACommand(testString));
    }

    @Test
    void getCommands() {
        boolean allCommandsGotten = true;

        for(String command : Command.allCommandStrings())
        {
            if(!Command.getCommandStringsAsSet().contains(command))
            {
                allCommandsGotten = false;
            }
        }

        assertTrue(allCommandsGotten);
    }

    @Test
    void sendCommand() {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.sendCommand(Command.QUIT);

        assert(commandHandler.getMessageQueue().contains(Command.QUIT));
    }

    @Test
    void reduceToFirstCommandWorksWithCommandAlone() {
        String testString = Command.QUIT.commandString();
        assertTrue(Arrays.asList(Command.values()).contains(CommandHandler.reduceToFirstCommand(testString)));
    }
    @Test
    void reduceToFirstCommandWorksWithCommandFirst() {
        String testString = Command.QUIT.commandString() + "Fiona";
        assertTrue(Arrays.asList(Command.values()).contains(CommandHandler.reduceToFirstCommand(testString)));
    }
    @Test
    void reduceToFirstCommandWorksWithCommandMiddle() {
        String testString = "Sydney" + Command.QUIT.commandString() + "Fiona";
        assertTrue(Arrays.asList(Command.values()).contains(CommandHandler.reduceToFirstCommand(testString)));
    }
    @Test
    void reduceToFirstCommandWorksWithCommandLast() {
        String testString = "Fiona" + Command.QUIT.commandString();
        assertTrue(Arrays.asList(Command.values()).contains(CommandHandler.reduceToFirstCommand(testString)));
    }
    @Test
    void reduceToFirstCommandReducesToFirstCommandOnly() {
        String testString = Command.QUIT.commandString() + Command.RESTART.commandString();
        assertEquals(Command.QUIT,CommandHandler.reduceToFirstCommand(testString));
    }

    @Test
    void handleCommands() {
        assertTrue(false);
    }
}