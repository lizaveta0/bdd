package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CardsPage {
    public static final String FIRST_CARD = "5559 0000 0000 0001";
    public static final String SECOND_CARD = "5559 0000 0000 0002";

    private SelenideElement card0001 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement debositButton0001 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");

    private SelenideElement card0002 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement debositButton0002 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    private SelenideElement submitButton = $("[data-test-id='action-verify']");

    //Заполнение формы и авторизация
    public void checkCardsBalance(String cardBalance1, String cardBalance2) {
        card0001.shouldBe(visible, innerText(cardBalance1));
        card0002.shouldBe(visible, innerText(cardBalance2));
    }

    public void depositCard(String card) {
        if (FIRST_CARD.equals(card)) {
            debositButton0001.click();
        } else {
            debositButton0002.click();
        }
    }

    public String getFirstCardInfo() {
        return card0001.shouldBe(visible).getText();
    }

    public void checkFirstCardInfo(String cardInfo) {
        card0001.shouldBe(visible, text(cardInfo));
    }

    public String getSecondCardInfo() {
        return card0002.shouldBe(visible).getText();
    }

    public void checkSecondCardInfo(String cardInfo) {
        card0002.shouldBe(visible, text(cardInfo));
    }
}
