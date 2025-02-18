package service.SuiviService;

import models.Suivi.DossierMedical;
import utils.MyDataBase;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DossierMedicalService implements IService<DossierMedical> {

    Connection con;

    public DossierMedicalService() {
        con = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void add(DossierMedical dossierMedical) {
        String sql = "INSERT INTO `dossiermedical`(`pathologies`, `antecedents`, `allergies`, `traitementEnCours`, `autonomie`, `conditionPsychique`, `appareillage`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dossierMedical.getPathologies());
            pstmt.setString(2, dossierMedical.getAntecedents());
            pstmt.setString(3, dossierMedical.getAllergies());
            pstmt.setString(4, dossierMedical.getTraitementEnCours());
            pstmt.setString(5, dossierMedical.getAutonomie());
            pstmt.setString(6, dossierMedical.getConditionPsychique());
            pstmt.setString(7, dossierMedical.getAppareillage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding dossier medical: " + e.getMessage());
        }
    }

    @Override
    public void update(DossierMedical dossierMedical) {
        String sql = "UPDATE `dossiermedical` SET `pathologies` = ?, `antecedents` = ?, `allergies` = ?, `traitementEnCours` = ?, `autonomie` = ?, `conditionPsychique` = ?, `appareillage` = ? WHERE `idDossier` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dossierMedical.getPathologies());
            pstmt.setString(2, dossierMedical.getAntecedents());
            pstmt.setString(3, dossierMedical.getAllergies());
            pstmt.setString(4, dossierMedical.getTraitementEnCours());
            pstmt.setString(5, dossierMedical.getAutonomie());
            pstmt.setString(6, dossierMedical.getConditionPsychique());
            pstmt.setString(7, dossierMedical.getAppareillage());
            pstmt.setInt(8, dossierMedical.getIdDossier());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating dossier medical: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `dossiermedical` WHERE `idDossier` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting dossier medical: " + e.getMessage());
        }
    }

    @Override
    public List<DossierMedical> display() {
        List<DossierMedical> dossierMedicals = new ArrayList<>();
        String sql = "SELECT * FROM `dossiermedical`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                DossierMedical d = new DossierMedical();
                d.setIdDossier(rs.getInt("idDossier"));
                d.setPathologies(rs.getString("pathologies"));
                d.setAntecedents(rs.getString("antecedents"));
                d.setAllergies(rs.getString("allergies"));
                d.setTraitementEnCours(rs.getString("traitementEnCours"));
                d.setAutonomie(rs.getString("autonomie"));
                d.setConditionPsychique(rs.getString("conditionPsychique"));
                d.setAppareillage(rs.getString("appareillage"));

                dossierMedicals.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching dossier medical: " + e.getMessage());
        }

        return dossierMedicals;
    }
}
