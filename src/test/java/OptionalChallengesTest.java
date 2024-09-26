import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OptionalChallengesTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }


    @Test
    public void interactWithIframeMenu() {
        driver.get("https://automationtesting.co.uk/iframes.html");

        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='index.html']"));
        driver.switchTo().frame(iframe);

        WebElement menu = driver.findElement(By.className("toggle"));
        menu.click();

        WebElement accordionOption = driver.findElement(By.xpath("//a[@href='accordion.html']"));
        accordionOption.click();

        driver.switchTo().defaultContent();

        WebElement mainMenu = driver.findElement(By.className("toggle"));
        mainMenu.click();
    }

    @Test
    public void triggerAndCloseAlert(){
        driver.get("https://automationtesting.co.uk/popups.html");

        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'Trigger Alert')]"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void openNewTabAndSearch() {
        driver.get("https://automationtesting.co.uk/browserTabs.html");

        WebElement openTabButton = driver.findElement(By.xpath("//input[@value='Open Tab']"));
        openTabButton.click();

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        assertNotEquals(originalWindow, driver.getWindowHandle());

        WebElement searchField = driver.findElement(By.xpath("//*[@title='Buscar']"));
        searchField.sendKeys("Selenium");
        searchField.submit();

        driver.switchTo().window(originalWindow);
    }
}
