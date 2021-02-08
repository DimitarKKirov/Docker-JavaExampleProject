package stepDefinitions.exampleDBOperationsTestsSteps;


import exampleDatabase.DAO.Tables;
import exampleDatabase.POJO.Items;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



import java.util.ArrayList;

public class ExampleDBOperationsTestsSteps {
    private Tables da;
    private ArrayList<Object> data;
    private String id1;


    @Given("the user inserts data in Items table rows {string}, {string}")
    public void theUserInsertsDataInTableRows(String id, String itemName) {

        da = new Tables();
        da.connection("mysql");
        da.addData(id, itemName);
        id1 = id;
    }

    @When("the user retrieves data")
    public void theUserRetrievesData() {
        data = da.getAllData("Items");


    }

    @Then("the data is the same")
    public void theDataIsAsserted() {
        Items item = data.get(10) != null ? (Items) data.get(10) : null;
        assert item != null;
        String check = item.itemSerialNumber();
        Assert.assertEquals(id1,check);
        String a = data.get(10).toString();
        System.out.println(a);
    }

}
