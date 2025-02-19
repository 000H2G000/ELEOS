package controllers.UserController;

import models.User.Utilisateur;
//import com.oussa.models.Role;  // if needed for signup input conversion (FamilyMember can sign up)
import service.UserService.ServiceUtilisateur;
import service.UserService.ServiceUtilisateurImpl;
import utils.PasswordUtil;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class UtilisateurController {
    private ServiceUtilisateur service = new ServiceUtilisateurImpl();

    public void addUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ajout d'un nouvel utilisateur ===");
        System.out.print("CIN (8 chiffres): ");
        String cin = scanner.nextLine();
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();
        String hashedPassword = PasswordUtil.hashPassword(password);
        System.out.print("Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Addresse: ");
        String addresse = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender (Male, Female, Other): ");
        String gender = scanner.nextLine();
        // For FamilyMember signup, employment fields can be left empty.
        System.out.print("Date Embauche (yyyy-mm-dd) [laisser vide si non applicable]: ");
        String dateEmbaucheStr = scanner.nextLine();
        Date dateEmbauche = dateEmbaucheStr.isEmpty() ? null : Date.valueOf(dateEmbaucheStr);
        System.out.print("Date Fin Contrat (yyyy-mm-dd) [laisser vide si non applicable]: ");
        String dateFinStr = scanner.nextLine();
        Date dateFinContrat = dateFinStr.isEmpty() ? null : Date.valueOf(dateFinStr);
        System.out.print("Poste [laisser vide si non applicable]: ");
        String poste = scanner.nextLine();

        Utilisateur u = new Utilisateur();
        u.setCin(cin);
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setMotDePasse(hashedPassword);
        u.setTelephone(telephone);
        u.setAddresse(addresse);
        u.setAge(age);
        u.setGender(gender);

        // Employment details: Only for Worker or Infermier. For FamilyMember or other types, these may be null.
        // The service layer might decide which subclass to create based on these fields.
        if (dateEmbauche != null) {
            // In a more advanced implementation, you would have separate signup forms for Worker/Infermier.
            // For now, we simply update the common user and then, via admin functions, create the corresponding subclass record.
            // You might store these values in a separate table in a real implementation.
            // Here, we assume that if employment fields are provided, the account is a Worker or Infermier.
            // But our DAO for Utilisateur only handles the common fields.
            System.out.println("Note: Employment details will be handled separately by Admin.");
        }

        if (service.createUtilisateur(u)) {
            System.out.println("Utilisateur ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
        }
    }

    public void displayAllUtilisateurs() {
        List<Utilisateur> list = service.getAllUtilisateurs();
        System.out.println("=== Liste des utilisateurs ===");
        for (Utilisateur u : list) {
            System.out.println(u);
        }
    }

    public void updateUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CIN de l'utilisateur à modifier: ");
        String cin = scanner.nextLine();
        Utilisateur u = service.getUtilisateurById(cin);
        if (u == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }
        System.out.println("Modifier les champs (appuyez sur Entrée pour laisser inchangé):");

        System.out.print("Nouveau nom (" + u.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) u.setNom(nom);
        System.out.print("Nouveau prenom (" + u.getPrenom() + "): ");
        String prenom = scanner.nextLine();
        if (!prenom.isEmpty()) u.setPrenom(prenom);
        System.out.print("Nouvel email (" + u.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) u.setEmail(email);
        System.out.print("Nouveau mot de passe (" + u.getMotDePasse() + "): ");
        String password = scanner.nextLine();
        String hashedPassword = PasswordUtil.hashPassword(password);
        if (!password.isEmpty()) u.setMotDePasse(hashedPassword);
        System.out.print("Nouveau telephone (" + u.getTelephone() + "): ");
        String telephone = scanner.nextLine();
        if (!telephone.isEmpty()) u.setTelephone(telephone);
        System.out.print("Nouvelle addresse (" + u.getAddresse() + "): ");
        String addresse = scanner.nextLine();
        if (!addresse.isEmpty()) u.setAddresse(addresse);
        System.out.print("Nouvel age (" + u.getAge() + "): ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) u.setAge(Integer.parseInt(ageStr));
        System.out.print("Nouvelle gender (" + u.getGender() + "): ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) u.setGender(gender);

        if (service.updateUtilisateur(u)) {
            System.out.println("Utilisateur mis à jour avec succès !");
        } else {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur.");
        }
    }

    public void deleteUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CIN de l'utilisateur à supprimer: ");
        String cin = scanner.nextLine();
        if (service.deleteUtilisateur(cin)) {
            System.out.println("Utilisateur supprimé avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression de l'utilisateur.");
        }
    }
}
