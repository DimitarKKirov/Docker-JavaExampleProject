package stepDefinitions.stepsExampleSeleniumEmagSteps;


import exampleSelenium.emagPOM.EmagHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class ExampleSeleniumEmagSteps {
    private EmagHomePage emag = new EmagHomePage();

    @Given("the user is on {string}")
    public void theUserIsOn(String Url) {

        emag.remoteDriver(Url, "chrome");
        emag.acceptCokies();

    }

    @When("tha user searches for {string}")
    public void thaUserSearchesFor(String nameOfItem) {
        emag.enterSearchCriteria(nameOfItem);
        emag.clickSearchEmag();
    }

    @Then("the user finds relevant result equal to {string}")
    public void theUserFindsRelevantResultEqualTo(String expectedresult) {
        String actualResult = emag.remoteSearchResult();

        Assert.assertEquals(expectedresult, actualResult);
    }
}
