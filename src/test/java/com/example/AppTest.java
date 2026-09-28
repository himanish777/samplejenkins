package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private final App app = new App();

    @Test
    void greetsTheProvidedName() {
        assertEquals("Hello, Jenkins!", app.greet("Jenkins"));
    }

    @Test
    void usesDefaultGreetingForBlankName() {
        assertEquals("Hello, World!", app.greet(""));
    }
}