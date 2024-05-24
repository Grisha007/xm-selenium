package tests;

import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PrivacyModal;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    private static ConfigurationReader configuration;
    protected WebDriverWait wait;

    private PrivacyModal privacyModal;

    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }

    @BeforeEach
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();
        try {
            driver = browserFactory.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }

        if (configuration.getWindowWidth() == 0 && configuration.getWindowHeight() == 0) {
            driver.manage().window().maximize();
        } else {
            driver.manage().window().setSize(new Dimension(configuration.getWindowWidth(), configuration.getWindowHeight()));
        }

        driver.get(configuration.getBaseUrl());
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> driver.getTitle().equals("Forex & CFD Trading on Stocks, Indices, Oil, Gold by XM™"));
        privacyModal = new PrivacyModal(driver);
        privacyModal.clickOnAcceptAllButton();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
