import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import selenide.*;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Epic("UI-тесты для сайта Победа")
//@Description("Набор тестов для проверки: главной страницы, блока поиска билетов, управления бронированием на сайте Победа")
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
    @Feature("Главная страница")
    @Description("Проверка отображения меню Информация и текста для пунктов меню")
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
    @Feature("Блок поиска")
    @Description("Проверка отображения полей и валидации формы поиска при отсутствии даты вылета")
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
    @Feature("Управление бронированием")
    @Description("Проверка отображения полей и сообщения об ошибке при вводе некорректных даттых бронирования")
    void testManageBookingError() {
        mainPage.getTitle().equals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        mainPage.isLogoVisible();

        mainPage.clickManageBooking();
        bookingPage.checkFieldsVisible();
        bookingPage.searchBooking("XXXXXX", "Qwerty");
        bookingPage.checkErrorMessageVisible();
    }
}
