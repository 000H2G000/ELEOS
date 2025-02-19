package controllers.Activite;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Activite.Activite;
import service.ActiviteService.ActiviteService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AjouterActivite {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descField;
    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private Button save;
    @FXML
    public void initialize() {
        start.setText(LocalDateTime.now().format(formatter));
        end.setText(LocalDateTime.now().plusHours(1).format(formatter));
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    ActiviteService as= new ActiviteService();
    @FXML
    private void save() {
        // Retrieve values from form fields
        String name = nameField.getText().trim();
        String description = descField.getText().trim();
        String startDateStr = start.getText().trim();
        String endDateStr = end.getText().trim();

        // Validate required fields
        if (name.isEmpty() || description.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            showAlert("Erreur", "Tous les champs sont obligatoires", Alert.AlertType.ERROR);
            return;
        }

        if (name.length() > 255 || description.length() > 1000) {
            showAlert("Erreur", "Le nom ne doit pas dépasser 255 caractères et la description 1000 caractères.", Alert.AlertType.ERROR);
            return;
        }


        try {
            // Parse date/time values
            LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, formatter);

            // Validate date logic
            if (endDateTime.isBefore(startDateTime)) {
                showAlert("Erreur", "La date de fin doit être après la date de début", Alert.AlertType.ERROR);
                return;
            }

            // Create and save Activite object
            Activite activite = new Activite();
            activite.setName(name);
            activite.setDescription(description);
            activite.setStart_time(startDateTime);
            activite.setEnd_time(endDateTime);

            try {
                as.add(activite);
            } catch (Exception e) {
                showAlert("Erreur", "Échec de l'ajout de l'activité: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace(); // Log the full stack trace for debugging
            }

            // Show success and clear form
            showAlert("Succes", "Activité ajoutée avec succès!", Alert.AlertType.INFORMATION);
            clearForm();

        } catch (DateTimeParseException e) {
            showAlert("Format incorrect",
                    "Format de date invalide. Utilisez JJ/MM/AAAA HH:MM\nExemple: 25/12/2024 14:30",
                    Alert.AlertType.ERROR);
        }
    }

    private void clearForm() {
        nameField.clear();
        descField.clear();
        start.clear();
        end.clear();
        nameField.requestFocus(); // Set focus back to the name field
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}