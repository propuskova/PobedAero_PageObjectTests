import selenide.*;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PobedaSelenideTests {

    MainPage mainPage;
    InfoMenuPage infoMenu;
    SearchPage searchPage;
    BookingPage bookingPage;

    @BeforeEach
    void setup() {
        open("https://www.pobeda.aero/");

        // Инициализация POM-классов
        mainPage = new MainPage();
        infoMenu = new InfoMenuPage();
        searchPage = new SearchPage();
        bookingPage = new BookingPage();
    }

    // Задание 1 — проверка выпадающего меню
    @Test
    void testInfoMenuPopup() {
        mainPage.getTitle().equals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        mainPage.isLogoVisible();
        actions().moveToElement(mainPage.getInfoMenuElement()).perform();

        infoMenu.isTextVisible("Подготовка к полету");
        infoMenu.isTextVisible("Полезная информация");
        infoMenu.isTextVisible("О компании");
    }

    // Задание 2 — проверка формы поиска рейса
    @Test
    void testFlightSearchValidation() {
        mainPage.getTitle().equals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        mainPage.isLogoVisible();

        searchPage.scrollToSearchBlock();
        searchPage.checkFieldsVisible();
        searchPage.setSearchCriteria("Москва", "Санкт-Петербург");
        searchPage.clickSearch();
        searchPage.checkInvalidField();
    }

    // Задание 3 — проверка ошибки при несуществующей брони
    @Test
    void testManageBookingError() {
        mainPage.getTitle().equals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        mainPage.isLogoVisible();

        mainPage.clickManageBooking();
        bookingPage.checkFieldsVisible();
        bookingPage.searchBooking("XXXXXX", "Qwerty");
        bookingPage.checkErrorMessageVisible();
    }
}
