package com.example.exceptions;

public class TooFewSidesException extends IllegalArgumentException{
    private int numberOfSides;

    public TooFewSidesException(String message, int numberOfSides) {
        super(message);
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}
