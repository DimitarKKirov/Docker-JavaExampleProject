package exampleDatabase.driverAndConnection;

import exampleDatabase.configFiles.Paths;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Driver implements Paths {

    /**
     * method for connecting to database, currently is able to connect to two databases, Mysql and Postgres,
     * by passing the name of the database connection the method searches and reads the needed properties files
     * with the jdbc url, name and password that are hardcoded in *Connection.properties in configFile folder
     *
     * @param name - by passing either Mysql or Postgres as a string to the method,the method tries to
     *             establish connection with the database that corresponds to the name by reading the
     *             parameters in the properties file of the needed database, those properties files
     *             are set up to connect to the databases that are instantiated in docker from the
     *             Dockerfiles in package dockerHelper, further more for demonstration purposes
     *             the two databases are with established database and filled tables, they can be changed form
     *             the sql file in earlier mentioned packages
     */
    public Connection databaseConnection(String name) {
        Properties properties = new Properties();
        Connection dBConnection = null;
        if (name.equalsIgnoreCase("Mysql")) {

            try {
                properties.load(new FileInputStream(mysqlConnection));
                String user = properties.getProperty("username");
                String pass = properties.getProperty("password");
                String url = properties.getProperty("Url");

                dBConnection = DriverManager.getConnection(url, user, pass);
                return dBConnection;
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else if (name.equalsIgnoreCase("postgres")) {
            try {
                properties.load(new FileInputStream(postgresConnection));
                String user = properties.getProperty("username");
                String pass = properties.getProperty("password");
                String url = properties.getProperty("Url");

                dBConnection = DriverManager.getConnection(url, user, pass);
                return dBConnection;
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return dBConnection;
    }
}
