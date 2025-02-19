package models.Stock;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String reference;
    private int minQuantity;
    private int maxQuantity;
    private int currentQuantity;
    private Date expirationDate;
    private BigDecimal unitPrice;
    private String location;
    private String category;

    // Constructors
    public Product() {
    }

    public Product(String name, String reference, int minQuantity, int maxQuantity, int currentQuantity, Date expirationDate, BigDecimal unitPrice, String location, String category) {
        this.name = name;
        this.reference = reference;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.expirationDate = expirationDate;
        this.unitPrice = unitPrice;
        this.location = location;
        this.category = category;
    }

    public Product(int id, String name, String reference, int minQuantity, int maxQuantity, int currentQuantity, Date expirationDate, BigDecimal unitPrice, String location, String category) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.expirationDate = expirationDate;
        this.unitPrice = unitPrice;
        this.location = location;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", minQuantity=" + minQuantity +
                ", maxQuantity=" + maxQuantity +
                ", currentQuantity=" + currentQuantity +
                ", expirationDate=" + expirationDate +
                ", unitPrice=" + unitPrice +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
