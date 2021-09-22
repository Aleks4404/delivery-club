package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryClub {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Поиск Картошки фри")
    void shouldFindTheProduct() {
        driver.get("https://www.delivery-club.ru/samara");
        driver.findElement(By.className("header__search-button__name")).click();
        driver.findElement(By.className("vendors-suggests__input")).sendKeys("Картошка фри" + Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        String expected = "Картошка фри";
        String actual = driver.findElement(By.className("search-results__query")).getText();
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Авторизация с невалидным номером")
    void shouldLogInWithAnInvalidNumber() {
        driver.get("https://www.delivery-club.ru/samara");
        driver.findElement(By.className("header-login-button")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/label/input")).sendKeys("0000000000" + Keys.ENTER);
        String expected = "Не удалось отправить код";
        String actual = driver.findElement(By.className("label--def__error")).getText();
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Авторизация с валидным номером")
    void shouldLogInWithValidNumber() {
        driver.get("https://www.delivery-club.ru/samara");
        driver.findElement(By.className("header-login-button")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/label/input")).sendKeys("92709873484");
        String expected = "Код из СМС";
        String actual = driver.findElement(By.cssSelector("div.verification__container.verification--active section span.label-title--def.is-required")).getText();
        assertEquals(expected, actual);
    }
}