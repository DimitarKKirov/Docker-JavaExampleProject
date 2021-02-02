package exampleDatabase.configFiles;
import java.io.File;

public interface Paths {
    /**
     *  mysqlConnection - path to the connection properties for Mysql Database
     *  postgresConnection1 - path to the connection properties for Postgres Database
     */
    File mysqlConnection1 = new File("./src/test/java/exampleDatabase/configFiles/MysqlConnection.properties");
    String mysqlConnection = mysqlConnection1.getAbsolutePath();

    File postgresConnection1 = new File("./src/test/java/exampleDatabase/configFiles/PostgresConnection.properties");
    String postgresConnection = postgresConnection1.getAbsolutePath();
}
