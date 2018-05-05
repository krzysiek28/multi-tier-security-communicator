import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSteps {

    private WebDriver driver;

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

    public void goToLoginPage(){

    }

    public String checkCurrentPage() throws InterruptedException {
        return driver.getCurrentUrl();
    }
}
