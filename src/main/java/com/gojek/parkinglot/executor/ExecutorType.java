package com.gojek.parkinglot.executor;

public enum ExecutorType {

    INTERACTIVE("Interactive"),
    FILE("File");

    private String value;


    private ExecutorType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }


}
