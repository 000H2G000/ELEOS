package service.UserService;

import models.User.Reclamation;
import java.util.List;

public interface ServiceReclamation {
    Reclamation getReclamationById(int id);
    List<Reclamation> getAllReclamations();
    boolean createReclamation(Reclamation rec);
    boolean updateReclamation(Reclamation rec);
    boolean deleteReclamation(int id);
}
