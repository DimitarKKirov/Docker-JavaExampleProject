package exampleSeleniumGrid.lillyRegularsElements;


import exampleSeleniumGrid.seleniumGridRemoteDriver.RemoteDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class used for creating WebDrivers for the requested web browser
 * this driver class is used for local, remote testing and
 * uses the requested web browser exe file and
 * Maven library selenium-remote-driver
 */
public abstract class LillyRegularsElements extends RemoteDriver{

    /*
    instantiation of singleton page classes, inheritance of methods of:
    1:MainMenu method for selecting from all products menu
    2:Wait method for all inheriting classes
    3:searching and returning current page title
    4:close tab and close browser methods
    */


    /**
     * using the remote web driver
     * the method is instantiating a WebDriver wait class
     * that can be use in every
     * page object class of the current Emag test project if need
     * that extends this class
     */
    public WebDriverWait createRemoteWait(int timeOut) {
        return new WebDriverWait(seleniumRemoteDriver, timeOut);
    }

    /**
     * using the remote web driver
     * the method is locating and clicking
     * on to left hand menu category
     * that is set from passed String variable
     */
    public void selectRemoteAllProductSubMenuElement(String name) {
        seleniumRemoteDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement mainMenu = seleniumRemoteDriver.findElement(By.xpath("//div[@class=\"menu-block\"]"));
        mainMenu.click();
        List<WebElement> subMenu = seleniumRemoteDriver.findElements(By.xpath("//div[@class=\"menu-block\"]//li/a[@class=\"level-top ui-corner-all\"]"));
        for (int i = 0; i < subMenu.size(); i++) {

            WebElement subm = subMenu.get(i);
            String nameSu = subm.getAttribute("innerHTML");
            if (nameSu.contentEquals("<span class=\"ui-menu-icon ui-icon ui-icon-carat-1-e\"></span><span>" + name + "</span>")) {
                subm.click();
                break;
            }

        }

    }


    /**
     * using the remote web driver the method
     * is locating and returning a String of
     * the page title that the driver is currently on
     */
    public String getRemotePageTitle() {
        return seleniumRemoteDriver.getTitle();
    }


    /**
     * using the remote driver build in method
     * we are closing the connection to the container instance
     */
    public void closeRemoteBrowser() {
        seleniumRemoteDriver.close();
    }
    public void quitRemoteBrowser(){
        seleniumRemoteDriver.quit();
    }

    /**
     * Agrees to the cookies policy
     */
    public void clickRemoteCookies() {
        seleniumRemoteDriver.findElement(By.xpath("//a[@class=\"v-button v-accept\"]")).click();
    }
}
