package service;

import models.Soin;
import models.Activite;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoinService implements Iservice<Soin> {

    private Connection con;

    public SoinService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(Soin s) {
        String sql = "INSERT INTO Soin (name, description, prix) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDescription());
            ps.setDouble(3, s.getPrix());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        s.setId(generatedId);
                        insertActivities(s);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }

    private void insertActivities(Soin soin) throws SQLException {
        String sql = "INSERT INTO Activite (name, description, start_time, end_time, soin_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (Activite activity : soin.getListeActivite()) {
                ps.setString(1, activity.getName());
                ps.setString(2, activity.getDescription());
                ps.setTimestamp(3, Timestamp.valueOf(activity.getStart_time()));
                ps.setTimestamp(4, Timestamp.valueOf(activity.getEnd_time()));
                ps.setInt(5, soin.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @Override
    public void update(Soin s) {
        String sql = "UPDATE Soin SET name=?, description=?, prix=? WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDescription());
            ps.setDouble(3, s.getPrix());
            ps.setInt(4, s.getId());

            ps.executeUpdate();
            updateActivities(s);
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    private void updateActivities(Soin soin) throws SQLException {
        // Delete existing activities
        String deleteSql = "DELETE FROM Activite WHERE soin_id = ?";
        try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
            ps.setInt(1, soin.getId());
            ps.executeUpdate();
        }

        // Insert updated activities
        insertActivities(soin);
    }

    @Override
    public void delete(int id) {
        String deleteActivities = "DELETE FROM Activite WHERE soin_id = ?";
        String deleteSoin = "DELETE FROM Soin WHERE id = ?";

        try (PreparedStatement ps1 = con.prepareStatement(deleteActivities);
             PreparedStatement ps2 = con.prepareStatement(deleteSoin)) {

            ps1.setInt(1, id);
            ps1.executeUpdate();

            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

    @Override
    public List<Soin> display() {
        List<Soin> soins = new ArrayList<>();
        String sql = "SELECT * FROM Soin";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Soin s = new Soin();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                s.setPrix(rs.getDouble("prix"));
                s.setListeActivite(getActivitiesForSoin(s.getId()));
                soins.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return soins;
    }

    private List<Activite> getActivitiesForSoin(int soinId) {
        List<Activite> activities = new ArrayList<>();
        String sql = "SELECT * FROM Activite WHERE soin_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soinId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Activite a = new Activite();
                    a.setId(rs.getInt("id"));
                    a.setName(rs.getString("name"));
                    a.setDescription(rs.getString("description"));
                    a.setStart_time(rs.getTimestamp("start_time").toLocalDateTime());
                    a.setEnd_time(rs.getTimestamp("end_time").toLocalDateTime());
                    activities.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Activity fetch error: " + e.getMessage());
        }
        return activities;
    }
}