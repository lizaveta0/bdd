package page;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String getCardBalance(String card) {

        Pattern pattern = Pattern.compile("баланс:\\s*(\\d+)\\s*р"); // Регулярное выражение для поиска последовательности цифр
        Matcher matcher = pattern.matcher($(By.xpath(String.format(cardInfo, card))).text());

        if (matcher.find()) { // Найдено первое совпадение
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Не удалось распознать баланс в строке");
    }
}
