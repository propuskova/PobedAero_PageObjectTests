package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InfoMenuPage {
    private final WebDriver driver;

    public InfoMenuPage(WebDriver driver) {
        this.driver = driver;
    }

//    public void checkPopupItems() {
//        driver.findElement(By.xpath("//a[text()='Подготовка к полёту']")).isDisplayed();
//        driver.findElement(By.xpath("//a[text()='Полезная информация']")).isDisplayed();
//        driver.findElement(By.xpath("//a[text()='О компании']")).isDisplayed();
//    }

    public boolean isTextVisible (String text) {
        return driver.findElement(By.xpath("//a[text()='" + text + "']")).isDisplayed();
    }
}
