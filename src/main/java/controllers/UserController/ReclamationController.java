package controllers.UserController;

import models.User.Reclamation;
import service.UserService.ServiceReclamation;
import service.UserService.ServiceReclamationImpl;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ReclamationController {
    private ServiceReclamation service = new ServiceReclamationImpl();

    public void addReclamation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ajout d'une nouvelle réclamation ===");
        System.out.print("CIN de l'utilisateur (qui crée la réclamation): ");
        String cin = scanner.nextLine();
        System.out.print("Sujet: ");
        String sujet = scanner.nextLine();
        System.out.print("Message: ");
        String message = scanner.nextLine();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        System.out.print("Status (en_attente, en_cours, resolu, rejete): ");
        String status = scanner.nextLine();
        Reclamation rec = new Reclamation();
        rec.setUtilisateurCin(cin);
        rec.setSujet(sujet);
        rec.setMessage(message);
        rec.setCreatedAt(createdAt);
        rec.setStatus(status);
        if (service.createReclamation(rec)) {
            System.out.println("Réclamation ajoutée avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout de la réclamation.");
        }
    }

    public void displayAllReclamations() {
        List<Reclamation> recs = service.getAllReclamations();
        System.out.println("=== Liste des réclamations ===");
        for (Reclamation r : recs) {
            System.out.println(r);
        }
    }

    public void modifyReclamation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de la réclamation à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Reclamation rec = service.getReclamationById(id);
        if (rec == null) {
            System.out.println("Réclamation non trouvée.");
            return;
        }
        System.out.println("Modifier les champs (appuyez sur Entrée pour laisser inchangé):");
        System.out.print("Nouveau sujet (" + rec.getSujet() + "): ");
        String newSujet = scanner.nextLine();
        if (!newSujet.isEmpty()) rec.setSujet(newSujet);
        System.out.print("Nouveau message (" + rec.getMessage() + "): ");
        String newMessage = scanner.nextLine();
        if (!newMessage.isEmpty()) rec.setMessage(newMessage);
        System.out.print("Nouveau status (" + rec.getStatus() + "): ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) rec.setStatus(newStatus);
        if (service.updateReclamation(rec)) {
            System.out.println("Réclamation mise à jour avec succès !");
        } else {
            System.out.println("Erreur lors de la mise à jour de la réclamation.");
        }
    }

    public boolean deleteReclamation(int id) {
        return service.deleteReclamation(id);
    }
}
