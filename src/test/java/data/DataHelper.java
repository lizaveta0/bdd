package data;

import lombok.Value;

import java.util.Map;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    public static String generateRandomAmount(String max) {
        Random random = new Random();
        return String.valueOf(random.nextInt(Integer.parseInt(max + 1)));
    }

    private static final Map<String, CardInfo> cards = Map.of(
            "0001", new CardInfo("5559 0000 0000 0001", "**** **** **** 0001"),
            "0002", new CardInfo("5559 0000 0000 0002", "**** **** **** 0002")
    );


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVeriffCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String maskedCardNumber;
    }

    public static CardInfo getCardInfo(String last4Numbers) {
        return cards.get(last4Numbers);
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}
