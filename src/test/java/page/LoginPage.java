package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement inputLogin = $("[data-test-id='login'] input");
    private SelenideElement inputPassword = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");


    //Заполнение формы и авторизация
    public void fillLoginForm(String login, String password) {
        inputLogin.setValue(login);
        inputPassword.setValue(password);
        loginButton.click();
    }
}
