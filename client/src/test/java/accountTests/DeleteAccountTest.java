package accountTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class DeleteAccountTest {
    private WebDriver driver;
    private final static String logIn = ".//*[@id='mainWrapper']/div/div/div/form/div[3]/input";

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
    }

    @Test
    public void deleteUser() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.xpath(logIn)).click();
        Thread.sleep(100);
        String logout = driver.findElement(By.xpath("//nav/div[@class='btn-group']/button")).getText();
        assertEquals("Wyloguj się", logout);
        driver.get("http://localhost:8211/delete/test");
        driver.get("http://localhost:8211/loginPage");

        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.xpath(logIn)).click();
        assertEquals("Wyloguj się", logout);
        String communicate = driver.findElement(By.xpath(".//*[@id='mainWrapper']//form/div[@class='alert alert-danger']/p")).getText();
        assertEquals("Niepoprawna nazwa użytkownika lub hasło", communicate);
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
