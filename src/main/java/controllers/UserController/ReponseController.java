package controllers.UserController;

import models.User.Reponse;
import service.UserService.ServiceReponse;
import service.UserService.ServiceReponseImpl;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ReponseController {
    private ServiceReponse service = new ServiceReponseImpl();

    public void addReponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ajout d'une nouvelle réponse ===");
        System.out.print("ID de la réclamation: ");
        int recId = Integer.parseInt(scanner.nextLine());
        System.out.print("CIN de l'utilisateur (celui qui répond): ");
        String cin = scanner.nextLine();
        System.out.print("Message: ");
        String message = scanner.nextLine();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Reponse rep = new Reponse();
        rep.setReclamationId(recId);
        rep.setUtilisateurCin(cin);
        rep.setMessage(message);
        rep.setCreatedAt(createdAt);
        if (service.createReponse(rep)) {
            System.out.println("Réponse ajoutée avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout de la réponse.");
        }
    }

    public void displayAllReponses() {
        List<Reponse> reps = service.getAllReponses();
        System.out.println("=== Liste des réponses ===");
        for (Reponse r : reps) {
            System.out.println(r);
        }
    }

    public void modifyReponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de la réponse à modifier: ");
        int id = Integer.parseInt(scanner.nextLine());
        Reponse rep = service.getReponseById(id);
        if (rep == null) {
            System.out.println("Réponse non trouvée.");
            return;
        }
        System.out.print("Nouveau message (" + rep.getMessage() + "): ");
        String newMessage = scanner.nextLine();
        if (!newMessage.isEmpty()) {
            rep.setMessage(newMessage);
        }
        if (service.updateReponse(rep)) {
            System.out.println("Réponse mise à jour avec succès !");
        } else {
            System.out.println("Erreur lors de la mise à jour de la réponse.");
        }
    }

    public boolean deleteReponse(int id) {
        return service.deleteReponse(id);
    }
}
