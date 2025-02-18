package controllers.SuiviController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import models.Suivi.DossierMedical;
import service.SuiviService.DossierMedicalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterDossier implements Initializable {

    @FXML
    private AnchorPane dossierAjSc;

    @FXML
    private TextField allergiesYes;

    @FXML
    private TextField antecedents;

    @FXML
    private ChoiceBox<String> appareillage;

    @FXML
    private CheckBox cbCoherence, cbCommunication, cbDeplacementExt, cbDeplacementInt, cbElimination;

    @FXML
    private CheckBox cbHabillage, cbManger, cbOrientation, cbSeServir, cbToilette;

    @FXML
    private ChoiceBox<String> conditionPsychique;

    @FXML
    private TextField pathologies;

    @FXML
    private TextField traitementEnCours;

    private final DossierMedicalService dossierMedicalService = new DossierMedicalService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate appareillage choices
        appareillage.getItems().addAll(
                "Fauteuil roulant", "Lit médicalisé", "Matelas anti-escarres",
                "Déambulateur", "Orthèse", "Prothèse", "Pace-maker");

        // Populate conditionPsychique choices
        conditionPsychique.getItems().addAll(
                "Idées délirantes", "Hallucinations", "Agitation, agressivité (cris…)",
                "Dépression", "Anxiété", "Apathie", "Désinhibition",
                "Comportements moteurs aberrants", "Troubles du sommeil");

        // Initially hide allergiesYes field
        allergiesYes.setVisible(false);
    }

    @FXML
    void ajouterDossier(ActionEvent event) {
        String selectedAutonomie = getSelectedAutonomie();
        String selectedAppareillage = appareillage.getValue();
        DossierMedical dossier = getDossierMedical(selectedAutonomie, selectedAppareillage);

        // Save to database
        dossierMedicalService.add(dossier);

        //Confirmation message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Dossier Medical ajouté avec succès !");
        alert.showAndWait();
    }

    private DossierMedical getDossierMedical(String selectedAutonomie, String selectedAppareillage) {
        String selectedConditionPsychique = conditionPsychique.getValue();

        // Create dossier object
        DossierMedical dossier = new DossierMedical(
                pathologies.getText(),
                antecedents.getText(),
                allergiesYes.isVisible() ? allergiesYes.getText() : "Aucune",
                traitementEnCours.getText(),
                selectedAutonomie,
                selectedConditionPsychique != null ? selectedConditionPsychique : "",
                selectedAppareillage != null ? selectedAppareillage : ""
        );
        return dossier;
    }

    @FXML
    void showAllergieYes(ActionEvent event) {
        allergiesYes.setVisible(!allergiesYes.isVisible());
    }

    @FXML
    void listeDossier(ActionEvent event) {
        try {
            // Revenir à l'interface précédente (liste des patients)
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuiviFXML/AfficherDossier.fxml"));
            dossierAjSc.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getSelectedAutonomie() {
        List<String> autonomieList = new ArrayList<>();
        if (cbDeplacementInt.isSelected()) autonomieList.add("Déplacements - A l’intérieur");
        if (cbDeplacementExt.isSelected()) autonomieList.add("Déplacements - A l’extérieur");
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
}
