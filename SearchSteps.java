package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchSteps {
    public static void main(String[] args) {

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Navigate to the WebstaurantStore website
        driver.manage().window().maximize();
        driver.get("https://webstaurantstore.com/");

        // Use WebDriverWait for waiting for the search input to be visible and interactable and input search criteria
        try {Thread.sleep(5000);} catch (Exception e) {}
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='searchval']")));
        searchInput.sendKeys("stainless work table");
        searchInput.submit();

        // Wait for the search results to be visible and verify search results contain expected word
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='itemDescription']")));
        if (searchResults.getText().contains("Table")) {
            System.out.println("Search results contain 'Table' in their titles");
        } else {
            System.out.println("Search results do not contain 'Table' in their titles");
        }

        // Click on the last item
        List<WebElement> productTitles = driver.findElements(By.xpath("//span[@data-testid='itemDescription']"));
        WebElement lastProduct = productTitles.getLast();
        lastProduct.click();

        // Wait for the add to cart button to be visible and clickable
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='addToCartButton']")));
        addToCartButton.click();

        // View cart
        WebElement viewCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/viewcart.cfm']")));
        viewCartButton.click();

        // Empty cart
        WebElement emptyCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']")));
        emptyCartButton.click();

        //confirm empty cart
        WebElement confirmEmptyCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open bg-white border-gray-200 rounded border-solid border mx-auto mt-5 opacity-100 outline-none overflow-y-auto overflow-x-hidden absolute inset-x-0 shadow-lg w-11/12 max-w-md']//button[contains(text(),'Empty')]")));
        confirmEmptyCartButton.click();

        //verify cart is empty
        WebElement cartEmptyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='header-1']")));
        String actualMessage = cartEmptyMessage.getText();
        String expectedMessage = "Your cart is empty.";
        assertEquals(actualMessage, expectedMessage);

        driver.quit();
    }
}