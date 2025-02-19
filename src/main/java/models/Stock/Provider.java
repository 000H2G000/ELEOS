package models.Stock;

public class Provider {
    private int id;
    private String name;
    private String contact;   // Corresponds to the "contact" field in SQL
    private String address;   // Corresponds to the "address" field in SQL
    private String paymentTerms;  // Corresponds to the "payment_terms" field in SQL

    // Default constructor
    public Provider() {
    }

    // Constructor with name, contact, address, and paymentTerms
    public Provider(String name, String contact, String address, String paymentTerms) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.paymentTerms = paymentTerms;
    }

    // Constructor with id, name, contact, address, and paymentTerms
    public Provider(int id, String name, String contact, String address, String paymentTerms) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.paymentTerms = paymentTerms;
    }

    // Getter and setter methods
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", paymentTerms='" + paymentTerms + '\'' +
                '}';
    }
}
