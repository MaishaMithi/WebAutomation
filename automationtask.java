package BMpackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class automationtask {

    WebDriver driver;

    @BeforeMethod
    public void setUp() 
    {
        System.setProperty("webdriver.chrome.driver", "E:/Apps/chromedriver-win64/chromedriver-win64");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testSuccessfulRegistration() 
    {
        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys("testuser123");
        driver.findElement(By.id("sign-password")).sendKeys("TestPassword123!");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains("Sign up successful"), "Success message should be displayed.");
        alert.accept();

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("testuser123");
        driver.findElement(By.id("loginpassword")).sendKeys("TestPassword123!");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        WebElement userProfile = driver.findElement(By.id("nameofuser"));
        Assert.assertTrue(userProfile.isDisplayed(), "User profile name should be visible after login.");
    }

    @Test
    public void testInvalidRegistration() 
    {
        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys("testuser123");
        driver.findElement(By.id("sign-password")).sendKeys("TestPassword123!");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys("testuser123");
        driver.findElement(By.id("sign-password")).sendKeys("TestPassword123!");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains("This user already exists."), "Error message for duplicate username should be displayed.");
        alert.accept();
    }

    @Test
    public void testInvalidLogin() 
    {
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("wronguser");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".text-danger"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for invalid login should be displayed.");
    }

    @AfterMethod
    public void tearDown() 
    {
        driver.quit();
    }
}
