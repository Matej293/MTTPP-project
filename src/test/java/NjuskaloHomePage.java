import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class NjuskaloHomePage extends BasePage {

    @FindBy(id = "didomi-notice-agree-button")
    private WebElement agreeButton;

    @FindBy(xpath = "//button[normalize-space()='Razumijem']")
    private WebElement iUnderstandButton;

    @FindBy(id = "keywords")
    private WebElement searchBox;

    @FindBy(xpath = "//ul[@class='categoryList']//a[@data-href]")
    private List<WebElement> navigationMenuLinks;

    @FindBy(xpath = "//ul[@class='Categories-list']//a[@data-href]")
    private List<WebElement> carCategoryLinks;

    @FindBy(id = "js-veza-uvjeti_koristenja")
    private WebElement termsAndConditions;

    @FindBy(xpath = "//a[@href='/prijava/']")
    private WebElement loginButton;

    public NjuskaloHomePage(WebDriver driver) {
        super(driver);
    }

    // Wait for the "I agree" and "I understand" buttons and click them (if they are clickable)
    public void acceptPolicyPopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(agreeButton)).click();
        } catch (Exception e) {
            System.out.println("'I Agree' button not found or already accepted.");
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(iUnderstandButton)).click();
        } catch (Exception e) {
            System.out.println("'I Understand' button not found or already accepted.");
        }
    }

    public boolean isTitleCorrect(String expectedTitle) {
        return wait.until(ExpectedConditions.titleContains(expectedTitle));
    }

    public void searchFor(String query) {
        // wait for the search box to become clickable and clear it
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).clear();
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    public void navigateToMenuItem(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(navigationMenuLinks));
        if (index >= 0 && index < navigationMenuLinks.size()) {
            navigationMenuLinks.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid menu index: " + index);
        }
    }

    public void clickFooterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditions)).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void navigateToCategory(String categoryName, String subCategory) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(navigationMenuLinks));
            // Click the main category
            boolean categoryFound = false;
            for (WebElement category : navigationMenuLinks) {
                if (category.getText().equalsIgnoreCase(categoryName)) {
                    category.click();
                    categoryFound = true;
                    break;
                }
            }
            if (!categoryFound) {
                throw new NoSuchElementException("Main category '" + categoryName + "' not found.");
            }

            wait.until(ExpectedConditions.visibilityOfAllElements(carCategoryLinks));
            // Click the subcategory
            boolean subCategoryFound = false;
            for (WebElement carCategory : carCategoryLinks) {
                WebElement h3Element = carCategory.findElement(By.tagName("h3"));
                if (h3Element.getText().equalsIgnoreCase(subCategory)) {
                    carCategory.click();
                    subCategoryFound = true;
                    break;
                }
            }
            if (!subCategoryFound) {
                throw new NoSuchElementException("Subcategory '" + subCategory + "' not found.");
            }
        } catch (Exception e) {
            System.err.println("Error during navigation: " + e.getMessage());
            throw e;
        }
    }

}

