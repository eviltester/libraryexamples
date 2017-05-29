package uk.co.compendiumdev.libraryexamples.webdriver;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class SwapiGUIFormUsageTest {

    /*
        This exmaple uses WebDriver from http://www.seleniumhq.org/
        see also http://SeleniumSimplified.com
     */

    @Test
    public void canGetSwapiGUIPage(){

        WebDriver driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED, true);

        driver.get("https://swapi.co/");

        Assert.assertTrue(driver.getTitle().contains("Star Wars"));

        driver.quit();

    }



    @Test
    public void usePersonApiGUIForLuke(){

        WebDriver driver;

        // this does actually work on HtmlUnitDriver
        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        ((HtmlUnitDriver)driver).setJavascriptEnabled(true);

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

        // this does actually work on HtmlUnitDriver
        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        ((HtmlUnitDriver)driver).setJavascriptEnabled(true);

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

        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        ((HtmlUnitDriver)driver).setJavascriptEnabled(true);

        // driver = new ChromeDriver();

        driver.get("https://swapi.co/");

        WebElement inputfield = driver.findElement(By.id("interactive"));

        inputfield.sendKeys("people/1/");

        driver.findElement(By.className("btn-primary")).click();

        WebElement output = driver.findElement(By.id("interactive_output"));
        String json = output.getText();

        Assert.assertTrue(json.contains("Luke Skywalker"));

        driver.quit();

    }


    /* Exercise search for C-3PO instead of Luke Skywalker:
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

        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        ((HtmlUnitDriver)driver).setJavascriptEnabled(true);

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
