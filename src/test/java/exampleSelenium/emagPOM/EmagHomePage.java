package exampleSelenium.emagPOM;

import exampleSelenium.seleniumRemoteDriver.RemoteDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class EmagHomePage extends RemoteDriver {


/**
 * method acceptCokies is for finding all elements with the given xpath, selects the third option and
 * click on int in order to accept the cookies on the home page of Emag
 * */
    public void acceptCokies() {
        Actions actions = new Actions(seleniumRemoteDriver);
        List<WebElement> buttons = seleniumRemoteDriver.findElements(By.xpath("//div[@class=\"ZFr60d CeoRYc\"]"));
        for (int i = 0; i < buttons.size(); i++) {
            WebElement but= buttons.get(i);
            if (i == 3) {
                actions.moveToElement(but).build().perform();
                but.click();
            }
        }


    }

    /**
     * method enterSearchCriteria is for searching in emag shop fora a given item
     * @param searchText - this string parameter is passed as a search criteria for the
     *                   search function in the web shop under test
     *                   by finding the filed and clicking on in selenium remote web driver
     *                   is passing the string parameter
     *
     * */
    public void enterSearchCriteria(String searchText) {
        Actions actions = new Actions(seleniumRemoteDriver);
        WebElement searchField = seleniumRemoteDriver.findElement(By.xpath("//input[@id=\"searchboxTrigger\"]"));
        actions.moveToElement(searchField).build().perform();
        searchField.click();
        searchField.sendKeys(searchText);

    }

    /**
     *
     * method clickSearchEmag is for finding and clicking the search button on the web shop,
     * selenium remote web driver is finding the element, moving to it and clicking in
     * so the search function of the website can be invoked
     *
     * */
    public void clickSearchEmag() {
        Actions actions = new Actions(seleniumRemoteDriver);
        WebElement searchButton = seleniumRemoteDriver.findElement(By.xpath("//i[@class=\"em em-search\"]"));
        actions.moveToElement(searchButton).build().perform();
        searchButton.click();
    }

    /**
     * method remoteSearchResult is returning the search result msg from the website after the search is done
     * @return String - is the msg that is available after the search is done showing how many results are found
     * */
        public String remoteSearchResult() {
            return seleniumRemoteDriver.findElement(By.xpath("//span[@class=\"title-phrasing title-phrasing-sm\"]")).getText();

        }

}
