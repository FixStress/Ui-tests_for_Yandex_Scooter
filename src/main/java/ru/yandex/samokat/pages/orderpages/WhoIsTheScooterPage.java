package ru.yandex.samokat.pages.orderpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Страница "Для кого самокат"
public class WhoIsTheScooterPage {
    private WebDriver driver;

    // Заголовок страницы "Для кого самокат"
    private static final By WHO_IS_THE_SCOOTER_PAGE = By.xpath(".//div[text()='Для кого самокат']");

    // Поле ввода "Имя"
    private static final By FIRST_NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");

    // Поле ввода "Фамилия"
    private static final By SECOND_NAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле ввода "Адресс"
    private static final By ADDRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле ввода "Станция метро"
    private static final By METRO_STATION_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");

    // Поле ввода "Телефон"
    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка <<Далее>>
    private static final By BUTTON_NEXT = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Конструктор
    public WhoIsTheScooterPage(WebDriver driver) {

        this.driver = driver;
    }

    // Метод для ожидания загрузки страницы "Для кого самокат"
    public WhoIsTheScooterPage whoIsTheScooterPageWait() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(WHO_IS_THE_SCOOTER_PAGE));
        return this;
    }

    // Метод для заполнения поля "Имя"
    public WhoIsTheScooterPage fillFirstNameField(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        return this;
    }

    // Метод для заполнения поля "Фамилия"
    public WhoIsTheScooterPage fillSecondNameField(String secondName) {
        driver.findElement(SECOND_NAME_FIELD).sendKeys(secondName);
        return this;

    }

    // Заполнения поля "Адресс"
    public WhoIsTheScooterPage fillAddressField(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
        return this;
    }

    // Заполнение поля "Станция метро" по индексу в выпадающем списке станций
    public WhoIsTheScooterPage fillMetroStationField(String metroIndex) {
        driver.findElement(METRO_STATION_FIELD).click();
        driver.findElement(By.xpath(".//ul/li[@data-index='" + metroIndex + "']")).click();
        return this;
    }

    // Заполнение поля "Телефон"
    public WhoIsTheScooterPage fillPhoneNumberField(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
        return this;
    }

    // Метод для нажатия на кнопку <<Далее>>
    public WhoIsTheScooterPage clickButtonNext() {
        driver.findElement(BUTTON_NEXT).click();
        return this;
    }

    // Общий метод для заполнения полей на странице "Для кого самокат" и клик на кнопку <<Далее>>
    public WhoIsTheScooterPage fillInAllInputFields(String firstName, String secondName, String address, String metroIndex, String phoneNumber) {
        fillFirstNameField(firstName);
        fillSecondNameField(secondName);
        fillAddressField(address);
        fillMetroStationField(metroIndex);
        fillPhoneNumberField(phoneNumber);
        clickButtonNext();
        return this;
    }

    // Метод для поиска поля ввода по плейсхолдеру, его некорректное заполнение,
    // клик на кнопку <<Дальше>> и поиск отобразившейся ошибки
    public WebElement negativeInAllInputFields(String placeholdertext, String inputValue, String expectedTextError){
        WebElement inputField = driver.findElement(By.xpath(".//input[@placeholder='" + placeholdertext + "']"));
        inputField.sendKeys(inputValue);
        clickButtonNext();
        // Добавил else if так как у поля ввода станция метро класс ошибки отличается
        String errorMessageXpath;
        if (placeholdertext.equals("* Станция метро")) {
            errorMessageXpath = "//div[@class='Order_MetroError__1BtZb' and text()='" + expectedTextError + "']";
        } else {
            errorMessageXpath = "//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='" + expectedTextError + "']";
        }
        WebElement errorMessage = driver.findElement(By.xpath(errorMessageXpath));
        return errorMessage;
    }

}
