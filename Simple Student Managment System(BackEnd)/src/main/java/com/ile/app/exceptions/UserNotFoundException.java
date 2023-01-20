package com.ile.app.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id){
        super("User could not be found with this ID:" + id);
    }
}
