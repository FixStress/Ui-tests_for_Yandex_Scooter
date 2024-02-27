package ru.yandex.samokat.tests.firefox.orderstests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.samokat.pages.HomePage;
import ru.yandex.samokat.pages.orderpages.AboutRentPage;
import ru.yandex.samokat.pages.orderpages.WhoIsTheScooterPage;

public class FullOrderingPath {
    private WebDriver driver;


    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();

    }

    private final boolean expected = true;

    // Тестирование полного пути оформления заказа
    // через кнопку <<Заказать>> в хедере
    @Test
    public void positiveCheckOutHeaderButton() {
        HomePage homePage = new HomePage(driver)
                .open()
                .clickOrderButtonHeader();
        WhoIsTheScooterPage whoIsTheScooterPage = new WhoIsTheScooterPage(driver)
                .whoIsTheScooterPageWait()
                .fillInAllInputFields(
                        "Наруто",
                        "Узумаки",
                        "Коноха",
                        "1",
                        "+79998887766");
        AboutRentPage aboutRentPage = new AboutRentPage(driver)
                .aboutRentWait()
                .fillInAllInputFields(
                        1,
                        "трое суток",
                        "black",
                        "Саске, вернись в Коноху!")
                .clickButtonYes();
        Assert.assertEquals(expected, aboutRentPage.orderIsProcessed());

    }

    // Тестирование полного пути оформления заказа
    // через кнопку <<Заказать>> в центре страницы
    @Test
    public void positiveCheckOutMiddleButton() {
        HomePage homePage = new HomePage(driver)
                .open().skrollAndClickOrderButtonMiddle();
        WhoIsTheScooterPage whoIsTheScooterPage = new WhoIsTheScooterPage(driver)
                .whoIsTheScooterPageWait()
                .fillInAllInputFields(
                        "Саске",
                        "Учиха",
                        "Деревня Скрытого Звука",
                        "5",
                        "89997776655");
        AboutRentPage aboutRent = new AboutRentPage(driver)
                .aboutRentWait()
                .fillInAllInputFields(
                        7,
                        "сутки",
                        "grey",
                        "Не вернусь!")
                .clickButtonYes();
        Assert.assertEquals(expected, aboutRent.orderIsProcessed());
        ;

    }
}
