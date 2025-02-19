package interfaces.IUser;

import models.User.Reponse;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IReponse implements ICRUD<Reponse> {

    @Override
    public Reponse findById(Object id) {
        String sql = "SELECT * FROM Reponse WHERE id = ?";
        try (Connection conn = MyDataBase.getInstance().getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer) id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Reponse(
                        rs.getInt("id"),
                        rs.getInt("reclamation_id"),
                        rs.getString("utilisateur_cin"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Reponse> findAll() {
        List<Reponse> list = new ArrayList<>();
        String sql = "SELECT * FROM Reponse";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Reponse rep = new Reponse(
                        rs.getInt("id"),
                        rs.getInt("reclamation_id"),
                        rs.getString("utilisateur_cin"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at")
                );
                list.add(rep);
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean insert(Reponse rep) {
        String sql = "INSERT INTO Reponse (reclamation_id, utilisateur_cin, message, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rep.getReclamationId());
            stmt.setString(2, rep.getUtilisateurCin());
            stmt.setString(3, rep.getMessage());
            stmt.setTimestamp(4, rep.getCreatedAt());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Reponse rep) {
        String sql = "UPDATE Reponse SET reclamation_id=?, utilisateur_cin=?, message=?, created_at=? WHERE id=?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rep.getReclamationId());
            stmt.setString(2, rep.getUtilisateurCin());
            stmt.setString(3, rep.getMessage());
            stmt.setTimestamp(4, rep.getCreatedAt());
            stmt.setInt(5, rep.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM Reponse WHERE id = ?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer) id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }
}
