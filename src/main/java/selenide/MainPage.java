package selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement logo = $("a img");
    private final SelenideElement infoMenu = $("a[href='/information']");
    private final SelenideElement manageBookingLink = $x("//a[contains(text(),'Управление бронированием')]");

    @Step("Открыть главную страницу сайта Победа")
    public void openPage() {
        open("https://www.pobeda.aero/");
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        return title();
    }

    @Step("Проверить отображение логотипа страницы")
    public void isLogoVisible() {
        logo.shouldBe(visible);
    }

    @Step("Получить элемент меню Информация")
    public SelenideElement getInfoMenuElement() {
        return infoMenu;
    }

    @Step("Кликнуть на элемент Управление бронированием ")
    public void clickManageBooking() {
        manageBookingLink.shouldBe(visible).click();
    }
}
