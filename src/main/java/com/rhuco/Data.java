package com.rhuco;

public class Data {
    Object value;

    public Data(int value){
        this.value = value;
    }

    public Data(double value){
        this.value = value;
    }

    public Data(String value){
        this.value = value;
    }

    public Data(Boolean value){
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

}
