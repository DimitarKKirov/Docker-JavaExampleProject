package exampleRest.steps;


import exampleRest.goApi.GoRestApiExampleRest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




public class exampleRestApiSteps {
    private GoRestApiExampleRest go = new GoRestApiExampleRest();


    @Given("user connects to api using url {string}")
    public void userConnectsTo(String url) {

        go.checkAccess(url);

    }

    @When("request for end {string}")
    public void requestForResponseBody(String endpoint) {
        go.getMsg(endpoint);
    }

    @Then("the response body is available")
    public void printingTheResponseBody() {
        go.printBody();
    }
}
