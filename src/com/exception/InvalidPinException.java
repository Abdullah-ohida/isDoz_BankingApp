package com.exception;

public class InvalidPinException extends BankingApplicationException{
    public InvalidPinException(String message){
        super(message);
    }
}
