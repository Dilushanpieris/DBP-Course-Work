package dto;

public class CartTm {
    private String ItmCode;
    private String Description;
    private int qty;
    private double UnitPrice;
    private double total;

    public CartTm(String itmCode, String description, int qty, double unitPrice, double total) {
        ItmCode = itmCode;
        Description = description;
        this.qty = qty;
        UnitPrice = unitPrice;
        this.total = total;
    }

    public CartTm() {
    }

    public String getItmCode() {
        return ItmCode;
    }

    public void setItmCode(String itmCode) {
        ItmCode = itmCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
