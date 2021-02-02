package exampleDatabase.POJO;

import lombok.Data;

/**
 * class representing the database ItemsDetails Table
 * for filling the data in the table
 */

@Data
public class ItemsDetails {


    private String detailsID;
    private String itemQuantity;
    private String itemSellingPrice;
    private String itemID;

    public void setDetailsID(String detailsID) {
        this.detailsID = detailsID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemSellingPrice(String itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }

    public ItemsDetails() {

    }

    public String toString() {
        return
                "\n detailsID: " + detailsID +
                        "\n itemID: " + itemID +
                        ",\n itemQuantity: " + itemQuantity +
                        ",\n itemSellingPrice: " + itemSellingPrice + "\n"
                ;
    }
}
