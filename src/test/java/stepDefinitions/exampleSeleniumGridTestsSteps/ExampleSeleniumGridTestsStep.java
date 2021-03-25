package stepDefinitions.exampleSeleniumGridTestsSteps;

import exampleSeleniumGrid.lillyPOM.LillyHomePage;
import exampleSeleniumGrid.lillyPOM.LillyHomeProductsListsPage;
import exampleSeleniumGrid.lillyPOM.LillyLoginPage;
import exampleSeleniumGrid.mastePageManager.MasterManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ExampleSeleniumGridTestsStep {

    LillyHomePage lillyHomePage;
    LillyLoginPage lillyLoginPage;
    LillyHomeProductsListsPage lillyHomeProducts;


    //Chrome test for Lilly web shop - navigating to login page and log in


    @Given("open Home Page {string}")
    public void openHomePage(String url) {

        lillyHomePage = MasterManager.getMasterManager().lillyPageManager().lillyHomePage();
        lillyHomePage.remoteDriver(url, "chrome");

        String title = lillyHomePage.getRemotePageTitle();
        Assert.assertEquals("Лили Дрогерие онлайн магазин | Лили Дрогерие", title);
    }

    @When("click on Вход button")
    public void clickEnterButton() {
        lillyHomePage.clickRemoteLogin();
    }

    @Then("you are redirected to the login page of Lilly website")
    public void redirectedToLoginPageOfLillyWebsite() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String title = lillyHomePage.getRemotePageTitle();
        Assert.assertEquals("Вход | Лили Дрогерие", title);
        lillyHomePage.quitRemoteBrowser();
    }

    //Chrome test for Lilly web shop - user is logged in with correct credentials

    @Given("the user is on the {string} page")
    public void destinationLoginPage(String url) {
        lillyLoginPage = MasterManager.getMasterManager().lillyPageManager().lillyLoginPage();
        lillyLoginPage.remoteDriver(url,"chrome");
        String title = lillyLoginPage.getRemotePageTitle();
        Assert.assertEquals("Вход | Лили Дрогерие", title);
    }

    @When("user enters the correct {string} and {string}")
    public void CredentialsFilling(String username, String password) {
        lillyLoginPage.emailRemoteField(username);
        lillyLoginPage.passRemoteField(password);
    }

    @When("clicks on button Вход")
    public void clicksOnButtonEnter() {
        lillyLoginPage.clickRemoteLogin();
    }

    @Then("user is successfully logged in")
    public void checkingSuccessfullyLoggedIn() {
        String title = lillyLoginPage.getRemotePageTitle();
        Assert.assertEquals("Лили Дрогерие онлайн магазин | Лили Дрогерие", title);
        lillyLoginPage.quitRemoteBrowser();
    }


    //FireFox test for Lilly web shop - user goes to Home Products from the left menu

    @Given("the user is on the {string}")
    public void userIsOnTheURL(String url) {
        lillyHomeProducts = MasterManager.getMasterManager().lillyPageManager().lillyHomeProductsListsPage();
        lillyHomeProducts.remoteDriver(url, "firefox");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("the user is logged in with {string} and {string}")
    public void credentialsSending(String email, String pass) {

        LillyHomePage home = MasterManager.getMasterManager().lillyPageManager().lillyHomePage();
        LillyLoginPage lillyLogIn = MasterManager.getMasterManager().lillyPageManager().lillyLoginPage();
        home.clickRemoteCookies();
        home.clickRemoteLogin();
        lillyLogIn.createRemoteWait(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"login[username]\"]")));
        lillyLogIn.emailRemoteField(email);
        lillyLogIn.passRemoteField(pass);
        lillyLogIn.clickRemoteLogin();
    }

    @When("the user clicks Home products from the left had menu")
    public void userClicksHomeProductsFromMenu() {
        lillyHomeProducts.selectRemoteAllProductSubMenuElement("Продукти за дома");
    }

    @Then("the user is redirected to the shop list with the corresponding items")
    public void finalDestinationCheck() {
        String title = lillyHomeProducts.getRemotePageTitle();
        Assert.assertEquals("Продукти за дома | Лили Дрогерие", title);
        lillyHomeProducts.createRemoteWait(7000);
        lillyHomeProducts.quitRemoteBrowser();
    }


//Chrome test for Lilly web shop - Adding items to the shopping cart and opening the shopping cart


    @Given("user is in Home products {string}")
    public void userIsOnHomeProductsPage(String url) {
        lillyHomeProducts = MasterManager.getMasterManager().lillyPageManager().lillyHomeProductsListsPage();
        lillyHomeProducts.remoteDriver(url, "chrome");
        String title = lillyHomeProducts.getRemotePageTitle();
        Assert.assertEquals("Лили Дрогерие онлайн магазин | Лили Дрогерие", title);


    }

    @Given("add items to the basket")
    public void addItemsToTheBasket() {
        try {
            lillyHomeProducts.clickRemoteFirstItemOfList();
            Thread.sleep(7000);

            lillyHomeProducts.clickRemoteSecondItemOfList();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Given("the user clicks the basket")
    public void enterTheBasket() {
        lillyHomeProducts.openRemoteCart();
        lillyHomeProducts.clickRemoteCookies();
    }

    @When("the total price is calculated correctly for the items in the basket")
    public void priceCheck() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String price = lillyHomeProducts.getRemotePriceOfCart();
        Assert.assertEquals("56,68 лв.",price);
    }

    @Then("the user is redirected to the shopping cart")
    public void userIsRedirectedToTheShoppingCart() {
        String title = lillyHomeProducts.getRemotePageTitle();
        Assert.assertEquals("Вашата количка | Лили Дрогерие",title);
        lillyHomeProducts.quitRemoteBrowser();
    }

}
