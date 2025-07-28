package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.pobeda.aero/");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoVisible() {
        return driver.findElement(By.xpath("//a/img")).isDisplayed();
    }

    //пункт меню информация
    public WebElement getInfoMenuElement() {
        return driver.findElement(By.cssSelector("a[href='/information']"));
    }

    public void clickManageBooking() {
        driver.findElement(By.xpath("//a[contains(text(),'Управление бронированием')]")).click();
    }
}

