package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleStockPage extends BasePage {

    @FindBy(xpath = ".//table[@class='table pull-left']//tr[not(@class)]//td[2]")
    private List<WebElement> firstTradingConditionsTableData;
    @FindBy(xpath = ".//table[@class='table pull-right']//tr//td[2]")
    private List<WebElement> secondTradingConditionsTableData;
    @FindBy(xpath = ".//h1[@class='ltr text-left']")
    private WebElement header;

    public SingleStockPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTradingConditionsTableData() {
        wait.until(driver -> header.isDisplayed());
        List<String> firstTradingConditionsData = firstTradingConditionsTableData.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> secondTradingConditionsData = secondTradingConditionsTableData.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> tradingConditionsTableData = Stream.concat(firstTradingConditionsData.stream(), secondTradingConditionsData.stream()).toList();

        System.out.println("Trading Conditions: ");
        for (Object o : tradingConditionsTableData)
            System.out.println(o);

        return tradingConditionsTableData;
    }
}
