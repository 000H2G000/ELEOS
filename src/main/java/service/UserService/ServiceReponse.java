package service.UserService;

import models.User.Reponse;
import java.util.List;

public interface ServiceReponse {
    Reponse getReponseById(int id);
    List<Reponse> getAllReponses();
    boolean createReponse(Reponse rep);
    boolean updateReponse(Reponse rep);
    boolean deleteReponse(int id);
}
