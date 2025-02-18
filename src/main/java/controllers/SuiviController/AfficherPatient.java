package controllers.SuiviController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Suivi.Patient;
import service.SuiviService.PatientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherPatient {

    @FXML
    private ListView<Patient> listePatient;

    @FXML
    private AnchorPane listePatientScene;

    private PatientService ps = new PatientService();

    @FXML
    void initialize() throws SQLException {
        // Fetch patients from the service
        List<Patient> patients = ps.display();
        ObservableList<Patient> observableList = FXCollections.observableList(patients);

        // Set the list items
        listePatient.setItems(observableList);

        // Set cell factory to customize how patient data is displayed, with buttons for each row
        listePatient.setCellFactory(param -> new ListCell<Patient>() {
            @Override
            protected void updateItem(Patient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Create a VBox to hold the patient info and buttons
                    VBox vbox = new VBox();

                    // Create label to show patient info
                    Label label = new Label(item.getNom() + " " + item.getPrenom() + " (ID: " + item.getIdPatient() + ")");
                    vbox.getChildren().add(label);

                    // Create delete button for the current row
                    Button deleteButton = new Button("Delete");
                    deleteButton.setOnAction(event -> deletePatient(event, item));
                    vbox.getChildren().add(deleteButton);

                    // Create update button for the current row
                    Button updateButton = new Button("Update");
                    updateButton.setOnAction(event -> openUpdateWindow(event, item));
                    vbox.getChildren().add(updateButton);

                    // Set the content of the cell to the VBox containing the label and buttons
                    setGraphic(vbox);
                }
            }
        });
    }

    // Delete patient method, takes the patient as a parameter
    private void deletePatient(ActionEvent event, Patient patient) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Patient");
        alert.setHeaderText("Are you sure you want to delete " + patient.getNom() + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ps.delete(patient.getIdPatient());
            listePatient.getItems().remove(patient);
        }
    }

    // Open the update window to modify the patient's information
    private void openUpdateWindow(ActionEvent event, Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SuiviFXML/modifierPatient.fxml"));
            Parent root = loader.load();

            // Get the controller for the update window and pass the selected patient
            ModifierPatient controller = loader.getController();
            controller.setPatientToUpdate(patient);

            Stage stage = new Stage();
            stage.setTitle("Modifier Patient");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnHiding(e -> refreshList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshList() {
        List<Patient> patients = ps.display();
        ObservableList<Patient> observableList = FXCollections.observableList(patients);
        listePatient.setItems(observableList);
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    void ajouterPatient(ActionEvent event) {
        try {
            // Revenir à l'interface précédente (exemple : liste des patients)
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SuiviFXML/AjouterPatient.fxml"));
            listePatientScene.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
