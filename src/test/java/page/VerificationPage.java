package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement inputCode = $("[data-test-id='code'] input");
    private SelenideElement submitButton = $("[data-test-id='action-verify']");

    //Заполнение формы и авторизация
    public void fillVerificationForm(String code) {
        inputCode.setValue(code);
        submitButton.click();
    }
}
