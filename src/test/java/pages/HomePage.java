package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = ".//div[@id='navigation-collapse']//li[@class='main_nav_trading']")
    private WebElement tradingTab;
    @FindBy(xpath = ".//li[@class='menu-stocks']//a[@href='https://www.xm.com/stocks']")
    private WebElement stocksButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnTradingTab() {
        tradingTab.click();
        wait.until(driver -> stocksButton.isDisplayed());
        return this;
    }

    public StocksPage clickOnStocks() {
        stocksButton.click();
        return new StocksPage(driver);
    }
}
