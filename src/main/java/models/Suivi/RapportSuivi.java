package models.Suivi;

import java.util.Date;
import java.util.Objects;

public class RapportSuivi {
    private int idRapport;
    private Date dateRapport;
    private int poids;
    private int tension;
    private int pulsation;
    private int glycemie;
    private String observations;
    private String alimentation;
    private String hydratation;
    private int douleur;

    // Constructeurs
    public RapportSuivi() {}

    public RapportSuivi(int idRapport, Date dateRapport, int poids, int tension, int pulsation, int glycemie,
                        String observations, String alimentation, String hydratation, int douleur) {
        this.idRapport = idRapport;
        this.dateRapport = dateRapport;
        this.poids = poids;
        this.tension = tension;
        this.pulsation = pulsation;
        this.glycemie = glycemie;
        this.observations = observations;
        this.alimentation = alimentation;
        this.hydratation = hydratation;
        this.douleur = douleur;
    }

    public RapportSuivi(Date dateRapport, int poids, int tension, int pulsation, int glycemie,
                        String observations, String alimentation, String hydratation, int douleur) {
        this.dateRapport = dateRapport;
        this.poids = poids;
        this.tension = tension;
        this.pulsation = pulsation;
        this.glycemie = glycemie;
        this.observations = observations;
        this.alimentation = alimentation;
        this.hydratation = hydratation;
        this.douleur = douleur;
    }

    // Getters et Setters
    public int getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(int idRapport) {
        this.idRapport = idRapport;
    }

    public Date getDateRapport() {
        return dateRapport;
    }

    public void setDateRapport(Date dateRapport) {
        this.dateRapport = dateRapport;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTension() {
        return tension;
    }

    public void setTension(int tension) {
        this.tension = tension;
    }

    public int getPulsation() {
        return pulsation;
    }

    public void setPulsation(int pulsation) {
        this.pulsation = pulsation;
    }

    public int getGlycemie() {
        return glycemie;
    }

    public void setGlycemie(int glycemie) {
        this.glycemie = glycemie;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(String alimentation) {
        this.alimentation = alimentation;
    }

    public String getHydratation() {
        return hydratation;
    }

    public void setHydratation(String hydratation) {
        this.hydratation = hydratation;
    }

    public int getDouleur() {
        return douleur;
    }

    public void setDouleur(int douleur) {
        this.douleur = douleur;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "RapportSuivi{" +
                "idRapport=" + idRapport +
                ", dateRapport=" + dateRapport +
                ", poids=" + poids +
                ", tension=" + tension +
                ", pulsation=" + pulsation +
                ", glycemie=" + glycemie +
                ", observations='" + observations + '\'' +
                ", alimentation='" + alimentation + '\'' +
                ", hydratation='" + hydratation + '\'' +
                ", douleur='" + douleur + '\'' +
                '}';
    }

    // Méthode equals() et hashCode() pour comparer les objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportSuivi that = (RapportSuivi) o;
        return idRapport == that.idRapport &&
                Double.compare(that.poids, poids) == 0 &&
                Double.compare(that.tension, tension) == 0 &&
                pulsation == that.pulsation &&
                Double.compare(that.glycemie, glycemie) == 0 &&
                Objects.equals(dateRapport, that.dateRapport) &&
                Objects.equals(observations, that.observations) &&
                Objects.equals(alimentation, that.alimentation) &&
                Objects.equals(hydratation, that.hydratation) &&
                Objects.equals(douleur, that.douleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRapport, dateRapport, poids, tension, pulsation, glycemie, observations, alimentation, hydratation, douleur);
    }
}
