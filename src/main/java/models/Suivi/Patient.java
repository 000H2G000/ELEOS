package models.Suivi;

import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;

public class Patient {
    private int idPatient;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String sexe;
    private Date dateAdmission;
    private int chambre;

    // Constructeurs
    public Patient() {}

    public Patient(int idPatient, String nom, String prenom, Date dateNaissance, String sexe, Date dateAdmission, int chambre) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.dateAdmission = dateAdmission;
        this.chambre = chambre;
    }

    public Patient(String nom, String prenom, Date dateNaissance, String sexe, Date dateAdmission, int chambre) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.dateAdmission = dateAdmission;
        this.chambre = chambre;
    }

    public Patient(String nom, String prenom, LocalDate dateNaissance, String sexe, LocalDate dateAdmission, int chambre) {
    }

    // Getters et Setters
    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public java.sql.Date getDateNaissance() {
        return (java.sql.Date) dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public java.sql.Date getDateAdmission() {
        return (java.sql.Date) dateAdmission;
    }

    public void setDateAdmission(Date dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public int getChambre() {
        return chambre;
    }

    public void setChambre(int chambre) {
        this.chambre = chambre;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Patient{" +
                "idPatient=" + idPatient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", sexe='" + sexe + '\'' +
                ", dateAdmission=" + dateAdmission +
                ", chambre='" + chambre + '\'' +
                '}';
    }

    // Méthode equals() et hashCode() pour comparer les objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return idPatient == patient.idPatient &&
                Objects.equals(nom, patient.nom) &&
                Objects.equals(prenom, patient.prenom) &&
                Objects.equals(dateNaissance, patient.dateNaissance) &&
                Objects.equals(sexe, patient.sexe) &&
                Objects.equals(dateAdmission, patient.dateAdmission) &&
                Objects.equals(chambre, patient.chambre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPatient, nom, prenom, dateNaissance, sexe, dateAdmission, chambre);
    }
}
