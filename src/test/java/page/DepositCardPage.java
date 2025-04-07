package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DepositCardPage {
    private SelenideElement inputAmount = $("[data-test-id='amount'] input");
    private SelenideElement inputFrom = $("[data-test-id='from'] input");
    private SelenideElement inputTo = $("[data-test-id='to'] input");

    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private SelenideElement submitButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");
    //Заполнение формы
    public void fillForm(String amount, String from) {
        inputAmount.setValue(amount);
        inputFrom.setValue(from);
    }

    public void submitTransfer() {
        submitButton.click();
    }

    public void cancelTransfer() {
        cancelButton.click();
    }

    public void checkCardNumberTo(String card) {
        inputTo.shouldBe(visible, value(card));
    }

    public void checkErrorMessageForAmount(String error) {
        errorNotification.shouldBe(visible, text(error));
    }
}
