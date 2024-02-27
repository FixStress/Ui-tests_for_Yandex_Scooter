package ru.yandex.samokat.tests.firefox.orderstests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.samokat.pages.HomePage;
import ru.yandex.samokat.pages.orderpages.WhoIsTheScooterPage;

@RunWith(Parameterized.class)
public class DisplayErrorsTests {
    private WebDriver driver;
    private String placeholderText;
    private String inputValue;
    private String expectedTextError;

    public DisplayErrorsTests(String nameField, String testData, String expectedTextError) {
        this.placeholderText = nameField;
        this.inputValue = testData;
        this.expectedTextError = expectedTextError;
    }


    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Object[][] getTestdata() {
        return new Object[][]{
                {"* Имя", "А", "Введите корректное имя"},
                {"* Фамилия", "А", "Введите корректную фамилию"},
                {"* Адрес: куда привезти заказ", "А", "Введите корректный адрес"},
                {"* Станция метро", "Бугагашенька", "Выберите станцию"},
                {"* Телефон: на него позвонит курьер", "1992-1992", "Введите корректный номер"},
        };
    }

    // Тестирование на отображение ошибок в полях ввода при некорректном вводе
    @Test
    public void displayingInputFieldErrors() {
        HomePage homePage = new HomePage(driver)
                .open()
                .clickOrderButtonHeader();
        WhoIsTheScooterPage whoIsTheScooterPage = new WhoIsTheScooterPage(driver);
        whoIsTheScooterPage.whoIsTheScooterPageWait();
        WebElement errorMessage = whoIsTheScooterPage.negativeInAllInputFields(placeholderText, inputValue, expectedTextError);
        Assert.assertTrue("Ошибка не отображается на странице", errorMessage.isDisplayed());

    }

}