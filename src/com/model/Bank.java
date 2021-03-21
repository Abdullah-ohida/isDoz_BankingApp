package com.model;
import com.Account;
import com.database.CentralDB;
import com.database.CentralDBImp;
import com.database.Storable;
import com.exception.BankingApplicationException;
import com.exception.DepositFailedException;
import com.exception.WithdrawFailedException;
import com.notification.NotificationService;
import com.notification.SmsNotification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.model.TransactionType.*;

public class Bank implements Storable {
    private final String bankFullName;
    private final String bankShortName;
    private final String bankCode;
    private final CentralDB<Account> accountCentralDB;
    private final NotificationService notificationService = new SmsNotification();

    public Bank(String bankFullName, String bankShortName, String bankCode) {
        this.bankFullName = bankFullName;
        this.bankShortName = bankShortName.toLowerCase();
        this.bankCode = bankCode;
        accountCentralDB = new CentralDBImp<>();
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void register(Customer customer, AccountType accountType){
        if(customer.getBvn() == null){
            CentralBank.createCentralBank().registerCreateBvnFor(customer);
        }
        String accountNumber = generateAccountNumberForCustomer();
        Account account = new Account(customer, accountNumber, this.bankFullName, accountType);
        customer.addAccount(account);
        addAccount(account);
    }

    private String generateAccountNumberForCustomer() {
        String accountNumber = bankCode + String.format("%03d", accountCentralDB.size() + 1);
        accountNumber = accountNumber + checkDigit(bankCode, accountNumber);
        return accountNumber;
    }

    private String checkDigit(String bankCode, String accountNumber) {
        String numberToVerify = bankCode + accountNumber;
        int[] checkDigitMultiplier = {3,7,3,3,7,3,3,7,3,3,7,3,3,7,3};
        int result = 0;

        for(int count = 0; count < checkDigitMultiplier.length; count++){
            result += checkDigitMultiplier[count] * numberToVerify.charAt(count);
        }

        int modulo = result % 10;
        int subtract = Math.abs(modulo - 10);
        if (subtract == 10){
            return "0";
        }
        return String.format("%d", subtract);
    }

    private void addAccount(Account account){
        accountCentralDB.save(account);
    }

    @Override
    public String getId() {
        return bankCode;
    }

    @Override
    public String getAccountNumber() {
        return getAccountNumber();
    }

    @Override
    public String getName() {
        return bankShortName;
    }

    public void closeAccountFor(Customer customer, String customerAccountNumber) throws BankingApplicationException {
        Optional<Account> account = accountCentralDB.findByAccountNumber(customerAccountNumber);
        if (account.isPresent()){
            if (!account.get().getAccountName().equalsIgnoreCase(customer.getFirstName() + " " + customer.getLastName())){
                throw new BankingApplicationException("Account number does not belong to customer");
            }
            accountCentralDB.delete(account);
            customer.getMyAccount().remove(account.get());
        }
    }

    public List<Account> getRegisteredCustomers() {
        return accountCentralDB.findAll();
    }


    @Override
    public String toString() {
        StringBuilder bankToString = new StringBuilder();
        bankToString.append("Bank Name: ").append(bankFullName).append("\n");
        bankToString.append("Bank Short Name: ").append(bankShortName).append("\n");
        bankToString.append("Bank Code: ").append(bankCode).append("\n");

        return bankToString.toString();
    }

    public void depositMoneyIntoAccount(BigDecimal amountToDeposit, String customerAccountNumber, TransactionType transactionType) throws DepositFailedException {
        Optional<Account> optionalAccount = accountCentralDB.findByAccountNumber(customerAccountNumber);
        if(optionalAccount.isPresent()){
            notifyCustomerViaSms(savesTransaction(optionalAccount.get(), transactionType, amountToDeposit), optionalAccount.get());
        }else
            throw new DepositFailedException("Account not fount");
    }

    public void notifyCustomerViaSms(Transaction transaction, Account customerAccount){
        notificationService.notify(notificationService.createAlert(customerAccount, transaction));
    }

    private Transaction savesTransaction(Account optionalAccount, TransactionType transactionType, BigDecimal transactionAmount){
        Transaction transaction = new Transaction(generateTransactionId(transactionType, optionalAccount.getAccountNumber()), transactionType, transactionAmount, optionalAccount.getAccountName());
        optionalAccount.getTransactions().add(transaction);
        return transaction;
    }

    private String generateTransactionId(TransactionType transactionType, String accountNumber){
        String transactionPrefix = switch (transactionType){
            case DEBIT -> "dbt";
            case CREDIT -> "crd";
            case TRANSFER_IN -> "tnxIn";
            case TRANSFER_OUT -> "tnaOut";
        };

        return accountNumber + transactionPrefix + LocalDateTime.now();
    }

    public void withDrawMoneyFrom(String accountNumber, BigDecimal amountToWithdraw, int accountPin, TransactionType transactionType) throws WithdrawFailedException {
        Optional<Account> optionalAccount = accountCentralDB.findByAccountNumber(accountNumber);
        if(optionalAccount.isPresent()){
            optionalAccount.get().verifyLegibilityForWithdraw(amountToWithdraw, accountPin);
        }else throw new WithdrawFailedException("Account not found");

        notifyCustomerViaSms((savesTransaction(optionalAccount.get(), transactionType, amountToWithdraw)), optionalAccount.get());
    }

    public void withDrawMoneyFrom(String accountNumber, BigDecimal amountToWithdraw, int accountPin) throws WithdrawFailedException {
        withDrawMoneyFrom(accountNumber,amountToWithdraw,accountPin, DEBIT);
    }

    public void depositMoneyIntoAccount(BigDecimal amountToDeposit, String accountNumber) throws DepositFailedException {
        depositMoneyIntoAccount(amountToDeposit, accountNumber, CREDIT);
    }

    public void transfer(TransferRequest transferRequest) throws WithdrawFailedException, DepositFailedException {
        Optional<Account> senderAccount = accountCentralDB.findByAccountNumber(transferRequest.getSenderAccountNumber());
        Optional<Account> receiverAccount = accountCentralDB.findByAccountNumber(transferRequest.getRecipientAccountNumber());
        if(senderAccount.isPresent() && receiverAccount.isPresent()){
            withDrawMoneyFrom(transferRequest.getSenderAccountNumber(), transferRequest.getAmountToTransfer(), transferRequest.getSenderAccountPin(), TRANSFER_OUT);
            depositMoneyIntoAccount(transferRequest.getAmountToTransfer(), transferRequest.getRecipientAccountNumber(), TRANSFER_IN);
        }
    }

    public void transfer(TransferRequest transferRequest, String receivingBankShortName) throws BankingApplicationException {
        CentralBank centralBank = CentralBank.createCentralBank();
        transferRequest.setRecipientBank(receivingBankShortName);
        transferRequest.setSenderBank(this);
        centralBank.transferFundsWith(transferRequest);
    }

}
