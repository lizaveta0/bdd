package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CardsPage {

    private String cardInfo = "//div[contains(text(), '%s')]";
    private String debositButton = "//div[contains(text(), '%s')]/button";

    //Заполнение формы и авторизация
    public void checkCardBalance(String cardNumber, String balance) {
        $(By.xpath(String.format(cardInfo, cardNumber)))
                .shouldBe(
                        visible,
                        innerText(cardNumber),
                        innerText(balance)
                );
    }

    public void depositCard(String card) {
        $(By.xpath(String.format(debositButton, card))).click();
    }
}
