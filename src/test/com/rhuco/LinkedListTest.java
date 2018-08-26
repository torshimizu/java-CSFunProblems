package com.rhuco;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedListTest {
    private Data data1;
    private Data data2;
    private Data data3;
    private Node node1;
    private Node node2;
    private Node node3;

    private LinkedList list;

    @BeforeAll
    protected void setUp() throws Exception {
        data1 = new Data(123);
        data2 = new Data (456);
        data3 = new Data (789);
        node1 = new Node(data1);
        node2 = new Node(data2);
        node3 = new Node(data3);
        list = new LinkedList(node1);

        node1.setNext(node2);
        node2.setNext(node3);
    }

    @Test
    public void testConstructorDefaults(){
        LinkedList list2 = new LinkedList();
        assertEquals(null,list2.getHead());
    }

    @Test
    public void testGetHead(){
        assertEquals(node1, list.getHead());
    }

    @Test
    public void testSetHead(){
        // arrange
        LinkedList newList = new LinkedList();

        // act
        assertNull(newList.getHead());
        Node headNode = node1;
        newList.setHead(headNode);

        // assert
        assertEquals(headNode, list.getHead());
    }

    @Nested
    class LengthTests {

        // this probably fails because of the insert one node test
        @Test
        public void testLength () {

            // act
            int listLength = list.length();

            // assert
            assertEquals(3, listLength);

        }

        @Test
        public void testLengthWhenOne () {
            // arrange
            Node listNode = new Node(data1);
            LinkedList listOfOne = new LinkedList(listNode);

            // act
            int listLength = listOfOne.length();

            // assert
            assertEquals(1, listLength);
        }

        @Test
        public void testLengthWhenZero () {
            // arrange
            LinkedList listOfZero = new LinkedList(null);

            // assert
            int listLength = listOfZero.length();

            //act
            assertEquals(0, listLength);
        }
    }

    // TODO: need to test what happens if num from beginning is > list.length
    @Test
    public void testNthFromBeginning(){
        // arrange
        Node node4 = new Node(data1);
        Node node5 = new Node(data2);

        node3.setNext(node4);
        node4.setNext(node5);

        // act
        int nodeNumber = 2;
        Data dataToReturn = node3.getData();
        Data valueOfNthNode = list.nthFromBeginning(nodeNumber);

        // assert
        assertEquals(dataToReturn, valueOfNthNode);

        // tear down
        node3.setNext(null);
    }

    // TODO: these are causing length errors
    @Nested
    class InsertFunctions {
        LinkedList listToInsertOn = new LinkedList();
        Node headNode;

        @BeforeEach
        public void insertSetUp() throws Exception {
           headNode = new Node(data1);
           listToInsertOn.setHead(headNode);
        }

        @AfterEach
        public void insertTearDown() throws Exception {
            listToInsertOn.setHead(null);
            headNode.setNext(null);
        }

        // TODO: make this like delete where you create an arraylist
        @Test
        public void testInsert(){
            Node nodeToInsert = new Node(data1);

            // act
            int beforeLength = listToInsertOn.length();
            listToInsertOn.insert(nodeToInsert);

            // assert
            assertEquals(beforeLength + 1, listToInsertOn.length());
            assertEquals(nodeToInsert, listToInsertOn.getHead());

        }

        @Test
        public void testInsertMultipleNodes(){
            // arrange
            Node additionalNode = new Node(data1);
            Node nodeToInsert = new Node(data1);
            nodeToInsert.setNext(additionalNode);

            // assert
            assertThrows(Error.class, () -> listToInsertOn.insert(nodeToInsert), "Cannot insert more than one node");
        }

        @Test
        public void testInsertAscending(){
            // arrange
            Node node2 = new Node(data2);
            Node node3 = new Node(data3);
            node2.setNext(node3);
            listToInsertOn.getHead().setNext(node2);

            Data dataToInsert = new Data (567);
            Node nodeToInsert = new Node(dataToInsert);

            // act
            listToInsertOn.insertAscending(nodeToInsert);

            // assert
            Data datumOfNthFromBegging = listToInsertOn.nthFromBeginning(2);
            assertEquals(dataToInsert, datumOfNthFromBegging); // is this a good test?
        }

        @Test
        public void testInsertAscendingAtHead(){
            Data dataToInsert = new Data (12);
            Node nodeToInsert = new Node (dataToInsert);

            listToInsertOn.insertAscending(nodeToInsert);

            Data headData = listToInsertOn.getHead().getData();
            assertEquals(dataToInsert, headData);
        }

        @Test
        public void testInsertAscendingAtTail(){
            Data dataToInsert = new Data (999);
            Node nodeToInsert = new Node (dataToInsert);

            listToInsertOn.insertAscending(nodeToInsert);

            int count = listToInsertOn.length();

            Data datumOfTail = listToInsertOn.nthFromBeginning(count - 1);
            assertEquals(dataToInsert, datumOfTail);
        }

        @Test
        public void testInsertAscendingWhenNotInt(){
            Data dataToInsert = new Data (true);
            Node nodeToInsert = new Node (dataToInsert);

            assertThrows(ClassCastException.class, () -> listToInsertOn.insertAscending(nodeToInsert), "data must be of type int to insert ascending");
        }

    }

    @Nested
    class TestFind {

        // test when the list is empty or when there's multiple occurrences of data?

        @Test
        public void testFindWhenExists() {
            // arrange
            Data dataToFind = data2;

            // act
            Boolean wasFound = list.find(dataToFind);

            // assert
            assertEquals(true, wasFound);
        }

        @Test
        public void testFindWhenDNE(){
            // arrange
            Data dataToFind = new Data(000);

            // act
            Boolean wasFound = list.find(dataToFind);

            // assert
            assertEquals(false, wasFound);
        }
    }

    @Nested
    class DeleteTests {

        LinkedList newList;
        Data data1;
        Data data2;
        Node node1;
        Node node2;


        @BeforeEach
        public void deleteSetUp() throws Exception {
            newList = new LinkedList();
            data1 = new Data(0);
            data2 = new Data(1);

            node1 = new Node(data1);
            node2 = new Node(data2);

            newList.setHead(node1);
            node1.setNext(node2);
        }

        @AfterEach
        public void deleteTearDown() throws Exception {
            newList.setHead(null);
            node1.setNext(null);
        }


        @Test
        public void testDeleteCount(){
            // arrange
            int beforeCount = newList.length();

            // act
            newList.delete(data2);

            // assert
            assertEquals(beforeCount - 1, newList.length());
        }

        @Test
        public void testDeleteContents() {

            /*
             * delete a node
             * create an arraylist of updated contents
             * create an arraylist of what you should see
             * see if the two arraylist's contents are equal or that one is missing
             */

            // arrange
            ArrayList<Node> expectedAfterDeletion = new ArrayList<>();
            expectedAfterDeletion.add(node1);

            ArrayList<Node> actualAfterDeletion = new ArrayList<>();


            // act
            newList.delete(data2);
            actualAfterDeletion = createArrayListFromLinkedList(newList);

            // assert
            assertEquals(expectedAfterDeletion, actualAfterDeletion);
        }

        public ArrayList<Node> createArrayListFromLinkedList(LinkedList list) {
            ArrayList<Node> al = new ArrayList<Node>();
            Node current = list.getHead();

            while (current != null) {
                al.add(current);
                current = current.getNext();
            }

            return al;
        }
    }
}
