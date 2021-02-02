package exampleDatabase.POJO;

import lombok.Data;

/**
 * class representing the database ItemsLoadingDetails Table
 * for filling and retrieving the data in the table
 */

@Data
public class ItemsLoadingDetails {
    private String loadingDetailId;
    private String storeLoadingDate;
    private String itemPaidPricePerPiece;
    private String detailsID;

    public void setLoadingDetailId(String loadingDetailId) {
        this.loadingDetailId = loadingDetailId;
    }

    public void setStoreLoadingDate(String storeLoadingDate) {
        this.storeLoadingDate = storeLoadingDate;
    }

    public void setItemPaidPricePerPiece(String itemPaidPricePerPiece) {
        this.itemPaidPricePerPiece = itemPaidPricePerPiece;
    }

    public void setDetailsID(String detailsID) {
        this.detailsID = detailsID;
    }

    public ItemsLoadingDetails() {

    }


    public String toString() {
        return

                "\n loadingDetailId: " + loadingDetailId +
                        "\n detailsID: " + detailsID +
                        "\n itemPaidPricePerPiece: " + itemPaidPricePerPiece +
                        ",\n storeLoadingDate: " + storeLoadingDate
                ;
    }

}
