import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NjuskaloCategoryPage extends BasePage {

    @FindBy(id = "sort")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//ul[@class='price-items cf']")
    private List<WebElement> adPrices;

    public NjuskaloCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectSortingOption(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropdown)).click();
        List<WebElement> options = sortingDropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(optionText)) {
                option.click();
                break;
            }
        }
    }

    public List<WebElement> getAdPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(adPrices));
        return adPrices;
    }
}
