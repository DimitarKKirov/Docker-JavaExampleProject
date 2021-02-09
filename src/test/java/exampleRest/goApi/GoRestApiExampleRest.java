package exampleRest.goApi;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

/**
 * this class is set up to test one end point of instantiated rest api container in Docker,
 * to show how easy is to use the docker containers for testing purposes
 */
public class GoRestApiExampleRest {

    private RequestSpecification request = RestAssured.given();
    private Response response;
    private String body;

    /**
     * RestAssured method for checking the response code for an end point
     *
     * @param url - by providing the method with the endpoint url,
     *            the method is able to assert the response time
     */
    public void checkAccess(String url) {
        response = request.request(Method.GET, url);
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Response status is: " + response.getStatusCode());

    }

    /**
     * method for retrieving the msg endpoint
     *
     * @param endpoint - passing the url endpoint that contains the msg
     */
    public void getMsg(String endpoint) {
        RestAssured.baseURI = endpoint;
        response = request.request(Method.GET, endpoint);
        body = response.getBody().asString();

    }

    /**
     * method that prints the retrieved msg from getMsg method
     */
    public void printBody() {
        System.out.println(body);
    }
}
