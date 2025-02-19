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
import models.Suivi.DossierMedical;
import service.SuiviService.DossierMedicalService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherDossier {

    @FXML
    private ListView<DossierMedical> listeDossier;

    @FXML
    private AnchorPane listeDossierScene;

    private DossierMedicalService ds = new DossierMedicalService();

    @FXML
    void initialize() throws SQLException {
        // Fetch dossiers from the service
        List<DossierMedical> dossiers = ds.display();
        ObservableList<DossierMedical> observableList = FXCollections.observableList(dossiers);

        // Set the list items
        listeDossier.setItems(observableList);

        // Set cell factory to customize how dossier data is displayed, with buttons for each row
        listeDossier.setCellFactory(param -> new ListCell<DossierMedical>() {
            @Override
            protected void updateItem(DossierMedical item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox();

                    // Display all fields of DossierMedical
                    Label labelId = new Label("ID: " + item.getIdDossier());
                    Label labelPathologies = new Label("Pathologies: " + item.getPathologies());
                    Label labelAntecedents = new Label("Antecedents: " + item.getAntecedents());
                    Label labelAllergies = new Label("Allergies: " + item.getAllergies());
                    Label labelTraitement = new Label("Traitement En Cours: " + item.getTraitementEnCours());
                    Label labelAutonomie = new Label("Autonomie: " + item.getAutonomie());
                    Label labelPsychique = new Label("Condition Psychique: " + item.getConditionPsychique());
                    Label labelAppareillage = new Label("Appareillage: " + item.getAppareillage());

                    vbox.getChildren().addAll(
                            labelId, labelPathologies, labelAntecedents, labelAllergies,
                            labelTraitement, labelAutonomie, labelPsychique, labelAppareillage
                    );

                    // Add buttons for actions
                    Button deleteButton = new Button("Delete");
                    deleteButton.setOnAction(event -> deleteDossier(event, item));
                    Button updateButton = new Button("Update");
                    updateButton.setOnAction(event -> openUpdateWindow(event, item));

                    // Add buttons to VBox
                    vbox.getChildren().addAll(deleteButton, updateButton);

                    setGraphic(vbox);
                }
            }
        });
    }

    private void deleteDossier(ActionEvent event, DossierMedical dossier) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Dossier Medical");
        alert.setHeaderText("Are you sure you want to delete dossier ID: " + dossier.getIdDossier() + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ds.delete(dossier.getIdDossier());
            listeDossier.getItems().remove(dossier);
        }
    }

    private void openUpdateWindow(ActionEvent event, DossierMedical dossier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SuiviFXML/modifierDossier.fxml"));
            Parent root = loader.load();
            ModifierDossier controller = loader.getController();
            controller.modifier(new ActionEvent());

            Stage stage = new Stage();
            stage.setTitle("Modifier Dossier Medical");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnHiding(e -> refreshList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshList() {
        List<DossierMedical> dossiers = ds.display();
        ObservableList<DossierMedical> observableList = FXCollections.observableList(dossiers);
        listeDossier.setItems(observableList);
    }
}
