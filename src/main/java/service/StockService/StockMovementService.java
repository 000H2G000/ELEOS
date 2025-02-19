package service.StockService;

import interfaces.IService;
import models.Stock.Stock_Movement; // Class names should be PascalCase
import utils.MyDataBase; // Follow naming conventions

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockMovementService implements IService<Stock_Movement> {

    Connection con = MyDataBase.getInstance().getConnection();

    @Override
    public void add(Stock_Movement stockMovement) {
        String req = "INSERT INTO stock_movement (product_id, type, quantity, movement_date, comment) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setInt(1, stockMovement.getProductId());
            pst.setString(2, stockMovement.getType());
            pst.setInt(3, stockMovement.getQuantity());
            pst.setTimestamp(4, new Timestamp(stockMovement.getMovementDate().getTime()));
            pst.setString(5, stockMovement.getComment());
            pst.executeUpdate();
            System.out.println("Stock movement added successfully");
        } catch (Exception e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM stock_movement WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    @Override
    public void update(Stock_Movement stockMovement) {
        String req = "UPDATE stock_movement SET product_id = ?, type = ?, quantity = ?, movement_date = ?, comment = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(req)) {
            pst.setInt(1, stockMovement.getProductId());
            pst.setString(2, stockMovement.getType());
            pst.setInt(3, stockMovement.getQuantity());
            pst.setTimestamp(4, new Timestamp(stockMovement.getMovementDate().getTime()));
            pst.setString(5, stockMovement.getComment());
            pst.setInt(6, stockMovement.getId());
            pst.executeUpdate();
            System.out.println("Stock movement updated successfully");
        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public List<Stock_Movement> display() {
        List<Stock_Movement> movements = new ArrayList<>();
        String req = "SELECT * FROM stock_movement";

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Stock_Movement movement = new Stock_Movement(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("movement_date"),
                        rs.getString("comment")
                );
                movements.add(movement);
            }
        } catch (Exception e) {
            System.err.println("Select error: " + e.getMessage());
        }
        return movements;
    }
}
