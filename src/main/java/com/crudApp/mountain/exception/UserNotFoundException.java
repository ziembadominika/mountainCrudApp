package com.crudApp.mountain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User not found by given id");
    }
}
