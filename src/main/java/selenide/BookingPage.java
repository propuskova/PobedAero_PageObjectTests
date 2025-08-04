package selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BookingPage {

    private final String errorMessageText = "Заказ с указанными параметрами не найден";

    private final SelenideElement bookingInput = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement nameInput = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement errorMessage = $x("//*[contains(text(),'" + errorMessageText + "')]");

    @Step("Проверить отображение полей Номера брони, Фамилии и кнопки")
    public void checkFieldsVisible() {
        bookingInput.shouldBe(visible);
        nameInput.shouldBe(visible);
        submitButton.shouldBe(visible);
    }

    @Step("Установить критерии поиска, заполнив поля Номер брони и Фамилия клиента")
    public void searchBooking(String number, String name) {
        bookingInput.setValue(number);
        nameInput.setValue(name);
        submitButton.click();
    }

    @Step("Проверка сообщения об ошибке")
    public void checkErrorMessageVisible() {
        errorMessage.shouldBe(visible);
    }
}
