package com.notification;

import com.Account;
import com.model.Transaction;

public class SmsNotification implements NotificationService{
    @Override
    public Alert createAlert(Account account, Transaction transaction) {
        return null;
    }

    @Override
    public void notify(Alert alert) {

    }
}
