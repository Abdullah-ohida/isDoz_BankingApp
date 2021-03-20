import com.Account;
import com.exception.BankingApplicationException;
import com.exception.DepositFailedException;
import com.model.AccountType;
import com.model.Bank;
import com.model.CentralBank;
import com.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BankAppTest {
    CentralBank centralBankOfNigeria;
    Bank gtBank;
    Customer chibuzor;

    @BeforeEach
    public void setUp(){
        centralBankOfNigeria = new CentralBank();
        gtBank = centralBankOfNigeria.registerNewBank("Guarantee Trust Bank PLC", "Trust Bank PLC");
        Customer customer = new Customer("Kelvin", "frank", "Plateau");

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
    void customer_maintainsOneBvn_acrossMultipleBanks() throws DepositFailedException {
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

        gtBank.withDrawMoneyFrom(chibuzor.getMyAccount().get(0).getAccountNumber(), BigInteger.valueOf(3000), 1234);
    }
}
