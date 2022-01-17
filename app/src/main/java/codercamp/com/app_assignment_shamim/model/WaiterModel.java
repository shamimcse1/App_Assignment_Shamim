package codercamp.com.app_assignment_shamim.model;

public class WaiterModel {
    private String Title, TotalQuantity,CurrentDateTime;
    private int Price,TotalPrice;

    public WaiterModel() {

    }

    public WaiterModel(String title, String totalQuantity, String currentDateTime, int price, int totalPrice) {
        Title = title;
        TotalQuantity = totalQuantity;
        CurrentDateTime = currentDateTime;
        Price = price;
        TotalPrice = totalPrice;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }
}
