package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class StocksPage extends BasePage {
    @FindBy(xpath = ".//div[@class='table-country-filter']//button[@data-value='Norway']")
    private WebElement norwayStockFilter;
    @FindBy(xpath = ".//div[@id='DataTables_Table_0_filter']//input[@type='search']")
    private WebElement searchInput;
    @FindBy(xpath = ".//table[@id='DataTables_Table_0']//tbody//a[@class='btn btn-green']")
    private WebElement readMoreButton;
    @FindBy(xpath = ".//table[@id='DataTables_Table_0']//thead//tr//th")
    private List<WebElement> stocksTableHeaders;
    @FindBy(xpath = ".//table[@id='DataTables_Table_0']//tbody//tr//td")
    private List<WebElement> stocksTableRowData;
    @FindBy(xpath = ".//table[@id='DataTables_Table_0']//tbody//tr")
    private List<WebElement> stocksTableRow;
    @FindBy(xpath = ".//table[@id='DataTables_Table_0']//tbody//tr//td[@data-xm-qa-name='symbol']")
    private WebElement stockSymbol;
    @FindBy(xpath = ".//div[@class='sticky-bar active']//i[@class='fa fa-times']")
    private WebElement blackBannerCloseButton;

    public StocksPage(WebDriver driver) {
        super(driver);
    }

    public StocksPage setFilterForNorwegianStocks() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", norwayStockFilter);
        blackBannerCloseButton.click();
        wait.until(driver -> norwayStockFilter.isDisplayed());
        norwayStockFilter.click();
        return this;
    }

    public StocksPage searchForStock(String stockSymbolWithDescription) {
        searchInput.sendKeys(stockSymbolWithDescription);
        wait.until(driver -> stocksTableRow.size() == 1);
        return this;
    }

    public SingleStockPage clickReadMoreButton() {
        wait.until(driver -> readMoreButton.isEnabled());
        readMoreButton.click();
        return new SingleStockPage(driver);
    }

    public List<String> getStocksSpreadsAndConditionsTableData() {
        List<String> stocksTableRowData = this.stocksTableRowData.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        stocksTableRowData.remove(stocksTableRowData.size() - 1);

        System.out.println("Stocks - Spreads / Conditions: ");
        for (Object o : stocksTableRowData)
            System.out.println(o);

        return stocksTableRowData;
    }
}
