package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class NopCommereceProject {
    public WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        //timestamp for current time - to create unique email value each time
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //setting up chromedriver path from your system
        System.setProperty("webdriver.chrome.driver", "src/test/Resourses/Browser/chromedriver.exe");
        //creating object for driver
        WebDriver driver = new ChromeDriver();
        // maximize the browser window
        driver.manage().window().maximize();
        //applying implicity wait to driver object
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //open URL
        driver.get("https://demo.nopcommerce.com/");

    }
    @Test
    public void Register(){
        //timestamp for current time - to create unique email value each time
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // click register button
        driver.findElement(By.xpath("//a[@href='/register?returnUrl=%2F']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //click on gender
        driver.findElement(By.xpath("//input[@id='gender-female']")).click();

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("register-button"))));
        //type first name
        driver.findElement(By.id("FirstName")).sendKeys("Rabia");
        //type last time
        driver.findElement(By.id("LastName")).sendKeys("Irfan");

        // select date of birth
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByVisibleText("12");
        // select month of birth
        Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")));
        selectMonth.selectByIndex(1);
         // select year of birth
        Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        selectYear.selectByValue("1986");

          // type email
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("test" + timestamp.getTime() + "@test.com");
        System.out.println("testtest" + timestamp.getTime() + "abc@gmail.com");
        // type company with name
        driver.findElement(By.id("Company")).sendKeys("Home");
        // click on newsletter
        driver.findElement(By.id("Newsletter")).click();
        //type password
        driver.findElement(By.id("Password")).sendKeys("pass123");
        //type confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("pass123");
        // click register button
        driver.findElement(By.name("register-button")).click();
        String titleTextActual = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String expectedTitleText ="Your registration completed";
        System.out.println(titleTextActual);
        Assert.assertEquals(titleTextActual,expectedTitleText,"Registration successful");


    }
    @Test
    public void MacBook(){
        //timestamp for current time - to create unique email value each time
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         // type name of item
        driver.findElement(By.name("Apple MacBook Pro 13-inch")).click();
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("$1,800.00"))));



        String actualPrice = driver.findElement(By.xpath("//div[@data-productid='4']/div/div/div/span")).getText();
        String expectedPrice ="$2,000.00";

        Assert.assertEquals(actualPrice,expectedPrice);


    }
    @Test
    public void BuildYourOwnComputer (){
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //
        driver.findElement(By.linkText("Build your own computer")).click();
      //  type processor name

        Select selectProcessor = new Select(driver.findElement(By.name("Processor")));
        selectProcessor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        // type RAM
        Select selectRAM = new Select(driver.findElement(By.name("RAM")));
        selectRAM.selectByValue("2 GB");
         // type HDD
        Select selectHDD = new Select(driver.findElement(By.name("HDD")));
        selectHDD.selectByVisibleText("320 GB");
           //type OS
        Select selectOS = new Select(driver.findElement(By.name("OS")));
        selectOS.selectByVisibleText("Vista Home [+$50.00]");
             // type software
        Select selectSoftware = new Select(driver.findElement(By.name("Software")));
        selectSoftware.selectByVisibleText("Microsoft Office[+$50.00]");
             // type for select 'add to cart' button
        driver.findElement(By.name("ADD TO CART")).click();
        // select shopping cart option
        driver.findElement(By.name("Shopping cart")).click();

        String titleTextActual = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
        String expectedTitleText ="Shopping cart displayed";
        System.out.println(titleTextActual);
        Assert.assertEquals(titleTextActual,expectedTitleText);
    }
    @Test
    public void CompareProducts(){
        // type and select 'Apple MacBook'
        driver.findElement(By.xpath("//div[@class='details']//a[@href='/apple-macbook-pro-13-inch']")).click();
        // select 'add - compare-list button'
        driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[1]")).click();
           // type and select 'HTC One android Lollipop'
        driver.findElement(By.xpath("//div[@class='details']//a[@href='/htc-one-m8-android-l-50-lollipop']")).click();
        // select 'add - compare-list button'
        driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[1]")).click();
        // select product comparison button
         driver.findElement(By.xpath("//p[@class='content']/a[@href='/compareproducts']")).click();
         // type path
         driver.findElement(By.xpath("//a[@href@='#']")).click();
    }
    @AfterMethod
    public void closeBrowser(){
        // it will close all opened browser windows
        driver.quit();
    }
}
