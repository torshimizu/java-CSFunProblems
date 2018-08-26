package com.rhuco;

public class Node {
    private Data data;
    private Node next;

    public Node(){
        this.data = null;
        this.next = null;
    }

    public Node(Data data){
        this.data = data;
        this.next = null;
    }

    public Data getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

