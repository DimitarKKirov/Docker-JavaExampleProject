package exampleSelenium.steps;


import exampleSelenium.emagPOM.EmagHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class ExampleSeleniumEmagSteps {
    private EmagHomePage emag = new EmagHomePage();

    @Given("the user is on {string}")
    public void the_user_is_on(String Url) {

        emag.remoteDriver(Url, "chrome");
        emag.acceptCokies();

    }

    @When("tha user searches for {string}")
    public void tha_user_searches_for(String name) {
        emag.enterSearchCriteria(name);
        emag.clickSearchEmag();
    }

    @Then("the user finds relevant result equal to {string}")
    public void the_user_finds_relevant_result_equal_to(String result) {
        String actualResult = emag.remoteSearchResult();

        Assert.assertEquals(result, actualResult);
    }
}
