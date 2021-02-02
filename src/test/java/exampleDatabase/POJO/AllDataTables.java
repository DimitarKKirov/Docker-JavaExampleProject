package exampleDatabase.POJO;

import lombok.Data;


/**
 * This class represents the all data Tables in Oracle database
 */
@Data
public class AllDataTables {


    private int itemSerialNumber;
    private String itemName;
    private int itemQuantity;
    private int itemID;
    private double itemPaidPricePerPiece;
    private double itemSellingPrice;
    private int detailsID;

    public int getItemSerialNumber() {
        return itemSerialNumber;
    }

    public void setItemSerialNumber(int itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getItemPaidPricePerPiece() {
        return itemPaidPricePerPiece;
    }

    public void setItemPaidPricePerPiece(double itemPaidPricePerPiece) {
        this.itemPaidPricePerPiece = itemPaidPricePerPiece;
    }

    public double getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(double itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }

    public int getDetailsID() {
        return detailsID;
    }

    public void setDetailsID(int detailsID) {
        this.detailsID = detailsID;
    }

    public AllDataTables(int itemSerialNumber, String itemName, int itemQuantity, int itemID, double itemPaidPricePerPiece, double itemSellingPrice, int detailsID) {
        this.itemSerialNumber = itemSerialNumber;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemID = itemID;
        this.itemPaidPricePerPiece = itemPaidPricePerPiece;
        this.itemSellingPrice = itemSellingPrice;
        this.detailsID = detailsID;
    }
}