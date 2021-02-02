package runner;

import dockerHelper.DockerEnvHelperClass;
import dockerHelper.DockerFilePaths;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/features"},
        tags = "@Show",
        plugin = {"pretty","html:target/cucumber-hmtl-report","json:target/cucumber.json"},
        glue = {"exampleSelenium.steps","exampleDatabase.steps","exampleRest.steps"},
        dryRun=false
)

public class Runner implements DockerFilePaths {

    private static DockerEnvHelperClass dockerHelp = new DockerEnvHelperClass();

/**
 * the method is downloading images, creating containers and starting them in Docker in order
 * to for project tests to use them as test environment
 *
 * All of the method that are used are described in DockerEnvHelperClass.class in package test.java.dockerHelper
 *
 * */
    @BeforeClass
    public static void createEnv() {
        long sec = 120;
        dockerHelp.connect();
        dockerHelp.downloadImage("selenium/standalone-chrome-debug", "latest", sec);
        dockerHelp.createContainer("selenium/standalone-chrome-debug", "chrome", "4445:4444", "4446:5900");
        dockerHelp.startContainer("chrome");
        dockerHelp.downloadImage("dimtiar/docke-java-restapi", "latest", sec);
        dockerHelp.createContainer("dimtiar/docke-java-restapi", "rest", "8083:8080");
        dockerHelp.startContainer("rest");
        dockerHelp.downloadImageFromDockerfile(mysql, "myslq");
        dockerHelp.deleteImage("mysql/mysql-server");
        dockerHelp.createContainer("myslq", "mydb", "8080:3306");
        dockerHelp.startContainer("mydb");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

@AfterClass
public static void rmEnv() {
    dockerHelp.stopContainer("rest");
    dockerHelp.removeContainer("rest");
    dockerHelp.stopContainer("mydb");
    dockerHelp.removeContainer("mydb");
    dockerHelp.stopContainer("chrome");
    dockerHelp.removeContainer("chrome");
    try {
        dockerHelp.closeConnection();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
