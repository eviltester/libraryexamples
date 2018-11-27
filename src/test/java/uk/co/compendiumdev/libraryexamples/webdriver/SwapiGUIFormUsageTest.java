package uk.co.compendiumdev.libraryexamples.webdriver;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Ignore("You need to amend for your setup and operating system to use this e.g. did you install a ChromeDriver? Are you on Mac for SafariDriver?")
public class SwapiGUIFormUsageTest {

    /*
        This example uses WebDriver from http://www.seleniumhq.org/
        see also http://SeleniumSimplified.com

        // mac:
        // brew install chromedriver

        // mac: to use SafariDriver remember to configure safari to allow remote execution - follow instructions in the error message if you have not

        // windows:
        // choco install chromedriver

     */

    private WebDriver getDefaultDriver(){

        WebDriver driver;

        // comment out the lines you want to get the driver you need

        //driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED, true);
        //((HtmlUnitDriver)driver).setJavascriptEnabled(true);

        //driver = new ChromeDriver();

        driver = new SafariDriver();

        return driver;
    }

    @Test
    public void canGetSwapiGUIPage(){

        WebDriver driver;
        driver = getDefaultDriver();

        //driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED, true);
        //driver = new ChromeDriver();

        // On mac SafariDriver is built in
        //driver = new SafariDriver();

        driver.get("https://swapi.co/");

        Assert.assertTrue(driver.getTitle().contains("Star Wars"));

        driver.quit();

    }



    @Test
    public void usePersonApiGUIForLuke(){

        WebDriver driver;

        driver = getDefaultDriver();

        // this does actually work on HtmlUnitDriver
        //driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        //((HtmlUnitDriver)driver).setJavascriptEnabled(true);
        //driver = new SafariDriver();
        //driver = new ChromeDriver();

        driver.get("http://swapi.co/api/people/1/?format=api");

        WebElement response = driver.findElement(By.cssSelector("div.response-info > pre"));

        String json = response.getText();

        System.out.println(json);
        Assert.assertTrue(json.contains("Luke Skywalker"));

        driver.quit();

    }

    @Test
    public void usePersonApiGUIForC3PO(){

        WebDriver driver;

        driver = getDefaultDriver();

        // this does actually work on HtmlUnitDriver
        //driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        //((HtmlUnitDriver)driver).setJavascriptEnabled(true);
        //driver = new SafariDriver();

        driver.get("http://swapi.co/api/people/2/?format=api");

        WebElement response = driver.findElement(By.cssSelector("div.response-info > pre"));

        String json = response.getText();

        System.out.println(json);
        Assert.assertTrue(json.contains("C-3PO"));

        driver.quit();

    }



    @Test
    public void canSubmitFormFromGUIPage(){

        // this doesn't actually work on HtmlUnitDriver since the JavaScript is not interpreted properly
        WebDriver driver;
        driver = getDefaultDriver();

        //driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        //((HtmlUnitDriver)driver).setJavascriptEnabled(true);

        //driver = new SafariDriver();
        //driver = new ChromeDriver();

        driver.get("https://swapi.co/");

        WebElement inputfield = driver.findElement(By.id("interactive"));

        inputfield.sendKeys("people/2/");

        driver.findElement(By.className("btn-primary")).click();

        new WebDriverWait(driver, 10).
                until(
                        ExpectedConditions.
                                textToBePresentInElementLocated(By.id("interactive_output"), "C-3PO"));

        WebElement output = driver.findElement(By.id("interactive_output"));
        String json = output.getText();

        Assert.assertTrue(json.contains("C-3PO"));

        driver.quit();

    }


    /* Exercise search for C-3PO instead of Luke Skywalker:
        If you are on Mac then you can uncomment out the SafariDriver lines and run the tests in Safari
           - you don't need to download ChromeDriver - although you can if you want to

        On windows you will need to download a Driver - currently recommend ChromeDriver

         Download ChromeDriver from  https://sites.google.com/a/chromium.org/chromedriver/
         And add the chromedriver executable to your path
         comment out the usage of HtmlUnitDriver and enable use of ChromeDriver
         Search for "people/2/"
         Assert on the name "C-3PO"
     */


    @Test
    public void canClickSearchLink(){

        // this doesn't actually work on HtmlUnitDriver since the JavaScript is not interpreted properly
        WebDriver driver;
        driver = getDefaultDriver();

        //driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        //((HtmlUnitDriver)driver).setJavascriptEnabled(true);
        //driver = new SafariDriver();
        //driver = new ChromeDriver();

        driver.get("https://swapi.co/");

        List<WebElement> links = driver.findElements(By.cssSelector("small > a"));

        links.get(0).click();

        WebElement output = driver.findElement(By.id("interactive_output"));
        String json = output.getText();

        System.out.println(json);
        Assert.assertTrue(json.contains("Luke Skywalker"));

        driver.quit();

    }

    /* Exercise search for C-3PO instead of Luke Skywalker:
         Download ChromeDriver from  https://sites.google.com/a/chromium.org/chromedriver/
         And add the chromedriver executable to your path
         comment out the usage of HtmlUnitDriver and enable use of ChromeDriver
         click on the second link (1)
         Assert on the name "Yavin IV"
     */

    /* for a real 'hack'

       create a SwapiApi implementation that uses WebDriver and parses the Json from the GUI

       - ugh it is horrible but you will learn how to parse Strings
       - and there will a come a time when you have to automate like this
     */

}
