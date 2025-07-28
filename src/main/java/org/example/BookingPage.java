package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingPage {
    private final WebDriver driver;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkFieldsVisible() {
        return driver.findElement(By.xpath("//input[@placeholder='Номер бронирования или билета']")).isDisplayed() &&
        driver.findElement(By.xpath("//input[@placeholder='Фамилия клиента']")).isDisplayed() &&
        driver.findElement(By.cssSelector("button[type='submit']")).isDisplayed();
    }

    public void searchBooking(String number, String name) {
        driver.findElement(By.xpath("//input[@placeholder='Номер бронирования или билета']")).sendKeys(number);
        driver.findElement(By.xpath("//input[@placeholder='Фамилия клиента']")).sendKeys(name);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean isErrorMessage() {
        return driver.findElement(By.xpath("//*[contains(text(),'Заказ с указанными параметрами не найден')]")).isDisplayed();
    }
}
