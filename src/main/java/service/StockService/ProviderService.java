package service.StockService;

import interfaces.IService;
import models.Stock.Provider; // Class names should be PascalCase
import utils.MyDataBase; // Follow naming conventions

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProviderService implements IService<Provider> {

    Connection con = MyDataBase.getInstance().getConnection();

    @Override
    public void add(Provider provider) {
        String req = "INSERT INTO provider (name, contact, address, payment_terms) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setString(1, provider.getName());
            pst.setString(2, provider.getContact());
            pst.setString(3, provider.getAddress());
            pst.setString(4, provider.getPaymentTerms());
            pst.executeUpdate();
            System.out.println("Provider added successfully");
        } catch (Exception e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM provider WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    @Override
    public void update(Provider provider) {
        String req = "UPDATE provider SET name = ?, contact = ?, address = ?, payment_terms = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setString(1, provider.getName());
            pst.setString(2, provider.getContact());
            pst.setString(3, provider.getAddress());
            pst.setString(4, provider.getPaymentTerms());
            pst.setInt(5, provider.getId());
            pst.executeUpdate();
            System.out.println("Provider updated successfully");
        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public List<Provider> display() {
        List<Provider> providers = new ArrayList<>();
        String req = "SELECT * FROM provider";

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Provider provider = new Provider(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("payment_terms")
                );
                providers.add(provider);
            }
        } catch (Exception e) {
            System.err.println("Select error: " + e.getMessage());
        }
        return providers;
    }
}
