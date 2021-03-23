package stepDefinitions.exampleDBOperationsTestsSteps;

import exampleDatabase.DAO.Tables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;

public class ExampleDBComparisonSteps {
    private Tables tables = new Tables();
    private ArrayList<Object> po;
    private ArrayList<Object> my;
    private int i;
    private int p;

    @Given("the two databases are not empty")
    public void theMigrationCountCheck() {
        tables.connection("mysql");
        i = tables.getRowCount();
        tables.connection("postgres");
        p = tables.getRowCount();
        Assert.assertTrue(i > 0);
        Assert.assertTrue(p > 0);
    }

    @When("the data of the two databases is compared")
    public void dataComparison() {
        int a=0;
        Assert.assertEquals(i, p);
        my = tables.getAllData();
        Assert.assertFalse(my.isEmpty());
        tables.connection("postgres");
        po = tables.getAllData();
        Assert.assertFalse(po.isEmpty());
        while (a<30) {
            po.get(a).toString();
        a++;
        }
    }

    @Then("the two data bases are holding the same data")
    public void EqualsCheck() {
        Assert.assertEquals(my, po);
    }

}
