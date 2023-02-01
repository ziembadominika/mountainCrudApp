package com.crudApp.mountain.exception;

public class MountainRangeNotFoundException extends RuntimeException{
    public MountainRangeNotFoundException(){
        super("Mountain range not found by given id");
    }
}
