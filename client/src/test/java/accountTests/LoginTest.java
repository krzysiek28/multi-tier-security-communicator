package accountTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;
    private WebElement user;
    private WebElement pass;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        // Launch Chrome
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Navigate to page
        driver.get("http://localhost:8211/loginPage");

        user = driver.findElement(By.name("username"));
        pass = driver.findElement(By.name("password"));
    }

    @Test
    public void login() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath(".//*[@id='mainWrapper']/div/div/div/form/div[3]/input"));

        user.sendKeys("test");
        pass.sendKeys("test");
        button.click();

        String logout = driver.findElement(By.xpath("//nav/div[@class='btn-group']/button")).getText();
        assertEquals("Wyloguj się", logout);
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }

    private By toBy(String xpath){
        return By.xpath(xpath);
    }
}
