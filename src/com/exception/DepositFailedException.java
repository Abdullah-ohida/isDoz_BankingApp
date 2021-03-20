package com.exception;

public class DepositFailedException extends BankingApplicationException{
    public DepositFailedException() {
    }

    public DepositFailedException(String message) {
        super(message);
    }

    public DepositFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositFailedException(Throwable cause) {
        super(cause);
    }
}
