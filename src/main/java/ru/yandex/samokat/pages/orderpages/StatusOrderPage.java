package ru.yandex.samokat.pages.orderpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusOrderPage {
    private WebDriver driver;
    // Локатор для картинки "Такого заказа нет. Точно верный номер?"
    private static final By NOT_FOUND_ORDER_IMAGE = By.cssSelector("div.Track_NotFound__6oaoY > img");

    // Конструктор
    public StatusOrderPage(WebDriver driver) {

        this.driver = driver;
    }
    public boolean notFoundImage(){
        WebElement notFoundElement = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(NOT_FOUND_ORDER_IMAGE));
        return notFoundElement.isDisplayed();
    }




}


