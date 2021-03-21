package com.notification;

import com.Account;
import com.model.Transaction;

public class SmsNotification implements NotificationService{
    @Override
    public Alert createAlert(Account account, Transaction transaction) {
        Alert alert = new SmsAlert(account, transaction);
        return alert;
    }

    @Override
    public void notify(Alert alert) {
        System.out.println(alert);
    }
}
