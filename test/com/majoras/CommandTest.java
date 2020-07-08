package com.majoras;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void allCommandStrings() {
        assertArrayEquals(Arrays.stream(Command.class.getEnumConstants()).map(Command::commandString).toArray(String[]::new),Command.allCommandStrings());
    }
}