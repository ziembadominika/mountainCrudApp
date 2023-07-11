package com.crudApp.mountain.exception;

public class UserNotFoundByGivenUserName extends RuntimeException {
    public UserNotFoundByGivenUserName() {
        super("User not found by given username");
    }
}
