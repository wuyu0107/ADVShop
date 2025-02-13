package id. ac.ui.cs.advprog.eshop.functional;

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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)

public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */

    @LocalServerPort
    private int serverPort;
    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;
    private String listUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        listUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl);

        // Input for Product Name
        WebElement productNameField = driver.findElement(By.id("nameInput"));
        productNameField.sendKeys("Apple");

        // Input for Product Quantity
        WebElement productQuantityField = driver.findElement(By.id("quantityInput"));
        productQuantityField.sendKeys("10");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Redirect to Product List
        driver.get(listUrl);
        WebElement productName = driver.findElement(By.id("productTable"));
        assertTrue(productName.getText().contains("Apple"));

        WebElement productQuantity = driver.findElement(By.id("productTable"));
        assertTrue(productQuantity.getText().contains("10"));
    }
}