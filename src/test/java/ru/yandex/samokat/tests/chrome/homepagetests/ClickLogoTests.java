package ru.yandex.samokat.tests.chrome.homepagetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.samokat.pages.HomePage;

import static org.junit.Assert.*;

public class ClickLogoTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();

    }

    // Тестирование клика на слово Яндекс в логотипе с главной страницы
    @Test
    public void testClickYandexLogoOnYheHomePage() {
        HomePage homePage = new HomePage(driver)
                .open();
        // Получаем идентификатор родительской вкладки
        String parentHandle = driver.getWindowHandle();
        // Открываем новую вкладку нажатием на слово Яндекс в логотипе
        homePage.clickWordYandexInLogo();
        // Ждем, пока не появится вторая вкладка
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        // Переключаемся на новую вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Проверяем URL текущей вкладки
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://ya.ru");
    }

    // Тестирование клика на слово Самокат со страницы заполнения формы заказа
    @Test
    public void testClickSamokatLogoOnTheOrderPage() {
        // Открываем главную страницу сервиса Яндекс Самокат и переходим на страницу оформления заказа
        HomePage homePage = new HomePage(driver)
                .open()
                .clickOrderButtonHeader();
        // Подождать, пока загрузится страница "Для кого самокат"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://qa-scooter.praktikum-services.ru/order"));
        // Зафиксировать URL страницы
        String urlBeforeClickSamokat = driver.getCurrentUrl();
        // Кликнуть на кнопку "Самокат"
        homePage.clickWordSamokatInLogo();
        // Подождать, пока загрузится главная страница сервиса
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeClickSamokat)));
        // Получить URL после клика на кнопку "Самокат"
        String urlAfterClickSamokat = driver.getCurrentUrl();
        // Сравнить URL до и после клика
        assertNotEquals(urlBeforeClickSamokat, urlAfterClickSamokat);
    }

    // Тестирование нажатия на слово Самокат с главной страницы
    // Ориентировался на требования из ручной части тестирования
    // Нам говорили, что нажатие на Самокат должно себя вести как нажатие на слово Переводчик в сервисе Яндекс переводчик
    // То есть обновляться
    // ОР после заполнения номера заказа и нажатия на слово Самокат
    // Страница должна обновиться, то есть поле станет пустым
    @Test
    public void testClickSamokatLogoOnTheHomePage() {
        HomePage homePage = new HomePage(driver)
                .open()
                .clickStatusOrderButton()
                .fillOrderNumber("12345");
        driver.findElement(By.xpath(".//input[@value='12345']"));
        homePage.clickWordSamokatInLogo();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='']")));
        assertTrue(driver.findElement(By.xpath(".//input[@value='']")).isDisplayed());

    }

}