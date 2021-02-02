package exampleDatabase.DAO;

import exampleDatabase.Helper.DbHelper;
import exampleDatabase.POJO.Items;
import exampleDatabase.POJO.ItemsDetails;
import exampleDatabase.POJO.ItemsLoadingDetails;
import exampleDatabase.driverAndConnection.Driver;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;

import java.util.Random;

@Getter
@Setter
public class Tables extends Driver implements DbHelper {

    private static Connection connection;
    private ResultSet set;
    private PreparedStatement prep;

    public void setName(String name) {
    }


    /**
     * Method add data takes the passed parameters as String values and is adding them to the database table
     * using a prepare statement and the connection setup from the Driver Class
     *
     * @param ItemQuantity     - ItemQuantity is a field in the database under test,
     *                         the parameter is used to pass the value for the field in Table ItemsDetails
     * @param ItemSellingPrice - ItemSellingPrice is a field in the database under test,
     *                         the parameter is used to pass the value for the field in Table ItemsDetails
     * @param ItemID           - ItemID filed contains the foreigner key and reference to the Items table.
     *                         the String number passed here must be existing entry ID in the Items table
     */
    @Override
    public void addData(String ItemQuantity, String ItemSellingPrice, String ItemID) {
        String addItem = "Insert ignore into ItemsDetails(ItemQuantity, ItemSellingPrice,ItemID) values " + "(?,?,?);";
        try {
            prep = connection.prepareStatement(addItem);
            prep.setString(1, ItemQuantity);
            prep.setString(2, ItemSellingPrice);
            prep.setString(2, ItemID);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method add data takes the passed parameters as Int values and is adding them to the database table
     * using a prepare statement and the connection setup from the Driver Class
     *
     * @param ItemPaidPricePerPiece - ItemPaidPricePerPiece is a field in the database under test,
     *                              the parameter is used to pass the value for the field in Table ItemsLoadingDetails
     * @param DetailsID             -DetailsID filed contains the foreigner key and reference to the ItemsDetails table.
     *                              the number passed here must be existing entry ID in the ItemsDetails table
     */
    @Override
    public void addData(int ItemPaidPricePerPiece, int DetailsID) {
        String addItem = "Insert ignore into ItemsLoadingDetails(ItemPaidPricePerPiece,DetailsID) values " + "(?,?);";
        try {
            prep = connection.prepareStatement(addItem);
            prep.setInt(1, ItemPaidPricePerPiece);
            prep.setInt(2, DetailsID);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method add data takes the passed parameters as Int values and is adding them to the database table
     * using a prepare statement and the connection setup from the Driver Class
     *
     * @param ItemSerialNumber - ItemSerialNumber is a field in the database under test,
     *                         the parameter is used to pass the value for the field in Table Items
     * @param nameItem         -nameItem filed contains the name of the item in the Items table.
     */
    @Override
    public void addData(String ItemSerialNumber, String nameItem) {
        String addItem = "Insert ignore into Items(ItemSerialNumber,ItemName) values (?,?)";

        try {
            prep = connection.prepareStatement(addItem);
            prep.setString(1, ItemSerialNumber);
            prep.setString(2, nameItem);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * method for extracting all of the data from a table of choice, by passing the name of the table
     * as a parameter the method is guided to the table of choice and extracts all of the data and
     * returns an ArrayList of the data for farther processing
     *
     * @param tableName - the passed argument that serves as a guide to the needed table
     * @return ArrayList of the fields of the table as Objects
     *
     * */
    @Override
    public ArrayList<Object> getAllData(String tableName) {
        String getData = "select * from " + tableName;
        ArrayList<Object> getAll = new ArrayList<>();
        Statement statement;
        if (tableName.equalsIgnoreCase("Items")) {
            try {
                statement = connection.createStatement();
                ResultSet set = statement.executeQuery(getData);
                while (set.next()) {
                    setName(set.getString("ItemID"));
                    for (int i = 0; i < 1; i++) {
                        Items temp = new Items();
                        temp.setItemID(set.getInt("ItemID"));
                        temp.setItemSerialNumber(set.getString("ItemSerialNumber"));
                        temp.setItemName(set.getString("ItemName"));
                        getAll.add(temp);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL query problem");
            }
        } else if (tableName.equalsIgnoreCase("ItemsDetails")) {
            try {
                statement = connection.createStatement();
                ResultSet set = statement.executeQuery(getData);
                while (set.next()) {
                    setName(set.getString("DetailsID"));
                    for (int i = 0; i < 1; i++) {
                        ItemsDetails temp = new ItemsDetails();
                        temp.setDetailsID(set.getString("DetailsID"));
                        temp.setItemID(set.getString("ItemID"));
                        temp.setItemQuantity(set.getString("ItemQuantity"));
                        temp.setItemSellingPrice(set.getString("ItemSellingPrice"));
                        getAll.add(temp);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL query problem");
            }
        } else if (tableName.equalsIgnoreCase("ItemsLoadingDetails")) {
            try {
                statement = connection.createStatement();
                ResultSet set = statement.executeQuery(getData);
                while (set.next()) {
                    setName(set.getString("ItemID"));
                    for (int i = 0; i < 1; i++) {
                        ItemsLoadingDetails temp = new ItemsLoadingDetails();
                        temp.setDetailsID(set.getString("DetailsID"));
                        temp.setItemPaidPricePerPiece(set.getString("ItemPaidPricePerPiece"));
                        temp.setLoadingDetailId(set.getString("LoadingDetailId"));
                        temp.setStoreLoadingDate(set.getString("StoreLoadingDate"));
                        getAll.add(temp);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL query problem");
            }
        }
        return getAll;
    }

    /**
     * method for retrieving data from a specific row and table of choice
     * @param tableName - passed parameter for the table that the user needs to extract data
     * @param rowNumber - the row of the selected table that needs to be extracted
     * */
    @Override
    public String getData(String tableName, String rowNumber) {
        String getData = "select from " + tableName + " , where itemID = " + rowNumber;

        try {
            set = connection.createStatement().executeQuery(getData);
            return set.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * method for extracting random field of given data table
     * @param tableName - by giving the name of the table the user points the sql query to it
     * @param idColumnName - by giving the Id column of the table the user is setting the column that
     *                     must mach the number of the ID of the field
     * */
    @Override
    public String getRandomData(String tableName, String idColumnName) {
        Random random = new Random(9);
        int rowNumber = random.nextInt();
        String result = "select from" + tableName + " , where " + idColumnName + " = " + rowNumber;
        try {
            set = connection.createStatement().executeQuery(result);
            return set.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * method for connecting to a database, the method is using method databaseConnection that resides in
     * Driver class, the databaseConnection method is made to connect to two different databases given tha
     * name of the database the method selects and reads the needed properties for the connection to be
     * established
     * @param name - by giving the name of the type of the database (Mysql, Postgres)
     *             the method connects to it.
     * */
    @Override
    public void connection(String name) {

        connection = databaseConnection(name);
    }

}
