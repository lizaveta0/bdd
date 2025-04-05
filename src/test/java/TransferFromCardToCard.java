import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CardsPage;
import page.DepositCardPage;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferFromCardToCard {
    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private CardsPage cardsPage;
    private DepositCardPage depositCardPage;

    private String firstCardBalance;
    private String firstCardNumber = "5559 0000 0000 0001";
    private String firstCardMaskedNumber = "**** **** **** 0001";
    private String secondCardBalance;
    private String secondCardNumber = "5559 0000 0000 0002";
    private String secondCardMaskedNumber = "**** **** **** 0002";
    private String transferAmount;


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
        loginPage = new LoginPage();
        verificationPage = new VerificationPage();
        cardsPage = new CardsPage();
        depositCardPage = new DepositCardPage();

        loginPage.fillLoginForm("vasya", "qwerty123");
        verificationPage.fillVerificationForm("12345");
    }

    @Test
    public void testTransferPositive() {
        firstCardBalance = "10000";
        secondCardBalance = "10000";
        transferAmount = "100";
        cardsPage.checkCardsBalance(firstCardBalance, secondCardBalance);
        cardsPage.depositCard(firstCardNumber);
        depositCardPage.fillForm(transferAmount, secondCardNumber);
        depositCardPage.checkCardNumberTo(firstCardMaskedNumber);
        depositCardPage.submitTransfer();

        firstCardBalance = "10100";
        secondCardNumber = "9900";
        cardsPage.checkCardsBalance(firstCardBalance, secondCardBalance);
    }

    @Test
    public void testTransferCancelTransferPositive() {
        firstCardBalance = "10100";
        secondCardBalance = "9900";
        transferAmount = "200";
        cardsPage.checkCardsBalance(firstCardBalance, secondCardBalance);
        cardsPage.depositCard(secondCardBalance);
        depositCardPage.fillForm(transferAmount, firstCardNumber);
        depositCardPage.checkCardNumberTo(secondCardMaskedNumber);
        depositCardPage.cancelTransfer();

        cardsPage.checkCardsBalance(firstCardBalance, secondCardBalance);
    }
}
