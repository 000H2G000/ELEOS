package service.UserService;

import interfaces.IUser.IReponse;
import models.User.Reponse;
import java.util.List;

public class ServiceReponseImpl implements ServiceReponse {
    private IReponse reponseDAO = new IReponse();

    @Override
    public Reponse getReponseById(int id) {
        return reponseDAO.findById(id);
    }

    @Override
    public List<Reponse> getAllReponses() {
        return reponseDAO.findAll();
    }

    @Override
    public boolean createReponse(Reponse rep) {
        return reponseDAO.insert(rep);
    }

    @Override
    public boolean updateReponse(Reponse rep) {
        return reponseDAO.update(rep);
    }

    @Override
    public boolean deleteReponse(int id) {
        return reponseDAO.delete(id);
    }
}
