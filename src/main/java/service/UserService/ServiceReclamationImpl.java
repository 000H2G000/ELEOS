package service.UserService;

import interfaces.IUser.IReclamation;
import models.User.Reclamation;
import java.util.List;

public class ServiceReclamationImpl implements ServiceReclamation {
    private IReclamation reclamationDAO = new IReclamation();

    @Override
    public Reclamation getReclamationById(int id) {
        return reclamationDAO.findById(id);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationDAO.findAll();
    }

    @Override
    public boolean createReclamation(Reclamation rec) {
        return reclamationDAO.insert(rec);
    }

    @Override
    public boolean updateReclamation(Reclamation rec) {
        return reclamationDAO.update(rec);
    }

    @Override
    public boolean deleteReclamation(int id) {
        return reclamationDAO.delete(id);
    }
}
