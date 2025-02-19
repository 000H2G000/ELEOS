package service.VisiteService;

import interfaces.IVisite.IVisite;
import models.Visite.Visite;
import models.Visite.VisiteStatus;
import models.Visite.VisiteType;
import utils.MyDataBase;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class VisiteService implements IVisite<Visite> {

    Connection cnx= MyDataBase.getInstance().getInstance().getConnection();

    @Override
    public void add(Visite visite) {
        String sql = "INSERT INTO visite (patient_id, worker_id, date_visite, status, type_visite) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            // Corrected indices:
            ps.setInt(1, visite.getPatientId());
            ps.setInt(2, visite.getWorkerId());
            ps.setDate(3, java.sql.Date.valueOf(visite.getDateVisite()));
            ps.setString(4, visite.getStatus().name()); // Convert enum to lowercase
            ps.setString(5, visite.getTypeVisite().name());
            ps.executeUpdate();
            System.out.println("visite added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void update(Visite visite) {
        String sql = "UPDATE visite SET patient_id = ?, worker_id = ?, date_visite = ?, status = ?, type_visite = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, visite.getPatientId());
            ps.setInt(2, visite.getWorkerId());
            ps.setDate(3, java.sql.Date.valueOf(visite.getDateVisite()));
            ps.setString(4, visite.getStatus().name());
            ps.setString(5, visite.getTypeVisite().name());
            ps.setInt(6, visite.getId());
            ps.executeUpdate();
            System.out.println("visite updated successfully");
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }






    @Override
    public void delete(int id) {
        String sql = "DELETE FROM visite WHERE id=?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }


    @Override
    public List<Visite> getAll() {
        List<Visite> visites= new ArrayList<>();
        String req = "SELECT * FROM visite";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while (rs.next())
            {
                Visite visite = new Visite();
                visite.setId(rs.getInt("id"));
                visite.setPatientId(rs.getInt("patient_id"));
                visite.setWorkerId(rs.getInt("worker_id"));

                // Convert SQL Date to LocalDate
                visite.setDateVisite(rs.getDate("date_visite").toLocalDate());

                String statusStr = rs.getString("status");
                String typeVisiteStr = rs.getString("type_visite");
                visite.setStatus(VisiteStatus.valueOf(statusStr));
                visite.setTypeVisite(VisiteType.valueOf(typeVisiteStr));

                visites.add(visite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visites;
    }


    @Override
    public Visite getOne(int id) {
        String sql = "SELECT * FROM visite WHERE id=?";
        Visite visite = null;

        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                visite = new Visite();
                visite.setId(rs.getInt("id"));
                visite.setPatientId(rs.getInt("patient_id"));
                visite.setWorkerId(rs.getInt("worker_id"));
                visite.setDateVisite(rs.getDate("date_visite").toLocalDate());

                // Convert database values to enums
                visite.setStatus(VisiteStatus.valueOf(rs.getString("status")));
                visite.setTypeVisite(VisiteType.valueOf(rs.getString("type_visite")));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching visite: " + e.getMessage());
        }

        return visite; // Returns null if no record is found
    }

}
