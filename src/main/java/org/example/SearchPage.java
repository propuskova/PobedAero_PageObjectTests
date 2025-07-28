package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class SearchPage {
    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //скролл к блоку поиска
    public void scrollToSearchBlock() {
        WebElement from = driver.findElement(By.xpath("//input[@placeholder='Откуда']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", from);
    }

    public boolean isFieldsVisible() {
        return driver.findElement(By.xpath("//input[@placeholder='Откуда']")).isDisplayed() &&
        driver.findElement(By.xpath("//input[@placeholder='Куда']")).isDisplayed() &&
        driver.findElement(By.xpath("//input[@placeholder='Туда']")).isDisplayed() &&
        driver.findElement(By.xpath("//input[@placeholder='Обратно']")).isDisplayed();
    }

    public void setSearchCriteria(String from, String to) {
        WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='Откуда']"));
        WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='Куда']"));

        fromInput.sendKeys(from);
        fromInput.sendKeys(Keys.ENTER);
        toInput.sendKeys(to);
        toInput.sendKeys(Keys.ENTER);
    }

    public void clickSearch() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public boolean checkInvalidField() {
        WebElement input = driver.findElement(By.xpath("//input[@placeholder='Туда']"));
        String classes = input.getAttribute("class");
        return classes.contains("input_invalid");
    }
}
