package ru.yandex.samokat.tests.firefox.orderstests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.samokat.pages.HomePage;
import ru.yandex.samokat.pages.orderpages.StatusOrderPage;

public class StatusOrdersTests {
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

    // Тестирование на отображение сообщения о не найдёном заказу
    // При вводе несуществующего заказа
    @Test
    public void NegativeTestsOrderStatus() {
        new HomePage(driver)
                .open()
                .goToOrderStatus("24252627");
        StatusOrderPage statusOrderPage = new StatusOrderPage(driver);
        statusOrderPage.notFoundImage();
    }


}
