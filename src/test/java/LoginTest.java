import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    @Test
    public void loginExitoso(){
        System.setProperty("webdriver.chrome.driver","./chromedriver");
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://saucedemo.com/");
        driver.quit();

    }
}
