package selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement logo = $("a img");
    private final SelenideElement infoMenu = $("a[href='/information']");
    private final SelenideElement manageBookingLink = $x("//a[contains(text(),'Управление бронированием')]");

    public void openPage() {
        open("https://www.pobeda.aero/");
    }

    public String getTitle() {
        return title();
    }

    public void isLogoVisible() {
        logo.shouldBe(visible);
    }

    public SelenideElement getInfoMenuElement() {
        return infoMenu;
    }

    public void clickManageBooking() {
        manageBookingLink.shouldBe(visible).click();
    }
}
