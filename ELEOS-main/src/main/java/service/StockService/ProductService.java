package service.StockService;

import interfaces.IService;
import models.Stock.Product; // Class names should be PascalCase
import utils.MyDataBase; // Follow naming conventions

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product> {

    private Connection con;

    public ProductService() {
        con = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void add(Product product) {
        String req = "INSERT INTO product (name, reference, min_quantity, max_quantity, current_quantity, expiration_date, unit_price, location, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setString(1, product.getName());
            pst.setString(2, product.getReference());
            pst.setInt(3, product.getMinQuantity());
            pst.setInt(4, product.getMaxQuantity());
            pst.setInt(5, product.getCurrentQuantity());
            pst.setDate(6, new java.sql.Date(product.getExpirationDate().getTime()));
            pst.setBigDecimal(7, product.getUnitPrice());
            pst.setString(8, product.getLocation());
            pst.setString(9, product.getCategory());
            pst.executeUpdate();
            System.out.println("Product added successfully");
        } catch (Exception e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }
    @Override
    public void delete(int id) {
        String req = "DELETE FROM product WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
}

    @Override
    public void update(Product product) {
        String req = "UPDATE product SET name = ?, reference = ?, min_quantity = ?, max_quantity = ?, current_quantity = ?, expiration_date = ?, unit_price = ?, location = ?, category = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setString(1, product.getName());
            pst.setString(2, product.getReference());
            pst.setInt(3, product.getMinQuantity());
            pst.setInt(4, product.getMaxQuantity());
            pst.setInt(5, product.getCurrentQuantity());
            pst.setDate(6, new java.sql.Date(product.getExpirationDate().getTime()));
            pst.setBigDecimal(7, product.getUnitPrice());
            pst.setString(8, product.getLocation());
            pst.setString(9, product.getCategory());
            pst.setInt(10, product.getId());
            pst.executeUpdate();
            System.out.println("Product updated successfully");
        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }



    @Override

        public List<Product> display() {
            List<Product> products = new ArrayList<>();
            String req = "SELECT * FROM product";
            try (Statement st = con.createStatement()) {
                var rs = st.executeQuery(req);
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("reference"),
                            rs.getInt("min_quantity"),
                            rs.getInt("max_quantity"),
                            rs.getInt("current_quantity"),
                            rs.getDate("expiration_date"),
                            rs.getBigDecimal("unit_price"),
                            rs.getString("location"),
                            rs.getString("category")
                    );
                    products.add(product);
                }
            } catch (Exception e) {
                System.err.println("Select error: " + e.getMessage());
            }
            return products;




}}
