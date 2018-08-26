package com.rhuco;

public class LinkedList {
    private Node head;

    public LinkedList(){
        this(null);
    }

    public LinkedList(Node head){
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    // need a setHead method
    public void setHead(Node newHead) {
        this.head = newHead;
    }

    public int length() {
        if (head == null) return 0;
        if (head.getNext() == null) return 1; // can this be false?

        int count = 1;
        Node current = head;

        while(current.getNext() != null){
            count += 1;

            current = current.getNext();
        }

        return count;

    }


    public void insert(Node nodeToInsert) {
        if (nodeToInsert.getNext() != null) {
            throw new Error("Cannot insert more than one node");
        } else {
            nodeToInsert.setNext(head);
            this.setHead(nodeToInsert);
        }
    }

    public Boolean find(Data dataToFind) {
        if (this.head == null) return false;
        Node current = this.head;


        while(current != null){
            // this returns first occurrence of Data
            if(current.getData() == dataToFind){
                return true;
            } else {
                current = current.getNext();
            }
        }

        return false;
    }

    public Data nthFromBeginning(int nodeNumber) {
        int count = 0;
        Node current = this.head;

        while(count < nodeNumber){
            current = current.getNext();
            count++;
        }

        return current.getData();
    }

    public void insertAscending(Node nodeToInsert) {
        if (nodeToInsert.getData() == null) throw new Error("need data in order to insert it in ascending order");

        int nodeValue;
        // guard clause against incoming data that is not sortable
        // currently only accepts ints (not strings to alphabetize)
        try {
            nodeValue = (int) nodeToInsert.getData().getValue();
        } catch (ClassCastException error) {
            throw new ClassCastException("data must be of type int to insert ascending");
        }

        if (this.head == null) {
            this.head = nodeToInsert;
            return;
        }

        int headData = (int) head.getData().getValue();
        // special case since you want to deal with head
        if (headData >= nodeValue) {
            nodeToInsert.setNext(this.head);
            this.setHead(nodeToInsert);
            return;
        }

        Node current = this.head;

        // going to assume that all nodes will have data
        // this doesn't take into account tail
        while (current.getNext() != null){
            // cast obj to int
            int nextNodeValue = (int) current.getNext().getData().getValue();

            if (nextNodeValue >= nodeValue) {
                nodeToInsert.setNext(current.getNext());
                current.setNext(nodeToInsert);
                return;

            } else {
                // move along list
                current = current.getNext();
            }
        }

        // tail case, no other node had data that was greater than nodeToInsert's value
        if (current.getNext() == null) {
            current.setNext(nodeToInsert);
            return;
        }


    }

    public void delete(Data dataToFind) {
        // return if data == null?
        if (head == null) return;

        Node current = head;

        // if data is at head
        if (current.getData() == dataToFind) {
            head = head.getNext();
            return;
        }

        // find in next so that you are able to reconnect list
        while(current.getNext() != null) {
            // when found
            if(current.getNext().getData() == dataToFind){
                // look before you leap
                if(current.getNext().getNext() != null) {
                    current.setNext(current.getNext().getNext());
                    return;
                } else {
                    current.setNext(null);
                }
            } else {
            // when not found
                current = current.getNext();
            }
        }

        return;

    }
}

