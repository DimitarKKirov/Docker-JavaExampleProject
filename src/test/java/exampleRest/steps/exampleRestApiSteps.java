package exampleRest.steps;


import exampleRest.goApi.GoRestApiExampleRest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




public class exampleRestApiSteps {
    private GoRestApiExampleRest go = new GoRestApiExampleRest();


    @Given("user connects to api using url {string}")
    public void user_connects_to_api(String url) {

        go.checkAccess(url);

    }

    @When("request for end {string}")
    public void request_for_response_body_is_made(String endpoint) {
        go.getMsg(endpoint);
    }

    @Then("the response body is available")
    public void the_response_body_is_available() {
        go.printBody();
    }
}
