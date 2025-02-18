package service.ActivityService;
import interfaces.IService;
import models.Activite.Activite; // Class names should be PascalCase
import utils.MyDataBase; // Follow naming conventions

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService implements IService<Activite> {

    private Connection con;

    public ActiviteService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(Activite acti) {
        String sql = "INSERT INTO activite (name, description, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // 1. Request generated keys
            ps.setString(1, acti.getName());
            ps.setString(2, acti.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(acti.getStart_time()));
            ps.setTimestamp(4, Timestamp.valueOf(acti.getEnd_time()));

            int affectedRows = ps.executeUpdate();

            // 2. Check if insertion was successful
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) { // 3. Retrieve generated keys
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1); // Get the auto-generated ID
                        acti.setId(generatedId);
                        // 4. Assign the ID to the Java object
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }


    @Override
    public void update(Activite acti) {
        String sql = "UPDATE activite SET name=?, description=?, start_time=?, end_time=? WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, acti.getName());
            ps.setString(2, acti.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(acti.getStart_time()));
            ps.setTimestamp(4, Timestamp.valueOf(acti.getEnd_time()));
            ps.setInt(5, acti.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM activite WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    @Override
    public List<Activite> display() {
        List<Activite> activities = new ArrayList<>();
        String sql = "SELECT * FROM activite";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Activite a = new Activite();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setDescription(rs.getString("description"));
                a.setStart_time(rs.getTimestamp("start_time").toLocalDateTime());
                a.setEnd_time(rs.getTimestamp("end_time").toLocalDateTime());
                activities.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return activities;
    }
}


