package com.model;

import com.database.CentralDBImp;
import com.exception.BankingApplicationException;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.model.TransactionType.*;

public class CentralBank {
    private final CentralDBImp<Bank> registeredBanks;
    private final Map<String, Customer> bvnDatabase;

    public CentralBank() {
        this.registeredBanks = new CentralDBImp<>();
        this.bvnDatabase = new HashMap<>();
    }

    public Bank registerNewBank(String bankFullName, String bankShortName) {
        String uniqueBankNumber = generateUniqueBankNumber();
        Bank newBank = new Bank(bankFullName, bankShortName, uniqueBankNumber);
        saveNewlyCreatedBankToDatabase(newBank);
        return newBank;
    }


    private void saveNewlyCreatedBankToDatabase(Bank newBank){
        registeredBanks.save(newBank);
    }

    private String generateUniqueBankNumber(){
        SecureRandom secureRandom = new SecureRandom();
        String uniqueBankNumber = String.format("%06d", secureRandom.nextInt(999999));
        return uniqueBankNumber;
    }

    public Bank findBankByBankCode(String bankCode) throws BankingApplicationException {
        Optional<Bank> optionalBank = registeredBanks.findById(bankCode);
        if(optionalBank.isPresent()) return optionalBank.get();
            else throw new BankingApplicationException("Bank does not exist");
    }

    public boolean validate(Bank bankToValidate) {
        return registeredBanks.contains(bankToValidate);
    }

    public boolean validate(String customerBvn){
        return bvnDatabase.containsKey(customerBvn);
    }

    public void registerCreateBvnFor(Customer customer) {
        customer.setBvn(generateBvn());
        bvnDatabase.put(customer.getBvn(), customer);
    }

    private String generateBvn() {
        StringBuilder generatedBvn = new StringBuilder();
        SecureRandom randomNumberGenerator = new SecureRandom();
        final int LENGTH_OF_BVN = 10;
        int totalNumberGenerated = 0;
        while (totalNumberGenerated < LENGTH_OF_BVN){
            int randomNumber = randomNumberGenerator.nextInt(9);
            generatedBvn.append(randomNumber);
            totalNumberGenerated++;
        }
        return generatedBvn.toString();
    }

    public static CentralBank createCentralBank() {
        return CentralBankSingleTonHelper.instance;
    }

    private static class CentralBankSingleTonHelper{
        private static final CentralBank instance = new CentralBank();
    }

    public void transferFundsWith(TransferRequest transferRequest) throws BankingApplicationException {
        Optional<Bank> recipientBank = Optional.ofNullable(findBankByBankCode(transferRequest.getRecipientAccountNumber()));
        boolean recipientBankExist = recipientBank.isPresent();
        if (recipientBankExist){
            recipientBank.get().depositMoneyIntoAccount(transferRequest.getAmountToTransfer(), transferRequest.getRecipientAccountNumber(), TRANSFER_IN);
            transferRequest.getSenderBank().withDrawMoneyFrom(transferRequest.getSenderAccountNumber(), transferRequest.getAmountToTransfer(), transferRequest.getSenderAccountPin(), TRANSFER_OUT);
        }
    }
}
