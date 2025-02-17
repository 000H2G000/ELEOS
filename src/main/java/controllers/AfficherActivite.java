package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.stage.Stage;
import models.Activite;
import service.ActiviteService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherActivite {

    @FXML
    private ListView<Activite> listView;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    private Activite selectedActivite;

    //@Override
    /*public void initialize(URL location, ResourceBundle resources) {
        // Set the custom cell factory
        listView.setCellFactory(CustomListCell.forListView());

        // Fetch data from the database and add to the ListView
        ActiviteService activiteService = new ActiviteService();
        List<Activite> activities = activiteService.display();
        listView.getItems().addAll(activities);

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierActivite.fxml"));
                Parent root = loader.load();



                // Show the modify stage
                Stage stage = new Stage();
                stage.setTitle("Modify Activity");
                stage.setScene(new Scene(root));
                stage.show();

                // Close the current stage
                Stage currentStage = (Stage) listView.getScene().getWindow();
                currentStage.close();

                // Get the controller and pass the selected activity to it
                ModifierActivite controller = loader.getController();
                controller.setActivite(selectedActivite);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDelete() {
        if (selectedActivite != null) {
            ActiviteService activiteService = new ActiviteService();
            activiteService.delete(selectedActivite.getId());
            listView.getItems().remove(selectedActivite);
        }
    }*/
}