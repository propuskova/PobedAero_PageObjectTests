package selenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class InfoMenuPage {

    public void isTextVisible(String text) {
        $x("//a[text()='" + text + "']").shouldBe(visible);
    }
}
