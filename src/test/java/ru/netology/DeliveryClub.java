package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryClub {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Поиск Картошки фри")
    void shouldTestV1() {
        driver.get("https://www.delivery-club.ru/samara");
        driver.findElement(By.className("header__search-button__name")).click();
        driver.findElement(By.className("vendors-suggests__input")).sendKeys("Картошка фри" + Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        String expected = "Картошка фри";
        String actual = driver.findElement(By.className("search-results__query")).getText();
        assertEquals(expected, actual);
    }
}