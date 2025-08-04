package selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.openqa.selenium.Keys;

public class SearchPage {

    private final SelenideElement fromInput = $x("//input[@placeholder='Откуда']");
    private final SelenideElement toInput = $x("//input[@placeholder='Куда']");
    private final SelenideElement dateTo = $x("//input[@placeholder='Туда']");
    private final SelenideElement dateBack = $x("//input[@placeholder='Обратно']");
    private final SelenideElement searchButton = $("button[type='submit']");

    public void scrollToSearchBlock() {
        fromInput.scrollIntoView(true);
    }

    public void checkFieldsVisible() {
        fromInput.shouldBe(visible);
        toInput.shouldBe(visible);
        dateTo.shouldBe(visible);
        dateBack.shouldBe(visible);
    }

    public void setSearchCriteria(String from, String to) {
        fromInput.setValue(from).sendKeys(Keys.ENTER);
        toInput.setValue(to).sendKeys(Keys.ENTER);
    }

    public void clickSearch() {
        searchButton.shouldBe(visible).click();
    }

    public void checkInvalidField() {
        dateTo.shouldHave(cssClass("input_invalid"));
    }
}
