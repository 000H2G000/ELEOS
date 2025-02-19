package service;

import javafx.scene.control.Alert;
import models.Activite;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService implements Iservice<Activite> {

    private Connection con;

    public ActiviteService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(Activite acti) {
        if (isTimeConflict(acti)) {
            showAlert("Time Conflict", "The specified time conflicts with another activity.");
            return;
        }

        String sql = "INSERT INTO activite (name, description, start_time, end_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, acti.getName());
            ps.setString(2, acti.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(acti.getStart_time()));
            ps.setTimestamp(4, Timestamp.valueOf(acti.getEnd_time()));

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        acti.setId(generatedId);
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            }
        } catch (SQLException e) {
            showAlert("Insert Error", "An error occurred while inserting the activity: " + e.getMessage());
        }
    }

    @Override
    public void update(Activite acti) {
        if (isTimeConflict(acti)) {
            showAlert("Time Conflict", "The specified time conflicts with another activity.");
            return;
        }

        String sql = "UPDATE activite SET name=?, description=?, start_time=?, end_time=? WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, acti.getName());
            ps.setString(2, acti.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(acti.getStart_time()));
            ps.setTimestamp(4, Timestamp.valueOf(acti.getEnd_time()));
            ps.setInt(5, acti.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            showAlert("Update Error", "An error occurred while updating the activity: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM activite WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            showAlert("Delete Error", "An error occurred while deleting the activity: " + e.getMessage());
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
            showAlert("Fetch Error", "An error occurred while fetching the activities: " + e.getMessage());
        }
        return activities;
    }

    public boolean isTimeConflict(Activite acti) {
        // SQL includes condition to exclude current activity's ID if it exists
        String sql = "SELECT COUNT(*) FROM activite WHERE (start_time < ? AND end_time > ?) AND (id != ? OR ? IS NULL)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Set the start and end time parameters
            ps.setTimestamp(1, Timestamp.valueOf(acti.getEnd_time()));
            ps.setTimestamp(2, Timestamp.valueOf(acti.getStart_time()));

            // Handle the ID parameters (for update vs. add)
            if (acti.getId() != 0) {
                // Existing activity (update): exclude its own ID
                ps.setInt(3, acti.getId());
                ps.setInt(4, acti.getId());
            } else {
                // New activity (add): ignore ID check
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Conflict exists if count > 0
                }
            }
        } catch (SQLException e) {
            showAlert("Time Conflict Check Error", "Error checking time conflict: " + e.getMessage());
        }
        return false;
    }

    private void showAlert(String title, String message) {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}