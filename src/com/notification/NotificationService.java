package com.notification;

import com.Account;
import com.model.Transaction;

public interface NotificationService {
    Alert createAlert(Account account, Transaction transaction);

    void notify(Alert alert);
}
