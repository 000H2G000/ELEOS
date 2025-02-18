package controllers.SuiviController;

import com.sun.javafx.geom.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import models.Suivi.Patient;
import service.SuiviService.IService;
import service.SuiviService.PatientService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import javafx.collections.ObservableArray;
import javafx.collections.FXCollections;


import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AjouterPatient {

    @FXML
    private Spinner<Integer> chambre; // Préciser le type Integer

    @FXML
    private DatePicker dateAdmission;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private ImageView imagePatient;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private AnchorPane sceneAjouterPatient;

    @FXML
    private ChoiceBox<String> sexe; // Préciser le type String

    @FXML
    private Button annuler;



    @FXML
    public void initialize() {

        sexe.setItems(FXCollections.observableArrayList("Femme", "Homme"));
        sceneAjouterPatient.getStylesheets().add(getClass().getResource("/style/ajouterPatient.css").toExternalForm());
        chambre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

    }

    private IService<Patient> ps = new PatientService();

    @FXML
    void save(ActionEvent event) {
        try {
            // Récupération des valeurs
            String nomPatient = nom.getText();
            String prenomPatient = prenom.getText();
            LocalDate dateNaiss = dateNaissance.getValue();
            String sexePatient = sexe.getValue();
            LocalDate dateAdmis = dateAdmission.getValue();
            Integer chambrePatient = chambre.getValue();

            // controle saisie
            if (nomPatient.isEmpty() || prenomPatient.isEmpty() || dateNaiss == null || sexePatient == null || dateAdmis == null || chambrePatient == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
                return;
            }

            //  Convertir LocalDate en java.sql.Date sinn la valeur sera nulle en retour
            java.sql.Date sqlDateNaiss = java.sql.Date.valueOf(dateNaiss);
            java.sql.Date sqlDateAdmis = java.sql.Date.valueOf(dateAdmis);

            // instancier la classe patient
            Patient newPatient = new Patient(nomPatient, prenomPatient, sqlDateNaiss, sexePatient, sqlDateAdmis, chambrePatient);

            // Add patient
            ps.add(newPatient);

            //Confirmation message
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Patient ajouté avec succès !");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace(); // Print error in console
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue : " + e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void afficherListe(ActionEvent event) {
        try {
            // Revenir à l'interface précédente (liste des patients)
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuiviFXML/AfficherPatient.fxml"));
            sceneAjouterPatient.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
