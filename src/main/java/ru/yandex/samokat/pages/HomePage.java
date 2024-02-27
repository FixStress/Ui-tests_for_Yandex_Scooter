package ru.yandex.samokat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Главная страница
public class HomePage {
    private WebDriver driver;
    // Адрес страницы
    private static final String PAGE_URL = ("https://qa-scooter.praktikum-services.ru/");
    // Часть логотипа в хедере -> слово "Яндекс"
    private static final By WORD_YANDEX_IN_LOGO = By.xpath(".//img[@alt = 'Yandex']");
    // Часть логотипа в хедере -> слово "Самокат"
    private static final By WORD_SCOOTER_IN_LOGO = By.xpath(".//img[@alt = 'Scooter']");
    // Кнопка "Заказать" в хедере
    private static final By ORDER_BUTTON_HEADER = By.className("Button_Button__ra12g");
    // Кнопка "Заказать" в центре экрана
    private static final By ORDER_BUTTON_MIDDLE = By.xpath("/html/body/div/div/div/div[4]/div[2]/div[5]/button");
    // Кнопка "Статус заказа" в хедере
    private static final By STATUS_ORDER_BUTTON = By.xpath(".//button[text()='Статус заказа']");
    // Поле ввода "Введите номер заказа"
    private static final By ORDER_NUMBER_FIELD = By.xpath(".//input[@placeholder='Введите номер заказа']");
    // Кнопка <<Go!>>
    private static final By GO_BUTTON = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");


    // Конструктор
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    // Метод для открытия страницы сайта
    public HomePage open() {
        driver.get(PAGE_URL);
        return this;
    }

    // Метод кликающий на кнопку "Заказать" в хедере
    public HomePage clickOrderButtonHeader() {
        driver.findElement(ORDER_BUTTON_HEADER).click();
        return this;
    }

    // Метод скролящий и кликающий на кнопку "Заказать" в центре страницы
    public HomePage skrollAndClickOrderButtonMiddle() {
        WebElement element = driver.findElement(ORDER_BUTTON_MIDDLE);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return this;
    }

    // Метод кликающий на слово Яндекс в логотипе "Яндекс Самокат"
    public HomePage clickWordYandexInLogo() {
        driver.findElement(WORD_YANDEX_IN_LOGO).click();
        return this;
    }

    // Метод кликающий на слово Самокат в логотипе "Яндекс Самокат"
    public HomePage clickWordSamokatInLogo() {
        driver.findElement(WORD_SCOOTER_IN_LOGO).click();
        return this;
    }

    // Метод кликающий на кнопку <<Статус заказа>>
    public HomePage clickStatusOrderButton() {
        driver.findElement(STATUS_ORDER_BUTTON).click();
        return this;
    }

    // Метод заполняющий поле "Введите номер заказа"
    public HomePage fillOrderNumber(String orderNumber) {
        driver.findElement(ORDER_NUMBER_FIELD);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER_FIELD)).sendKeys(orderNumber);
        return this;
    }

    // Метод кликающий на кнопку <<Go!>>
    public HomePage pressGoButton() {
        driver.findElement(GO_BUTTON).click();
        return this;
    }

    // Общий метод для перехода к статусу определённого заказа
    public HomePage goToOrderStatus(String orderNumber) {
        clickStatusOrderButton();
        fillOrderNumber(orderNumber);
        pressGoButton();
        return this;
    }

    // Метод для поиска необходимого вопроса, скрол и клик на него
    public HomePage searchScrollAndClickQuestion(String question) {
        WebElement questionText = driver.findElement(By.xpath(".//div[text()='" + question + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionText);
        questionText.click();
        return this;
    }

    // Метод который ищет и возвращает необходимый элемент, то есть корректный ответ в развёрнутом состоянии
    public WebElement searchAnswerNotHidden(String answer) {
        return driver.findElement(By.xpath(".//div[@class='accordion__panel' and not(@hidden)]//p[text()='" + answer + "']"));

    }


}










