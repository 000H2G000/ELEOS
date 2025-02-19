package controllers.Activite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.stage.Stage;
import models.Activite;
import service.ActiviteService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherActivite implements Initializable {

    @FXML
    private ListView<Activite> listView;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    private Activite selectedActivite;
    private ObservableList<Activite> activitiesObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the custom cell factory
        listView.setCellFactory(CustomListCell.forListView());

        // Fetch data from the database and add to the ListView
        ActiviteService activiteService = new ActiviteService();
        List<Activite> activities = activiteService.display();
        activitiesObservableList = FXCollections.observableArrayList(activities);
        listView.setItems(activitiesObservableList);

        // Add listener for selection changes
        SelectionModel<Activite> selectionModel = listView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedActivite = newValue;
            boolean selected = newValue != null;
            modifyButton.setDisable(!selected);
            deleteButton.setDisable(!selected);
        });
    }

    @FXML
    private void handleModify() {
        if (selectedActivite != null) {
            try {
                // Load the modifierActivite.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Activite/ModifierActivite.fxml"));
                Parent root = loader.load();

                // Get the controller and pass the selected activity to it
                ModifierActivite controller = loader.getController();
                controller.setActivite(selectedActivite);
                controller.setAfficherActiviteController(this); // Pass the current controller

                // Show the modify stage
                Stage stage = new Stage();
                stage.setTitle("Modify Activity");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                showAlert("Error", "An error occurred while loading the modify activity: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleDelete() {
        if (selectedActivite != null) {
            ActiviteService activiteService = new ActiviteService();
            activiteService.delete(selectedActivite.getId());
            activitiesObservableList.remove(selectedActivite);
        }
    }

    public void refreshList() {
        ActiviteService activiteService = new ActiviteService();
        List<Activite> activities = activiteService.display();
        activitiesObservableList.setAll(activities);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}