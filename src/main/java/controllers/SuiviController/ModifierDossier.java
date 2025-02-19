package controllers.SuiviController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Suivi.DossierMedical;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.SuiviService.DossierMedicalService;
import service.SuiviService.IService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierDossier implements Initializable {

    @FXML
    private AnchorPane dossierUpSc;

    @FXML
    private TextField allergiesYes, antecedents, pathologies, traitementEnCours;

    @FXML
    private Button annuler;

    @FXML
    private ChoiceBox<String> appareillage, conditionPsychique;

    @FXML
    private CheckBox cbCoherence, cbCommunication, cbDeplacementExt, cbDeplacementInt, cbElimination, cbHabillage,
            cbManger, cbOrientation, cbSeServir, cbToilette;

    @FXML
    private AnchorPane dossierAjSc;

    private DossierMedical dossierToUpdate;
    private final IService<DossierMedical> dossierMedicalService = new DossierMedicalService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate choice boxes
        appareillage.getItems().addAll(
                "Fauteuil roulant", "Lit médicalisé", "Matelas anti-escarres",
                "Déambulateur", "Orthèse", "Prothèse", "Pace-maker");

        conditionPsychique.getItems().addAll(
                "Idées délirantes", "Hallucinations", "Agitation, agressivité (cris…)",
                "Dépression", "Anxiété", "Apathie", "Désinhibition",
                "Comportements moteurs aberrants", "Troubles du sommeil");

        // Initially hide allergies input field
        allergiesYes.setVisible(false);
    }

    @FXML
    void listeDossier(ActionEvent event) {
        try {
            // Revenir à l'interface précédente (liste des patients)
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuiviFXML/AfficherDossier.fxml"));
            dossierUpSc.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        // Validate required fields
        if (pathologies.getText().isEmpty() || antecedents.getText().isEmpty() ||
                traitementEnCours.getText().isEmpty() || conditionPsychique.getValue() == null ||
                appareillage.getValue() == null) {

            System.out.println("Veuillez remplir tous les champs obligatoires !");
            return;
        }

        // Retrieve selected autonomy
        String autonomie = getSelectedAutonomie();
        if (autonomie.isEmpty()) {
            System.out.println("Veuillez sélectionner au moins une option d'autonomie !");
            return;
        }

        // Create dossier object
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setPathologies(pathologies.getText());
        dossierMedical.setAntecedents(antecedents.getText());
        dossierMedical.setAllergies(allergiesYes.isVisible() ? allergiesYes.getText() : "Aucune");
        dossierMedical.setTraitementEnCours(traitementEnCours.getText());
        dossierMedical.setAutonomie(autonomie);
        dossierMedical.setConditionPsychique(conditionPsychique.getValue());
        dossierMedical.setAppareillage(appareillage.getValue());

        // Retrieve dossier ID
        int dossierId = getDossierIdToUpdate();
        dossierMedical.setIdDossier(dossierId);

        // Update dossier in database
        dossierMedicalService.update(dossierMedical);

        System.out.println("Dossier médical mis à jour avec succès !");
    }

    @FXML
    void showAllergieYes(ActionEvent event) {
        allergiesYes.setVisible(!allergiesYes.isVisible());
    }

    private String getSelectedAutonomie() {
        List<String> autonomieList = new ArrayList<>();
        if (cbDeplacementInt.isSelected()) autonomieList.add("Déplacement intérieur");
        if (cbDeplacementExt.isSelected()) autonomieList.add("Déplacement extérieur");
        if (cbToilette.isSelected()) autonomieList.add("Toilette");
        if (cbElimination.isSelected()) autonomieList.add("Élimination");
        if (cbHabillage.isSelected()) autonomieList.add("Habillage");
        if (cbSeServir.isSelected()) autonomieList.add("Alimentation - Se servir");
        if (cbManger.isSelected()) autonomieList.add("Alimentation - Manger");
        if (cbOrientation.isSelected()) autonomieList.add("Orientation (Temps, Espace)");
        if (cbCommunication.isSelected()) autonomieList.add("Communication pour alerter");
        if (cbCoherence.isSelected()) autonomieList.add("Cohérence");

        return String.join(", ", autonomieList);
    }

    // Implement logic to fetch the ID of the dossier being modified
    private int getDossierIdToUpdate() {
        if (dossierToUpdate != null) {
            return dossierToUpdate.getIdDossier();
        }
        return -1; // Default invalid ID (you need to replace this logic with the actual selection)
    }
}
