package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public WebDriver createInstance(ConfigurationReader configuration) throws NoSuchBrowserException {

        String browser = configuration.getBrowser();

        switch (browser) {
            case "chrome" -> {
                return new ChromeDriver();
            }
            case "firefox" -> {
                return new FirefoxDriver();
            }
            case "edge" -> {
                return new EdgeDriver();
            }
            default -> throw new NoSuchBrowserException(browser);
        }
    }
}
