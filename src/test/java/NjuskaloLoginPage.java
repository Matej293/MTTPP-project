import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NjuskaloLoginPage extends BasePage {

    @FindBy(id = "login_username")
    private WebElement usernameInput;

    @FindBy(id = "login_password")
    private WebElement passwordInput;

    public NjuskaloLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(usernameInput)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed();
    }
}
