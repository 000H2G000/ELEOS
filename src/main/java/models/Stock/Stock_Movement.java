package models.Stock;

import java.util.Date;

public class Stock_Movement {

    private int id;
    private int productId;
    private String type; // 'entry' or 'exit'
    private int quantity;
    private Date movementDate;
    private String comment;

    // Default constructor
    public Stock_Movement() {
    }

    // Constructor with fields
    public Stock_Movement(int productId, String type, int quantity, Date movementDate, String comment) {
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.comment = comment;
    }

    // Constructor with all fields including id
    public Stock_Movement(int id, int productId, String type, int quantity, Date movementDate, String comment) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.comment = comment;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Stock_Movement{" +
                "id=" + id +
                ", productId=" + productId +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", movementDate=" + movementDate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
