package service.SuiviService;

import models.Suivi.RapportSuivi;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RapportSuiviService implements IService<RapportSuivi> {

    Connection con;

    public RapportSuiviService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(RapportSuivi rapportSuivi) {
        String sql = "INSERT INTO `rapportsuivi`(`dateRapport`, `poids`, `tension`, `pulsation`, `glycemie`, `observations`, `alimentation`, `hydratation`, `douleur`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(rapportSuivi.getDateRapport().getTime())); // Convert Date to SQL Date
            pstmt.setInt(2, rapportSuivi.getPoids());
            pstmt.setInt(3, rapportSuivi.getTension());
            pstmt.setInt(4, rapportSuivi.getPulsation());
            pstmt.setInt(5, rapportSuivi.getGlycemie());
            pstmt.setString(6, rapportSuivi.getObservations());
            pstmt.setString(7, rapportSuivi.getAlimentation());
            pstmt.setString(8, rapportSuivi.getHydratation());
            pstmt.setInt(9, rapportSuivi.getDouleur());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding rapport suivi: " + e.getMessage());
        }
    }

    @Override
    public void update(RapportSuivi rapportSuivi) {
        String sql = "UPDATE `rapportsuivi` SET `dateRapport` = ?, `poids` = ?, `tension` = ?, `pulsation` = ?, `glycemie` = ?, `observations` = ?, `alimentation` = ?, `hydratation` = ?, `douleur` = ? WHERE `idRapport` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(rapportSuivi.getDateRapport().getTime())); // Convert Date to SQL Date
            pstmt.setInt(2, rapportSuivi.getPoids());
            pstmt.setInt(3, rapportSuivi.getTension());
            pstmt.setInt(4, rapportSuivi.getPulsation());
            pstmt.setInt(5, rapportSuivi.getGlycemie());
            pstmt.setString(6, rapportSuivi.getObservations());
            pstmt.setString(7, rapportSuivi.getAlimentation());
            pstmt.setString(8, rapportSuivi.getHydratation());
            pstmt.setInt(9, rapportSuivi.getDouleur());
            pstmt.setInt(10, rapportSuivi.getIdRapport());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating rapport suivi: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `rapportsuivi` WHERE `idRapport` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting rapport suivi: " + e.getMessage());
        }
    }

    @Override
    public List<RapportSuivi> display() {
        List<RapportSuivi> rapportsSuivi = new ArrayList<>();
        String sql = "SELECT * FROM `rapportsuivi`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                RapportSuivi r = new RapportSuivi();
                r.setIdRapport(rs.getInt("idRapport"));
                r.setDateRapport(rs.getDate("dateRapport"));
                r.setPoids(rs.getInt("poids"));
                r.setTension(rs.getInt("tension"));
                r.setPulsation(rs.getInt("pulsation"));
                r.setGlycemie(rs.getInt("glycemie"));
                r.setObservations(rs.getString("observations"));
                r.setAlimentation(rs.getString("alimentation"));
                r.setHydratation(rs.getString("hydratation"));
                r.setDouleur(rs.getInt("douleur"));

                rapportsSuivi.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rapports suivi: " + e.getMessage());
        }

        return rapportsSuivi;
    }
}
