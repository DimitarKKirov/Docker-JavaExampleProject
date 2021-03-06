package exampleDatabase.Helper;

import java.util.ArrayList;

/**
 * interface guide for methods that need to be implemented for the Database manipulations
 * all of the methods are explained in the Tables.Class
 */
public interface DbHelper {



    void addData(String data, String nameItem);

    void addData(String ItemQuantity, String ItemSellingPrice, String ItemID);

    void addData(int ItemPaidPricePerPiece, int DetailsID);

    ArrayList<Object> getAllData(String tableName);

    ArrayList<Object> getAllData();

    String getData(String tableName, String rowNumber);

    String getRandomData(String tableName, String idColumnName);

    void connection(String name);


}
