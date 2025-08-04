package selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class SearchPage {

    private final SelenideElement fromInput = $x("//input[@placeholder='Откуда']");
    private final SelenideElement toInput = $x("//input[@placeholder='Куда']");
    private final SelenideElement dateTo = $x("//input[@placeholder='Туда']");
    private final SelenideElement dateBack = $x("//input[@placeholder='Обратно']");
    private final SelenideElement searchButton = $("button[type='submit']");

    @Step("Сделать скролл к блоку поиска")
    public void scrollToSearchBlock() {
        fromInput.scrollIntoView(true);
    }

    @Step("Проверить отображение полей Откуда, Куда, Туда, Обратно")
    public void checkFieldsVisible() {
        fromInput.shouldBe(visible);
        toInput.shouldBe(visible);
        dateTo.shouldBe(visible);
        dateBack.shouldBe(visible);
    }

    @Step("Установить критерии поиска, заполнив поля Откуда, Куда")
    public void setSearchCriteria(String from, String to) {
        fromInput.setValue(from).sendKeys(Keys.ENTER);
        toInput.setValue(to).sendKeys(Keys.ENTER);
    }

    @Step("Сделать клик для поиска")
    public void clickSearch() {
        searchButton.shouldBe(visible).click();
    }

    @Step("Проверить отображение невалидного ввода в поле Туда")
    public void checkInvalidField() {
        dateTo.shouldHave(cssClass("input_invalid"));
    }
}
