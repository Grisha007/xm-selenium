package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SingleStockPage;
import pages.StocksPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StocksTest extends BaseTest {

    private final String STOCK_SYMBOL = "Orkla ASA (ORK.OL)";

    @Test
    public void comparisonOfValuesInStockTables() {
        StocksPage stocksPage = new HomePage(driver)
                .clickOnTradingTab()
                .clickOnStocks()
                .setFilterForNorwegianStocks()
                .searchForStock(STOCK_SYMBOL);

        List<String> firstList = stocksPage.getStocksSpreadsAndConditionsTableData();
        stocksPage.clickReadMoreButton();

        SingleStockPage singleStockPage = new SingleStockPage(driver);
        List<String> secondList = singleStockPage.getTradingConditionsTableData();

        assertTrue(secondList.stream().anyMatch(firstList::contains));
    }
}
