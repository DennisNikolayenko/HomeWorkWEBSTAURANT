package stepDefinitions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchTest {
    private WebDriver driver;

    protected static By SEARCH_INPUT = By.xpath("//input[@id='searchval']");
    protected static By SEARCH_RESULT_SPAN = By.xpath("//span[@data-testid='itemDescription']");
    protected static By PRODUCT_TITLES = By.xpath("//span[@data-testid='itemDescription']");
    protected static By ADD_TO_CART_BUTTON = By.xpath("//input[@name='addToCartButton']");
    protected static By VIEW_CART_BUTTON = By.xpath("//a[@href='/viewcart.cfm']");
    protected static By EMPTY_CART_BUTTON = By.xpath("//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']");
    protected static By CONFIRM_EMPTY_CART_BUTTON = By.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open bg-white border-gray-200 rounded border-solid border mx-auto mt-5 opacity-100 outline-none overflow-y-auto overflow-x-hidden absolute inset-x-0 shadow-lg w-11/12 max-w-md']//button[contains(text(),'Empty')]");
    protected static By YOUR_CART_IS_EMPTY_MESSAGE = By.xpath("//p[@class='header-1']");

    @Before
    public void setUp() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }

    @Test
    public void testSearchAndAddToCart() {

        driver.get("https://webstaurantstore.com/");
        // Use WebDriverWait for waiting for the search input to be visible and interactable and input search criteria
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_INPUT));
        searchInput.sendKeys("stainless work table");
        searchInput.submit();

        // Wait for the search results to be visible and verify search results contain expected word
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULT_SPAN));
        Assert.assertTrue(searchResults.getText().contains("Table"), "Search results contain 'Table' in their titles");

        // Click on the last item
        List<WebElement> productTitles = driver.findElements(PRODUCT_TITLES);
        WebElement lastProduct = productTitles.getLast();
        lastProduct.click();

        // Wait for the add to cart button to be visible and clickable
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_BUTTON));
        addToCartButton.click();

        // View cart
        WebElement viewCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(VIEW_CART_BUTTON));
        viewCartButton.click();

        // Empty cart
        WebElement emptyCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EMPTY_CART_BUTTON));
        emptyCartButton.click();

        //confirm empty cart modal
        WebElement confirmEmptyCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_EMPTY_CART_BUTTON));
        confirmEmptyCartButton.click();

        //verify cart is empty message is displayed
        WebElement cartIsEmptyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(YOUR_CART_IS_EMPTY_MESSAGE));
        String actualMessage = cartIsEmptyMessage.getText();
        String expectedMessage = "Your cart is empty.";
        assertEquals(expectedMessage, actualMessage, "Cart is empty message");
    }
}