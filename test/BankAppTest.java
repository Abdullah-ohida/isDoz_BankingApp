import com.Account;
import com.exception.BankingApplicationException;
import com.exception.DepositFailedException;
import com.exception.WithdrawFailedException;
import com.model.*;
import com.notification.Alert;
import com.notification.NotificationService;
import com.notification.SmsNotification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankAppTest {
    CentralBank centralBankOfNigeria;
    Bank gtBank;
    Bank firstBank;

    @BeforeEach
    public void setUp(){
        centralBankOfNigeria = new CentralBank();
        gtBank = centralBankOfNigeria.registerNewBank("Guarantee Trust Bank PLC", "Trust Bank PLC");

        firstBank = centralBankOfNigeria.registerNewBank("First Bank PLC", "FBN");
    }

    @AfterEach
    public void tearDown(){
        centralBankOfNigeria = null;
    }

    @Test
    void bank_canBeCreated(){
        Bank gtBank = new Bank("Guarantee Trust Bank PLC", "gtb", "056");
        assertNotNull(gtBank);
        String expectedBankDetails = """
                Bank Name: Guarantee Trust Bank PLC
                Bank Short Name: gtb
                Bank Code: 056
                """;
        assertEquals(gtBank.toString(), expectedBankDetails);
    }

    @Test
    void centralBank_canBeCreated(){
        assertNotNull(centralBankOfNigeria);
        CentralBank centralBankOfGhana = CentralBank.createCentralBank();
        CentralBank centralBankOfTogo =  CentralBank.createCentralBank();;
        assertEquals(centralBankOfGhana, centralBankOfTogo);
    }

    @Test
    void centralBank_canFindNameByBankCode() throws BankingApplicationException {
        assertEquals(centralBankOfNigeria.findBankByBankCode(gtBank.getBankCode()), gtBank);
    }

    @Test
    void centralBank_canThrowException_whenBankIsNotFound(){
        assertThrows(BankingApplicationException.class, ()-> centralBankOfNigeria.findBankByBankCode("08455"));
    }

    @Test
    void onlyCentralBank_canCreateBank(){
        assertTrue(centralBankOfNigeria.validate(gtBank));
        System.out.println(gtBank);
        Bank firstBank = new Bank("First Bank PLC", "FBN", "090999");
        assertFalse(centralBankOfNigeria.validate(firstBank));
    }

    @Test
    void banks_getUniqueSixDigitBankCode_afterCreation(){
        assertTrue(centralBankOfNigeria.validate(gtBank));
        assertNotNull(gtBank.getBankCode());
        assertEquals(gtBank.getBankCode().length(), 6);
    }

    @Test
    void banks_closeCustomerAccount() throws BankingApplicationException {
        Customer customer = new Customer("Abdul", "Ismail", "Semicolon");
        gtBank.register(customer, AccountType.SAVINGS);
        String customerAccountNumber = customer.getMyAccount().get(0).getAccountNumber();
        Account customerAccount = customer.getMyAccount().get(0);
        gtBank.closeAccountFor(customer, customerAccountNumber);
        assertFalse(gtBank.getRegisteredCustomers().contains(customerAccount));
    }

    @Test
    void banks_canRegisterCustomers(){
        Customer customer = new Customer("Whalewalker","Adex", "shomolu");
        gtBank.register(customer, AccountType.CURRENT);
        assertTrue(gtBank.getRegisteredCustomers().contains(customer.getMyAccount().get(0)));
    }

    @Test
    void customer_canOnlyCloseHisOwnAccount(){
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);

        Customer rudy = new Customer("Rudy", "John", "Mako");
        gtBank.register(rudy, AccountType.CURRENT);

        String kelvinAccountNumber = kelvin.getMyAccount().get(0).getAccountNumber();
        assertThrows(BankingApplicationException.class, ()-> gtBank.closeAccountFor(rudy, kelvinAccountNumber));
    }

    @Test
    void customers_getBvnWhenHeRegistersForFirstAccount(){
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        assertNull(kelvin.getBvn());
        gtBank.register(kelvin, AccountType.CURRENT);
        assertNotNull(kelvin.getBvn());
        assertEquals(kelvin.getBvn().length(), 10);
    }

    @Test
    void cbn_canVerifyCreatedBVN(){
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        assertNull(kelvin.getBvn());
        gtBank.register(kelvin, AccountType.CURRENT);

        assertTrue(centralBankOfNigeria.validate(kelvin.getBvn()));
        assertFalse(centralBankOfNigeria.validate("9998443743"));
    }

    @Test
    void customer_maintainsOneBvn_acrossMultipleBanks() throws DepositFailedException, WithdrawFailedException {
        Customer chibuzor = new Customer("Chibuzo", "Gabriel", "Semicolon Village");
        gtBank.register(chibuzor, AccountType.SAVINGS);

        chibuzor.getMyAccount().get(0).updatePin(0, 1234);
        String chibuzorBvn = chibuzor.getBvn();

        Bank firsBank = centralBankOfNigeria.registerNewBank("First Bank PLC", "FBN");
        firsBank.register(chibuzor, AccountType.CURRENT);
        String chibuzohBvnAfterSecondAccount = chibuzor.getBvn();

        assertEquals(chibuzorBvn, chibuzohBvnAfterSecondAccount);

        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(4000), chibuzor.getMyAccount().get(0).getAccountNumber());
        System.out.println(chibuzor);

        gtBank.withDrawMoneyFrom(chibuzor.getMyAccount().get(0).getAccountNumber(), BigDecimal.valueOf(3000), 1234);
    }

    @Test
    void customer_getsBankSpecificAccountNumber_whenHeCreatedAnAccount(){
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);

        String customerGtBankAccountNumber = kelvin.getMyAccount().get(0).getAccountNumber();
        firstBank.register(kelvin, AccountType.KIDDES);
        String customerFirstBankAccountNumber = kelvin.getMyAccount().get(1).getAccountNumber();

        assertEquals(customerGtBankAccountNumber.length(), 10);
        assertNotEquals(customerFirstBankAccountNumber, customerGtBankAccountNumber);
        System.out.println(kelvin);
    }

    @Test
    void customer_canDepositMoneyFromBank_WithTheirAccountNumber() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);

        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);
        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(1000));
    }

    @Test
    void customer_canWithdrawMoney_inTheirAccount() throws DepositFailedException, WithdrawFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();

        kelvinAccount.updatePin(0, 1234);

        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);
        gtBank.withDrawMoneyFrom(kelvinAccountNumber, BigDecimal.valueOf(500), 1234);

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(500));

    }


    @Test
    void customer_cantWithdrawMoney_ifPinIsNotSet() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);

        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();

        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);
        assertThrows(WithdrawFailedException.class, ()->gtBank.withDrawMoneyFrom(kelvinAccountNumber, BigDecimal.valueOf(400), 1111));

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(1000));
        System.out.println(kelvinAccount);
    }

    @Test
    void customer_cantWithdrawMoney_ifPinIsWrong() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();

        kelvinAccount.updatePin(0, 1234);

        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);
        assertThrows(WithdrawFailedException.class, ()-> gtBank.withDrawMoneyFrom(kelvinAccountNumber, BigDecimal.valueOf(599), 1111));

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(1000));
    }


    @Test
    void customer_cantWithdraw_ifAccountNumberIsWrong() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();

        kelvinAccount.updatePin(0, 1234);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);

        assertThrows(WithdrawFailedException.class, ()-> gtBank.withDrawMoneyFrom("234567891", BigDecimal.valueOf(500), 1234));
    }

    @Test
    void customer_cantWithdrawMoney_ifAccountIsLow() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();

        kelvinAccount.updatePin(0, 1234);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(1000), kelvinAccountNumber);

        assertThrows(WithdrawFailedException.class, ()-> gtBank.withDrawMoneyFrom(kelvinAccountNumber, BigDecimal.valueOf(1500), 1234));
    }

    @Test
    void transferRequest_canBeCreated(){
        TransferRequest transferRequest = new TransferRequest(BigDecimal.valueOf(100), "1298383747", "1255332364", 1111);
        assertEquals(transferRequest.getAmountToTransfer(), BigDecimal.valueOf(100));
        assertEquals(transferRequest.getRecipientAccountNumber(), "1255332364");
        assertEquals(transferRequest.getSenderAccountNumber(), "1298383747");
        assertEquals(transferRequest.getSenderAccountPin(), 1111);
    }

    @Test
    void customers_canDoIntraBankTransfer() throws DepositFailedException, WithdrawFailedException {
        Customer chibuzo = new Customer("Chibuzo", "Gabriel", "semicolon");
        gtBank.register(chibuzo, AccountType.SAVINGS);
        Account chibuzoAccount = chibuzo.getMyAccount().get(0);
        String chibuzoAccountNumber = chibuzoAccount.getAccountNumber();
        chibuzoAccount.updatePin(0, 1234);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(3000), chibuzoAccountNumber);

        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();
        kelvinAccount.updatePin(0, 1111);

        gtBank.transfer(new TransferRequest(BigDecimal.valueOf(2000), chibuzoAccountNumber, kelvinAccountNumber, 1234));

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(2000));
        assertEquals(chibuzoAccount.calculateAccountBalance(), BigDecimal.valueOf(1000));

        gtBank.transfer(new TransferRequest(BigDecimal.valueOf(2000), kelvinAccountNumber, chibuzoAccountNumber, 1111));

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(0));
        assertEquals(chibuzoAccount.calculateAccountBalance(), BigDecimal.valueOf(3000));
    }

    @Test
    void customers_getAListOfTransactionOnSuccessfulTransaction() throws DepositFailedException, WithdrawFailedException {
        Customer chibuzo = new Customer("Chibuzo", "Gabriel", "semicolon");
        gtBank.register(chibuzo, AccountType.SAVINGS);
        Account chibuzoAccount = chibuzo.getMyAccount().get(0);
        String chibuzoAccountNumber = chibuzoAccount.getAccountNumber();
        chibuzoAccount.updatePin(0, 1234);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(3000), chibuzoAccountNumber);

        assertEquals(chibuzoAccount.getTransactions().size(), 1);

        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(2000), chibuzoAccountNumber);

        assertEquals(chibuzoAccount.getTransactions().size(),2);


        gtBank.withDrawMoneyFrom(chibuzoAccountNumber, BigDecimal.valueOf(1200), 1234);

        assertEquals(chibuzoAccount.getTransactions().size(), 3);

    }

    @Test
    void customers_canTransferFunds_viaCbn() throws BankingApplicationException {
        Customer chibuzo = new Customer("Chibuzo", "Gabriel", "semicolon");
        gtBank.register(chibuzo, AccountType.SAVINGS);
        Account chibuzoAccount = chibuzo.getMyAccount().get(0);
        String chibuzoAccountNumber = chibuzoAccount.getAccountNumber();
        chibuzoAccount.updatePin(0, 1234);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(2500), chibuzoAccountNumber);

        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();
        kelvinAccount.updatePin(0, 1111);

        gtBank.transfer(new TransferRequest(BigDecimal.valueOf(1000), chibuzoAccountNumber, kelvinAccountNumber, 1234), "fbn");

        assertEquals(kelvinAccount.calculateAccountBalance(), BigDecimal.valueOf(1000));
        assertEquals(chibuzoAccount.calculateAccountBalance(), BigDecimal.valueOf(1500));
    }

    @Test
    void notificationService_canCreateAlert_fromCompletedTransaction() throws DepositFailedException {
        Customer kelvin = new Customer("Kelvin", "frank", "Plateau");
        gtBank.register(kelvin, AccountType.SAVINGS);
        Account kelvinAccount = kelvin.getMyAccount().get(0);
        String kelvinAccountNumber = kelvinAccount.getAccountNumber();
        kelvinAccount.updatePin(0, 1111);
        gtBank.depositMoneyIntoAccount(BigDecimal.valueOf(25000), kelvinAccountNumber);

        Transaction transaction = kelvinAccount.getTransactions().get(0);
        NotificationService notifier = new SmsNotification();
        Alert alert = notifier.createAlert(kelvinAccount, transaction);
        assertNotNull(alert);
        System.out.println(alert);
    }

}
