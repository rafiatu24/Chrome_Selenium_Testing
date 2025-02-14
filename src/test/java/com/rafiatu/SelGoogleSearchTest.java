package com.rafiatu;  // Package name (organizes code logically)

import io.github.bonigarcia.wdm.WebDriverManager; // Manages WebDriver setup
import org.junit.jupiter.api.AfterAll;  // Runs code after all tests
import org.junit.jupiter.api.BeforeAll; // Runs code before all tests
import org.junit.jupiter.api.Test;      // Marks a test method

import org.openqa.selenium.By;         // Used to locate elements on a webpage
import org.openqa.selenium.WebDriver;   // Controls the browser
import org.openqa.selenium.WebElement;  // Represents elements on a webpage
import org.openqa.selenium.chrome.ChromeDriver;  // Chrome browser driver
import org.openqa.selenium.chrome.ChromeOptions; // Sets Chrome browser options
import org.openqa.selenium.support.ui.ExpectedConditions; // Defines wait conditions
import org.openqa.selenium.support.ui.WebDriverWait; // Waits for elements

import org.slf4j.Logger;  // Logging messages
import org.slf4j.LoggerFactory; // Creates a logger

import java.time.Duration; // Handles time durations

import static org.junit.jupiter.api.Assertions.assertEquals; // For assertions (checks)
import static org.junit.jupiter.api.Assertions.assertTrue;   // Checks if a condition is true

// Class that performs a Google search test using Selenium WebDriver
public class SelGoogleSearchTest { 

    // Logger to record test actions
    private static final Logger logger = LoggerFactory.getLogger(SelGoogleSearchTest.class);
    
    // WebDriver to control Chrome browser
    private static WebDriver driver;
    
    // URL of Google homepage
    private static final String BASE_URL = "https://www.google.com"; 

    // Setup method runs once before all tests
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); // Automatically sets up ChromeDriver

        // Chrome options to configure browser behavior
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Open browser in full-screen mode
        options.addArguments("--disable-infobars"); // Remove annoying Chrome info bars
        // options.addArguments("--headless"); // Uncomment to run tests without opening the browser

        driver = new ChromeDriver(options); // Start Chrome browser with options
        logger.info("ChromeDriver initialized."); // Log message for confirmation
    }

    // Cleanup method runs once after all tests
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit(); // Close browser and stop WebDriver
            logger.info("ChromeDriver closed."); // Log confirmation
        }
    }

    // Test method: Performs a Google search
    @Test
    public void googleSearchTest() {
        driver.get(BASE_URL); // Open Google homepage
        logger.info("Navigated to: " + BASE_URL); // Log navigation

        // Wait until the search box is available (max 90 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q"))); 

        // Find the search box and type "Selenium testing"
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium testing"); // Enter text in the search box
        searchBox.submit(); // Press Enter to search
        logger.info("Search query entered."); // Log search action

        // Wait until the page title contains "Selenium testing"
        wait.until(ExpectedConditions.titleContains("Selenium testing")); 

        // Expected title format for Google search results
        String expectedTitlePortion = "Selenium testing - Google Search"; 
        String actualTitle = driver.getTitle(); // Get the current page title

        // Verify the page title contains expected text
        assertTrue(actualTitle.contains(expectedTitlePortion), "Title does not match!"); 
        logger.info("Title assertion passed."); // Log success message
    }
}
