package stepDefinitions.hooks;

import dockerHelper.DockerEnvHelperClass;
import dockerHelper.filePathsInterface.DockerFilePaths;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

/**
 *
 * the methods are downloading images, creating containers and starting them in Docker in order
 * to for project tests to use them as test environment using @Before and @After hooks for easy maintenance
 *
 * All of the method that are used are described in DockerEnvHelperClass.class in package test.java.dockerHelper
 *
 * */
public class Hooks implements DockerFilePaths {
    private static final DockerEnvHelperClass dockerHelp = new DockerEnvHelperClass();
    private final long sec = 120;

    @Before("@Selenium")
    public void envSetUpSelenium() {
        dockerHelp.connect();
        dockerHelp.downloadImage("selenium/standalone-chrome-debug", "latest", sec);
        dockerHelp.createContainer("selenium/standalone-chrome-debug", "chrome", "4445:4444", "4446:5900");
        dockerHelp.startContainer("chrome");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After("@Selenium")
    public void rmEnvSelenium() {
        dockerHelp.stopContainer("chrome");
        dockerHelp.removeContainer("chrome");
        try {
            dockerHelp.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Before("@Rest")
    public void envSetUpRest() {

        dockerHelp.connect();
        dockerHelp.downloadImage("dimtiar/docke-java-restapi", "latest", sec);
        dockerHelp.createContainer("dimtiar/docke-java-restapi", "rest", "8083:8080");
        dockerHelp.startContainer("rest");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After("@Rest")
    public void rmEnvRest() {
        dockerHelp.stopContainer("rest");
        dockerHelp.removeContainer("rest");
        try {
            dockerHelp.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Before("@DB")
    public void envSetUpDB() {
        dockerHelp.connect();
        dockerHelp.downloadImageFromDockerfile(mysql, "myslq");
        dockerHelp.deleteImage("mysql/mysql-server");
        dockerHelp.createContainer("myslq", "mydb", "8080:3306");
        dockerHelp.startContainer("mydb");
        dockerHelp.downloadImageFromDockerfile(postgres, "mypost");
        dockerHelp.deleteImage("postgres");
        dockerHelp.createContainer("mypost", "mypost", "8081:5432");
        dockerHelp.startContainer("mypost");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After("@DB")
    public void rmEnvDB() {
        dockerHelp.stopContainer("mydb");
        dockerHelp.removeContainer("mydb");
        dockerHelp.stopContainer("mypost");
        dockerHelp.removeContainer("mypost");
        try {
            dockerHelp.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
