package com.crudApp.mountain.exception;

public class UserNotFoundByGivenIdException extends RuntimeException{
    public UserNotFoundByGivenIdException(){
        super("User not found by given id");
    }
}
