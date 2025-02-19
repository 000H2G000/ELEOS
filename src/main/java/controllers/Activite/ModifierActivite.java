package controllers.Activite;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Activite;
import service.ActiviteService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModifierActivite {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextField endTimeField;

    private Activite activite;
    private ActiviteService activiteService = new ActiviteService();
    private AfficherActivite afficherActiviteController;

    public void setActivite(Activite activite) {
        this.activite = activite;
        nameField.setText(activite.getName());
        descriptionField.setText(activite.getDescription());
        startTimeField.setText(activite.getStart_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        endTimeField.setText(activite.getEnd_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    public void setAfficherActiviteController(AfficherActivite afficherActiviteController) {
        this.afficherActiviteController = afficherActiviteController;
    }

    @FXML
    private void handleSave() {
        try {
            activite.setName(nameField.getText());
            activite.setDescription(descriptionField.getText());
            activite.setStart_time(LocalDateTime.parse(startTimeField.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            activite.setEnd_time(LocalDateTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

            if (activiteService.isTimeConflict(activite)) {
                showAlert("Time Conflict", "The specified time conflicts with another activity.");
                return;
            }

            activiteService.update(activite);
            afficherActiviteController.refreshList(); // Refresh the list in the main controller

            // Close the stage after saving
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while saving the activity: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        // Close the stage without saving
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}