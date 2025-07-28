import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.example.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class PobedaTests {

    WebDriver driver;
    MainPage mainPage;
    InfoMenuPage infoMenu;
    SearchPage searchBlock;
    BookingPage bookingPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //инициализация классов-страниц для РОМ
        mainPage = new MainPage(driver);
        infoMenu = new InfoMenuPage(driver);
        searchBlock = new SearchPage(driver);
        bookingPage = new BookingPage(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    //задание №1
    @Test
    void testInfoMenuPopup() {
        mainPage.open(); //1. Перейти на сайт pobeda.aero.
        //сравнить текст заголовка
        assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                mainPage.getTitle(), "Заголовок не соответствует");

        //сравнить отображение логотипа
        assertTrue(mainPage.isLogoVisible(), "Логотип не отображается");

        //action по наведению мыши на пункт Информация
        new Actions(driver).moveToElement(mainPage.getInfoMenuElement()).perform();

        // Убедиться, что появилось всплывающее окно, которое содержит следующие заголовки
        assertTrue(infoMenu.isTextVisible("Подготовка к полету"), "Пункт 'Подготовка к полету' не найден");
        assertTrue(infoMenu.isTextVisible("Полезная информация"), "Пункт 'Полезная информация' не найден");
        assertTrue(infoMenu.isTextVisible("О компании"), "Пункт 'О компании' не найден");
    }

    //задание №2
    @Test
    void testFlightSearchValidation() {
        mainPage.open();//1. Перейти на сайт pobeda.aero.
        //сравнить текст заголовка
        assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                mainPage.getTitle());

        //сравнить отображение логотипа
        assertTrue(mainPage.isLogoVisible(), "Логотип не отображается");

        //Проскроллить страницу к блоку поиска билета
        searchBlock.scrollToSearchBlock();

        //убедиться, что блок с поиском билета действительно отображается
        // (есть поле Откуда, Куда, Дата вылета Туда, Дата вылета Обратно)
        assertTrue(searchBlock.isFieldsVisible(), "Блок с полями поиска не отображается");

        //Выбрать (или ввести) следующие критерии поиска:
        //откуда – Москва (без выбора аэропорта) + нажать Enter
        //куда – Санкт-Петербург + нажать Enter.
        searchBlock.setSearchCriteria("Москва", "Санкт-Петербург");
        searchBlock.clickSearch();

        //6. Убедиться, что около поля «Туда» появилась красная обводка.
        assertTrue(searchBlock.checkInvalidField(), "Поле 'Туда' не подсвечено красным");
    }

    @Test
    void testManageBookingError() {
        mainPage.open();//1. Перейти на сайт pobeda.aero.
        //сравнить текст заголовка
        assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                mainPage.getTitle());
        //сравнить отображение логотипа
        assertTrue(mainPage.isLogoVisible(), "Логотип не отображается");

        //3. Проскроллить страницу чуть ниже и кликнуть на пункт «Управление бронированием».
        mainPage.clickManageBooking();

        //Убедиться, что открылась необходимая страница:
        //а) есть поле «Номер заказа или билета»;
        //б) есть поле «Фамилия клиента»;
        //в) есть кнопка «Поиск».
        assertTrue(bookingPage.checkFieldsVisible(), "Поля ввода для управления бронированием не видны");

        //5. Ввести в поля ввода данные:
        //номер заказа – XXXXXX, фамилия – Qwerty
        //и нажать кнопку «Поиск».
        bookingPage.searchBooking("XXXXXX", "Qwerty");

        //6. Убедиться, что в новой вкладке на экране отображается текст ошибки «Заказ с указанными параметрами не найден».
        assertTrue(bookingPage.isErrorMessage(), "Заказ с указанными параметрами не найден");
    }
}