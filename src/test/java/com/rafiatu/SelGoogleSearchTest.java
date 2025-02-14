import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelGoogleSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set ChromeDriver path (optional if added to system path)
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        // Verify title contains search term
        assertEquals(true, driver.getTitle().contains("Selenium WebDriver"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
