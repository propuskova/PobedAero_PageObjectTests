package selenide;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class InfoMenuPage {

    @Step("Проверить отображение текста в блоке информации")
    public void isTextVisible(String text) {
        $x("//a[text()='" + text + "']").shouldBe(visible);
    }
}
