package exampleDatabase.sqlQuery;

public interface SqlQuery {

    String COUNT_ITEMS = "select count(*) from Items;";
    String COUNT_DETAILS = "select count(*) from ItemsDetails;";
    String COUNT_LOADING = "select count(*) from ItemsLoadingDetails;";
    String SELECT_ALL_ITEMS = "select * from Items";
    String SELECT_ALL_Details = "select * from ItemsDetails";
    String SELECT_ALL_Loading = "select * from ItemsLoadingDetails";
    String ADD_IN_ITEMSDETAILS = "Insert ignore into ItemsDetails(ItemQuantity, ItemSellingPrice,ItemID) values " + "(?,?,?);";
    String ADD_IN_ITEMSLOADINGDETAILS = "Insert ignore into ItemsLoadingDetails(ItemPaidPricePerPiece,DetailsID) values " + "(?,?);";
    String ADD_IN_ITEMS = "Insert ignore into Items(ItemSerialNumber,ItemName) values (?,?)";


}
