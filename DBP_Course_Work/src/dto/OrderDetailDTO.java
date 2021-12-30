package dto;

import Entity.OrderDetail;

public class OrderDetailDTO extends OrderDetail {
    private String itemCode;
    private int orderId;
    private int orderQty;
    private double discount;

    public OrderDetailDTO(String itemCode, int orderId, int orderQty, double discount) {
        this.itemCode = itemCode;
        this.orderId = orderId;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public OrderDetailDTO() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemCode='" + itemCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderQty=" + orderQty +
                ", discount=" + discount +
                '}';
    }
}
