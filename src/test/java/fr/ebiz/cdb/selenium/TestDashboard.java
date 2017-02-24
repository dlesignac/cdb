package fr.ebiz.cdb.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestDashboard {

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = "http://localhost:8080/training-java/";
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGet() {
        driver.get(baseUrl);
        driver.findElement(By.id("results"));
    }

    @Test
    public void testAddComputerButton() {
        driver.get(baseUrl);
        driver.findElement(By.id("addComputer")).click();
        driver.findElement(By.id("addComputerForm"));
    }

}
