package interfaces.IUser;

import models.User.Utilisateur;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IUtilisateur implements ICRUD<Utilisateur> {

    @Override
    public Utilisateur findById(Object id) {
        String sql = "SELECT * FROM Utilisateur WHERE cin = ?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, (String) id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Utilisateur(
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("telephone"),
                        rs.getString("addresse"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM Utilisateur";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Utilisateur u = new Utilisateur(
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("telephone"),
                        rs.getString("addresse"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
                list.add(u);
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean insert(Utilisateur u) {
        String sql = "INSERT INTO Utilisateur (cin, nom, prenom, email, mot_de_passe, telephone, addresse, age, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getCin());
            stmt.setString(2, u.getNom());
            stmt.setString(3, u.getPrenom());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getMotDePasse());
            stmt.setString(6, u.getTelephone());
            stmt.setString(7, u.getAddresse());
            stmt.setInt(8, u.getAge());
            stmt.setString(9, u.getGender());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Utilisateur u) {
        String sql = "UPDATE Utilisateur SET nom=?, prenom=?, email=?, mot_de_passe=?, telephone=?, addresse=?, age=?, gender=? WHERE cin=?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getMotDePasse());
            stmt.setString(5, u.getTelephone());
            stmt.setString(6, u.getAddresse());
            stmt.setInt(7, u.getAge());
            stmt.setString(8, u.getGender());
            stmt.setString(9, u.getCin());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Object id) {
        String sql = "DELETE FROM Utilisateur WHERE cin = ?";
        try (Connection conn = MyDataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, (String) id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){ e.printStackTrace(); }
        return false;
    }
}
