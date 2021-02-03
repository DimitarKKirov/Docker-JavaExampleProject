package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/features"},
        tags = "@Show",
        plugin = {"pretty","html:target/cucumber-hmtl-report","json:target/cucumber.json"},
        glue = {"stepDefinitions"},
        dryRun=false
)

public class Runner {


}
