package models.Suivi;

import java.util.Objects;

public class DossierMedical {
    private int idDossier;
    private String pathologies;
    private String antecedents;
    private String allergies;
    private String traitementEnCours;
    private String autonomie;
    private String conditionPsychique;
    private String appareillage;

    // Constructeurs
    public DossierMedical() {}

    public DossierMedical(int idDossier, String pathologies, String antecedents, String allergies,
                          String traitementEnCours, String autonomie, String conditionPsychique, String appareillage) {
        this.idDossier = idDossier;
        this.pathologies = pathologies;
        this.antecedents = antecedents;
        this.allergies = allergies;
        this.traitementEnCours = traitementEnCours;
        this.autonomie = autonomie;
        this.conditionPsychique = conditionPsychique;
        this.appareillage = appareillage;
    }

    public DossierMedical(String pathologies, String antecedents, String allergies,
                          String traitementEnCours, String autonomie, String conditionPsychique, String appareillage) {
        this.pathologies = pathologies;
        this.antecedents = antecedents;
        this.allergies = allergies;
        this.traitementEnCours = traitementEnCours;
        this.autonomie = autonomie;
        this.conditionPsychique = conditionPsychique;
        this.appareillage = appareillage;
    }

    // Getters et Setters
    public int getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(int idDossier) {
        this.idDossier = idDossier;
    }

    public String getPathologies() {
        return pathologies;
    }

    public void setPathologies(String pathologies) {
        this.pathologies = pathologies;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTraitementEnCours() {
        return traitementEnCours;
    }

    public void setTraitementEnCours(String traitementEnCours) {
        this.traitementEnCours = traitementEnCours;
    }

    public String getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(String autonomie) {
        this.autonomie = autonomie;
    }

    public String getConditionPsychique() {
        return conditionPsychique;
    }

    public void setConditionPsychique(String conditionPsychique) {
        this.conditionPsychique = conditionPsychique;
    }

    public String getAppareillage() {
        return appareillage;
    }

    public void setAppareillage(String appareillage) {
        this.appareillage = appareillage;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "DossierMedical{" +
                "idDossier=" + idDossier +
                ", pathologies='" + pathologies + '\'' +
                ", antecedents='" + antecedents + '\'' +
                ", allergies='" + allergies + '\'' +
                ", traitementEnCours='" + traitementEnCours + '\'' +
                ", autonomie='" + autonomie + '\'' +
                ", conditionPsychique='" + conditionPsychique + '\'' +
                ", appareillage='" + appareillage + '\'' +
                '}';
    }

    // Méthode equals() et hashCode() pour comparer les objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierMedical that = (DossierMedical) o;
        return idDossier == that.idDossier &&
                Objects.equals(pathologies, that.pathologies) &&
                Objects.equals(antecedents, that.antecedents) &&
                Objects.equals(allergies, that.allergies) &&
                Objects.equals(traitementEnCours, that.traitementEnCours) &&
                Objects.equals(autonomie, that.autonomie) &&
                Objects.equals(conditionPsychique, that.conditionPsychique) &&
                Objects.equals(appareillage, that.appareillage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDossier, pathologies, antecedents, allergies, traitementEnCours, autonomie, conditionPsychique, appareillage);
    }
}
