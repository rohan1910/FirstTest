import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {

    private static WebDriver driver;

    public static void main(String[] args) {

System.setProperty("webdriver.chrome.driver","src\\broswerdriver\\chromedriver.exe");
driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.manage().window().fullscreen();
driver.get("https://demo.nopcommerce.com/");
driver.findElement(By.xpath("rohan")).sendKeys("patel");
driver.quit();
    }

}
