import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public void loginExitoso(){
        System.setProperty("webdriver.chrome.driver","C://tools//chromedriver");
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();

    }
}
