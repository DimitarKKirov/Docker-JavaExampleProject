package exampleDatabase.DAO;

import exampleDatabase.Helper.DbHelper;
import exampleDatabase.POJO.Items;
import exampleDatabase.POJO.ItemsDetails;
import exampleDatabase.POJO.ItemsLoadingDetails;
import exampleDatabase.driverAndConnection.Driver;
import exampleDatabase.sqlQuery.SqlQuery;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;

import java.util.Random;

@Getter
@Setter
public class Tables extends Driver implements DbHelper, SqlQuery {

    private static Connection connection;
    private ResultSet set;
    private PreparedStatement prep;
    String name;

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for counting the fields in all of the database tables and
     * combining them in one final value INT that is returned from the method
     * for further use.
     */
    public int getRowCount() {
        ResultSet set1;
        ResultSet set2;
        ResultSet set3;
        int s = 0;
        int item = 0;
        int details = 0;
        int loading = 0;
        try {
            Statement statement = connection.createStatement();
            set1 = statement.executeQuery(COUNT_ITEMS);
            while (set1.next()) {
                int id = set1.getInt(1);
                item = +id;
            }
            set2 = statement.executeQuery(COUNT_DETAILS);
            while (set2.next()) {
                int id = set2.getInt(1);
                details = +id;
            }
            set3 = statement.executeQuery(COUNT_LOADING);
            while (set3.next()) {
                int id = set3.getInt(1);
                loading = +id;
            }
            s = item + details + loading;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Method add data takes the passed parameters as String values and is adding them to the database table
     * ItemsDetails using a prepare statement and the connection setup from the Driver Class
     * The sql queries are coming from interface sqlQueries in exampleDatabase.sqlQuery package.
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
        try {
            prep = connection.prepareStatement(ADD_IN_ITEMSDETAILS);
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
     * ItemsLoadingDetails using a prepare statement and the connection setup from the Driver Class
     * The sql queries are coming from interface sqlQueries in exampleDatabase.sqlQuery package.
     *
     * @param ItemPaidPricePerPiece - ItemPaidPricePerPiece is a field in the database under test,
     *                              the parameter is used to pass the value for the field in Table ItemsLoadingDetails
     * @param DetailsID             -DetailsID filed contains the foreigner key and reference to the ItemsDetails table.
     *                              the number passed here must be existing entry ID in the ItemsDetails table
     */
    @Override
    public void addData(int ItemPaidPricePerPiece, int DetailsID) {
        try {
            prep = connection.prepareStatement(ADD_IN_ITEMSLOADINGDETAILS);
            prep.setInt(1, ItemPaidPricePerPiece);
            prep.setInt(2, DetailsID);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method add data takes the passed parameters as Int values and is adding them to the database table
     * Items using a prepare statement and the connection setup from the Driver Class
     * The sql queries are coming from interface sqlQueries in exampleDatabase.sqlQuery package.
     *
     * @param id - ItemSerialNumber is a field in the database under test,
     *                         the parameter is used to pass the value for the field in Table Items
     * @param itemName         -nameItem filed contains the name of the item in the Items table.
     */
    @Override
    public void addData(String id, String itemName) {
        try {
            prep = connection.prepareStatement(ADD_IN_ITEMS);
            prep.setString(1, id);
            prep.setString(2, itemName);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * method for extracting all of the data from a table of choice, by passing the name of the table
     * as a parameter the method is guided to the table of choice and extracts all of the data and
     * returns an ArrayList of the data for farther processing
     * The sql queries are coming from interface sqlQueries in exampleDatabase.sqlQuery package.
     *
     * @param tableName - the passed argument that serves as a guide to the needed table
     * @return ArrayList of the fields of the table as Objects
     */
    @Override
    public ArrayList<Object> getAllData(String tableName) {
        ArrayList<Object> getAll = new ArrayList<>();
        Statement statement;
        if (tableName.equalsIgnoreCase("Items")) {
            try {
                statement = connection.createStatement();
                ResultSet set = statement.executeQuery(SELECT_ALL_ITEMS);
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
                ResultSet set = statement.executeQuery(SELECT_ALL_Details);
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
                ResultSet set = statement.executeQuery(SELECT_ALL_Loading);
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
     * Method for retrieving all data of connected database.
     * By using the connection to send Sql request the method retrieves the data from
     * the tre Tables and stores the data in a ArrayList for further use.
     * The sql queries are coming from interface sqlQueries in exampleDatabase.sqlQuery package.
     * @return ArrayList<Object> - method returns data from all table in array list with object type:
     * Items, ItemsDetails and ItemsLoadingDetails (classes can be found in package POJO)
     */
    @Override
    public ArrayList<Object> getAllData() {
        ArrayList<Object> getAll = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(SELECT_ALL_ITEMS);
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
            Statement statement2 = connection.createStatement();
            ResultSet set2 = statement2.executeQuery(SELECT_ALL_Details);
            while (set2.next()) {
                setName(set2.getString("DetailsID"));
                for (int i = 0; i < 1; i++) {
                    ItemsDetails temp = new ItemsDetails();
                    temp.setDetailsID(set2.getString("DetailsID"));
                    temp.setItemID(set2.getString("ItemID"));
                    temp.setItemQuantity(set2.getString("ItemQuantity"));
                    temp.setItemSellingPrice(set2.getString("ItemSellingPrice"));
                    getAll.add(temp);

                }
            }
            Statement statement3 = connection.createStatement();
            ResultSet set3 = statement3.executeQuery(SELECT_ALL_Loading);
            while (set3.next()) {
                setName(set3.getString("StoreLoadingDate"));
                for (int i = 0; i < 1; i++) {
                    ItemsLoadingDetails temp = new ItemsLoadingDetails();
                    temp.setDetailsID(set3.getString("DetailsID"));
                    temp.setItemPaidPricePerPiece(set3.getString("ItemPaidPricePerPiece"));
                    temp.setLoadingDetailId(set3.getString("LoadingDetailsId"));
                    temp.setStoreLoadingDate(set3.getString("StoreLoadingDate"));
                    getAll.add(temp);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL query problem");
        }
        return getAll;
    }

    /**
     * method for retrieving data from a specific row and table of choice
     *
     * @param tableName - passed parameter for the table that the user needs to extract data
     * @param rowNumber - the row of the selected table that needs to be extracted
     */
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
     *
     * @param tableName    - by giving the name of the table the user points the sql query to it
     * @param idColumnName - by giving the Id column of the table the user is setting the column that
     *                     must mach the number of the ID of the field
     */
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
     *
     * @param name - by giving the name of the type of the database (Mysql, Postgres)
     *             the method connects to it.
     */
    @Override
    public void connection(String name) {

        connection = databaseConnection(name);
    }

}
