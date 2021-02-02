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

    public String getDetailsID() {
        return detailsID;
    }

    public void setDetailsID(String detailsID) {
        this.detailsID = detailsID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(String itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }
    public ItemsDetails(){

    }
    public ItemsDetails(String itemQuantity, String itemSellingPrice, String itemID) {
        this.itemQuantity = itemQuantity;
        this.itemSellingPrice = itemSellingPrice;
        this.itemID = itemID;
    }
    public String toString() {
        return
                "\n detailsID: " + detailsID +
                "\n itemID: " + itemID +
                        ",\n itemQuantity: " + itemQuantity +
                        ",\n itemSellingPrice: " + itemSellingPrice +"\n"
                ;
    }
    public String itemSellingPrice() {
        return
               itemSellingPrice;

    }
}
