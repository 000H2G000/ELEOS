package service.UserService;

import models.User.Utilisateur;
import java.util.List;

public interface ServiceUtilisateur {
    Utilisateur getUtilisateurById(String cin);
    List<Utilisateur> getAllUtilisateurs();
    boolean createUtilisateur(Utilisateur u);
    boolean updateUtilisateur(Utilisateur u);
    boolean deleteUtilisateur(String cin);
}
