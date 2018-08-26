package com.rhuco;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NodeTest {
    private Node node1;
    private Node node2;
    private Data dataInt1;
    private Data dataInt2;

    @BeforeAll
    protected void setUp(){
        dataInt1 = new Data(123);
        dataInt2 = new Data(000);
        node1 = new Node(dataInt1);
        node2 = new Node(dataInt2);
    }

    // test default set with construction
    @Test
    public void testDefault(){
        Node node3 = new Node();
        assertEquals(null, node3.getNext());
        assertEquals(null, node3.getData());
    }

    @Test
    public void testGetData(){
        assertEquals(dataInt1, node1.getData());
        assertEquals(dataInt2, node2.getData());
    }


    // is this a valid way of testing set and get next?
    @Test
    public void testSetNext(){
        // act
        node2.setNext(node1);

        //assert
        assertEquals(node1, node2.getNext());
    }

    @Test
    public void testGetNext(){
        // arrange & act
        Node node3 = new Node(dataInt1);
        node3.setNext(node2);

        //assert
        assertEquals(node2, node3.getNext());
    }
}
