package exampleDatabase.POJO;


import lombok.Data;


/**
 * class representing the database Item Table
 * for filling the data in the table
 */
@Data

public class Items {


    private String itemSerialNumber;
    private String itemName;
    private int itemID;

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemSerialNumber(String itemsSerialNumber) {
        this.itemSerialNumber = itemsSerialNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Items() {
    }

    public String toString() {
        return
                "\n itemID: " + itemID +
                        ",\n itemName: " + itemName +
                        ",\n itemSerialNumber: " + itemSerialNumber + "\n"
                ;
    }

    public String itemSerialNumber() {
        return
                itemSerialNumber;

    }
}
