package controllers.SuiviController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Suivi.Patient;
import service.SuiviService.IService;
import service.SuiviService.PatientService;
import java.time.LocalDate;
import java.sql.Date;
import java.io.IOException;
import java.time.zone.ZoneRulesProvider;
import java.util.Objects;

public class ModifierPatient {
    @FXML
    private Spinner<Integer> chambre;

    @FXML
    private AnchorPane sceneModifierPatient;

    @FXML
    private DatePicker dateAdmission;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ChoiceBox<String> sexe;

    @FXML
    private Button annuler;

    @FXML
    public void initialize() {

        sexe.setItems(FXCollections.observableArrayList("Femme", "Homme"));
        sceneModifierPatient.getStylesheets().add(getClass().getResource("/style/ajouterPatient.css").toExternalForm());
        chambre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

    }

   // private IService<Patient> ps = new PatientService();
    private Patient patientToUpdate;  // Stores the patient to be updated
   private IService<Patient> patientService = new PatientService();

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


    public void setPatientToUpdate(Patient patient) {
        this.patientToUpdate = patient;

        if (patient == null) return;

        // Set text fields
        nom.setText(patient.getNom());
        prenom.setText(patient.getPrenom());

        dateNaissance.setValue(convertToLocalDate(patient.getDateNaissance()));
        dateAdmission.setValue(convertToLocalDate(patient.getDateAdmission()));






        // Set choice box
        sexe.setValue(patient.getSexe());

        // Set spinner value
        chambre.getValueFactory().setValue(patient.getChambre());
    }




    @FXML
    void modifier(ActionEvent event) {
        try {
            if (patientToUpdate == null) {
                System.out.println("No patient selected for update!");
                return;
            }

            // Get updated values from UI
            String nomPatient = nom.getText();
            String prenomPatient = prenom.getText();
            LocalDate dateNaiss = dateNaissance.getValue();
            String sexePatient = sexe.getValue();
            LocalDate dateAdmis = dateAdmission.getValue();
            int chambrePatient = chambre.getValue();

            // Validate inputs
            if (nomPatient.isEmpty() || prenomPatient.isEmpty() || dateNaiss == null || sexePatient == null || dateAdmis == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
                return;
            }

            // Update the patient object
            patientToUpdate.setNom(nomPatient);
            patientToUpdate.setPrenom(prenomPatient);
            patientToUpdate.setDateNaissance(java.sql.Date.valueOf(dateNaissance.getValue()));
            patientToUpdate.setSexe(sexePatient);
            patientToUpdate.setDateAdmission(java.sql.Date.valueOf(dateAdmission.getValue()));
            patientToUpdate.setChambre(chambrePatient);

            // Call update function in service
            patientService.update(patientToUpdate);

            // Show confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Patient mis à jour avec succès !");
            alert.showAndWait();



        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue : " + e.getMessage());
            alert.showAndWait();
        }

    }


    @FXML
    void afficherListe(ActionEvent event) {
        try {
            // Revenir à l'interface précédente (exemple : liste des patients)
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuiviFXML/AfficherPatient.fxml"));
            sceneModifierPatient.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

