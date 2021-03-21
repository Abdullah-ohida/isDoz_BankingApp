package com.exception;

public class WithdrawFailedException extends BankingApplicationException{
    public WithdrawFailedException() {
    }

    public WithdrawFailedException(String message) {
        super(message);
    }

    public WithdrawFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawFailedException(Throwable cause) {
        super(cause);
    }
}
