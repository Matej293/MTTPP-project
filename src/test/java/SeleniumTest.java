import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browserName;
    private final String url = "https://www.njuskalo.hr";
    private NjuskaloHomePage njuskaloHomePage;
    private NjuskaloLoginPage njuskaloLoginPage;
    private NjuskaloCategoryPage njuskaloCategoryPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        browserName = browser.toLowerCase();
        driver.set(createRemoteDriver(browserName));
        getDriver().manage().window().maximize();
        getDriver().get(url);

        njuskaloHomePage = new NjuskaloHomePage(getDriver());
        njuskaloHomePage.acceptPolicyPopup();
    }

    private WebDriver getDriver() {
        return driver.get();
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testPageTitle(String browser) {
        System.out.println("Starting test: testPageTitle for " + browser);
        String expectedTitle = "Njuskalo.hr oglasnik";
        Assert.assertTrue(njuskaloHomePage.isTitleCorrect(expectedTitle), "Title Mismatch!");
        System.out.println("Test Passed for " + browser);
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testSearchFunctionality(String browser) {
        System.out.println("Starting test: testSearchFunctionality for " + browser);
        String expectedTitle = "bicikl";
        njuskaloHomePage.searchFor("bicikl");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(getDriver().getTitle())));

        Assert.assertTrue(getDriver().getTitle().contains(expectedTitle), "Search test failed!");
        System.out.println("Test Passed for " + browser);
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testFooterLinks(String browser) {
        System.out.println("Starting test: testFooterLinks for " + browser);
        njuskaloHomePage.clickFooterLink();
        Assert.assertNotEquals(getDriver().getCurrentUrl(), url, "Footer link did not navigate to Terms and Conditions.");
        System.out.println("Footer Links Test Passed for " + browser);
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testNavigationMenu(String browser) {
        System.out.println("Starting test: testNavigationMenu for " + browser);
        njuskaloHomePage.navigateToMenuItem(0);
        Assert.assertNotEquals(getDriver().getCurrentUrl(), url, "Navigation menu did not navigate.");
        System.out.println("Navigation Menu Test Passed for " + browser);
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testLoginButton(String browser) {
        System.out.println("Starting test: testLoginButton for " + browser);
        njuskaloHomePage.clickLoginButton();
        njuskaloLoginPage = new NjuskaloLoginPage(getDriver());
        Assert.assertTrue(njuskaloLoginPage.isLoginPageDisplayed(), "Login page did not open correctly.");
        System.out.println("Login Button Test Passed for " + browser);
    }

    @Test(enabled = true)
    @Parameters("browser")
    public void testSortingOptions(String browser) {
        System.out.println("Starting test: testSortingOptions for " + browser);
        String categoryName = "Auto Moto Nautika";
        String subCategory = "Osobni automobili";
        njuskaloHomePage.navigateToCategory(categoryName, subCategory);

        njuskaloCategoryPage = new NjuskaloCategoryPage(getDriver());
        String sortingOption = "S nižom cijenom";
        njuskaloCategoryPage.selectSortingOption(sortingOption);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(njuskaloCategoryPage.getAdPrices()));

        List<WebElement> prices = njuskaloCategoryPage.getAdPrices();
        int counter = 1;
        for (int i = 0; i < prices.size() - 1; i++) {
            double price1 = Double.parseDouble(prices.get(i).getText().replaceAll("[^0-9.,]", "").replace(",", "."));
            double price2 = Double.parseDouble(prices.get(i + 1).getText().replaceAll("[^0-9.,]", "").replace(",", "."));

            if (price1 > price2) break;
            counter++;
        }

        for (int i = counter; i < prices.size() - 1; i++) {
            double price1 = Double.parseDouble(prices.get(i).getText().replaceAll("[^0-9.,]", "").replace(",", "."));
            double price2 = Double.parseDouble(prices.get(i + 1).getText().replaceAll("[^0-9.,]", "").replace(",", "."));

//            System.out.println("price1: " + price1 + ", price2: " + price2);
            Assert.assertTrue(price1 <= price2, "Sorting failed: Prices are not in ascending order.");
        }

        System.out.println("Sorting Options Test Passed for " + browser);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            getDriver().quit();
            System.out.println("Closed " + browserName);
        }
    }

    private WebDriver createRemoteDriver(String browser) throws Exception {
        WebDriver driver;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-gpu", "--no-sandbox", "--safe-mode", "--disable-software-rasterizer");
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                capabilities.setBrowserName("firefox");
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-gpu", "--no-sandbox", "--disable-extensions", "--disable-software-rasterizer");
                capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
                capabilities.setBrowserName("MicrosoftEdge");
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-gpu", "--no-sandbox", "--disable-extensions", "--disable-software-rasterizer");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setBrowserName("chrome");
                break;
        }

        URI hubUri = new URI("http://localhost:4444/wd/hub");
        URL hubUrl = hubUri.toURL();

        driver = new RemoteWebDriver(hubUrl, capabilities);

        return driver;
    }
}
