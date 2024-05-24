package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyModal extends BasePage {

    @FindBy(xpath = ".//div[@class='modal-content']//div[@class='modal-footer']//button[@data-dismiss='modal']")
    private WebElement modalAcceptAllButton;

    public PrivacyModal(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnAcceptAllButton() {
        modalAcceptAllButton.click();
        return new HomePage(driver);
    }
}
