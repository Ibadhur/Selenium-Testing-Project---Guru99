import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Guru99Test {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ribaz\\IdeaProjects\\SeleniumProject1\\src\\test\\resources\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/V4/");
    }

    @Test
    public void successfulLogin() {

        WebElement userIDText = driver.findElement(By.name("uid"));
        userIDText.sendKeys("mngr494310");

        WebElement passwordText = driver.findElement(By.name("password"));
        passwordText.sendKeys("bUbuqEm");

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();

        String alertMsg = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMsg, "User or Password is not valid", "Error : Alert Message incorrect");

        WebElement welcomeLabel = driver.findElement(By.xpath("//tr[@class='heading3']/td"));

        Assert.assertEquals(welcomeLabel.getText(), "Manger Id : mngr494310", "Error : The User name is incorrect");

    }

    @Test
    public void unsuccessfulLogin() {

        WebElement userIDText = driver.findElement(By.name("uid"));
        userIDText.sendKeys("mngr494310");

        WebElement passwordText = driver.findElement(By.name("password"));
        passwordText.sendKeys("bUbuqEm!@#");

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();

        String alertMsg = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMsg, "User or Password is not valid", "Error : Alert Message incorrect");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}