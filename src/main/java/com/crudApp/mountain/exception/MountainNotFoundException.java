package com.crudApp.mountain.exception;

public class MountainNotFoundException extends RuntimeException{
    public MountainNotFoundException() {
        super("Mountain not found by given id");
    }
}
