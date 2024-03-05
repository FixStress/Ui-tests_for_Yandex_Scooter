package ru.yandex.samokat.pages.orderpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Страница "Про аренду"
public class AboutRentPage {
    private WebDriver driver;

    //Заголовок страницы "Про аренду"
    private static final By ABOUT_RENT = By.xpath(".//div[text()='Про аренду']");

    // Поле "Когда привезти самокат"
    private static final By WHEN_TO_BRING_THE_SCOOTER_FIELD = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");

    // Поле "Срок аренды"
    private static final By RENTAL_PERIOD_FIELD = By.xpath(".//div[text()='* Срок аренды']");

    // Поле "Комментарий для курьера"
    private static final By COMMENT_FOR_THE_COURIER = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка <<Заказать>>
    private static final By BUTTON_ORDER = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // кнопка <<Да>> во всплывающем окне "Хотите оформить заказ?"
    private static final By BUTTON_YES = By.xpath(".//button[text()='Да']");

    // Модальное окно "Заказ оформлен"
    private static final By ORDER_IS_PROCESSED = By.xpath(".//div[text()='Заказ оформлен']");

    // Конструктор
    public AboutRentPage(WebDriver driver) {

        this.driver = driver;
    }

    // Метод для ожидания загрузки страницы "Про аренду"
    public AboutRentPage aboutRentWait() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(ABOUT_RENT));
        return this;
    }

    //Метод для выбора в выпадающем календаре сегодняшнего дня + необходимое нам число
    public AboutRentPage selectionData(int day) {
        // Определение текущей даты
        LocalDate currentDate = LocalDate.now();
        // Плюс нужное количество дней
        LocalDate nextDay = currentDate.plusDays(day);
        // Отбрасываем лишний ноль, если число однозначное
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
        String formattedDay = String.format("%d", nextDay.getDayOfMonth());

        driver.findElement(WHEN_TO_BRING_THE_SCOOTER_FIELD).click();
        driver.findElement(By.xpath(".//div[text()='" + formattedDay + "']")).click();
        return this;
    }

    // Метод для выбора срока аренды в выпадающем списке
    public AboutRentPage selectionOfRentalPeriod(String period) {
        driver.findElement(RENTAL_PERIOD_FIELD).click();
        driver.findElement(By.xpath(".//div[text()='" + period + "']")).click();
        return this;
    }

    // Метод для выбора цвета самоката
    public AboutRentPage selectionColorScooter(String color) {
        driver.findElement(By.xpath(".//label[@for='" + color + "']")).click();
        return this;
    }

    // Метод для заполнения комментария
    public AboutRentPage fillOutAComment(String comment) {
        driver.findElement(COMMENT_FOR_THE_COURIER).sendKeys(comment);
        return this;
    }

    // Метод для нажатия кнопки <<Заказать>>
    public AboutRentPage clickButtonOrder() {
        driver.findElement(BUTTON_ORDER).click();
        return this;
    }

    // Метод с явным ожиданием для нажатия кнопки <<Да>> во всплывающем окне "Хотите оформить заказ?"
    public AboutRentPage clickButtonYes(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_YES));
        driver.findElement(BUTTON_YES).click();
        return this;
    }

    // Метод который ищет модальное окно "Заказ оформлен"
    public boolean orderIsProcessed() {
        return driver.findElement(ORDER_IS_PROCESSED).isDisplayed();
    }

    // Общий метод для заполнения всех полей на экране "Про аренду" и нажатия кнопки <<Заказать>>
    public AboutRentPage fillInAllInputFields(int day, String period, String color, String comment) {
        selectionData(day);
        selectionOfRentalPeriod(period);
        selectionColorScooter(color);
        fillOutAComment(comment);
        clickButtonOrder();
        return this;

    }
}
