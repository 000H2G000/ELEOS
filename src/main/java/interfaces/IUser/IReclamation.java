package interfaces.IUser;

import models.User.Reclamation;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IReclamation implements ICRUD<Reclamation> {

    @Override
    public Reclamation findById(Object id) {
        String sql = "SELECT * FROM Reclamation WHERE id = ?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer) id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Reclamation(
                        rs.getInt("id"),
                        rs.getString("utilisateur_cin"),
                        rs.getString("sujet"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at"),
                        rs.getString("status"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Reclamation> findAll() {
        List<Reclamation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reclamation";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Reclamation rec = new Reclamation(
                        rs.getInt("id"),
                        rs.getString("utilisateur_cin"),
                        rs.getString("sujet"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at"),
                        rs.getString("status"),
                        rs.getTimestamp("updated_at")
                );
                list.add(rec);
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean insert(Reclamation rec) {
        String sql = "INSERT INTO Reclamation (utilisateur_cin, sujet, message, created_at, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rec.getUtilisateurCin());
            stmt.setString(2, rec.getSujet());
            stmt.setString(3, rec.getMessage());
            stmt.setTimestamp(4, rec.getCreatedAt());
            stmt.setString(5, rec.getStatus());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Reclamation rec) {
        String sql = "UPDATE Reclamation SET utilisateur_cin=?, sujet=?, message=?, created_at=?, status=? WHERE id=?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rec.getUtilisateurCin());
            stmt.setString(2, rec.getSujet());
            stmt.setString(3, rec.getMessage());
            stmt.setTimestamp(4, rec.getCreatedAt());
            stmt.setString(5, rec.getStatus());
            stmt.setInt(6, rec.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM Reclamation WHERE id = ?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer) id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }
}
