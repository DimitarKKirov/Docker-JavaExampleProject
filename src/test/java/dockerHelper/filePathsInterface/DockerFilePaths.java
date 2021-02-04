package dockerHelper.filePathsInterface;

import java.io.File;

public interface DockerFilePaths {
    File mysql = new File("./src/test/java/dockerHelper/exampleMySqlDockerfileAndSQLFile/Dockerfile");
    File postgres = new File("./src/test/java/dockerHelper/examplePostgresDockerfileAndSQLFile/Dockerfile");


}
