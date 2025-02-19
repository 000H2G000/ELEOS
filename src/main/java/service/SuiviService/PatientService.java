package service.SuiviService;

import models.Suivi.Patient;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientService implements IService<Patient> {

    Connection con;

    public PatientService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(Patient patient) {
        String sql = "INSERT INTO `patient`(`nom`, `prenom`, `dateNaissance`, `sexe`, `dateAdmission`, `chambre`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setDate(3, new java.sql.Date(patient.getDateNaissance().getTime())); // Convert Date to SQL Date
            pstmt.setString(4, patient.getSexe());
            pstmt.setDate(5, new java.sql.Date(patient.getDateAdmission().getTime())); // Convert Date to SQL Date
            pstmt.setInt(6, patient.getChambre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
        }
    }

    @Override
    public void update(Patient patient) {
        String sql = "UPDATE `patient` SET `nom` = ?, `prenom` = ?, `dateNaissance` = ?, `sexe` = ?, `dateAdmission` = ?, `chambre` = ? WHERE `idPatient` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setDate(3, new java.sql.Date(patient.getDateNaissance().getTime())); // Convert Date to SQL Date
            pstmt.setString(4, patient.getSexe());
            pstmt.setDate(5, new java.sql.Date(patient.getDateAdmission().getTime())); // Convert Date to SQL Date
            pstmt.setInt(6, patient.getChambre());
            pstmt.setInt(7, patient.getIdPatient());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `patient` WHERE `idPatient` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }

    @Override
    public List<Patient> display() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM `patient`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient();
                p.setIdPatient(rs.getInt("idPatient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDateNaissance(rs.getDate("dateNaissance"));
                p.setSexe(rs.getString("sexe"));
                p.setDateAdmission(rs.getDate("dateAdmission"));
                p.setChambre(rs.getInt("chambre"));

                patients.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching patients: " + e.getMessage());
        }

        return patients;
    }
}
