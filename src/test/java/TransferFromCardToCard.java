import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CardsPage;
import page.DepositCardPage;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;

public class TransferFromCardToCard {
    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private CardsPage cardsPage;
    private DepositCardPage depositCardPage;
    private DataHelper dataHelper = new DataHelper();

    CardInfo firstCardInfo = dataHelper.getCardInfo("0001");
    CardInfo secondCardInfo = dataHelper.getCardInfo("0002");

    private String firstCardBalance;
    private String firstCardNumber = firstCardInfo.getCardNumber();
    private String firstCardMaskedNumber = firstCardInfo.getMaskedCardNumber();
    private String secondCardBalance;
    private String secondCardNumber = secondCardInfo.getCardNumber();
    private String secondCardMaskedNumber = secondCardInfo.getMaskedCardNumber();
    private String transferAmount;


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
        loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = getAuthInfo();
        DataHelper.VerificationCode verificationCode = getVeriffCodeFor(authInfo);
        loginPage.fillLoginForm(authInfo.getLogin(), authInfo.getPassword());

        verificationPage = new VerificationPage();
        verificationPage.fillVerificationForm(verificationCode.getCode());
    }

    @Test
    public void testTransferPositive() {
        cardsPage = new CardsPage();

        firstCardBalance = cardsPage.getCardBalance(firstCardMaskedNumber);
        secondCardBalance = cardsPage.getCardBalance(secondCardMaskedNumber);

        transferAmount = generateRandomAmount(secondCardBalance);

        cardsPage.checkCardBalance(firstCardMaskedNumber, firstCardBalance);
        cardsPage.checkCardBalance(secondCardMaskedNumber, secondCardBalance);

        cardsPage.depositCard(firstCardMaskedNumber);
        depositCardPage = new DepositCardPage();
        depositCardPage.fillForm(transferAmount, secondCardNumber);
        depositCardPage.checkCardNumberTo(firstCardMaskedNumber);
        depositCardPage.submitTransfer();

        firstCardBalance = String.valueOf((Integer.parseInt(firstCardBalance) + Integer.parseInt(transferAmount)));
        secondCardBalance = String.valueOf((Integer.parseInt(secondCardBalance) - Integer.parseInt(transferAmount)));
        cardsPage.checkCardBalance(firstCardMaskedNumber, firstCardBalance);
        cardsPage.checkCardBalance(secondCardMaskedNumber, secondCardBalance);
    }

    @Test
    public void testTransferCancelTransferPositive() {
        cardsPage = new CardsPage();
        firstCardBalance = cardsPage.getCardBalance(firstCardMaskedNumber);
        secondCardBalance = cardsPage.getCardBalance(secondCardMaskedNumber);

        transferAmount = generateRandomAmount(firstCardBalance);
        cardsPage.depositCard(secondCardMaskedNumber);
        depositCardPage = new DepositCardPage();
        depositCardPage.fillForm(transferAmount, firstCardNumber);
        depositCardPage.checkCardNumberTo(secondCardMaskedNumber);
        depositCardPage.cancelTransfer();

        cardsPage.checkCardBalance(firstCardMaskedNumber, firstCardBalance);
        cardsPage.checkCardBalance(secondCardMaskedNumber, secondCardBalance);
    }

    @Test
    //Тест с багом. Если сумма перевода превышает баланс по счету, должна возникать ошибка
    //https://github.com/lizaveta0/bdd/issues/1
    public void testTransferNegative() {
        transferAmount = "10000000";

        cardsPage = new CardsPage();
        cardsPage.depositCard(firstCardMaskedNumber);

        depositCardPage = new DepositCardPage();
        depositCardPage.fillForm(transferAmount, secondCardNumber);
        depositCardPage.checkErrorMessageForAmount("Сумма перевода больше чем остаток по счету");
    }
}
