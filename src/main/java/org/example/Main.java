package org.example;

<<<<<<< HEAD
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}
=======
import controllers.UserController.ReclamationController;
import controllers.UserController.ReponseController;
import controllers.UserController.UtilisateurController;
import models.Stock.Product;
import models.Stock.Provider;
import service.StockService.ProductService;
import service.StockService.ProviderService;
import utils.MyDataBase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class    Main {
    public static void main(String[] args) {
        try {
            if (MyDataBase.getInstance().getConnection() != null) {
                System.out.println("Connexion établie avec succès !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        UtilisateurController userController = new UtilisateurController();
        ReclamationController recController = new ReclamationController();
        ReponseController repController = new ReponseController();

        boolean run = true;
        while (run) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Ajouter utilisateur");
            System.out.println("2. Afficher utilisateurs");
            System.out.println("3. Modifier utilisateur");
            System.out.println("4. Supprimer utilisateur");
            System.out.println("5. Ajouter réclamation");
            System.out.println("6. Afficher réclamations");
            System.out.println("7. Modifier réclamation");
            System.out.println("8. Supprimer réclamation");
            System.out.println("9. Ajouter réponse");
            System.out.println("10. Afficher réponses");
            System.out.println("11. Modifier réponse");
            System.out.println("12. Supprimer réponse");
            System.out.println("13. Quitter");
            System.out.print("Choix: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    userController.addUtilisateur();
                    break;
                case 2:
                    userController.displayAllUtilisateurs();
                    break;
                case 3:
                    userController.updateUtilisateur();
                    break;
                case 4:
                    userController.deleteUtilisateur();
                    break;
                case 5:
                    recController.addReclamation();
                    break;
                case 6:
                    recController.displayAllReclamations();
                    break;
                case 7:
                    recController.modifyReclamation();
                    break;
                case 8:
                    System.out.print("Entrez l'ID de la réclamation à supprimer: ");
                    int idRec = Integer.parseInt(scanner.nextLine());
                    if (recController.deleteReclamation(idRec)) {
                        System.out.println("Réclamation supprimée avec succès !");
                    } else {
                        System.out.println("Erreur lors de la suppression de la réclamation.");
                    }
                    break;
                case 9:
                    repController.addReponse();
                    break;
                case 10:
                    repController.displayAllReponses();
                    break;
                case 11:
                    repController.modifyReponse();
                    break;
                case 12:
                    System.out.print("Entrez l'ID de la réponse à supprimer: ");
                    int idRep = Integer.parseInt(scanner.nextLine());
                    if (repController.deleteReponse(idRep)) {
                        System.out.println("Réponse supprimée avec succès !");
                    } else {
                        System.out.println("Erreur lors de la suppression de la réponse.");
                    }
                    break;
                case 13:
                    run = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }

    }
}
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da
