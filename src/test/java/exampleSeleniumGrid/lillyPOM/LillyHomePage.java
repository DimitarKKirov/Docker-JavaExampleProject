package exampleSeleniumGrid.lillyPOM;


import org.openqa.selenium.By;
import exampleSeleniumGrid.lillyRegularsElements.LillyRegularsElements;

/**
 * this class represents the Home page of Lilly web shop site
 * class with predefined web elements with actions
 * and method set for the current test purposes
 */
public class LillyHomePage extends LillyRegularsElements {

    /**
     * this method finds and clicks the login button
     * that is located on the home page
     * using the remote driver that is set to connect and
     * use the docker standalone chrome/firefox container
     * class that uses the Maven dependency
     * selenium-remote-driver
     */
    public void clickRemoteLogin() {
        seleniumRemoteDriver.findElement(By.xpath("//a[@href=\"https://shop.lillydrogerie.bg/customer/account/login/referer/aHR0cHM6Ly9zaG9wLmxpbGx5ZHJvZ2VyaWUuYmcv/\"]")).click();

    }


}


