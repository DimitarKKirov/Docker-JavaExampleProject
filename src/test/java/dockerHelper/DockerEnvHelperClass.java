package dockerHelper;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallbackTemplate;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


/**
 * This class is used for setting the needed docker environment with images and containers
 * This class uses Docker-Java library to achieve its goals
 * Docker-Java is used in order operations to be made in Docker
 * without the usage of Docker CLI (writing commands in Windows command prompt)
 * base class for Docker connection and command execution
 * https://docs.docker.com/engine/reference/commandline/dockerd/
 * More info for the Docker-Java library - https://www.baeldung.com/docker-java-api
 * https://github.com/docker-java/docker-java
 * <p>
 * PARAMETERS FOR NAMES MUST ALL BE IN LOWERCASE, OTHERWISE DOCKER_JAVA WILL THROW AN ERROR
 */
public class DockerEnvHelperClass {

    private DockerClient dockerClient;
    private HostConfig hostConfig;

    /**
     * creating connection to docker daemon locally,by changing the host you can change the docker daemon client
     * In order this to happen the port of the DAEMON must be exposed, for Windows Docker there is option in
     * Settings->General Tab->Expose daemon on tcp://localhost:2375 without TLS : this is ok for local use
     * not recommended for remote, if possible Expose the port with TLS
     */
    public void connect() {
        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://localhost:2375").build();
        dockerClient = DockerClientBuilder.getInstance(standard).build();


    }

    /**
     * method for docker client builder to close the connection
     *
     * @throws IOException for example if there is no established connection with the docker daemon
     */

    public void closeConnection() throws IOException {
        dockerClient.close();
        System.out.println("connection to docker is closed");
    }

    /**
     * if there is a container we can remove it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will remove it if found any
     *             with that name
     */
    public void removeContainer(String name) {
        dockerClient.removeContainerCmd(name).exec();
        System.out.println("container " + name + " is removed (deleted)");
    }


    /**
     * if there is a container we can stop it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will stop it if found any
     *             with that name
     */
    public void stopContainer(String name) {
        dockerClient.stopContainerCmd(name).exec();
        System.out.println("container " + name + " is stopped");
    }


    /**
     * if there is a container we can start it by sending the command with the name of the container
     *
     * @param name by stating the name of the container the method will start it if found any
     *             with that name
     */
    public void startContainer(String name) {
        dockerClient.startContainerCmd(name).exec();
        System.out.println("container " + name + " is started");

    }

    /**
     * for some reason when the image is created from a docker file without creating it with a custom name
     * the environment variables from the dockerfile are not applied,when downloadImageFromDockerfile with name param method
     * is used Docker downloads the base image set in the dockerfile and from that creates addition image with
     * the passed name variable, this results in Docker having two images and one of which is not used, in order to escape the additional
     * storage memory from the base image is advised to use delete image method and remove the unnecessary
     * base image
     *
     * @param name user passes the name of the image to be deleted
     */
    public void deleteImage(String name) {
        dockerClient.removeImageCmd(name).exec();
    }

    /**
     * method for downloading images from Docker repository
     *
     * @param name           This parameter must be da actual name of the image in Docker repository for example
     *                       if the name is set to mysql docker will download the mysql database
     * @param tag            Tag is equal to the application version that the user want to download from the repository,
     *                       if the tag is ste to latest the "latest" version of the application will be downloaded
     * @param timeoutSeconds tis variable sets how much time docker-java will waite for the image to be downloaded,
     *                       this method timeout is set in seconds
     */
    public void downloadImage(String name, String tag, Long timeoutSeconds) {
        try {
            dockerClient.pullImageCmd(name)
                    .withTag(tag)
                    .start()
                    .awaitCompletion(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * method for downloading images from Docker repository
     *
     * @param name           This parameter must be da actual name of the image in Docker repository for example
     *                       if the name is set to mysql docker will download the mysql database
     * @param tag            Tag is equal to the application version that the user want to download from the repository,
     *                       if the tag is ste to latest the "latest" version of the application will be downloaded
     * @param timeoutMinutes tis variable sets how much time docker-java will waite for the image to be downloaded,
     *                       this method timeout is set in minutes
     */
    public void downloadImageMinutes(String name, String tag, Long timeoutMinutes) {
        try {
            dockerClient.pullImageCmd(name)
                    .withTag(tag)
                    .start()
                    .awaitCompletion(timeoutMinutes, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * method for downloading images from Docker repository
     *
     * @param dockerfile This parameter must show the Filepath to the actual dockerfile in order for docker-java to be able
     *                   to pass it to the Docker Daemon
     */
    public void downloadImageFromDockerfile(File dockerfile) {
        try {
            dockerClient.buildImageCmd()
                    .withDockerfile(dockerfile)
                    .start().awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * method for downloading images from Docker repository
     *
     * @param dockerfile      This parameter must show the Filepath to the actual dockerfile in order for docker-java to be able
     *                        to pass it to the Docker Daemon
     * @param customImageName if the user wants he/she can set there own name for the image residing in Docker
     */

    public void downloadImageFromDockerfile(File dockerfile, String customImageName) {
        try {
            dockerClient.buildImageCmd()
                    .withDockerfile(dockerfile)
                    .withTags(Collections.singleton(customImageName))
                    .start().awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * method for instantiating a container form image
     *
     * @param nameOfImage     the name of the image that the container will be instantiated
     * @param nameOfContainer the name of the container that the user is creating
     * @param ports           the ports that be binding with the exposed port of the container
     */
    public void createContainer(String nameOfImage, String nameOfContainer, String ports) {

        hostConfig = new HostConfig().withPortBindings(PortBinding.parse(ports));
        dockerClient.createContainerCmd(nameOfImage)
                .withName(nameOfContainer)
                .withHostConfig(hostConfig)
                .exec();
    }

    /**
     * method for instantiating a container form image
     *
     * @param nameOfImage     the name of the image that the container will be instantiated
     * @param nameOfContainer the name of the container that the user is creating
     * @param ports           the ports that be binding with the exposed port of the container
     * @param secondPort      some applications like tha selenium stand alone chrome have more exposed ports,
     *                        one for the actual selenium remote driver to connect and one for VNC for debug purposes
     *                        of course the method can be overloaded with more ports
     */
    public void createContainer(String nameOfImage, String nameOfContainer, String ports, String secondPort) {
        PortBinding one = PortBinding.parse(ports);
        PortBinding two = PortBinding.parse(secondPort);
        hostConfig = new HostConfig().withPortBindings(one, two);
        dockerClient.createContainerCmd(nameOfImage)
                .withName(nameOfContainer)
                .withHostConfig(hostConfig)
                .exec();
    }
    /**
     * sending commands to Command Prompt
     *
     * @param commandSequence A String that represents the command that needs to be send to the console
     * @throws IOException
     */
    public void sendCommand(String commandSequence) throws IOException {
        Process process = Runtime.getRuntime().exec(commandSequence);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}
