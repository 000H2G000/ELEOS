package service.UserService;

import interfaces.IUser.IUtilisateur;
import models.User.Utilisateur;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    private IUtilisateur utilisateurDAO = new IUtilisateur();

    @Override
    public Utilisateur getUtilisateurById(String cin) {
        return utilisateurDAO.findById(cin);
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurDAO.findAll();
    }

    @Override
    public boolean createUtilisateur(Utilisateur u) {
        return utilisateurDAO.insert(u);
    }

    @Override
    public boolean updateUtilisateur(Utilisateur u) {
        return utilisateurDAO.update(u);
    }

    @Override
    public boolean deleteUtilisateur(String cin) {
        return utilisateurDAO.delete(cin);
    }
}
