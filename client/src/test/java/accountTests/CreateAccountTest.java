package accountTests;



import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;

import static org.junit.Assert.assertEquals;

public class CreateAccountTest {

    private WebDriver driver;
    private WebElement user;
    private WebElement pass;
    private WebElement email;
    private WebElement login;
    private WebElement personalData;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        // Launch Chrome
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to page
        driver.get("http://localhost:8211/registrationPage");

        email = driver.findElement(By.name("email"));
        user = driver.findElement(By.name("username"));
        pass = driver.findElement(By.name("password"));
        login = driver.findElement(By.name("login"));
        personalData = driver.findElement(By.name("personal"));
    }

    @Test
    public void createAccount() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath(".//*[@id='mainWrapper']/div/div/div/form/div[5]/input"));

        email.sendKeys("test@yahoo.com");
        user.sendKeys("test");
        login.sendKeys("test");
        pass.sendKeys("test");
        personalData.sendKeys("test");
        button.submit();
        Thread.sleep(100);
        String logout = driver.findElement(By.xpath(".//nav/div[@class='btn-group']/button")).getText();
        assertEquals("Wyloguj się", logout);
    }

    @AfterTest
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }
}
