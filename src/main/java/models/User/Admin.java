package models.User;

public class Admin extends models.User.Utilisateur {
    public Admin() {
        super();
    }
    public Admin(String cin, String nom, String prenom, String email, String motDePasse,
                 String telephone, String addresse, int age, String gender) {
        super(cin, nom, prenom, email, motDePasse, telephone, addresse, age, gender);
    }
}
