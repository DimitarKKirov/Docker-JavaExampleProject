package exampleDatabase.steps;


import dockerHelper.DockerFilePaths;
import exampleDatabase.DAO.Tables;
import exampleDatabase.POJO.Items;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



import java.util.ArrayList;

public class exampleDBOperationsTestsSteps implements DockerFilePaths {
    private Tables da;
    private ArrayList<Object> data;
    private String id1;


    @Given("the user inserts data in Items table rows {string}, {string}")
    public void the_user_inserts_data_in_table_rows_56874236item12(String id, String itemName) {

        da = new Tables();
        da.connection("mysql");
        da.addData(id, itemName);
        id1 = id;
    }

    @When("the user retrieves data")
    public void the_user_retrieves_data() {
        data = da.getAllData("Items");


    }

    @Then("the data is the same")
    public void the_data_is_the_same() {
        Items item = data.get(10) != null ? (Items) data.get(10) : null;
        assert item != null;
        String check = item.itemSerialNumber();
        Assert.assertEquals(id1,check);
        String a = data.get(10).toString();
        System.out.println(a);
    }

}
