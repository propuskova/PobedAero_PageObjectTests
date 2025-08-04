package selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BookingPage {

    private final String errorMessageText = "Заказ с указанными параметрами не найден";

    private final SelenideElement bookingInput = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement nameInput = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement errorMessage = $x("//*[contains(text(),'" + errorMessageText + "')]");

    public void checkFieldsVisible() {
        bookingInput.shouldBe(visible);
        nameInput.shouldBe(visible);
        submitButton.shouldBe(visible);
    }

    public void searchBooking(String number, String name) {
        bookingInput.setValue(number);
        nameInput.setValue(name);
        submitButton.click();
    }

    public void checkErrorMessageVisible() {
        errorMessage.shouldBe(visible);
    }
}
