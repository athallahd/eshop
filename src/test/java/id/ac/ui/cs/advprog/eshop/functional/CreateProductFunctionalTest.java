package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createPage_isCorrect(ChromeDriver driver) throws  Exception {
        driver.get(baseUrl + "/product/create");
        String pageHeader = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Create New Product", pageHeader);
    }
    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Navigate to the Create Product Page
        driver.get(baseUrl + "/product/create");

        // Fill out the Product Name field
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Sampo Cap Bambang");

        // Fill out the Product Quantity field
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("15");

        // Click the Submit button
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        String itemName = driver.findElement(By.cssSelector("table tbody tr:first-child td:first-child")).getText();
        String quantity = driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(2)")).getText();
        assertEquals("Sampo Cap Bambang", itemName);
        assertEquals("15", quantity);
    }
}
