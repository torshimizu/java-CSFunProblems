package com.rhuco;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataTest {
    private Data dataInt;
    private Data dataString;
    private Data dataDouble;
    private Data dataBoolean;

    @BeforeAll
    protected void setUp() throws Exception {
        dataInt = new Data(123);
        dataString = new Data("Taco");
        dataDouble = new Data(10.5);
        dataBoolean = new Data(true);
    }

    @Test
    public void testGetValue() {
        System.out.println("Hello world");
        assertEquals(123, dataInt.getValue());
        assertEquals("Taco", dataString.getValue());
        assertEquals(10.5, dataDouble.getValue());
        assertEquals(true, dataBoolean.getValue());
    }
}