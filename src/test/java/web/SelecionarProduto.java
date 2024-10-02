package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SelecionarProduto {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void selecionarSauceLabsBackpack() {
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        
        assertThat(driver.findElement(By.cssSelector("div.login_logo")).getText(), is("Swag Labs"));
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".submit-button.btn_action")).click();
        assertThat(driver.findElement(By.cssSelector("span.title")).getText(), is("Products"));

        driver.findElement(By.cssSelector("[alt='Sauce Labs Backpack']")).click();
        assertThat(driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText(), is("Sauce Labs Backpack"));
        assertThat(driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText(), is("$29.99"));
        driver.findElement(By.name("add-to-cart")).click();

        assertThat(driver.findElement(By.cssSelector("span.shopping_cart_badge")).getText(), is("1"));
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        
        assertThat(driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText(), is("Sauce Labs Backpack"));
        assertThat(driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText(), is("$29.99"));
        
    }
}