package Main;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Selenium {
    protected static WebDriver driver;
    public String expected_Registration_Successful_Message = "Your registration completed";

    @Test
            public void verify_registration(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Rohan");
        driver.findElement(By.id("LastName")).sendKeys("Patel");
        Select dob = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dob.selectByIndex(3);
        Select dob1 = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        dob1.selectByVisibleText("October");
        Select dob2 = new Select(driver.findElement(By.name("DateOfBirthYear")));
        dob2.selectByValue("1995");
        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String date2 = dateFormat.format(date);
        driver.findElement(By.id("Email")).sendKeys("rohanp"+date2+"@hotmail.com");
        driver.findElement(By.name("Company")).sendKeys("Tester");
        driver.findElement(By.id("Password")).sendKeys("tester1");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("tester1");
        driver.findElement(By.id("register-button")).click();
        String actual_Registration_Successful_Message = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        Assert.assertEquals("Test case failed",expected_Registration_Successful_Message,actual_Registration_Successful_Message);
        driver.findElement(By.xpath("//ul/li[2]/a[@href=\"/logout\"]")).click();
        driver.quit();
    }

    public String expected_Currency = "Ђ1130.90";

    @Test
    public void verify_change_in_currency(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//select[@id=\"customerCurrency\"]/option[2]")).click();
        driver.findElement(By.xpath("//img[@alt=\"Picture of Build your own computer\"]")).click();
        String actual_Currency = driver.findElement(By.xpath("//div[@class=\"product-price\"]/span[@itemprop=\"price\"]")).getText();
        Assert.assertEquals("Test case failed",expected_Currency,actual_Currency);
        driver.quit();

    }


    public String expected_error_message = "Only registered customers can use email a friend feature";

    @Test
    public void verify_error_message(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//img[@alt=\"Picture of Build your own computer\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String date3 = dateFormat.format(date);
        driver.findElement(By.id("FriendEmail")).sendKeys("rajshah"+date3+"@gmail.com");
        driver.findElement(By.id("YourEmailAddress")).sendKeys("dhru"+date3+"@gmail.com");
        driver.findElement(By.id("PersonalMessage")).sendKeys("Hello");
        driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
        String actual_error_message = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li")).getText();
        Assert.assertEquals("Test case failed",expected_error_message,actual_error_message);
        driver.quit();

    }


}

